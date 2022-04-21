/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.gradle.plugins.workspace.configurators;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.Dependency;
import org.gradle.api.artifacts.DependencySet;
import org.gradle.api.artifacts.ResolvableDependencies;
import org.gradle.api.file.CopySpec;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.file.FileCollection;
import org.gradle.api.file.FileCopyDetails;
import org.gradle.api.file.FileTreeElement;
import org.gradle.api.initialization.Settings;
import org.gradle.api.plugins.BasePlugin;
import org.gradle.api.plugins.ExtensionAware;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.WarPlugin;
import org.gradle.api.plugins.WarPluginConvention;
import org.gradle.api.provider.Property;
import org.gradle.api.specs.Spec;
import org.gradle.api.tasks.Copy;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.api.tasks.TaskOutputs;
import org.gradle.api.tasks.bundling.War;
import org.gradle.api.tasks.bundling.Zip;

import com.liferay.gradle.plugins.LiferayBasePlugin;
import com.liferay.gradle.plugins.css.builder.BuildCSSTask;
import com.liferay.gradle.plugins.css.builder.CSSBuilderPlugin;
import com.liferay.gradle.plugins.node.NodePlugin;
import com.liferay.gradle.plugins.node.tasks.PackageRunBuildTask;
import com.liferay.gradle.plugins.theme.builder.BuildThemeTask;
import com.liferay.gradle.plugins.theme.builder.ThemeBuilderPlugin;
import com.liferay.gradle.plugins.workspace.FrontendPlugin;
import com.liferay.gradle.plugins.workspace.WorkspaceExtension;
import com.liferay.gradle.plugins.workspace.WorkspacePlugin;
import com.liferay.gradle.plugins.workspace.internal.util.GradleUtil;
import com.liferay.gradle.util.FileUtil;
import com.liferay.gradle.util.Validator;

import groovy.json.JsonSlurper;
import groovy.lang.Closure;

/**
 * @author Simon Jiang
 */
public class DesignPacksProjectConfigurator extends BaseProjectConfigurator {

	public static final String DESIGN_PACK_TASK_NAME = "zipDesignPack";

	private boolean _javaBuild;
	
	private static final boolean _JAVA_BUILD = false;
	
	private static final boolean _DEFAULT_REPOSITORY_ENABLED = true;

	private boolean _defaultRepositoryEnabled;
	
	public DesignPacksProjectConfigurator(Settings settings) {
		super(settings);

		_javaBuild = GradleUtil.getProperty(
				settings, WorkspacePlugin.PROPERTY_PREFIX + "themes.java.build",
				_JAVA_BUILD);
		
		String defaultRootDirNames = GradleUtil.getProperty(
			settings, getDefaultRootDirPropertyName(), (String)null);

		if (Validator.isNotNull(defaultRootDirNames)) {
			_defaultRootDirs = new HashSet<>();

			for (String dirName : defaultRootDirNames.split("\\s*,\\s*")) {
				_defaultRootDirs.add(new File(settings.getRootDir(), dirName));
			}
		}
		else {
			File dir = new File(settings.getRootDir(), getDefaultRootDirName());

			_defaultRootDirs = Collections.singleton(dir);
		}
		
		_defaultRepositoryEnabled = GradleUtil.getProperty(
			settings,
			WorkspacePlugin.PROPERTY_PREFIX + NAME +
				".default.repository.enabled",
			_DEFAULT_REPOSITORY_ENABLED);
	}
	
	public boolean isJavaBuild() {
		return _javaBuild;
	}
	
	
	@SuppressWarnings("unchecked")
	private void _configureTaskBuildTheme(Project project) {
		File packageJsonFile = project.file("package.json");

		if (!packageJsonFile.exists()) {
			return;
		}

		BuildThemeTask buildThemeTask = (BuildThemeTask)GradleUtil.getTask(
			project, ThemeBuilderPlugin.BUILD_THEME_TASK_NAME);

		Map<String, Object> packageJsonMap = _getPackageJsonMap(
			packageJsonFile);

		Map<String, String> liferayDesignPackMap =
			(Map<String, String>)packageJsonMap.get("liferayDesignPack");

		String baseTheme = liferayDesignPackMap.get("baseTheme");

		if (baseTheme.equals("styled") || baseTheme.equals("unstyled")) {
			baseTheme = "_" + baseTheme;
		}

		buildThemeTask.setParentName(baseTheme);
		buildThemeTask.setTemplateExtension("ftl");
	}


	private void _configureWar(Project project) {
		WarPluginConvention warPluginConvention = GradleUtil.getConvention(
			project, WarPluginConvention.class);

		warPluginConvention.setWebAppDirName("src");
	}
	
	private void _configureTaskProcessResources(Project project) {
		project.afterEvaluate(
			curProject -> {
				if (GradleUtil.hasTask(
						curProject, CSSBuilderPlugin.BUILD_CSS_TASK_NAME)) {

					Copy copy = (Copy)GradleUtil.getTask(
						project, JavaPlugin.PROCESS_RESOURCES_TASK_NAME);

					if (copy != null) {
						copy.dependsOn(CSSBuilderPlugin.BUILD_CSS_TASK_NAME);

						copy.exclude("**/*.css");
						copy.exclude("**/*.scss");

						copy.filesMatching(
							"**/.sass-cache/",
							fileCopyDetails -> {
								String path = fileCopyDetails.getPath();

								fileCopyDetails.setPath(
									path.replace(".sass-cache/", ""));
							});

						copy.setIncludeEmptyDirs(false);
					}
				}
			});
	}
	
	public static final String PARENT_THEMES_CONFIGURATION_NAME =
			"parentThemes";

	
	public boolean isDefaultRepositoryEnabled() {
		return _defaultRepositoryEnabled;
	}
	
	public static final String PORTAL_COMMON_CSS_CONFIGURATION_NAME =
			"portalCommonCSS";
	
	private void _addDependenciesPortalCommonCSS(Project project) {
		GradleUtil.addDependency(
			project, PORTAL_COMMON_CSS_CONFIGURATION_NAME, "com.liferay",
			"com.liferay.frontend.css.common", "latest.release", false);
		GradleUtil.addDependency(
			project, PORTAL_COMMON_CSS_CONFIGURATION_NAME, "org.webjars",
			"font-awesome", "latest.release", false);
	}

	
	
	
	private void _addDependenciesParentThemes(Project project) {
		GradleUtil.addDependency(
			project, PARENT_THEMES_CONFIGURATION_NAME, "com.liferay",
			"com.liferay.frontend.theme.styled", "latest.release");
		GradleUtil.addDependency(
			project, PARENT_THEMES_CONFIGURATION_NAME, "com.liferay",
			"com.liferay.frontend.theme.unstyled", "latest.release");
		GradleUtil.addDependency(
			project, PARENT_THEMES_CONFIGURATION_NAME, "com.liferay.plugins",
			"classic-theme", "latest.release");
	}
	

	private File _getThemeFile(Iterable<File> files, String name)
		throws Exception {

		for (File file : files) {
			String fileName = file.getName();

			if (fileName.endsWith(".war")) {
				if (fileName.startsWith(name + "-theme-")) {
					return file;
				}
			}
			else {
				try (ZipFile zipFile = new ZipFile(file)) {
					ZipEntry zipEntry = zipFile.getEntry(
						"META-INF/resources/" + name + "/");

					if (zipEntry != null) {
						return file;
					}
				}
			}
		}

		return null;
	}
	
	private Configuration _addConfigurationParentThemes(final Project project) {
		Configuration configuration = GradleUtil.addConfiguration(
			project, PARENT_THEMES_CONFIGURATION_NAME);

		configuration.defaultDependencies(
			new Action<DependencySet>() {

				@Override
				public void execute(DependencySet dependencySet) {
					_addDependenciesParentThemes(project);
				}

			});

		configuration.setDescription(
			"Configures the parent theme JARs required to build theme files.");
		configuration.setTransitive(false);
		configuration.setVisible(false);

		return configuration;
	}
	
	public static final String BUILD_THEME_TASK_NAME = "buildTheme";

	private BuildThemeTask _addTaskBuildTheme(
		Project project, final Iterable<File> parentThemeFiles,
		final WarPluginConvention warPluginConvention) {

		final BuildThemeTask buildThemeTask = GradleUtil.addTask(
			project, BUILD_THEME_TASK_NAME, BuildThemeTask.class);

		buildThemeTask.setDescription("Builds the theme files.");

		buildThemeTask.setDiffsDir(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					return warPluginConvention.getWebAppDir();
				}

			});

		buildThemeTask.setGroup(BasePlugin.BUILD_GROUP);

		buildThemeTask.setOutputDir(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					Project project = buildThemeTask.getProject();

					return new File(
						project.getBuildDir(), buildThemeTask.getName());
				}

			});

		buildThemeTask.setParentFile(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					String parentName = buildThemeTask.getParentName();

					if (Validator.isNull(parentName)) {
						return null;
					}

					return _getThemeFile(parentThemeFiles, parentName);
				}

			});

		buildThemeTask.setParentName("_styled");
		buildThemeTask.setTemplateExtension("ftl");
		buildThemeTask.setThemeName(project.getName());

		buildThemeTask.setUnstyledFile(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					return _getThemeFile(parentThemeFiles, "_unstyled");
				}

			});

		return buildThemeTask;
	}

	private void _configureTaskBuildCSS(
			BuildCSSTask buildCSSTask, final BuildThemeTask buildThemeTask) {

			buildCSSTask.dependsOn(buildThemeTask);

			buildCSSTask.setBaseDir(
				new Callable<File>() {

					@Override
					public File call() throws Exception {
						return buildThemeTask.getOutputDir();
					}

				});
		}

	
	public static final String THEME_BUILDER_CONFIGURATION_NAME =
			"themeBuilder";
	private void _addDependenciesThemeBuilder(Project project) {
		GradleUtil.addDependency(
			project, THEME_BUILDER_CONFIGURATION_NAME, "com.liferay",
			"com.liferay.portal.tools.theme.builder", "latest.release");
	}
	
	private Configuration _addConfigurationThemeBuilder(final Project project) {
		Configuration configuration = GradleUtil.addConfiguration(
			project, THEME_BUILDER_CONFIGURATION_NAME);

		configuration.defaultDependencies(
			new Action<DependencySet>() {

				@Override
				public void execute(DependencySet dependencySet) {
					_addDependenciesThemeBuilder(project);
				}

			});

		configuration.setDescription(
			"Configures Liferay Portal Tools Theme Builder for this project.");
		configuration.setVisible(false);

		return configuration;
	}
	

	private void _configureTaskWar(
		War war, final BuildCSSTask buildCSSTask,
		final BuildThemeTask buildThemeTask) {

		war.dependsOn(buildThemeTask);

		war.eachFile(
			new Action<FileCopyDetails>() {

				@Override
				public void execute(FileCopyDetails fileCopyDetails) {
					String dirName = buildCSSTask.getOutputDirName();

					dirName = dirName.replace('\\', '/');

					if (dirName.charAt(0) != '/') {
						dirName = '/' + dirName;
					}

					if (dirName.charAt(dirName.length() - 1) != '/') {
						dirName = dirName + '/';
					}

					String path = fileCopyDetails.getPath();

					fileCopyDetails.setPath(path.replace(dirName, "/"));
				}

			});

		war.exclude(
			new Spec<FileTreeElement>() {

				@Override
				public boolean isSatisfiedBy(FileTreeElement fileTreeElement) {
					File diffsDir = buildThemeTask.getDiffsDir();

					if ((diffsDir != null) &&
						FileUtil.isChild(fileTreeElement.getFile(), diffsDir)) {

						return true;
					}

					return false;
				}

			});

		war.exclude("**/*.scss");

		war.from(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					return buildThemeTask.getOutputDir();
				}

			});

		war.setIncludeEmptyDirs(false);
	}

	private void _configureTasksBuildTheme(
			Project project, final FileCollection classpath) {

			TaskContainer taskContainer = project.getTasks();

			taskContainer.withType(
				BuildThemeTask.class,
				new Action<BuildThemeTask>() {

					@Override
					public void execute(BuildThemeTask buildThemeTask) {
						buildThemeTask.setClasspath(classpath);
					}

				});
		}
	
	public void executeIfEmpty(
			final Configuration configuration, final Action<Configuration> action, List<String> artifctNames) {

			ResolvableDependencies resolvableDependencies =
				configuration.getIncoming();

			resolvableDependencies.beforeResolve(
				new Action<ResolvableDependencies>() {

					@Override
					public void execute(
						ResolvableDependencies resolvableDependencies) {

						DependencySet dependencies2 = resolvableDependencies.getDependencies();
						
						Dependency[] Dependencies = dependencies2.toArray();
						
						for(String artifactName : artifctNames) {
							
						}
						
						
						
						action.execute(configuration);
						
						
						
						
						Set<Dependency> dependencies =
							configuration.getDependencies();
						Set<Configuration> parentConfigurations =
							configuration.getExtendsFrom();

						if (dependencies.isEmpty() &&
							parentConfigurations.isEmpty()) {

							action.execute(configuration);
						}
						
						
					}

				});
		}
	
	
	@Override
	public void apply(Project project) {
		
		WorkspaceExtension workspaceExtension = GradleUtil.getExtension(
				(ExtensionAware)project.getGradle(), WorkspaceExtension.class);
		
		File packageJsonFile = project.file("package.json");

		if (isJavaBuild()) {
			
			GradleUtil.applyPlugin(project, LiferayBasePlugin.class);
			GradleUtil.applyPlugin(project, WarPlugin.class);

			_configureTaskProcessResources(project);

//			_addConfigurationParentThemesUnstyled(project);

			if (isDefaultRepositoryEnabled()) {
				GradleUtil.addDefaultRepositories(project);
			}	
			
			GradleUtil.applyPlugin(project, ThemeBuilderPlugin.class);
			
			Configuration parentThemesConfiguration = GradleUtil.getConfiguration(project, ThemeBuilderPlugin.PARENT_THEMES_CONFIGURATION_NAME);
			Configuration portalCommonCssonfiguration = GradleUtil.getConfiguration(project, CSSBuilderPlugin.PORTAL_COMMON_CSS_CONFIGURATION_NAME);
			
			executeIfEmpty(
				parentThemesConfiguration,
				new Action<Configuration>() {

					@Override
					public void execute(Configuration configuration) {
						_addDependenciesParentThemes(project);
					}

				});
			
			executeIfEmpty(
				portalCommonCssonfiguration,
				new Action<Configuration>() {

					@Override
					public void execute(Configuration configuration) {
						
					}

				});
				
			//_addDependenciesParentThemes(project);
			//_addDependenciesPortalCommonCSS(project);
			
//
//			GradleUtil.applyPlugin(project, CSSBuilderPlugin.class);
//			GradleUtil.applyPlugin(project, WarPlugin.class);
//
//			BuildCSSTask buildCSSTask = (BuildCSSTask)GradleUtil.getTask(
//				project, CSSBuilderPlugin.BUILD_CSS_TASK_NAME);
//			War war = (War)GradleUtil.getTask(project, WarPlugin.WAR_TASK_NAME);
//			WarPluginConvention warPluginConvention = GradleUtil.getConvention(
//				project, WarPluginConvention.class);
//
//			Configuration parentThemesConfiguration = _addConfigurationParentThemes(
//				project);
//			Configuration themeBuilderConfiguration = _addConfigurationThemeBuilder(
//				project);
//
//			_addDependenciesParentThemes(project);
//			_addDependenciesPortalCommonCSS(project);
//
//			BuildThemeTask buildThemeTask = _addTaskBuildTheme(
//				project, parentThemesConfiguration, warPluginConvention);
//
//			_configureTaskBuildCSS(buildCSSTask, buildThemeTask);
//			_configureTaskWar(war, buildCSSTask, buildThemeTask);
//
//			_configureTasksBuildTheme(project, themeBuilderConfiguration);
			
			_configureTaskBuildTheme(project);
			_configureWar(project);
	
		}
		else if (packageJsonFile.exists()) {
			GradleUtil.applyPlugin(project, FrontendPlugin.class);

			Zip zipDesignPackTask = _addTaskZipDesignPack(
				project, DESIGN_PACK_TASK_NAME, workspaceExtension);

			zipDesignPackTask.setDescription(
				"Assembles design pack project (zip).");

			zipDesignPackTask.setGroup(BasePlugin.BUILD_GROUP);

			_configureTaskDesignPack(project, zipDesignPackTask);

			_configureTaskPackageRunBuild(project, zipDesignPackTask);
		}
	}

	@Override
	public Iterable<File> getDefaultRootDirs() {
		return _defaultRootDirs;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	protected Iterable<File> doGetProjectDirs(File rootDir) throws Exception {
		final Set<File> projectDirs = new HashSet<>();

		Files.walkFileTree(
			rootDir.toPath(),
			new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(
						Path dirPath, BasicFileAttributes basicFileAttributes)
					throws IOException {

					String dirName = String.valueOf(dirPath.getFileName());

					if (isExcludedDirName(dirName)) {
						return FileVisitResult.SKIP_SUBTREE;
					}

					Path packageJsonPath = dirPath.resolve("package.json");

					if (Files.exists(packageJsonPath) &&
						_isLiferayTheme(packageJsonPath)) {

						projectDirs.add(dirPath.toFile());

						return FileVisitResult.SKIP_SUBTREE;
					}

					return FileVisitResult.CONTINUE;
				}

			});

		return projectDirs;
	}

	@Override
	protected String getDefaultRootDirName() {
		return "design-packs";
	}

	protected static final String NAME = "design.pack";

	@SuppressWarnings("serial")
	private Zip _addTaskZipDesignPack(
		Project project, String taskName,
		final WorkspaceExtension workspaceExtension) {

		Zip task = GradleUtil.addTask(project, taskName, Zip.class);

		task.dependsOn(NodePlugin.PACKAGE_RUN_BUILD_TASK_NAME);

		_configureTaskDisableUpToDate(task);

		task.into(
			new Callable<String>() {

				@Override
				public String call() throws Exception {
					String bundleDistRootDirName =
						workspaceExtension.getBundleDistRootDirName();

					if (Validator.isNull(bundleDistRootDirName)) {
						bundleDistRootDirName = "";
					}

					return bundleDistRootDirName;
				}

			},
			new Closure<Void>(task) {

				@SuppressWarnings("unused")
				public void doCall(CopySpec copySpec) {
					copySpec.from(new File(project.getBuildDir(), "css"));
					copySpec.setIncludeEmptyDirs(false);
					copySpec.include("**/clay.css");
					copySpec.include("**/clay_rtl.css");
					copySpec.include("**/main.css");
					copySpec.include("**/main_rtl.css");
				}

			});

		DirectoryProperty destinationDirectoryProperty =
			task.getDestinationDirectory();

		destinationDirectoryProperty.set(
			new File(project.getProjectDir(), "dist"));

		return task;
	}

	private void _configureTaskDesignPack(Project project, Zip zipTask) {
		Property<String> archiveBaseNameProperty = zipTask.getArchiveBaseName();

		archiveBaseNameProperty.set(
			project.provider(
				new Callable<String>() {

					@Override
					public String call() throws Exception {
						StringBuilder sb = new StringBuilder();

						sb.append(project.getName());

						return sb.toString();
					}

				}));

		Property<String> archiveVersion = zipTask.getArchiveVersion();

		archiveVersion.set(
			project.provider(
				new Callable<String>() {

					@Override
					public String call() throws Exception {
						return null;
					}

				}));

		zipTask.doLast(
			new Action<Task>() {

				@Override
				public void execute(Task task) {
					project.delete(
						new File(
							project.getProjectDir(),
							"dist/" + project.getName() + ".war"));
				}

			});
	}

	private void _configureTaskDisableUpToDate(Task task) {
		TaskOutputs taskOutputs = task.getOutputs();

		taskOutputs.upToDateWhen(
			new Spec<Task>() {

				@Override
				public boolean isSatisfiedBy(Task task) {
					return false;
				}

			});
	}

	private void _configureTaskPackageRunBuild(
		Project project, Zip taskZipDesignPack) {

		TaskContainer taskContainer = project.getTasks();

		taskContainer.withType(
			PackageRunBuildTask.class,
			new Action<PackageRunBuildTask>() {

				@Override
				public void execute(PackageRunBuildTask packageRunBuildTask) {
					packageRunBuildTask.finalizedBy(taskZipDesignPack);
				}

			});
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> _getPackageJsonMap(File packageJsonFile) {
		if (!packageJsonFile.exists()) {
			return Collections.emptyMap();
		}

		JsonSlurper jsonSlurper = new JsonSlurper();

		return (Map<String, Object>)jsonSlurper.parse(packageJsonFile);
	}

	@SuppressWarnings("unchecked")
	private boolean _isLiferayTheme(Path packageJsonPath) {
		Map<String, Object> packageJsonMap = _getPackageJsonMap(
			packageJsonPath.toFile());

		Map<String, Object> liferayTheme =
			(Map<String, Object>)packageJsonMap.get("liferayDesignPack");

		if (liferayTheme != null) {
			return true;
		}

		return false;
	}

	private final Set<File> _defaultRootDirs;

}