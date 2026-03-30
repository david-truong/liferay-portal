# Liferay DXP: Complete End-User Product Guide

<!--
schema: product-documentation
version: 1.0
platform: Liferay DXP
source: codebase-analysis
generated: 2026-03-28
audience: [content-creators, marketers, intranet-users, intranet-admins]
format: markdown
sections: 18
ai-parseable: true
-->

> This documentation was produced entirely from analysis of the Liferay DXP codebase, including user interface files and language label files. All feature names, labels, and UI terms come directly from what the platform shows to users.

---

## Table of Contents

1. [Product Overview](#1-product-overview)
2. [Content Management](#2-content-management)
   - [Web Content](#21-web-content)
   - [Documents and Media](#22-documents-and-media)
   - [Blogs](#23-blogs)
   - [Knowledge Base](#24-knowledge-base)
   - [Wiki](#25-wiki)
   - [Message Boards](#26-message-boards)
3. [Site Building and Design](#3-site-building-and-design)
   - [Pages Administration](#31-pages-administration)
   - [Content Page Editor](#32-content-page-editor)
   - [Page Fragments](#33-page-fragments)
   - [Style Books](#34-style-books)
   - [Navigation Menus](#35-navigation-menus)
4. [Content Discovery and Aggregation](#4-content-discovery-and-aggregation)
   - [Asset Publisher](#41-asset-publisher)
   - [Collections](#42-collections)
   - [Search](#43-search)
   - [Content Dashboard](#44-content-dashboard)
5. [Collaboration Tools](#5-collaboration-tools)
   - [Questions and Answers](#51-questions-and-answers)
   - [Calendar](#52-calendar)
   - [Sharing](#53-sharing)
   - [Notifications](#54-notifications)
   - [Announcements](#55-announcements)
6. [Personalization and Targeting](#6-personalization-and-targeting)
   - [Segments](#61-segments)
   - [Experiences](#62-experiences)
   - [A/B Testing](#63-ab-testing)
7. [Forms](#7-forms)
8. [Commerce](#8-commerce)
   - [Products and Catalogs](#81-products-and-catalogs)
   - [Orders and Checkout](#82-orders-and-checkout)
   - [Pricing and Discounts](#83-pricing-and-discounts)
   - [Inventory and Warehouses](#84-inventory-and-warehouses)
   - [Commerce Accounts](#85-commerce-accounts)
9. [Workflow and Approvals](#9-workflow-and-approvals)
10. [Site and User Administration](#10-site-and-user-administration)
    - [Site Settings](#101-site-settings)
    - [Users and Organizations](#102-users-and-organizations)
    - [Roles and Permissions](#103-roles-and-permissions)
    - [Account Management](#104-account-management)
    - [Password Policies](#105-password-policies)
    - [Accessibility](#106-accessibility)
11. [Content Operations](#11-content-operations)
    - [Staging](#111-staging)
    - [Publications](#112-publications)
    - [Translation](#113-translation)
    - [Export and Import](#114-export-and-import)
    - [Asset Libraries](#115-asset-libraries)
    - [Custom Objects](#116-custom-objects)
12. [Platform Tools](#12-platform-tools)
    - [URL Redirects](#121-url-redirects)
    - [Job Scheduler](#122-job-scheduler)
    - [Data Migration Center](#123-data-migration-center)
    - [Categories and Tags](#124-categories-and-tags)
    - [Recycle Bin](#125-recycle-bin)
    - [Adaptive Media](#126-adaptive-media)
    - [Digital Signatures](#127-digital-signatures)
    - [AI Creator](#128-ai-creator)
13. [CMS (Content Management System)](#13-cms-content-management-system)
    - [CMS Dashboard](#131-cms-dashboard)
    - [Assets Management](#132-assets-management)
    - [Spaces and Collaboration](#133-spaces-and-collaboration)
    - [Structures and Content Types](#134-structures-and-content-types)
    - [Categorization Management](#135-categorization-management)
    - [Bulk Operations](#136-bulk-operations)
    - [Translation Pipeline](#137-translation-pipeline)
14. [CMP (Collaborative Management Platform)](#14-cmp-collaborative-management-platform)
    - [Projects](#141-projects)
    - [Tasks](#142-tasks)
    - [Project and Task Workflows](#143-project-and-task-workflows)
    - [Notifications](#144-notifications)
15. [Digital Sales Room](#15-digital-sales-room)
    - [Rooms](#151-rooms)
    - [Room Templates](#152-room-templates)
    - [Room Analytics](#153-room-analytics)
    - [Member Invitations](#154-member-invitations)
16. [AI Hub](#16-ai-hub)
    - [AI Agents](#161-ai-agents)
    - [Built-In AI Workflows](#162-built-in-ai-workflows)
    - [Chat Interface](#163-chat-interface)
    - [MCP Servers](#164-mcp-servers)
17. [Site Templates](#17-site-templates)
18. [Sign In and Account Settings](#18-sign-in-and-account-settings)

---

## 1. Product Overview

**Liferay DXP** (Digital Experience Platform) is an enterprise platform for building websites, intranets, customer portals, and digital commerce experiences. Think of it as the foundation your organization uses to publish content, manage documents, connect with users, run online stores, and build fully designed web pages — all from one place.

**Who is it for?**
- **Content creators and editors** who write articles, blog posts, and knowledge base articles
- **Marketers** who build landing pages, target content to different audiences, and run A/B tests
- **Intranet managers and administrators** who set up sites, manage users, configure permissions, and publish organizational content
- **Site designers** who assemble page layouts using drag-and-drop tools
- **Commerce managers** who run product catalogs, manage orders, set pricing, and handle inventory

**What can you do with it?**

At its core, Liferay DXP lets you:
- Create and manage structured **web content**, **blog posts**, **documents**, **wiki pages**, **knowledge base articles**, and **discussion forums**
- Design and publish **web pages** using a visual drag-and-drop editor with pre-built and custom building blocks
- **Target content** to specific audiences using segments and personalized page experiences
- Run **online stores** with product catalogs, shopping carts, pricing rules, inventory tracking, and order management
- Manage **users, organizations, and roles** with fine-grained permission controls
- **Translate** content into multiple languages
- Move content through **approval workflows** before it goes live
- **Stage changes safely** using Publications — make edits to pages, content, and settings in a sandbox without affecting live users, then publish everything at once when ready
- **Export content** for translation, back up sites, or migrate data between environments

The platform supports multiple separate **sites** — each site can have its own pages, content, users, and settings. Content from a shared resource called an **Asset Library** can be reused across multiple sites. All of this can be managed from a central **Control Panel** available to administrators.

### Navigating the Interface

Liferay DXP's interface is organized into three main areas:

| Area | Where It Is | What It Contains |
|---|---|---|
| **Site Menu** | Left side of the screen | Actions, content, and settings scoped to the site you're currently viewing. This is where you access Web Content, Documents, Blogs, Pages, Navigation Menus, and site-specific settings. |
| **Personal Menu** | Top-right corner (your profile picture) | Your personal account settings, notifications, My Workflow Tasks, and the option to sign out. |
| **Global Menu** | Top-right corner (grid icon) | The **Applications Menu** (site-independent tools), the **Commerce Menu** (store management), and the **Control Panel** (platform-wide administration for users, roles, sites, system settings, and more). |

When navigating Liferay, remember: the **Site Menu** is for the current site's content, and the **Global Menu** is for platform-wide administration.

---

## 2. Content Management
<!-- category: content-management, audience: [content-creators, marketers, intranet-admins], modules: [journal-web, document-library-web, blogs-web, knowledge-base-web, wiki-web, message-boards-web] -->

### 2.1 Web Content
<!-- module: journal-web, portlet: com_liferay_journal_web_portlet_JournalPortlet, audience: [content-creators, marketers], capabilities: [create-articles, version-control, scheduling, workflow-integration, structured-content, templates, categorization] -->

**Web Content** is the primary tool for creating and managing structured text and media articles on your site. You would use it for announcements, news articles, product descriptions, policy documents, employee profiles, or any content that follows a consistent format.

#### What You Can Do

- **Add Web Content** — Create a new article using a text editor. Articles can include text, images, embedded videos, tables, and links.
- **Save as Draft** — Save your work-in-progress without publishing it.
- **Submit for Workflow** — If your site uses an approval process, submit your article for review before it goes live.
- **Publish** — Make your article immediately visible to site visitors.
- **Schedule publication** — Set a date and time for your article to automatically go live. You can also set an **Expiration Date** (when the article automatically stops displaying) and a **Review Date** (when it should be reviewed for accuracy).
- **Organize into folders** — Group related articles into folders for easier management.
- **View version history** — Every time you edit a published article, Liferay automatically saves a new version. You can compare different versions or restore an older one.

#### Content Structures

Each article is based on a **Structure** — a template that defines what fields the article contains. For example, a "Press Release" structure might have fields for headline, date, body text, and a quote. A "Job Posting" structure might have role, department, location, and description.

When you create or edit a web content article, you select which structure it uses. The structure determines what information you fill in.

#### Display Templates

A **Template** controls how a structure's content is displayed on a page. Your administrator or designer may have created multiple templates for the same structure — for example, a "Full View" template and a "Summary Card" template — so you can choose how the content appears.

#### Key Settings on an Article

When editing an article, you can configure:

| Setting | What It Does |
|---|---|
| **Title** | The article's name |
| **ID** | A unique identifier (can be auto-generated) |
| **Structure** | The content model this article follows |
| **Version** | Automatically incremented each time you edit a published article |
| **Status** | Draft, Pending, Approved, Scheduled, or Expired |
| **Folder** | Where the article lives in the content library |
| **Display Page Template** | The page layout used when someone views this article on its own page |
| **Friendly URL** | The custom web address for this article |
| **Expiration Date** | When this article should stop displaying |
| **Review Date** | When this article should be flagged for review |
| **Categories** | Taxonomy labels for organizing and filtering content |
| **Tags** | Freeform keywords for search and filtering |
| **Related Assets** | Links to other content or documents |
| **Permissions** | Who can view, edit, or comment on this article |
| **Small Image** | A thumbnail image representing this article in lists |

#### Finding and Managing Articles

The Web Content administration screen shows all your articles in a list or grid view. You can search, filter by folder or status, sort, and perform bulk actions like moving or deleting multiple articles at once. From the actions menu on any article you can copy, move, delete, expire, subscribe to notifications, or view usage (which pages display it).

#### Walkthrough: Creating and Publishing Web Content

**Goal:** Write a news article and publish it to your site.

1. Navigate to your site's **Web Content** section (via the site menu or Content panel)
2. Click **Add** and select your desired structure (e.g., "Basic Web Content" or "News Article")
3. Fill in the required fields — at minimum, the **Title** and the main body content
4. In the sidebar, configure optional settings:
   - Set the **Display Date** to schedule it for a future time if needed
   - Add **Categories** and **Tags** to help users find it
   - Set a **Friendly URL** for a custom web address
   - Assign it to a **Display Page Template** so it has its own dedicated page
5. To publish immediately, click **Publish**. If your site has a workflow:
   - Click **Submit for Workflow** to send it for review
   - Track its status (Pending → Approved → Published) from the content list
6. To save a draft and come back later, click **Save as Draft**

**To update a published article:**

1. Find the article in the Web Content list
2. Click the article's name or the **Actions** menu → **Edit**
3. Make your changes — Liferay automatically creates a new version
4. Click **Publish** (or **Submit for Workflow** if workflow is active)

---

### 2.2 Documents and Media
<!-- module: document-library-web, portlet: com_liferay_document_library_web_portlet_DLPortlet, audience: [all-users], capabilities: [file-upload, versioning, check-in-check-out, folders, document-types, metadata, preview, webdav, sharing] -->

**Documents and Media** is the file management system built into Liferay DXP. Use it to upload, organize, share, and manage any type of file — PDFs, Word documents, spreadsheets, images, videos, or any other file your team works with.

#### What You Can Do

- **Upload files** — Drag and drop files directly into the browser, or use the upload button. You can upload multiple files at once.
- **Organize into folders** — Create a folder structure as deep as you need. Folders can have their own document type restrictions and workflow settings.
- **Create document types** — A **Document Type** is like a metadata template for your files. For example, a "Contract" document type might add fields for "Client Name," "Contract Date," and "Renewal Date." This makes it easy to search and filter documents by their properties.
- **Preview files** — Documents, images, videos, and many other file types can be previewed directly in the browser without downloading.
- **Download files** — Download any file you have access to.
- **Share files** — Send a link to a specific file for other users, or share with specific people with defined access levels.

#### Versioning Documents

When you update an existing document, you can choose how to record the change:

- **Major version** — A significant update (e.g., 1.0 becomes 2.0). Use this for major revisions.
- **Minor version** — A small update (e.g., 1.0 becomes 1.1). Use this for corrections or tweaks.
- **Keep current version number** — For administrative fixes that don't warrant a version bump.
- **Version Notes** — Add a short description explaining what changed.

You can view the full version history of any document and download or restore any previous version.

#### Check Out and Check In

To prevent two people from editing the same document at the same time:

1. **Check Out** a document to "lock" it. While checked out, others cannot make changes. You now have an exclusive editing window.
2. Download the file, make your changes, and re-upload.
3. **Check In** (or **Save and Check In**) to release the lock and save the new version.

If someone else has a document checked out, you will see a message telling you who has it locked and when.

#### Document Properties

When editing a document, you can set:

| Field | Description |
|---|---|
| **Title** | The display name of the document |
| **Description** | A summary of the document |
| **Document Type** | The metadata template applied to this document |
| **Friendly URL** | A custom URL for linking directly to this document |
| **Permissions** | Who can view, download, comment on, or edit the file |

#### Folder Settings

Folders have additional settings for administrators:

- **Document Type Restrictions** — Limit a folder to only accept certain document types
- **Default Document Type** — Automatically apply a document type to files added to this folder (including via WebDAV)
- **Workflow** — Require approval before documents uploaded to this folder become visible

#### WebDAV Access

If enabled by your administrator, you can connect to Documents and Media using WebDAV — this lets you access your organization's files through Windows Explorer, Mac Finder, or any WebDAV-compatible tool, as if they were a local folder on your computer.

#### Walkthrough: Managing Documents

**Goal:** Upload a PDF, organize it, and make it available on a page.

1. Go to **Documents and Media** in your site
2. Navigate to (or create) the folder where the document should live
3. Click **Add** → **File Upload**
4. Drag and drop your file or click to browse and select it
5. Fill in the **Title**, **Description**, and select the **Document Type** if applicable
6. If applicable, fill in the document type's custom metadata fields
7. Click **Publish** to save and make the document available

**To update a document:**

1. Click the document's **Actions** menu → **Edit**
2. Upload the new version of the file (or change metadata only)
3. Under the **Versioning** section, choose whether this is a **Major**, **Minor**, or same-version update
4. Add **Version Notes** to describe what changed
5. Click **Publish**

**To check out a document for exclusive editing:**

1. Click **Actions** → **Checkout**
2. Download the file, make changes
3. Click **Actions** → **Check In** (or edit the document and click **Save and Check In**)

---

### 2.3 Blogs
<!-- module: blogs-web, portlet: com_liferay_blogs_web_portlet_BlogsPortlet, audience: [content-creators, marketers], capabilities: [blog-posts, cover-images, scheduling, comments, subscriptions, rss, categorization] -->

**Blogs** is a tool for writing and publishing long-form posts, personal perspectives, news updates, or any content where an author's voice and publication date matter. Blog posts support rich formatting, images, comments, and subscriptions.

#### What You Can Do

- **Write blog entries** — Use a full rich-text editor that supports headings, bold and italic text, lists, images, videos, tables, and code blocks.
- **Add a cover image** — Choose or upload a large featured image that appears at the top of your post.
- **Add a cover image caption** — Describe or credit the cover image.
- **Set a display date** — Schedule when the post becomes visible. By default it publishes immediately, but you can set a future date and time.
- **Write an abstract** — A brief summary that appears in blog listing views. You can use the automatic abstract (first portion of the post) or write a **custom abstract** with an optional small image.
- **Set a custom URL** — Customize the web address for this post. You can also add categories to the URL for more meaningful paths.
- **Categorize and tag** — Add categories from your site's taxonomy and freeform tags.
- **Allow pingbacks and trackbacks** — These are notifications that get sent when other blogs link to your post.

#### Viewing and Finding Blog Entries

The blogs widget on a page shows a list of recent entries. Readers can browse by category or tag, and search within the blog. Each entry shows the author, date, cover image, title, abstract, and reading metrics.

**Subscribing to a blog** — Users can subscribe to receive a notification whenever a new post is published.

#### Blog Images Management

There is a separate **Images** tab in the Blogs administration view where you can manage all images that have been uploaded for blog posts across the site.

#### Walkthrough: Writing a Blog Post

**Goal:** Publish a blog entry with a cover image and schedule it for tomorrow.

1. Navigate to **Blogs** (either through the site menu or the Blogs Administration section)
2. Click **Add Blog Entry**
3. Upload or select a **Cover Image** by clicking the image area at the top
4. Add a **Cover Image Caption** if desired
5. Type your post **title** in the title field
6. Optionally add a **subtitle**
7. Write your post content in the rich text editor
8. In the sidebar options:
   - Under **Abstract**, write a custom summary or use the automatic one
   - Set the **Display Date** to tomorrow's date and your preferred time
   - Add **Categories** and **Tags**
   - Customize the **Friendly URL** if desired
9. Click **Publish**. Since the display date is in the future, the post will be written but will only become visible to readers at that date and time.

---

### 2.4 Knowledge Base
<!-- module: knowledge-base-web, audience: [content-creators, intranet-admins], capabilities: [articles, hierarchy, templates, sections, versioning, import-export, suggestions, expiration, subscriptions] -->

The **Knowledge Base** is designed for publishing organized, searchable documentation — user manuals, policies, how-to guides, FAQs, or any reference material that benefits from a structured hierarchy.

#### What You Can Do

- **Write articles** — Each article has a title, body text, and optional attachments.
- **Organize into a hierarchy** — Articles can have parent and child articles, creating a tree structure. You can also organize articles into folders.
- **Use templates** — Knowledge Base templates let you pre-fill the structure of an article with standard headings and placeholder text, ensuring consistency across your documentation.
- **Set article sections** — Administrators can define named sections (like "Admin Knowledge Base" or "User Guide") to categorize articles by audience.
- **Compare versions** — See exactly what changed between two versions of an article.
- **Import articles** — Import articles from Markdown files in a ZIP archive.
- **Export articles** — Download articles for backup or migration.
- **View suggestions** — Users who read articles can submit suggestions or corrections. Admins review these from the **Suggestions** view.
- **Set expiration dates** — Articles can be flagged for review or automatically expired after a configured period.
- **Subscribe to notifications** — Get notified when an article is updated.
- **Print articles** — Print-friendly views are available for each article.

#### How It Displays to Readers

There are several ways to display the Knowledge Base to site visitors:

- **Knowledge Base Display** — Shows the full navigation tree alongside the selected article, ideal for browsing a documentation set.
- **Knowledge Base Article** — Displays a single article you specify.
- **Knowledge Base Section** — Shows all articles belonging to a particular section.
- **Knowledge Base Search** — A search widget scoped to the knowledge base.

#### Walkthrough: Creating a Knowledge Base Article

**Goal:** Publish a how-to guide organized under a parent article.

1. Go to **Content > Knowledge Base** in your site administration
2. Click **Add** → **Basic Article**
3. Enter a **Title** (e.g., "How to Submit a Vacation Request")
4. Write the article body using the rich text editor — include headings, numbered steps, and screenshots as needed
5. Optionally attach supporting files (PDF forms, templates)
6. Under **Configuration**, select a **Parent Article** if this article should be nested under an existing topic (e.g., "HR Policies")
7. Add **Categories** and **Tags** to help users find it
8. Click **Publish**
9. To add a child article underneath it, navigate to the published article and click **Add Child Article**
10. To display the Knowledge Base on a page, add the **Knowledge Base Display** widget and configure it to show your articles

---

### 2.5 Wiki (Deprecated)
<!-- module: wiki-web, audience: [intranet-users, content-creators], capabilities: [wiki-pages, collaborative-editing, versioning, attachments, page-tree, subscriptions], status: deprecated, feature-flag: LPD-35013 -->

> **Deprecation Notice:** Wiki is deprecated and will be removed in a future release. To avoid data loss, you should migrate your wiki content to **Custom Objects** (see [Section 11.6](#116-custom-objects)). Existing wiki data will continue to work, but no new enhancements are planned. Your administrator may need to enable the Wiki feature flag (LPD-35013) to access this feature.

A **Wiki** is a collaborative documentation tool where multiple users can create and edit pages together. It is well-suited for team documentation, project notes, internal reference material, and collaborative guides that evolve over time.

#### What You Can Do

- **Create wiki pages** — Write content using a rich text editor.
- **Organize pages** — Pages are organized within a **Wiki node** (a named collection of pages). Sites can have multiple wiki nodes.
- **Edit pages** — Any user with the appropriate permissions can edit existing pages.
- **View page history** — See all previous versions of a page, who made each change, and when.
- **Compare versions** — View a side-by-side or inline diff showing exactly what changed between versions.
- **Add attachments** — Attach files to any wiki page.
- **Browse the page tree** — Navigate the wiki using a hierarchical tree view.
- **View all pages** — Browse a flat list of all pages in the wiki.
- **View categorized pages** — Browse pages by category.
- **Subscribe to pages** — Receive notifications when a page is updated.
- **Import pages** — Import wiki content from external sources.

#### Wiki Configuration

Each wiki can be configured to allow or restrict editing to certain user roles. Administrators can also connect a wiki to a specific site navigation so it integrates with the overall site structure.

---

### 2.6 Message Boards (Deprecation Pending)
<!-- module: message-boards-web, audience: [intranet-users, intranet-admins], capabilities: [forums, threads, categories, moderation, subscriptions, permissions], status: deprecation-pending -->

> **Deprecation Notice:** Message Boards is in the process of being deprecated. While still functional and not yet behind a feature flag, a deprecation banner is displayed in the administration interface. Plan to evaluate alternative discussion tools for new projects.

**Message Boards** is a discussion forum tool where community members can start threads, reply to messages, and engage in structured conversations. It is similar to familiar internet forums.

#### Structure

- **Categories** — Top-level groupings of related discussions (for example, "General Discussion," "Technical Support," "Announcements")
- **Threads** — A conversation initiated by a first message (called a "topic" or "thread")
- **Messages** — Individual posts within a thread. The original post starts the thread; all subsequent posts are replies.

#### What You Can Do

- **Create categories** — Organize your forum by topic area.
- **Start a new thread** — Post a new discussion topic in a category with a subject and body.
- **Reply to messages** — Reply to the original post or to any specific reply in the thread.
- **Attach files** — Attach documents or images to your messages.
- **Mark as question** — Some forums are configured so threads can be marked as questions, with replies able to be marked as answers.
- **Subscribe to threads or categories** — Receive email or in-app notifications when new messages arrive.
- **Lock threads** — Moderators can lock a thread to prevent further replies.
- **Move threads** — Move a thread to a different category.
- **Split threads** — Split a thread at a specific point into two separate threads.
- **Ban users** — Moderators can ban disruptive users from the message boards.
- **View statistics** — See message counts, most active users, and recent activity.

#### Formatting Messages

Messages support a rich text editor with formatting options. You can also use anonymous posting if the forum is configured to allow it.

---

## 3. Site Building and Design
<!-- category: site-building, audience: [marketers, intranet-admins], modules: [layout-admin-web, layout-content-page-editor-web, fragment-web, style-book-web, site-navigation-admin-web] -->

### 3.1 Pages Administration
<!-- module: layout-admin-web, audience: [marketers, intranet-admins], capabilities: [page-hierarchy, page-creation, page-types, seo, friendly-urls, page-templates, utility-pages] -->

The **Pages Administration** section is where site administrators and designers manage all the pages that make up a website. Think of it as the control center for your site's structure.

#### Page Types

When adding a new page, you choose its type:

| Page Type | Description |
|---|---|
| **Content Page** | A visually designed page built with drag-and-drop building blocks (fragments and widgets). This is the recommended type for most pages. |
| **Widget Page** | A traditional column-based layout where you add self-contained apps. Useful for functional pages like search results or document libraries. |
| **Full Page Application** | Dedicates the entire page to a single app, like a full-screen wiki or blog. |
| **Embedded** | Embeds an external URL inside the page. |
| **Panel** | Shows a list of your site's apps in a sidebar-style layout. |
| **Link to a Page** | Creates a navigation entry that points to another page within the site. |
| **Link to URL** | Creates a navigation entry that points to any external URL. |
| **Collection Page** | Displays items from a collection (a list of content), great for blog listing pages or product grids. |

#### Page Settings

For any page, you can configure:

- **Name** — What appears in navigation menus and browser tabs
- **Friendly URL** — The custom web address for this page (e.g., `/about-us`)
- **Navigation Menus** — Which navigation menus this page automatically appears in when created
- **Categories** — Taxonomy labels for the page
- **Design** — The **Master Page** (defines the header, footer, and fixed areas) and the **Style Book** (defines colors, fonts, and spacing)
- **SEO Settings** — Page title, meta description, keywords, canonical URL, Open Graph social sharing settings, robots directives, and hreflang settings for multilingual sites
- **Custom CSS** — Add custom CSS directly to a specific page
- **Custom Fields** — Any additional fields configured by your administrator

#### Page Templates

**Page Templates** let you create reusable starting points for new pages. When you create a page from a template, it inherits the layout and content of the template. Page templates are organized into **Page Template Sets**.

There are also **Master Pages** — these define the common areas shared across multiple pages (like a consistent header and footer with a navigation bar and logo). When you change a master page, all pages using it update automatically.

#### Display Page Templates

A **Display Page Template** is a special type of template that defines how a specific type of content (like a web content article, a blog post, or a document) looks when someone visits its dedicated URL. Instead of building a unique page for every piece of content, you design one display page template and all content of that type uses it automatically.

#### Walkthrough: Adding a New Page to Your Site

**Goal:** Add a "Resources" section page to your site.

1. Go to **Site Administration > Site Builder > Pages**
2. Click **Add** (the + button) at the level where you want the page
3. Select **Content Page** as the type
4. Choose a **Page Template** to start from, or select **Blank** to start empty
5. Name the page "Resources"
6. Configure which **Navigation Menus** the page should appear in (or uncheck all to add it manually later)
7. Click **Add** — you are taken into the Content Page Editor to start designing
8. When done designing, click **Publish**
9. Back in Pages Administration, drag the page to reorder it in the hierarchy if needed
10. Click the page's **Actions** menu → **Configure** to set the **Friendly URL**, **SEO** settings, and **Design** (master page and style book)

---

### 3.2 Content Page Editor
<!-- module: layout-content-page-editor-web, audience: [marketers, content-creators, intranet-admins], capabilities: [drag-and-drop, fragments, responsive-design, inline-editing, experiences, collections, widgets, undo-redo] -->

The **Content Page Editor** is the visual design tool for building content pages. When you click **Edit** on a content page, you enter the editor.

#### The Editor Layout

The editor has three main areas:

1. **The Canvas** — The center area shows the page as it looks to visitors, where you place and arrange building blocks.
2. **The Sidebar** — A panel on the left side with multiple tabs.
3. **The Toolbar** — The bar at the top with publish controls, viewport selectors, and undo/redo.

#### Sidebar Panels

The sidebar can show different panels depending on what you're doing:

- **Fragments and Widgets** — Browse and drag building blocks onto the page. Fragments are designed visual components; widgets are self-contained functional apps.
- **Page Design Options** — Switch the master page or style book for this page.
- **Page Content** — Shows all the content pieces (images, web content articles, etc.) that have been placed on this page.
- **Comments** — Add and view editorial comments tied to specific page elements. Useful for team review.
- **Mapping** — Configure how fragment fields are mapped to content fields.

#### Building the Page

You build a content page by dragging elements from the sidebar onto the canvas:

- **Containers** — Flexible layout wrappers that can hold other elements. You set the container width, padding, margins, and background.
- **Grids** — Organize content into responsive column layouts.
- **Fragments** — Pre-built components like banners, card grids, testimonials, and feature sections.
- **Widgets** — Functional apps like search bars, blogs displays, forms, and document lists.
- **Text** — A simple rich text editing element.
- **Images** — Upload or select an image.
- **Video** — Embed a video.
- **HTML** — Add custom HTML directly.
- **Collection Display** — Automatically display items from a collection (like a list of blog posts or web content articles) with pagination.
- **Content Display** — Display a single, specific piece of content.

#### Responsive Preview

Use the **viewport selector** in the toolbar to preview how your page looks on:
- **Desktop**
- **Tablet**
- **Mobile Phone**

You can also adjust element visibility and styles specifically for each breakpoint.

#### Mapping Content

Most fragment fields can be **mapped** to a content field. For example, a heading fragment's text can be mapped to the "Title" field of a web content article. When the article is updated, the heading on the page updates automatically. This is powerful for display page templates where the same layout needs to render many different pieces of content.

#### Experiences

For **Content Page Personalization**, you can create multiple **Experiences** of the same page — each tailored to a different audience **Segment**. For example:
- The **Default** experience shows to everyone not in a specific segment.
- A "New Visitors" experience might show a welcome banner.
- A "VIP Customers" experience might show exclusive offers.

When a visitor loads the page, the highest-priority experience for their segment is shown.

#### Undo and Redo

Every change you make in the editor can be undone. Use the **Undo** button in the toolbar, or keyboard shortcut, to step backward through your changes.

#### Saving and Publishing

- **Discard Draft** — Throw away all unsaved changes and return to the last published version.
- **Preview Draft** — Preview how the page will look before publishing.
- **Publish** — Make the current state of the page live for site visitors. (If workflow is enabled, this becomes **Submit for Workflow**.)

#### Walkthrough: Building a Content Page

**Goal:** Design a new "About Us" page with a hero banner, team grid, and a contact section.

1. Go to **Pages** in your site's administration menu
2. Click **Add** and choose **Content Page** as the type
3. Name the page "About Us" and configure navigation menu settings
4. Click **Add** to create it — you'll be taken directly into the **Content Page Editor**
5. In the **Fragments and Widgets** sidebar, browse the available fragments
6. Drag a **Banner** fragment onto the canvas for the hero section
7. Click the banner to select it, then use the **Item Configuration** panel to change the background image, edit the heading text, and update the call-to-action button
8. Below the banner, drag a **Card Grid** or **Team Members** fragment
9. Add your team members — click each card's image to replace it, and click the text to edit names and roles
10. Add a **Container** fragment at the bottom and place a contact information fragment inside it
11. Preview the page on tablet and mobile using the **viewport selector** in the toolbar
12. When satisfied, click **Publish**

---

### 3.3 Page Fragments
<!-- module: fragment-web, audience: [marketers, intranet-admins], capabilities: [fragment-sets, fragment-creation, fragment-preview, fragment-import-export, contributed-fragments, usage-tracking] -->

**Page Fragments** are the building blocks of content pages — pre-built visual components that page editors drag onto the canvas. They are managed in the **Fragments** administration section.

#### Fragment Sets

Fragments are organized into **Fragment Sets** — collections of related fragments. Liferay comes with a default set of fragments, and administrators can create custom sets to house fragments specific to your organization.

#### What Fragments Look Like

Each fragment can include:
- HTML markup that defines the structure
- CSS styles that control the appearance
- JavaScript for interactivity
- **Editable areas** — places where a page editor can change text, images, or links without touching the underlying code

#### Fragment Composition

You can select multiple elements on a page — say, a heading, an image, and a button — and **save them as a Fragment Composition**. This saves that combination as a reusable fragment in your fragment set, so your team can quickly apply the same layout on other pages.

#### Importing and Exporting Fragment Sets

Fragment sets can be exported as a ZIP file and imported into other sites or environments. This makes it easy to share design work across projects.

#### Walkthrough: Saving a Fragment Composition

**Goal:** Save a group of page elements as a reusable fragment.

1. Open a content page in the editor
2. Hold **Shift** and click to select multiple elements on the canvas (e.g., a heading, an image, and a button arranged together)
3. Right-click the selection and choose **Save Composition**
4. Name it (e.g., "Hero with CTA Button") and choose which **Fragment Set** to save it in
5. Click **Save**
6. The composition now appears in the **Fragments and Widgets** sidebar under your chosen set
7. Drag it onto any other page to reuse the same layout instantly

---

### 3.4 Style Books
<!-- module: style-book-web, audience: [intranet-admins], capabilities: [design-tokens, color-themes, typography, spacing, visual-consistency] -->

A **Style Book** defines the visual design language for a site — colors, fonts, spacing, and other design tokens. Think of it like a brand guideline translated into settings that every page on your site can use.

#### What Style Books Control

- **Colors** — Primary, secondary, accent, background, text, and link colors
- **Typography** — Font families, sizes, weights, and line heights for headings and body text
- **Spacing** — Margin and padding defaults, grid gaps
- **Borders** — Border radii, widths
- **Buttons** — Default button styles

#### How Style Books Work

1. Your administrator or designer creates a style book and sets values for each design token.
2. Pages are assigned to a style book (or inherit the site default).
3. Fragments and components on those pages automatically use the style book's values wherever they are referenced.
4. To change the look of your entire site, you update the style book — not every individual page.

If you change which style book a page uses, a warning lets you know that the new style book might not match the page's current design.

#### Walkthrough: Changing Your Site's Colors with a Style Book

**Goal:** Update your site's primary brand color across all pages.

1. Go to **Site Administration > Site Builder > Style Books**
2. Click **Add** to create a new style book, or click an existing one to edit it
3. In the style book editor, find the **Color System** section
4. Change the **Primary** color to your new brand color
5. The preview on the right updates in real time — check how buttons, links, and accents look
6. Adjust **Secondary** and **Success/Warning/Error** colors if needed
7. Click **Publish**
8. To apply it site-wide: go to **Site Settings > Design > Style Book** and select your new style book as the default
9. All pages using the default style book will update automatically

---

### 3.5 Navigation Menus
<!-- module: site-navigation-admin-web, audience: [intranet-admins, marketers], capabilities: [menu-creation, menu-items, hierarchy, page-links, url-links, submenus, display-widgets] -->

**Navigation Menus** are managed lists of links that you can add to pages so visitors can navigate your site. Unlike a simple links list, navigation menus are structured hierarchically and can update automatically when you add pages.

#### Menu Item Types

You can add the following types of items to a navigation menu:

| Type | Description |
|---|---|
| **Page** | Links to an existing page in your site |
| **Submenu** (Node) | A non-linking parent item that groups child items below it |
| **URL** | Links to any external or internal URL |
| **Display Page** | Links to a display page template |
| **Vocabulary** | Automatically generates menu items from a vocabulary's categories |

#### Auto-Updating Menus

When you create a new page, you can choose which navigation menus to automatically add it to. This means your navigation stays up to date without manual management.

#### Menu Types

Sites have predefined automatic menu types:
- **Primary Navigation** — The main navigation of the site
- **Secondary Navigation** — Sub-navigation or secondary links
- **Social Navigation** — Social media links or supplementary links

#### Navigation Widgets

You add navigation menus to pages using the **Navigation Menu** widget. Configure it to display a specific named menu and choose from display styles like a horizontal bar, vertical list, or styled menu.

Additional navigation-related widgets include:
- **Breadcrumb** — Shows the current page's path through the site hierarchy
- **Site Map** — Shows a visual map of all pages in the site
- **Language Selector** — Lets visitors switch the language of the site

#### Walkthrough: Setting Up Navigation

**Goal:** Create a main navigation menu for your site.

1. Go to **Site Administration > Site Builder > Navigation Menus**
2. Click **Add** and name your menu "Main Navigation"
3. The menu editor opens. Click **Add Item** to add your first item
4. Choose **Page** and select your homepage from the list
5. Repeat for each top-level page (About Us, Products, Blog, Contact)
6. To create a dropdown menu, add a **Submenu** item (called a "Node"), give it a label, and then drag existing items under it as children
7. To add an external link, choose **URL**, enter the link text and full URL
8. Save the menu
9. Now add the **Navigation Menu** widget to a master page's header area
10. Configure the widget to display the "Main Navigation" menu you just created
11. Publish the master page

---

## 4. Content Discovery and Aggregation
<!-- category: content-discovery, audience: [marketers, content-creators, intranet-admins], modules: [asset-publisher-web, asset-list-web, portal-search-web, content-dashboard-web] -->

### 4.1 Asset Publisher
<!-- module: asset-publisher-web, audience: [marketers, intranet-admins], capabilities: [dynamic-content-display, filtering, sorting, display-templates, manual-selection, related-assets] -->

The **Asset Publisher** is a powerful widget that automatically collects and displays content from across your site (or multiple sites) on a single page. Instead of hand-picking content to highlight, you define rules and it populates itself.

#### Dynamic vs. Manual Collections

**Dynamic** — You set filters and rules:
- Content type (web content, blog posts, documents, wiki pages, etc.)
- Categories or tags
- Author
- Date range
- Site or folder
- Any combination of the above

Content matching your rules appears automatically, including new content as it is created.

**Manual** — You hand-pick specific items. The widget shows exactly those items in the order you choose.

#### Display Options

You can control:
- How many items to show
- The order (newest first, alphabetical, by score)
- The display style (full content, abstract, title list, table, card)
- Whether to show item borders, author info, publication dates, and ratings

#### Common Uses

- A "Latest News" section on the homepage that automatically shows the 5 most recent web content articles in the "News" category
- A "Featured Documents" section that shows documents tagged "featured"
- A sidebar showing the current user's recently uploaded files

#### Walkthrough: Creating a "Latest News" Widget

**Goal:** Add a widget to your homepage that automatically shows the 5 most recent news articles.

1. Navigate to your homepage and click **Edit** to open the Content Page Editor
2. Drag the **Asset Publisher** widget from the **Fragments and Widgets** sidebar onto the page
3. Click the widget's **Configuration** (gear icon)
4. Under **Asset Selection**, choose **Dynamic**
5. Set **Asset Type** to **Web Content Article**
6. Under **Filter**, add a rule: **Category** → select "News"
7. Under **Display Settings**, set **Number of Items to Display** to 5
8. Set **Order By** to **Modified Date** (Descending)
9. Choose a **Display Template** (e.g., "Abstracts" for title + summary, or "Full Content" for complete articles)
10. Click **Save**, then **Publish** the page

New articles in the "News" category will automatically appear in this widget.

---

### 4.2 Collections
<!-- module: asset-list-web, audience: [marketers, content-creators], capabilities: [manual-collections, dynamic-collections, filtering-rules, personalized-variations] -->

**Collections** are saved, reusable lists of content items. They power the **Collection Display** fragment on content pages, which shows the items from a collection in a designed layout.

#### Dynamic Collection

A **Dynamic Collection** is defined by a set of filters and rules (similar to the Asset Publisher). It automatically includes all content matching those rules. As new content is created that matches, it's added automatically.

#### Manual Collection

A **Manual Collection** is a curated list where you hand-pick each item. The order is exactly as you set it.

#### Collection Providers

Beyond your own site's content, collections can be populated by **Collection Providers** — specialized sources like:
- "Most Viewed Assets"
- "Highest Rated Assets"
- "Related Assets" (content related to what's currently being viewed)
- Commerce product recommendations

#### Using a Collection on a Page

Once you have a collection, add a **Collection Display** fragment to a content page. Choose your collection, then design how each item looks using the fragment's editable fields and mapping. You can also add a **Collection Filter** fragment to let visitors filter the displayed items by category or keyword.

#### Walkthrough: Creating a Collection and Displaying It

**Goal:** Create a "Featured Articles" collection and display it as a card grid on a page.

1. Go to **Site Administration > Content > Collections**
2. Click **Add** → **Manual Collection**
3. Name it "Featured Articles"
4. Click **Select Items** → **Web Content Article**, then check the articles you want to feature
5. Arrange them in the order you want by dragging
6. Click **Save**
7. Now go to a content page and click **Edit**
8. Drag a **Collection Display** fragment onto the canvas
9. In the fragment configuration, click **Select Collection** and choose "Featured Articles"
10. Choose a layout style (grid, list) and configure how many columns
11. Map the fragment's editable fields (heading → Title, text → Description, image → Small Image)
12. Publish the page

---

### 4.3 Search
<!-- module: portal-search-web, audience: [all-users, intranet-admins], capabilities: [full-text-search, facets, suggestions, search-bar, search-results, search-configuration, synonyms, blueprints] -->

The **Search** tool lets site visitors find content across your site quickly. It is made up of several configurable widgets that work together.

#### Search Widgets

You typically place these on a dedicated search results page:

- **Search Bar** — Where users type their query. Can be placed anywhere on any page.
- **Search Results** — Displays matching content. Shows titles, snippets, content type, author, and date.
- **Sort** — Lets visitors sort results by relevance, title, modified date, etc.
- **Facets** — Filters that narrow down results. Available facets include:
  - **Category Facet** — Filter by content categories
  - **Tag Facet** — Filter by tags
  - **Type Facet** — Filter by content type (web content, documents, blogs, etc.)
  - **Site Facet** — Filter by site
  - **Modified Facet** — Filter by how recently content was modified
  - **Folder Facet** — Filter by folder
  - **User Facet** — Filter by content author

#### Search Features

- **Spell Check** — Suggests corrections for misspelled search terms
- **Suggestions** — Shows autocomplete suggestions as you type
- **Scope** — Search can be scoped to the current site, or expanded to search everything

#### Search Blueprints (Advanced)

Administrators can configure **Search Blueprints** to customize how search results are ranked, which fields are searched, and how results are boosted. For example, you could boost results from a specific category, or pin specific results to the top for certain queries.

#### Synonyms

Admins can define **synonym sets** — groups of words that should be treated as equivalent in search. For example, "car," "automobile," and "vehicle" could be synonyms so searching for any of them returns results mentioning all three.

#### Reindexing

When you make significant changes to content or configuration, your administrator may need to **Reindex** the search engine to ensure results are up-to-date. This process rebuilds the search index from scratch.

#### Walkthrough: Setting Up a Search Page

**Goal:** Create a dedicated search results page with filters.

1. Create a new **Widget Page** (or Content Page) named "Search"
2. Add the **Search Bar** widget — this is where users type their query
3. Below it, add the **Search Results** widget — this displays matching content
4. Add facet widgets to let users narrow results:
   - **Type Facet** — filter by content type (articles, documents, blogs)
   - **Category Facet** — filter by categories
   - **Tag Facet** — filter by tags
   - **Modified Facet** — filter by date range
   - **User Facet** — filter by author
5. Add a **Sort** widget so users can sort results by relevance, date, or title
6. Configure the **Search Bar** widget's scope (current site or all sites)
7. Publish the page
8. To add the search bar to every page, place a **Search Bar** widget in your master page's header and configure its **Destination Page** to point to your new search page

---

### 4.4 Content Dashboard
<!-- module: content-dashboard-web, audience: [content-creators, marketers, intranet-admins], capabilities: [content-overview, filtering, metrics, audit-graph, content-sharing, vocabulary-filtering] -->

The **Content Dashboard** is a centralized view where content managers can see and manage all content across multiple sites from a single screen.

#### What You Can See

- All web content articles, blog posts, documents, and other assets across all sites you have access to
- Each item's title, type, site, author, modification date, and current status (Draft, Pending, Approved, etc.)
- How many times each item is used (for example, how many pages display a particular web content article)

#### Filtering and Searching

Filter the content list by:
- **Author** — See only content from a specific person
- **Status** — Show only draft content, or only approved content
- **Content Type** — Filter by web content, blogs, documents, etc.
- **Site** — View content from a specific site
- **Categories and Vocabularies** — Filter by categorization

#### Downloading the List

Click **Download Spreadsheet** to export the current filtered view as a spreadsheet file. This is useful for content audits.

#### Content Performance

For sites connected to **Liferay Analytics Cloud**, each piece of content shows **Content Performance** metrics — page views, bounce rates, traffic sources, and audience data. This helps you understand which content is working and which needs improvement.

#### Content Audit (Categories)

The Content Dashboard includes a visual **Content Audit** tool — a chart view that shows how your content is distributed across your vocabulary categories. This helps identify content gaps — for example, you might see that most of your articles are tagged "Product Updates" but few are tagged "How-To," suggesting an area to invest in.

#### Walkthrough: Running a Content Audit

**Goal:** Identify content gaps across your sites.

1. Navigate to the **Content Dashboard** (via the Global Menu > Applications > Content Dashboard)
2. Use the filters to scope your view — select a specific **Site**, **Content Type**, or **Author**
3. Look at the **Content Audit** chart at the top — it shows how your content is distributed across vocabulary categories
4. Notice any categories with very few or zero articles — these are your content gaps
5. Click on a bar in the chart to filter the list below to only that category
6. Review the content list — check the **Status** column to find draft or expired articles that need attention
7. Click **Download Spreadsheet** to export the filtered list for your content planning meeting

---

## 5. Collaboration Tools
<!-- category: collaboration, audience: [all-users], modules: [questions-web, calendar-web, sharing-web, notifications-web, announcements-web] -->

### 5.1 Questions and Answers (Deprecated)
<!-- module: questions-web, audience: [intranet-users], capabilities: [ask-questions, answers, voting, tags, subscriptions, search], status: deprecated -->

> **Deprecation Notice:** The Questions widget is deprecated and will be removed in a future release. Existing Q&A content will continue to function, but no new enhancements are planned.

The **Questions** tool is a community Q&A platform — think Stack Overflow for your intranet or customer community. Users ask questions, others provide answers, and the community votes to surface the best answers.

#### How It Works

1. A user asks a **question** — giving it a title, body, and up to a set number of tags to describe the topic
2. Other users post **answers** to the question
3. Community members **vote** answers up or down
4. The question asker (or a moderator) can mark one answer as the **Best Answer** (also shown as **Accepted Answer**)
5. Questions are marked as **Answered** once a best answer is selected

#### Browsing Questions

- Browse questions by **topic** (tag-based categories) or by date
- Filter by answered/unanswered questions
- See your own questions and answers
- **Subscribe** to a topic to get notifications about new questions in that area

#### Voting

Users can upvote or downvote both questions and answers. The total vote count is visible next to each item. The answer with the most votes appears first (unless a best answer is pinned at the top).

---

### 5.2 Calendar
<!-- module: calendar-web, audience: [all-users], capabilities: [events, scheduling, reminders, rsvp, recurring-events, shared-calendars] -->

The **Calendar** lets users and teams track events, schedule meetings, and share schedules.

#### Key Features

- **Create events** — Set a title, location, description, start and end time, or mark as **All Day**
- **Recurring events** — Set events to repeat daily, weekly, monthly, or yearly
- **Multiple calendars** — Each user has personal calendars. Shared calendars can be created for teams, projects, or sites. **Calendar Resources** represent physical resources (like meeting rooms) that can be booked.
- **Color coding** — Assign different colors to different calendars for quick visual identification
- **Views** — Switch between day, week, month, and agenda views
- **Invite others** — Invite other users to events; they receive notifications and can accept or decline
- **Reminders** — Set email reminders to be sent before an event starts
- **Notification templates** — Customize the email notifications sent for calendar events

#### Walkthrough: Creating a Team Event

**Goal:** Schedule a recurring weekly team meeting and invite participants.

1. Go to a page with the **Calendar** widget, or navigate to the Calendar in your site
2. Click on a time slot in the calendar view, or click **Add Event**
3. Fill in:
   - **Title**: "Weekly Team Standup"
   - **Start/End Time**: e.g., Monday 9:00 AM – 9:30 AM
   - **Location**: "Conference Room B" or a video link
4. Check **Repeat** and configure: **Weekly**, every **1** week, on **Monday**
5. Under **Invitations**, search for and add your team members
6. Set a **Reminder** (e.g., 15 minutes before)
7. Click **Save**
8. Invited users receive a notification and can **Accept** or **Decline** from their Calendar

---

### 5.3 Sharing
<!-- module: sharing-web, audience: [all-users], capabilities: [share-content, collaborators, view-update-permissions, shared-with-me] -->

**Sharing** lets users share content items (documents, web content, and other assets) directly with other users — without needing to manage complex permissions.

#### How to Share

1. Open any document or content item you own or have permission to share
2. Click **Share**
3. Enter the email address or name of the person to share with
4. Choose their permission level:
   - **Viewer** — Can view (and download, for documents)
   - **Commenter** — Can view and leave comments
   - **Editor** — Can view, comment, and make edits

The recipient receives a notification with a link directly to the shared item. Shared items also appear in the recipient's **Shared with Me** list for easy access.

#### Share Link

You can also generate a **Share Link** — a URL that can be shared with anyone, granting them view access without requiring them to have an account (depending on your site's configuration).

#### Walkthrough: Sharing a Document with a Colleague

**Goal:** Share a contract PDF with a colleague who needs to review it.

1. Go to **Documents and Media** and find the document
2. Click the document's **Actions** menu → **Share**
3. Type your colleague's name or email in the **To** field
4. Select their permission: **Commenter** (so they can view and leave comments)
5. Optionally add a message explaining what you need them to review
6. Click **Share**
7. Your colleague receives a notification and can find the document in their **Shared with Me** section

---

### 5.4 Notifications
<!-- module: notifications-web, audience: [all-users], capabilities: [notification-list, mark-read, notification-delivery, email-notifications, website-notifications] -->

**Notifications** is the system that keeps you informed about activity happening around you in the platform.

#### Types of Notifications

You receive notifications for events like:
- Someone commenting on your content
- A workflow task being assigned to you
- Content you submitted being approved or rejected
- A blog you subscribe to publishing a new entry
- A document you follow being updated
- Someone replying to your forum post
- A new question posted in a Q&A topic you follow
- Workflow items reaching a review stage

#### Managing Notifications

Click the **Notifications** bell icon in the top navigation bar to see your notification inbox.

From there you can:
- **Mark as Read** — Dismiss a single notification
- **Mark All as Read** — Clear all unread notifications at once
- **Delete notifications** — Remove notifications you no longer need

#### Notification Delivery

Notifications can be delivered:
- **In-app** — Shown in the notifications bell
- **Email** — Sent to your registered email address
- **Push Notification** — Sent to a mobile device (if push notifications are configured)

You can control which events trigger notifications in your personal account settings.

#### Notification Templates (for Administrators)

Administrators can create and manage **Notification Templates** — these define the subject and body of notification emails for specific events. Templates can use dynamic variables to include the content name, author, date, and other relevant details automatically.

---

### 5.5 Announcements

**Announcements** is a simple tool for broadcasting site-wide or role-specific messages to users. Unlike a blog post or web content article, an announcement appears as a banner or alert on pages where the Announcements widget is placed.

Announcements can be targeted to specific user groups — for example, only administrators, or only members of a certain site.

---

## 6. Personalization and Targeting
<!-- category: personalization, audience: [marketers, intranet-admins], modules: [segments-web, segments-simulation-web, segments-experiment-web] -->

### 6.1 Segments
<!-- module: segments-web, audience: [marketers, intranet-admins], capabilities: [audience-segments, user-properties, organization-properties, session-properties, conditions-builder, preview] -->

**Segments** let you define groups of users based on their characteristics. Once you have segments, you can personalize pages, collections, and experiences to show different content to different groups.

#### What You Can Segment On

Segments are built using conditions based on:
- **User attributes** — job title, language, city, country, age, organizational memberships
- **Organization memberships** — which organizations a user belongs to
- **Role memberships** — which roles a user has been assigned
- **User group memberships** — which user groups the user is in
- **Session properties** — attributes about the user's current visit (such as device type, referral URL, or time of day)

> **Note:** For more advanced behavioral segments based on browsing history, purchase history, and engagement patterns, your instance needs to be connected to **Liferay Analytics Cloud**. The platform will show a note if this capability requires Analytics Cloud.

#### Creating a Segment

1. Go to **Segments** in your site's menu
2. Click **Add** to create a new segment
3. Give the segment a descriptive name (e.g., "Spanish-speaking users," "Enterprise customers")
4. Build your criteria by adding conditions and combining them with AND/OR logic
5. Use the **Preview** feature to see a list of current users who match your criteria
6. Save the segment

Once saved, the segment is available for use in personalized experiences and content collections.

#### Walkthrough: Segmenting Your Audience

**Goal:** Create an audience segment for "Spanish-speaking users" and show them a personalized page banner.

1. Go to **Site Administration > People > Segments**
2. Click **Add**
3. Name the segment "Spanish-speaking Users"
4. In the conditions builder, add a condition:
   - Property: **User** → **Language**
   - Condition: **equals**
   - Value: **Spanish (Spain)** (or the specific locale you want to target)
5. Use the **Preview** button to see which users currently match
6. Save the segment
7. Now go to the page you want to personalize in the content page editor
8. Click the **Experience Selector** (shows "Default") in the toolbar
9. Click **New Experience**
10. Name it "Spanish Speakers" and select the segment "Spanish-speaking Users"
11. Set its priority above the default
12. In this experience, edit the hero banner to show Spanish-language text or different imagery
13. Publish

Spanish-speaking users will now see the personalized version when they visit that page.

---

### 6.2 Experiences

**Experiences** are variations of a content page that are shown to specific audience segments. Every content page has at least one experience — the **Default** experience, which is shown to everyone not in a more specific segment.

#### Creating an Experience

1. Open a content page in the editor
2. Click the **Experience Selector** (shows "Default" by default) in the toolbar
3. Click **New Experience**
4. Name the experience and choose which **Segment** it applies to
5. The page editor switches to that experience — make your changes (add sections, change content, hide elements)
6. Publish

When a visitor loads the page, Liferay checks which segment they belong to and shows the highest-ranked experience that matches.

Experiences can be ranked to control priority when a user belongs to multiple segments.

#### Walkthrough: Creating a Personalized Experience

**Goal:** Show a different hero banner to users in the "Enterprise Customers" segment.

1. Open a content page in the editor
2. Click the **Experience Selector** in the toolbar (shows "Default")
3. Click **New Experience**
4. Name it "Enterprise Welcome" and select the **Enterprise Customers** segment
5. Set its priority above Default (drag it higher in the list)
6. The editor now shows the "Enterprise Welcome" experience — make your changes: swap the hero banner image, change the headline text to "Welcome back, valued partner"
7. Switch back to "Default" using the selector to confirm the original page is unchanged
8. Click **Publish** — both experiences are saved

Enterprise customers now see the personalized banner; everyone else sees the default.

---

### 6.3 A/B Testing

**A/B Testing** lets you test two versions of a content page to see which one performs better, using real visitor data to make the decision scientifically.

> A/B testing requires a connection to **Liferay Analytics Cloud**.

#### Setting Up a Test

1. Open a content page in the editor
2. Click **Test** in the toolbar
3. Create a new test and name it
4. You start with a **Control** variant (the current page) and create one or more additional **Variants** with changes
5. Set the **Traffic Split** — what percentage of visitors see each variant (e.g., 50/50)
6. Set the **Confidence Level** — how statistically certain you want to be before declaring a winner (e.g., 95%)
7. Choose a **Goal** — what action you want to measure (like clicking a specific button, or viewing a specific page)
8. **Run Test**

#### Reviewing Results

Once the test has collected enough data to reach your confidence level, you can:
- Declare a **Winning Variant** — make that version the permanent page
- **Discard the test** — keep the original

#### Walkthrough: Running an A/B Test on a Call-to-Action Button

**Goal:** Test whether changing a button label from "Learn More" to "Get Started Free" increases clicks.

1. Open the content page in the editor
2. Click **Test** in the toolbar → **Create Test**
3. Name it "CTA Button Test"
4. The current page is your **Control** variant
5. Click **Create Variant** and name it "Get Started Free"
6. In the variant, change the button text from "Learn More" to "Get Started Free"
7. Set **Traffic Split** to 50/50
8. Set **Confidence Level** to 95%
9. Set the **Goal** to clicking that button
10. Click **Run Test**
11. Wait for enough traffic to reach statistical significance — check the test results periodically
12. When a winner is declared, choose to **Publish** the winning variant or discard the test

---

## 7. Forms
<!-- category: forms, module: dynamic-data-mapping-form-web, audience: [intranet-admins, marketers], capabilities: [form-builder, field-types, validation-rules, success-pages, form-entries, email-notifications, form-rules] -->

**Forms** is a tool for creating data-collection forms — surveys, registration forms, contact forms, feedback forms, quizzes, and more. No coding is needed.

#### Building a Form

The form builder uses a drag-and-drop interface. Available field types include:

| Field Type | Description |
|---|---|
| **Text** | Single-line or multi-line text input |
| **Numeric** | Numbers only (integer or decimal) |
| **Date** | Date picker |
| **Date and Time** | Date and time picker |
| **Select from List** | Dropdown list of options |
| **Single Selection** | Radio buttons — pick one option |
| **Multiple Selection** | Checkboxes — pick multiple options |
| **Grid** | A matrix of options (rows and columns) |
| **Rich Text** | A formatted text area with a text editor |
| **Paragraph** | Static text shown to the user (not a data field) |
| **Separator** | A visual dividing line |
| **Image** | Upload an image |
| **Upload** | Upload any document or file |
| **Color** | Color picker |
| **Geolocation** | Select a location on a map |

#### Field Settings

For each field you can:
- Set the **label** (what the user sees)
- Add **help text** (a hint below the field)
- Mark as **Required** so the form cannot be submitted without filling it in
- Add **validation rules** (e.g., minimum length, pattern matching, numeric range)
- Configure **placeholder text** shown inside the field before the user types
- Apply a **mask** (for fields like phone numbers or date formats)

#### Multi-Page Forms

Long forms can be split across multiple **pages** with a **Next** button to step through them. A progress indicator can show users how far through the form they are.

#### Rules

Form **Rules** create conditional logic:
- **Show/hide fields** based on what the user selected in another field
- **Enable/disable fields** based on conditions
- **Require a field** only when specific conditions are true
- **Jump to a page** based on answers (branching/skip logic)
- **Auto-fill a field** using a **Data Provider** (a connected external data source)

#### Field Groups (Element Sets)

Frequently reused combinations of fields can be saved as a **Field Group** (also called an **Element Set**). For example, a standard "Address" group with street, city, state, and zip. You can then drag that entire group onto any form without recreating it each time.

#### Success Page

Configure what happens after a user submits the form:
- Show a custom **success message**
- **Redirect** to a specific page

#### Viewing Form Entries

Submitted form data appears in the **Entries** tab of the form. You can view individual submissions, filter and sort them, and **export** them to a spreadsheet.

#### Workflow Integration

Forms can be connected to a workflow so each submission is reviewed and approved by someone before being recorded.

#### Walkthrough: Creating a Form

**Goal:** Build a "Contact Us" form with name, email, message, and department selection.

1. Go to **Content > Forms** in your site administration
2. Click **Add** to create a new form
3. Name it "Contact Us"
4. In the form builder, drag a **Text** field onto the canvas
5. Set the label to "Full Name" and mark it **Required**
6. Drag another **Text** field, set label to "Email Address," mark Required, and add an email validation rule
7. Drag a **Select from List** field, label it "Department," and add options: "Sales," "Support," "General Inquiry"
8. Drag a **Text** field with multi-line enabled, label it "Message," and mark Required
9. Configure the **Success Page** — write a thank-you message
10. Click **Publish** to activate the form
11. Add the **Form** widget to a page in your site, configure it to point to "Contact Us," and publish the page
12. To view submissions, return to the form in the administration and click the **Entries** tab

---

## 8. Commerce
<!-- category: commerce, audience: [commerce-managers, intranet-admins], modules: [commerce-product-content-web, commerce-checkout-web, commerce-cart-content-web, commerce-order-web, commerce-catalog-web, commerce-pricing-web, commerce-discount-web, commerce-inventory-web, commerce-account-web, commerce-wish-list-web] -->

Liferay DXP includes a full **Commerce** platform for running online stores, B2B portals, and product catalogs. Commerce integrates tightly with the rest of the platform, meaning your product pages are content pages with full design control.

### 8.1 Products and Catalogs

#### Catalogs

A **Catalog** is a collection of products. In B2B scenarios, different buyers (accounts) may have access to different catalogs. A catalog is linked to a **Channel** (storefront), and each channel can display one or more catalogs.

#### Product Types

| Type | Description |
|---|---|
| **Simple** | A physical product with one or more SKUs (variants) |
| **Grouped** | A bundle of multiple simple products sold together |
| **Virtual** | A downloadable product (file download, license key, streaming link) |

#### SKUs

A **SKU** (Stock Keeping Unit) represents a specific variant of a product. For example, a T-shirt product might have SKUs for each size-color combination: "Medium-Blue," "Large-Red," etc. Each SKU can have its own:
- Price
- Inventory level
- Availability dates (available from/until)

#### Product Details

When creating or editing a product, you configure:
- **Name and Description** — What customers see
- **Categories** — How the product is classified and navigated
- **Media** — Product images and attachments
- **Pricing** — Base price, sale price, tier pricing
- **Inventory** — Stock levels per warehouse
- **Subscriptions** — Whether the product can be purchased as a subscription (weekly, monthly, yearly delivery)
- **Visibility** — Published or unpublished, availability window
- **Related Products** — Cross-sell and upsell suggestions

#### Walkthrough: Adding a Product to Your Catalog

**Goal:** Add a new product with two size variants.

1. Go to **Commerce > Products**
2. Click **Add** → **Simple Product**
3. Enter the **Name** (e.g., "Classic T-Shirt") and **Description**
4. Select the **Catalog** this product belongs to
5. Click **Submit**
6. On the product detail page, go to the **SKUs** tab
7. Click **Add SKU** — enter SKU name "Classic T-Shirt - Medium", set the **Price** (e.g., $24.99)
8. Add another SKU: "Classic T-Shirt - Large" at $24.99
9. Go to the **Media** tab and upload product images
10. Go to the **Categories** tab and assign the product to "Clothing > T-Shirts"
11. Set the product status to **Published**
12. The product is now visible in your storefront

### 8.2 Orders and Checkout

#### The Shopping Experience

Customers browse products in the **catalog**, add items to the **Cart**, and proceed through **Checkout**.

#### Checkout Steps

The checkout process typically includes:
- Billing address
- Shipping address
- Shipping method selection (with estimated costs and delivery times)
- Order summary review
- Payment
- Order confirmation

#### Order Management

Administrators and order managers see all orders in the **Orders** administration section. Each order tracks:
- Order status (Open, Pending, Processing, Shipped, Completed, Cancelled)
- Payment status
- Shipment status
- Billing and shipping addresses
- Order items and quantities
- Order notes

#### Order Approval

Orders can be configured to require internal approval before processing (for B2B scenarios where an account manager reviews orders before fulfillment).

### 8.3 Pricing and Discounts

#### Price Lists

A **Price List** defines a set of prices for products. Different buyers or segments can be assigned different price lists, enabling tiered pricing for B2B:
- A standard retail price list
- A wholesale price list for distributor accounts
- A VIP customer price list

Price lists support **tiered pricing** — discounts for buying in quantity (e.g., 1-9 units at full price, 10-49 at 10% off, 50+ at 20% off).

#### Discounts

**Discounts** are promotional reductions that can be:
- A percentage off or a fixed dollar amount
- Applied to specific products, categories, or the entire order
- Triggered by coupon codes or automatically applied
- Restricted to specific account groups, order amounts, or date ranges

### 8.4 Inventory and Warehouses

#### Warehouses

**Warehouses** represent physical locations where inventory is stored. Each SKU can have inventory tracked per warehouse.

#### Inventory Management

From the **Inventory** admin, you can:
- View stock levels per SKU per warehouse
- Adjust inventory (add stock, record losses)
- Set **Safety Stock** (reserve a buffer so the product shows as "out of stock" before it truly is)
- Set **Backorder Allowed** (continue selling even when stock reaches zero, fulfilling when restocked)
- View **Replenishment** records (incoming stock)

### 8.5 Commerce Accounts

**Commerce Accounts** are buyer profiles for B2B commerce. Unlike standard site users, commerce accounts represent companies or organizations that purchase from you.

- **Account Groups** — Organize accounts into groups for applying pricing rules, discounts, or catalog visibility
- **Account Roles** — Control what different members of an account can do (e.g., an "Account Administrator" can manage users; an "Account Order Manager" can place orders)
- **Account Users** — The individual people associated with the account

---

## 9. Workflow and Approvals
<!-- category: workflow, modules: [portal-workflow-web, portal-workflow-task-web, portal-workflow-instance-tracker-web], audience: [content-creators, intranet-admins], capabilities: [workflow-definitions, approval-processes, task-assignment, workflow-configuration, workflow-instances, workflow-logs] -->

**Workflow** is the system for routing content through an approval process before it is published. Instead of content going live the moment it is saved, it first passes through one or more review steps.

### The Basics

A **Workflow Definition** describes the approval process — the steps, who approves at each step, and what happens when content is approved or rejected.

The built-in **Single Approver** workflow is the simplest: content goes from Draft to Pending review, one approver reviews it, and approves (it becomes Published) or rejects it (it returns to the author with comments).

### What Can Use Workflow

Almost any content type can be connected to a workflow:
- Web content articles
- Documents
- Blog posts
- Knowledge base articles
- Wiki pages
- Form entries
- Custom object entries
- Pages

An administrator configures which workflow applies to each content type in the site's settings.

### The Content Creator Experience

When workflow is active:
1. You create or edit your content and click **Submit for Workflow** (instead of Publish)
2. The content status changes to **Pending**
3. You can no longer edit the content until it is reviewed
4. If rejected, you receive a notification with the reviewer's comments, the status returns to draft, and you can make changes and resubmit

### The Reviewer Experience

When content is submitted to you for review:
1. You receive a notification in your **My Workflow Tasks** section
2. Click the notification or navigate to **My Workflow Tasks**
3. You see a list of all tasks **Assigned to Me** and tasks in **My Roles** (tasks you could claim)
4. **Assign to Myself** to take ownership of a task from a role queue
5. Review the content — you can click **View Diffs** to see what changed compared to the previous version, or **Edit** to make changes yourself
6. Add a **Comment** to explain your decision
7. Choose a transition:
   - **Approve** — The content moves to the next step or is published
   - **Reject** — The content is returned to the author with your comments

### Due Dates

Tasks can have **Due Dates** set by administrators or other workflow nodes. Overdue tasks are highlighted in the task list.

### Workflow Logs

Every action in the workflow (submission, approval, rejection, reassignment) is recorded in the **Workflow Log**, visible on the task detail page.

#### Walkthrough: Reviewing and Approving Content

**Goal:** As a content reviewer, review and approve a submitted article.

1. Look for the **notifications bell** in the top navigation bar — a badge indicates pending workflow tasks
2. Click the bell and click the notification about a pending workflow task, or navigate directly to **My Account > My Workflow Tasks**
3. Under **Assigned to Me** (tasks you've claimed) or **Assigned to My Roles** (tasks your role can handle), find the pending content item
4. If the task is under **Assigned to My Roles**, click **Assign to Me** to take ownership
5. Click on the task to open the task detail view
6. Review the content:
   - Click the content title to read it in context
   - Click **View Diffs** if available to see changes compared to the previous version
   - Click **Edit** to make minor corrections yourself
7. Add a **Comment** if needed to explain your decision
8. Choose an action:
   - Click **Approve** to publish the content (or move it to the next workflow step)
   - Click **Reject** to send it back to the author — they will be notified

---

## 10. Site and User Administration
<!-- category: administration, audience: [intranet-admins], modules: [site-admin-web, users-admin-web, roles-admin-web, account-admin-web] -->

### 10.1 Site Settings

Site administrators configure their sites through **Site Settings**, accessible from the site's administration menu.

#### Key Settings Areas

**General**
- Site name and description (with translations for each language)
- Site friendly URL (the path that appears in the browser address bar)
- Custom fields

**Membership**
- **Open site** — Anyone can join
- **Restricted site** — Users can request to join; administrators approve
- **Private site** — Users must be invited; the site is not discoverable

**Languages**
- Define which languages are available for this site
- Set the default language

**Virtual Hosts**
- Map a custom domain name (e.g., `store.yourcompany.com`) to your site

**Search Engine Optimization**
- Canonical URL strategy
- Open Graph settings
- Hreflang for multilingual sites
- Google PageSpeed Insights integration

**Analytics**
- Connect the site to Liferay Analytics Cloud

**Staging**
- Enable local or remote staging (see [Section 11.1](#111-staging))

### 10.2 Users and Organizations

#### Users

From **Users** in the Control Panel, administrators can:

- **View all users** — Search, filter, and browse the full list
- **Add a user** — Create a new account with email address, screen name, first/last name, gender, birthday
- **Edit a user** — Update profile info, contact information, organizations, user groups, roles, and site memberships
- **Deactivate a user** — Disable the account without deleting data. The user cannot log in while deactivated.
- **Delete a user** — Permanently remove the account (requires deactivating first)
- **Impersonate a user** — Log in as another user to troubleshoot issues they are experiencing. The session is marked as impersonated.
- **Reset a password** — Send a password reset email
- **Export users** — Download user data

#### Organizations

**Organizations** model your company's hierarchy — divisions, departments, subsidiaries, or geographic regions. Each organization can have its own:
- Name and type
- Contact information and addresses
- Opening hours
- Parent organization (for hierarchies)
- Site (each organization can have its own organization site)
- Members (users can belong to multiple organizations)
- Roles assigned to members within that organization

Organizations help scope permissions — a user with an "Organization Administrator" role in the "Marketing" organization has admin powers only within Marketing's scope.

#### Walkthrough: Managing Users

**Goal:** Create a new user account, assign them to a department, and give them a specific role.

1. Go to **Control Panel > Users and Organizations > Users**
2. Click **Add User**
3. Enter the user's:
   - **Screen Name** (their login username)
   - **Email Address**
   - **First Name** and **Last Name**
4. Click **Save**
5. The user record opens with multiple sections. Go to **Password** and set an initial password
6. Go to **Organizations** and click **Select** to assign them to the appropriate organization (department)
7. Go to **Roles** and assign any regular roles they need (e.g., "Power User")
8. Go to **Sites** to assign them to the appropriate site with a site role if needed (e.g., assign them to the "Marketing" site with the "Content Creator" role)
9. Click **Save**

The user will receive an email (if configured) with instructions to complete their account setup.

### 10.3 Roles and Permissions

**Roles** are named collections of permissions. Assign a role to a user and they gain all the permissions that role includes.

#### Role Types

| Type | Scope |
|---|---|
| **Regular Roles** | Platform-wide (e.g., "Power User," "Administrator") |
| **Site Roles** | Scoped to a specific site (e.g., "Site Administrator," "Site Member") |
| **Organization Roles** | Scoped to an organization (e.g., "Organization Administrator") |
| **Asset Library Roles** | Scoped to an asset library |
| **Account Roles** | Scoped to a commerce account (e.g., "Account Administrator," "Account Order Manager") |

#### Permissions

Permissions are fine-grained controls attached to resources. For example, for Web Content:
- **Add Web Content** — Can create new articles
- **Approve Content** — Can approve articles in workflow
- **Delete** — Can delete articles
- **Permissions** — Can change permissions on articles
- **Update** — Can edit articles
- **View** — Can view articles

Most content and tools have a comparable set of permissions. The **Guest** role (unauthenticated visitors) typically only has "View" permission on public content.

#### Walkthrough: Creating a Custom "Content Editor" Role

**Goal:** Create a site role that lets users create and edit web content but not delete or manage permissions.

1. Go to **Control Panel > Roles**
2. Click **Add** → choose **Site Role**
3. Name it "Content Editor" and add a description
4. Click **Save**
5. Go to the **Define Permissions** tab
6. In the permission tree, expand **Site Administration > Content > Web Content**
7. Check: **Add Web Content**, **Update**, **View**
8. Leave unchecked: **Delete**, **Permissions**
9. Expand **Site Administration > Content > Documents and Media** and grant **Add Document**, **Update**, **View**
10. Click **Save**
11. To assign the role: go to **Site Administration > Members > Site Memberships**, find the user, and assign the "Content Editor" role

### 10.4 Account Management

**Account Management** lets users manage their own information, and gives administrators tools to manage business accounts for B2B use cases.

#### My Account (Personal Settings)

Users access their personal settings by clicking their profile picture:

- **Account Information** — Change name, profile picture, time zone, and language preferences
- **Password** — Change your password
- **Email Notifications** — Choose which events trigger email notifications
- **Multi-Factor Authentication** — Set up TOTP (authenticator app), email OTP, or FIDO2 (security key/biometric) for extra security
- **Contact Information** — Phone numbers, addresses, social media profiles

#### Commerce Accounts (Business Accounts)

Administrators manage business buyer accounts from the **Accounts** section. Each account has:
- Account name, type (business or personal), tax ID, address, and email domains
- Account users (buyers belonging to the account)
- Account groups
- Account roles assigned to users
- Order history

### 10.5 Password Policies
<!-- module: password-policies-admin-web, audience: [intranet-admins], capabilities: [password-rules, expiration, history, lockout] -->

**Password Policies** let administrators define rules for user passwords across the platform. A password policy controls how strong passwords must be, how often they expire, and what happens after failed login attempts.

#### What You Can Configure

| Setting | What It Does |
|---|---|
| **Minimum Length** | Require passwords to be at least a certain number of characters |
| **Character Requirements** | Require uppercase, lowercase, numbers, or special characters |
| **Password History** | Prevent users from reusing their last N passwords |
| **Maximum Age** | Force passwords to expire after a set number of days |
| **Minimum Age** | Prevent users from changing their password too frequently |
| **Lockout** | Lock an account after a configured number of failed login attempts |
| **Lockout Duration** | How long an account stays locked before automatically unlocking |
| **Grace Limit** | Allow a set number of logins after a password expires before forcing a change |
| **Reset Failure Count** | How long before the failed login counter resets |

#### Managing Password Policies

1. Go to **Control Panel > Security > Password Policies**
2. The **Default Password Policy** applies to all users unless overridden
3. Create additional policies for specific user groups or organizations
4. Assign policies to users, organizations, or user groups

### 10.6 Accessibility
<!-- module: accessibility-menu-web, audience: [all-users], capabilities: [accessibility-settings, underline-links, high-contrast] -->

Liferay DXP includes built-in **accessibility** features that help users with visual or motor impairments navigate the platform more easily.

The **Accessibility Menu** provides quick-access settings that users can toggle:

- **Underlined Links** — Make all hyperlinks visually distinct by underlining them
- **High Contrast** — Switch to a high-contrast color scheme for better readability

These settings are stored per-user, so each person can configure them for their own needs without affecting other users. Administrators can also configure accessibility defaults at the site or instance level.

### 10.7 Administration Quick Reference
<!-- audience: [intranet-admins], type: administration-reference -->

This subsection covers common administrative tasks that intranet administrators perform day-to-day.

#### Setting Up a New Site

1. Go to **Control Panel > Sites > Sites**
2. Click **Add** — choose to start **Blank Site** or from a **Site Template**
3. Configure the site:
   - **Name** — The display name of the site
   - **Membership Type** — Open, Restricted, or Private
   - **Virtual Host** — If this site has a custom domain
   - **Languages** — Available languages and the default
4. Add pages using the **Pages Administration** section
5. Configure site settings including SEO defaults, analytics, and notifications
6. Set up navigation menus
7. Add content

#### Managing Permissions

The permission system works in layers:
1. **Instance-level permissions** — Controlled by the global Administrator role
2. **Site-level permissions** — Controlled by Site Administrator and custom site roles
3. **Individual item permissions** — Can be set on each content item

**Best practice:** Create named roles for your common user types (e.g., "Content Editor," "Section Manager"), define what each role can do, and assign users to those roles. Avoid assigning permissions to individual users directly.

**Granting a user content editing rights for a site:**
1. Go to **Site Administration > Members > Site Memberships**
2. Find the user and assign them the "Content Creator" or relevant site role
3. Or create a custom site role with exactly the permissions your editors need

#### Configuring Workflow

1. Go to **Site Administration > Process Builder > Workflows**
2. Click **Add** to create a new workflow, or use the built-in "Single Approver" workflow
3. To assign workflow to content types: go to **Site Administration > Process Builder > Configuration**
4. For each content type that requires review (Web Content, Blogs, Documents, etc.), select the appropriate workflow from the dropdown
5. Save

Now when anyone creates content of that type on the site, it must go through the selected workflow before publishing.

#### Setting Up Staging or Publications

**Enabling Publications (Recommended):**
1. Go to **Control Panel > Instance Settings > Publications**
2. Enable Publications for your instance
3. Users can now create publications from the **Publications** button at the top of the screen

**Enabling Local Staging:**
1. Go to **Site Administration > Publishing > Staging**
2. Click **Local Live** to enable local staging
3. Configure what content types should be staged
4. Click **Save** — a staging environment is created

#### Configuring Email Notifications

1. Go to **Control Panel > System Settings > Email**
2. Set up the **Mail** configuration (SMTP server, from address, display name)
3. Test by sending a test email
4. Configure site-specific notification settings under **Site Administration > Configuration > Notifications**

#### Managing Site Templates

1. Go to **Control Panel > Sites > Site Templates**
2. Create a site template with pre-configured pages, content, and settings
3. Allow site administrators to create sites from this template by checking the appropriate option
4. Optionally, configure whether site admins can modify pages from the template, or if template pages should propagate updates automatically

#### Configuring Search

1. Go to **Control Panel > Search**
2. Connect to your search engine (Elasticsearch or OpenSearch) under **Connections**
3. Perform an **Index Actions** reindex if needed after major content changes
4. Configure **Synonym Sets** for important search terms your users commonly use

#### Managing the Recycle Bin

1. Go to **Control Panel > Instance Settings > Trash**
2. Set the **Trash Entries Max Age** (in days) — items older than this are deleted permanently from the Recycle Bin
3. You can also disable the Recycle Bin entirely if you prefer immediate deletion

#### Bulk User Import

1. Go to **Control Panel > Users and Organizations > Users**
2. Click **Actions** → **Export Users** to get the current user list as a template CSV
3. Prepare your import file with the user data
4. Go to **Control Panel > Apps > Data Migration Center**
5. Select **Users** as the import type, upload your file, map fields, and run the import

#### System Health and Monitoring

- **Search > Index Actions** — Reindex content if search results seem outdated
- **System Settings** — Fine-tune platform behavior
- **Gogo Shell** (for technical admins) — Command-line access for diagnostics
- **Server Administration** — Log viewer, portal properties, and system cleanup tools

---

## 11. Content Operations
<!-- category: content-operations, audience: [intranet-admins, content-creators], modules: [staging-web, change-tracking-web, translation-web, export-import-web, depot-web, object-web] -->

### 11.1 Staging

**Staging** creates a parallel copy of your site where you can make and test changes before publishing them to the live site. Staging is an older, site-level approach to change management (the newer approach is **Publications**, described in Section 11.2).

#### Local Staging

With **Local Staging**, a staging site is created on the same server. You make changes on the staging site, and when ready, you **Publish** to push those changes to the live site.

**Page Variations** — Within local staging, you can create named variations of the entire page set, allowing parallel work on different versions of the site.

**Site Pages Variation** — Manage independent sets of page variants within a staging environment.

#### Remote Staging

With **Remote Staging**, your staging environment is on a separate server from your live site. Publishing sends changes over the network to the live server.

#### Publishing

When you publish from staging, you can choose what to include:
- All changes or specific pages
- All content types or specific ones
- Permission changes
- Whether to include page deletions

A **Publication Process** log shows the progress and results of each publish operation.

#### Walkthrough: Publishing a Staged Site

**Goal:** You've been making changes on the staging site. Publish them to live.

1. Make sure you are on the **Staging** site (the staging toolbar at the top of the page confirms this)
2. Review your changes — navigate through the pages you edited
3. Click the **Publish to Live** button in the staging toolbar, or go to **Publishing > Publish to Live**
4. In the publish configuration screen:
   - Choose to publish **All** content or select specific pages/types
   - Choose whether to include permissions
   - Schedule it for a specific time if you don't want it to go live immediately, or select **Now**
5. Click **Publish**
6. Monitor the progress — the publish log shows what is being processed
7. Once complete, navigate to your live site to verify the changes are visible

### 11.2 Publications

**Publications** is the modern approach to change management — it creates sandboxes for individual sets of changes rather than whole-site staging. Think of a publication like a branch in a version control system.

> Publications and Staging cannot be used together on the same site.

#### How Publications Work

1. **Create a Publication** — Give it a name (e.g., "March Homepage Redesign") and optionally a description
2. **Make your changes** — Edit pages, update content, change settings. All changes are tracked inside this publication's sandbox.
3. **Invite collaborators** — Add other team members to the publication so they can contribute changes
4. **Review changes** — See a summary of everything that changed in this publication
5. **Publish** — When ready, publish to make all the changes go live at once

#### Conflict Detection

If someone else has published changes to the same content in the meantime, Publications will flag conflicts and help you resolve them before publishing.

#### Walkthrough: Using Publications

**Goal:** Make a set of homepage changes and publish them all at once.

1. Click the **Publications** button in the top navigation bar
2. Click **Create New Publication**
3. Name it "March Homepage Refresh" and add a description
4. Click **Create** — you are now working inside the publication
5. Navigate to your homepage and edit it — change the hero banner, update text, add a new section
6. Edit a web content article that appears on the homepage
7. All your changes are tracked inside "March Homepage Refresh" — nothing is live yet
8. Click the **Publications** button again → **Review Changes** to see a summary of everything you changed
9. When ready, click **Publish**
10. All changes go live simultaneously

#### Publication Templates

If you frequently make similar types of changes, create a **Publication Template** so new publications start with a standard name, description, and set of collaborators.

#### Publications Workflow

Publications can be connected to workflow for governance — requiring approval before changes are published to production.

### 11.3 Translation

**Translation** tools let you create translated versions of your content without manually typing in each translation.

#### Exporting for Translation

1. Select one or more pieces of content (web content articles, content pages)
2. Choose **Export for Translation**
3. Select the **original language** and the **target languages**
4. Choose the file format (XLIFF 1.2 or XLIFF 2.1)
5. For content pages with multiple experiences, choose whether to export just the default experience or all experiences
6. Download the XLIFF file(s) — one file per target language

XLIFF is a standard translation file format that professional translators and translation tools (CAT tools) understand. Send the files to your translators.

#### Importing Translations

1. Receive the completed XLIFF files from your translators
2. Navigate to the content item
3. Choose **Import Translation**
4. Upload the XLIFF file
5. Review the import results

The translated fields are automatically applied to the correct language version of the content.

#### Auto-Translate

If your administrator has connected a translation service (like **Microsoft Translator**), you can use **Auto Translate** to automatically translate individual fields using machine translation. This is useful for a first draft, though human review is recommended.

#### Marking as Translated

Once a language version is complete, you can mark all fields for that language as **Translated**. This status helps your team track translation progress.

#### Walkthrough: Translating Content

**Goal:** Translate a web content article from English to Spanish.

1. Open the article you want to translate in the Web Content editor
2. Click **Actions** (or the three-dot menu) → **Export for Translation**
3. In the export dialog:
   - **Export File Format**: Choose XLIFF 2.1 (recommended)
   - **Original Language**: English (or whatever your default language is)
   - **Languages to Translate To**: Check "Spanish"
4. Click **Export** and download the XLIFF file
5. Send the XLIFF file to your translator or translation service
6. When the translated file is returned, open the article again
7. Click **Actions** → **Import Translation**
8. Upload the translated XLIFF file
9. Review the import results — the Spanish fields are now populated
10. In the article editor, switch to **Spanish** using the language selector at the top
11. Review and make any corrections to the Spanish content
12. Publish the article — it will display in Spanish to users whose browser language or site language is set to Spanish

### 11.4 Export and Import

**Export/Import** lets you move site content between different Liferay environments — for example, exporting content from a staging server and importing it into production, or migrating content between sites.

#### What Can Be Exported/Imported

- Pages and their configurations
- Web content articles
- Documents and media
- Blog entries
- Wiki pages
- Message board categories and threads
- Form definitions
- Portlet/widget data and configurations
- Categories, tags, and permissions

#### Export Process

1. Go to **Site Administration > Publishing > Export**
2. Choose what to include (all pages, specific pages, specific applications)
3. Set date range filters if needed
4. Click **Export**
5. Download the resulting `.lar` (Liferay Archive) file

#### Import Process

1. Go to **Site Administration > Publishing > Import**
2. Upload the `.lar` file
3. Review what will be imported and configure any import settings
4. Click **Import**

#### Walkthrough: Exporting a Site for Backup

**Goal:** Create a backup of your site's content.

1. Go to **Site Administration > Publishing > Export**
2. Click **Add** to create a new export
3. Give the export a name (e.g., "Marketing Site Backup - March 2026")
4. Under **Pages**, select **All Pages** or choose specific ones
5. Under **Content**, check the types to include: Web Content, Documents, Blogs, etc.
6. Click **Export**
7. When the export completes, click the download icon to save the `.lar` file
8. Store the `.lar` file safely — you can import it into another environment or restore it later

### 11.5 Asset Libraries

**Asset Libraries** (also called **Depots**) are shared content repositories that are not tied to a specific site. Instead, they connect to multiple sites and make their content available across all of them.

#### Why Use an Asset Library?

Imagine your company has 5 different regional websites. All of them use the same brand images, company logo, product photos, and shared web content articles. Without an asset library, you'd need to upload and manage these assets separately in each site.

With an asset library, you store them once and connect the library to all 5 sites. Each site can then use content from the library.

#### What Asset Libraries Contain

Asset libraries can hold:
- Documents and Media
- Web Content and its structures and templates
- Blogs
- Collections
- Segments
- Translation of content stored within the library

#### Connecting an Asset Library to a Site

1. Open the asset library's settings
2. Go to **Connected Sites**
3. Add the sites that should have access to this library's content

Once connected, content creators on those sites can select and use content from the asset library.

#### Walkthrough: Setting Up a Shared Image Library

**Goal:** Create an asset library of brand images that multiple sites can use.

1. Go to **Control Panel > Asset Libraries**
2. Click **Add** and name it "Brand Assets"
3. Click **Save** — the library is created
4. Inside the library, go to **Documents and Media**
5. Upload your brand images (logos, product photos, team headshots)
6. Organize them into folders (e.g., "Logos," "Products," "Team")
7. Now connect it to your sites: go to **Connected Sites** in the asset library settings
8. Click **Add** and select each site that should access these images
9. Content creators on those sites can now select images from "Brand Assets" when editing pages or content

> **Note:** A locally staged asset library cannot be connected to a remotely staged site, and vice versa.

### 11.6 Custom Objects

**Custom Objects** let administrators create entirely new types of data and content without any coding. If the built-in content types (web content, blog, document, etc.) don't fit your needs, you can define your own.

#### Defining a Custom Object

1. Go to **Objects** in the Control Panel
2. Create a new **Object Definition** — give it a name (e.g., "Project," "Store Location," "Employee Benefit")
3. Add **Fields** — define the data fields this object will have (text, number, date, image, relationship to another object, etc.)
4. Define **Relationships** — link this object to other objects or built-in content types
5. Design **Layouts** — the forms used to create and edit entries
6. Create **Views** — how lists of entries are displayed
7. Add **Validations** — rules that enforce data integrity (e.g., a phone number must be 10 digits)
8. Set up **Actions** — automated behaviors when entries are created, updated, or deleted (e.g., send a notification, trigger a webhook, add a related entry)
9. **Publish** the object to activate it

#### Using Custom Object Entries

Once published, the new object type:
- Appears in the site menu for data entry
- Can have its entries displayed on pages using the page editor
- Can be searched
- Can participate in workflow
- Can be translated
- Can be categorized and tagged
- Has a full permissions system

#### Object Folders

Organize multiple object definitions into **Object Folders** for easier management when you have many custom objects.

#### Walkthrough: Creating a Custom "Store Location" Object

**Goal:** Create a custom content type for managing store locations.

1. Go to **Control Panel > Objects**
2. Click **Add**
3. Name it "Store Location" (plural: "Store Locations")
4. Click **Save**
5. Go to the **Fields** tab and add:
   - "Store Name" (Text, required)
   - "Address" (Long Text)
   - "City" (Text)
   - "Phone Number" (Text)
   - "Opening Hours" (Text)
   - "Store Photo" (Attachment)
6. Go to the **Layouts** tab and arrange the fields into a logical form layout
7. Go to the **Views** tab and create a list view showing Store Name, City, and Phone Number
8. Click **Publish** to activate the object
9. "Store Locations" now appears in the site menu — you can add entries, categorize them, and display them on pages using the Collection Display fragment

---

## 12. Platform Tools
<!-- category: platform-tools, audience: [intranet-admins], modules: [redirect-web, dispatch-web, batch-planner-web, categories-admin-web, trash-web, adaptive-media-web, digital-signature-web, ai-creator-web] -->

### 12.1 URL Redirects

**URL Redirects** help you manage situations where content has moved — keeping old links working by automatically forwarding visitors to the new location.

#### Types of Redirects

| Type | Code | Use Case |
|---|---|---|
| **Permanent** | 301 | The page has permanently moved to a new URL. Search engines will update their index. |
| **Temporary** | 302 | The page is temporarily at a different URL. Search engines keep the original URL. |

#### Creating a Redirect

1. Go to **Site Administration > Configuration > Redirection**
2. Click **Add**
3. Enter the **Source URL** (the old address visitors might use)
4. Enter the **Destination URL** (where they should be sent)
5. Choose permanent or temporary
6. Save

#### Redirect Patterns

For situations where you need to redirect many similar URLs at once (for example, after restructuring an entire section of your site), use **Redirect Patterns**. These use regular expressions to match multiple source URL patterns and redirect them to a destination.

#### 404 URL Tracking

When the **404 URL tracking** feature is enabled, Liferay records all URLs that result in "page not found" errors. Review this list regularly to find broken links and add redirects for them.

#### Walkthrough: Redirecting an Old URL

**Goal:** You renamed a page from "/services" to "/our-services" and need the old URL to still work.

1. Go to **Site Administration > Configuration > Redirection**
2. Click **Add**
3. Enter **Source URL**: `/services`
4. Enter **Destination URL**: `/our-services`
5. Select **Permanent (301)** — this tells search engines to update their index
6. Click **Save**
7. Anyone visiting `/services` is now automatically redirected to `/our-services`

### 12.2 Job Scheduler

The **Job Scheduler** (also called **Dispatch**) lets administrators schedule automated tasks to run at specific times or on a recurring schedule.

#### What Can Be Scheduled

- Data export or import jobs
- Commerce catalog/order synchronization
- Analytics data uploads
- Custom automated tasks

#### Configuring a Job

Each job (called a **Trigger**) has:
- A **name** and description
- A **cron expression** defining when it runs (e.g., every day at midnight, every Monday at 6am)
- Start and end date/time (optional)
- Configuration settings specific to that task type

The job scheduler shows a log of recent runs and their success or failure status.

### 12.3 Data Migration Center

The **Data Migration Center** (previously called the Batch Planner) is a tool for bulk importing and exporting data to migrate content between systems or load large data sets.

#### Import

1. Choose what type of data to import (Users, Accounts, Products, Custom Object entries, etc.)
2. Upload your data file (CSV, JSON, JSONL, or JSONT format)
3. Map the fields in your file to the corresponding fields in Liferay
4. Run the import

#### Export

1. Choose the data type to export
2. Select the file format
3. Configure any date range filters
4. Run the export and download the file

#### Field Mapping

**Field Mappings** save the column-to-field mapping you configure, so you can reuse the same mapping for recurring imports without reconfiguring it each time.

### 12.4 Categories and Tags

**Categories** and **Tags** are the two classification systems used throughout the platform to organize and filter content.

#### Categories (Vocabularies)

Categories are organized, hierarchical classifications managed by administrators. They are grouped into **Vocabularies** — named sets of categories.

For example:
- Vocabulary: "Content Topics"
  - Category: "Company News"
  - Category: "Product Updates"
    - Subcategory: "Feature Releases"
    - Subcategory: "Bug Fixes"
  - Category: "How-To Guides"

Vocabularies can be:
- **Public** — visible in navigation and filters on the site
- **Internal** — used only for internal organization, not shown in public navigation

**Allow Multiple Categories** — a vocabulary can be configured to allow or restrict multiple category selections per content item.

#### Tags

**Tags** are freeform keywords that users can apply to content. Unlike categories, tags don't have a controlled list — any user can create new tags as needed. This makes tags more flexible but less structured than categories.

#### Walkthrough: Setting Up a Vocabulary and Categories

**Goal:** Create a "Department" vocabulary with categories for your intranet.

1. Go to **Site Administration > Categorization > Categories**
2. Click **Add Vocabulary**
3. Name it "Department"
4. Under **Associated Asset Types**, select which content types can use this vocabulary (e.g., Web Content, Documents, Blogs) — or leave as "All" for universal use
5. Choose **Allow Multiple Categories** if content can belong to more than one department
6. Choose **Public** (visible in navigation) or **Internal** (for organization only)
7. Click **Save**
8. Now click **Add Category** under the "Department" vocabulary
9. Name the first category "Marketing" and click **Save**
10. Add more categories: "Engineering," "Sales," "Human Resources," "Finance"
11. To create subcategories, click on "Engineering" and then **Add Category** — for example, "Frontend" and "Backend"
12. When editing content, authors can now select from these department categories

#### Display Pages for Categories

Each category can have a **Category Display Page** — a content page template used to render a landing page for that category. Clicking on a category link takes the visitor to a page that lists all content tagged with that category.

### 12.5 Recycle Bin

The **Recycle Bin** is a safety net for accidental deletions. When you delete most types of content, it is moved to the Recycle Bin rather than permanently destroyed.

#### How It Works

- When you delete a web content article, document, blog post, wiki page, message board thread, or calendar event, a confirmation prompt may tell you it's going to the Recycle Bin
- The item disappears from its normal location and is no longer visible to site visitors
- **Restore from Recycle Bin** — Navigate to the Recycle Bin and click Restore to put it back where it was
- **Permanently Delete** — Removes the item forever with no recovery possible

#### Automatic Cleanup

Administrators configure how long items stay in the Recycle Bin (the **Trash Entries Max Age** setting — measured in days). Items older than this threshold are automatically deleted permanently.

### 12.6 Adaptive Media

**Adaptive Media** automatically generates multiple size variants of uploaded images to serve appropriately sized images on different devices.

#### How It Works

Administrators define **Image Resolutions** — named configurations that specify maximum dimensions. For example:
- "Desktop" — 1920px wide
- "Tablet" — 768px wide
- "Thumbnail" — 300px wide

When an image is uploaded, Adaptive Media processes it and creates variants at each defined resolution. When a page is loaded, the browser receives the most appropriately sized image for the current screen.

#### Adapting Existing Images

You can trigger **Adapt All Images** to retroactively process images that were uploaded before a resolution was configured.

#### Backward Compatibility

If you have old content (fragments, web content, blog posts) using images with hardcoded sizes, the **Backwards Compatibility HTML Content Transformer** can re-process those images to use adaptive media. Note: enabling this may have a minor performance impact.

### 12.7 Digital Signatures

**Digital Signatures** allow you to send documents for electronic signature. This integrates with external signature services (such as DocuSign) to manage the signing workflow.

Administrators configure the integration with the signature provider. Once set up, users can initiate signature requests directly from documents stored in Documents and Media.

### 12.8 AI Creator

**AI Creator** integrates large language model AI capabilities (currently via **OpenAI**) into the content creation experience.

When enabled by your administrator, content editors see an **AI Creator** button when editing web content, blogs, or other text fields. You can use it to:
- **Generate content** — provide a topic or brief and receive a draft
- **Summarize** existing content
- **Classify or suggest tags** for content

Administrators configure the OpenAI API key and control whether AI Creator is available at the site or instance level.

---

## 13. CMS (Content Management System)
<!-- category: cms-platform, modules: [site-initializer-cms, site-cms-site-initializer], audience: [content-creators, marketers, intranet-admins], capabilities: [asset-management, spaces, bulk-operations, structures, categorization, translation-pipeline, version-history, content-editor] -->

The **CMS** module is a full content management platform built into Liferay DXP. It provides a dedicated workspace for managing all your content — web articles, documents, blog posts, and custom content types — from a single, unified interface. Think of it as a command center for everything your team creates and publishes.

Unlike managing content through individual modules (going to Web Content separately, then Documents and Media separately), the CMS brings everything together in one place with powerful bulk operations, collaborative Spaces, and a streamlined content editor.

### 13.1 CMS Dashboard
<!-- module: site-cms-site-initializer, audience: [content-creators], capabilities: [quick-actions, recent-assets, workflow-tasks, search] -->

When you open the CMS, the **Home** screen gives you an at-a-glance view of your content world:

- **Search Bar** — Instantly find any content item across all your assets
- **Quick Actions** — Shortcuts to your most recent operations (recently edited items, recent uploads)
- **Recent Assets** — A feed of the most recently created or modified content across all types
- **Workflow Tasks** — Any content items waiting for your review or approval

### 13.2 Assets Management
<!-- module: site-cms-site-initializer, audience: [content-creators, marketers], capabilities: [unified-content-view, contents, files, version-history, shared-with-me, folders] -->

The **Assets** section is where you browse, search, and manage all your content. It provides multiple views:

| View | What It Shows |
|---|---|
| **All** | Every content item across all types — articles, documents, blog posts, and custom objects — in one list |
| **Contents** | Only web content articles and custom object entries |
| **Files** | Only documents and media files |
| **Version History** | A timeline of all changes made to content, letting you track who changed what and when |
| **Shared with Me** | Content that other users have shared with you |

From any view, you can:
- **Search and filter** by type, status, author, date, category, or tag
- **Sort** by name, date modified, date created, or status
- **Open** any item to view or edit it in the built-in content editor
- **Organize** items into folders for logical grouping
- **Perform bulk actions** on multiple selected items (see [Section 13.6](#136-bulk-operations))

### 13.3 Spaces and Collaboration
<!-- module: site-cms-site-initializer, audience: [content-creators, intranet-admins], capabilities: [spaces, team-collaboration, member-management, space-content] -->

**Spaces** are collaborative work areas within the CMS. A Space is like a shared project room where a team can organize content, manage members, and collaborate without interfering with other teams.

#### What You Can Do with Spaces

- **Create a Space** — Set up a new workspace for a team, department, or project. Give it a name, description, and visual identity.
- **Add Members** — Invite other users to join the Space. Members can view, create, and edit content within that Space.
- **Browse Space Content** — Each Space has its own content view showing only the assets that belong to it — including content summaries and file summaries.
- **Manage Space Settings** — Configure the Space's properties, membership, and visibility.

#### When to Use Spaces

- A marketing team needs a dedicated area for campaign assets
- A department wants to manage its own internal documentation
- A project team needs a shared workspace for project-related files and articles

### 13.4 Structures and Content Types
<!-- module: site-cms-site-initializer, audience: [intranet-admins], capabilities: [structure-builder, object-definitions, display-pages, content-types] -->

The **Structures** section is where administrators define and manage the types of content that can be created in the CMS. A Structure defines the fields and layout for a content type.

#### What You Can Do

- **View all Structures** — See every content type available in your CMS
- **Build a Structure** — Use the visual **Structure Builder** to define fields (text, date, number, image, rich text, relationships, etc.) for a new content type
- **View Structure Usages** — See how many content items use a particular structure, and which pages display them
- **Assign Display Page Templates** — Connect a structure to a display page template so content of that type has a dedicated page layout

For example, you might create structures for "Press Release," "Product Spec Sheet," "Employee Profile," or "Policy Document" — each with its own set of fields tailored to that content type.

### 13.5 Categorization Management
<!-- module: site-cms-site-initializer, audience: [intranet-admins, content-creators], capabilities: [vocabularies, categories, tags, picklists, usage-tracking] -->

The CMS provides a dedicated **Categorization** section for managing your taxonomy — the classification systems that help organize and discover content.

- **Vocabularies** — Create and manage named groups of categories (e.g., "Departments," "Content Types," "Regions")
- **Categories** — Add, edit, and organize hierarchical categories within vocabularies. View how many content items use each category.
- **Tags** — Manage freeform tags applied to content. View tag usage across all content items.
- **Picklists** — Configure predefined dropdown lists for structured content fields (e.g., a "Status" picklist with values like "Active," "Retired," "Under Review")

Each of these views supports **usage tracking** — you can see exactly which content items are using a particular category or tag, helping you maintain a clean and useful taxonomy.

### 13.6 Bulk Operations
<!-- module: site-cms-site-initializer, audience: [content-creators, intranet-admins], capabilities: [bulk-delete, bulk-copy, bulk-tag, bulk-categorize, bulk-permissions, bulk-expire, bulk-status, task-monitoring] -->

One of the most powerful CMS features is the ability to perform **bulk operations** on many content items at once. Select multiple items from any asset list view and apply an action to all of them simultaneously.

#### Available Bulk Actions

| Action | What It Does |
|---|---|
| **Delete** | Move selected items to the Recycle Bin (or permanently delete) |
| **Copy** | Duplicate selected items and their folder structures |
| **Edit Categories** | Add or remove categories from all selected items |
| **Edit Tags** | Add or remove tags from all selected items |
| **Change Status** | Update the publication status (e.g., mark as expired, set to draft) |
| **Set Expiration** | Apply an expiration date to all selected items |
| **Set Due Date** | Assign a due date to selected items |
| **Update Field Values** | Change a specific custom field value across all selected items |
| **Edit Permissions** | Grant, revoke, or reset permissions for selected items |
| **Reset Permissions** | Revert permissions to defaults |
| **Assign Workflow** | Set a default workflow for a content structure |
| **Delete Versions** | Remove specific versions from selected items |

#### Monitoring Bulk Operations

Bulk actions run in the background so you can continue working. The **Bulk Action Task Report** section shows the progress of each operation — including how many items were processed successfully and how many failed. This is especially useful when operating on hundreds or thousands of items.

### 13.7 Translation Pipeline
<!-- module: site-cms-site-initializer, audience: [content-creators, marketers], capabilities: [xliff-export, translation-management, multi-language] -->

The CMS includes a built-in translation workflow that lets you export content for translation and import the results — all without leaving the CMS interface.

1. Select a content item and choose **Translate**
2. The system exports the content to **XLIFF format** — the industry-standard file format used by professional translators and translation tools
3. Send the XLIFF file to your translator or translation service
4. Import the completed XLIFF file back into the CMS
5. The translated text is automatically applied to the correct language version of the content

This is the same translation capability described in [Section 11.3](#113-translation), but accessible directly from the CMS content editor without navigating to a separate admin area.

---

## 14. CMP (Collaborative Management Platform)
<!-- category: project-management, modules: [site-initializer-cmp, site-cmp-site-initializer], audience: [intranet-users, intranet-admins], capabilities: [projects, tasks, state-management, notifications, collaboration, comments, assignees] -->

The **CMP** is a built-in project and task management system. It lets teams create projects, break them into tasks, assign work to team members, track progress, and collaborate — all within Liferay DXP. Think of it as a lightweight project management tool similar to Trello, Asana, or Jira, built directly into your portal.

### 14.1 Projects
<!-- module: site-cmp-site-initializer, audience: [intranet-users], capabilities: [create-projects, assign-managers, due-dates, completion-tracking, related-assets] -->

A **Project** represents a body of work your team is tackling. It contains tasks, has assigned people, and tracks overall progress.

#### Creating a Project

When you create a project, you fill in:

| Field | Description |
|---|---|
| **Title** | The project name (required) |
| **Description** | A detailed explanation of the project's goals and scope |
| **Due Date** | The deadline for project completion |
| **Project Manager** | The person responsible for driving the project forward |
| **Project Sponsor** | The stakeholder or executive backing the project |
| **Tags** | Keywords for organizing and finding projects |

#### Viewing a Project

The project detail page shows:

- **Details Tab** — The project description, a summary of all associated tasks, and an activity feed showing comments and history
- **Tasks Tab** — A list of all tasks linked to this project, with their status and assignees
- **Assets Tab** — Related documents, files, or other content attached to the project
- **Project Info Sidebar** — A summary card showing the project manager, sponsor, due date, state, and completion rate

#### Completion Rate

The **Completion Rate** is automatically calculated based on how many of the project's tasks are marked as "Done." You don't need to update it manually — it updates in real time as tasks are completed.

### 14.2 Tasks
<!-- module: site-cmp-site-initializer, audience: [intranet-users], capabilities: [create-tasks, assign-users, state-transitions, link-to-projects, comments] -->

A **Task** is an individual unit of work within a project. Every task must be linked to a project.

#### Creating a Task

| Field | Description |
|---|---|
| **Title** | What needs to be done (required) |
| **Description** | Detailed instructions or context |
| **Assign To** | The person responsible for completing the task |
| **Project** | Which project this task belongs to (required) |
| **Due Date** | When the task should be completed |
| **State** | The current status of the task |
| **Tags** | Keywords for organizing and filtering |

#### Viewing a Task

The task detail page shows the task description, its current state, the assignee, and an activity section with **Comments** and **History** tabs. A sidebar shows the task info summary and any related assets.

#### Comments and Collaboration

Both projects and tasks support **comments**. Team members can leave notes, ask questions, or provide updates directly on the project or task. The **History** tab shows a timeline of all changes — who changed the state, who was assigned, and when.

### 14.3 Project and Task Workflows
<!-- module: site-cmp-site-initializer, audience: [intranet-users], capabilities: [state-machine, controlled-transitions] -->

Projects and tasks follow a **four-state workflow** with controlled transitions. You can only move between states in specific, logical ways:

```
                    ┌──────────────┐
          ┌────────►│  In Progress │◄────────┐
          │         └──────┬───────┘         │
          │                │                 │
          │                ▼                 │
   ┌──────┴───────┐  ┌──────────┐  ┌────────┴──┐
   │ Not Started  │  │ Blocked  │  │   Done     │
   │  (default)   │  │          │  │            │
   └──────┬───────┘  └──────┬───┘  └────────────┘
          │                 │
          └────────►────────┘
```

| Current State | Can Move To |
|---|---|
| **Not Started** (default for new items) | In Progress, Blocked |
| **In Progress** | Done, Blocked |
| **Blocked** | In Progress, Done |
| **Done** | In Progress (to reopen if needed) |

This prevents accidental state changes — for example, you cannot mark something as "Done" directly from "Not Started" without first moving it to "In Progress."

### 14.4 Notifications
<!-- module: site-cmp-site-initializer, audience: [intranet-users], capabilities: [email-notifications, state-change-alerts, comment-alerts] -->

The CMP automatically sends email notifications to keep team members informed:

| Event | Who Gets Notified |
|---|---|
| **Project comment added** | All project subscribers |
| **Project state changed to Blocked** | All project subscribers |
| **Project updated** | All project subscribers |
| **Task created** | All project subscribers |
| **Task comment added** | All task subscribers |
| **Task state changed to Blocked** | All task subscribers |
| **Task updated** | All task subscribers |

The "Blocked" state triggers special notifications because it typically means something is preventing progress and requires attention.

Users can **subscribe** to any project or task to receive these notifications. Project managers and sponsors are automatically subscribed.

#### Walkthrough: Creating a Project with Tasks

**Goal:** Set up a "Website Redesign" project and break it into tasks.

1. Navigate to the **CMP** and go to **Planning > Projects**
2. Click **Add** to create a new project
3. Fill in:
   - **Title**: "Website Redesign"
   - **Description**: "Redesign the corporate website with new branding and improved navigation"
   - **Project Manager**: Select yourself
   - **Project Sponsor**: Select the marketing director
   - **Due Date**: Set to the end of the quarter
4. Click **Save**
5. Open the project and go to the **Tasks** tab
6. Click **Add Task** and create:
   - "Design new homepage mockup" — assign to designer, due in 2 weeks
   - "Write new About Us copy" — assign to copywriter, due in 1 week
   - "Update product photography" — assign to photographer, due in 3 weeks
   - "Build new page templates" — assign to web admin, due in 4 weeks
7. As team members complete tasks, they move each from **Not Started** → **In Progress** → **Done**
8. The project's **Completion Rate** updates automatically as tasks are completed
9. If a task is blocked, the assignee sets it to **Blocked** — subscribers receive a notification

---

## 15. Digital Sales Room
<!-- category: digital-sales-room, modules: [site-initializer-dsr, site-dsr-site-initializer, digital-sales-room-site-initializer], audience: [marketers, sales-teams], capabilities: [sales-rooms, branded-spaces, document-sharing, analytics, member-invitations, templates] -->

The **Digital Sales Room** (DSR) lets sales teams create branded, private collaboration spaces for engaging with prospects and customers. Each "room" is a dedicated mini-site where you can share documents, presentations, and proposals with a specific client — complete with your branding, your client's logo, and engagement analytics that show you how the client is interacting with your materials.

Think of it as a virtual conference room where you leave materials for your prospect, and you can see exactly which documents they opened and how long they spent with each one.

### 15.1 Rooms
<!-- module: site-dsr-site-initializer, audience: [sales-teams], capabilities: [create-rooms, manage-rooms, branded-spaces, document-management] -->

A **Room** is a private, branded space created for a specific client engagement.

#### Creating a Room

1. Navigate to the **Rooms** section in the Digital Sales Room
2. Click **Create**
3. The system automatically provisions a new dedicated site for this room
4. Upload and organize the documents, presentations, and materials you want to share
5. Invite team members and client contacts

Each room is tied to an **Account**, so all rooms for a specific client are grouped together. Your team sees only the rooms belonging to accounts they have access to.

#### What's in a Room

- A branded landing page with your company and client logos
- A **Documents** section where you upload and organize sales materials
- Team collaboration features — comments, sharing, and co-editing
- A unique, shareable URL for your client

#### Room Roles

| Role | What They Can Do |
|---|---|
| **DSR Contributor** | Upload documents, edit content, manage room materials |
| **DSR Seller** | View room content (read-only access for oversight) |
| **Site Owner** | Full administrative control over the room |

### 15.2 Room Templates
<!-- module: site-dsr-site-initializer, audience: [marketers, sales-teams], capabilities: [branded-templates, custom-colors, logos, banners] -->

**Room Templates** let you create reusable designs for your sales rooms so every new room starts with a consistent, professional look.

A template includes:

| Setting | Description |
|---|---|
| **Banner** | A large header image for the room (up to 100 MB) |
| **Client Logo** | The client's logo displayed in the room |
| **Client Name** | The client's company name |
| **Primary Color** | The main brand color (hex code) used for buttons and accents |
| **Secondary Color** | A complementary color for backgrounds and borders |

When you create a new room, you select a template and the room is automatically styled with those settings. You can still customize individual rooms after creation.

### 15.3 Room Analytics
<!-- module: site-dsr-site-initializer, audience: [sales-teams, marketers], capabilities: [engagement-tracking, visitor-analytics, activity-logs, performance-metrics] -->

The **Analytics** section gives you visibility into how clients are engaging with your sales rooms:

- **General Analytics** — Overall room engagement metrics (views, time spent, interactions)
- **Statistics** — Performance metrics across all your rooms
- **Latest Activity** — A real-time feed of recent visitor actions (who visited, what they viewed, when)
- **Most Active Visitors** — A ranking of which contacts are most engaged with your materials

This data helps sales teams understand which prospects are most interested, which materials resonate, and when to follow up.

### 15.4 Member Invitations
<!-- module: site-dsr-site-initializer, audience: [sales-teams], capabilities: [invite-members, user-provisioning, role-assignment] -->

You can invite both internal team members and external contacts to a room:

1. Open a room and click **Share** or use the invitation feature
2. Enter the person's name or email address
3. They receive an invitation notification with a direct link to the room
4. Once they accept, they can access the room's documents and content based on their assigned role

#### Walkthrough: Creating a Sales Room for a Client

**Goal:** Set up a branded room for a proposal to Acme Corp.

1. Navigate to the **Digital Sales Room** and go to **Templates**
2. Create a template (or select an existing one) with your company banner and Acme Corp's logo
3. Set the **Primary Color** to Acme's brand color and the **Client Name** to "Acme Corp"
4. Go to **Rooms** and click **Create**
5. The system creates a new room using your template
6. Upload your proposal documents: the pitch deck PDF, pricing spreadsheet, and product demo video
7. Organize the documents into logical sections
8. Click **Share** and invite your sales team members as **Contributors** and the Acme contacts as **Sellers** (view-only)
9. Send the room link to the Acme team
10. Check back in the **Analytics** section to see who viewed the materials and how much time they spent on each document

---

## 16. AI Hub
<!-- category: ai-hub, modules: [ai-hub-site-initializer], audience: [content-creators, marketers, intranet-admins], capabilities: [ai-agents, llm-workflows, chat-interface, text-transformation, mcp-servers, content-retrieval] -->

The **AI Hub** is an enterprise AI platform built into Liferay DXP. It lets you create, manage, and use AI-powered agents that can transform text, answer questions, search your company's content, and automate writing tasks. Think of it as having a team of AI assistants that know your organization's content and can help with everyday writing and research tasks.

### 16.1 AI Agents
<!-- module: ai-hub-site-initializer, audience: [content-creators, intranet-admins], capabilities: [agent-definitions, custom-agents, input-output-variables, activate-deactivate] -->

An **AI Agent** is a named, purpose-built AI assistant that performs a specific task. Each agent has a clear job — like improving writing, fixing grammar, or searching your documents.

#### What Defines an Agent

| Field | Description |
|---|---|
| **Title** | The agent's name (e.g., "Writing Improver," "Grammar Checker") |
| **Description** | What the agent does, shown to users when they select it |
| **Workflow** | The underlying AI workflow that powers this agent (defines what happens when the agent runs) |
| **Input Variables** | What information the agent needs from you (e.g., text to improve, desired tone) |
| **Output Variable** | What the agent produces (e.g., rewritten text) |
| **Active** | Whether this agent is currently available to users |

#### Browsing Agents

The **Agents** page shows all available AI agents in a list or grid. You can browse them, see their descriptions, and click one to start using it.

Administrators can **activate or deactivate** agents to control which ones are available to users, and can create new custom agents by defining a workflow and its input/output variables.

### 16.2 Built-In AI Workflows
<!-- module: ai-hub-site-initializer, audience: [content-creators, marketers], capabilities: [improve-writing, fix-grammar, change-tone, make-shorter, make-longer, liferay-search] -->

AI Hub comes with several pre-built AI workflows that power the default agents:

| Workflow | What It Does | Inputs |
|---|---|---|
| **Improve Writing** | Makes your text more concise, removes filler words, eliminates passive voice, and improves clarity | Text to improve |
| **Fix Spelling and Grammar** | Corrects spelling mistakes and grammatical errors while preserving your meaning | Text to fix |
| **Change Tone** | Rewrites your text in a different tone — professional, casual, formal, friendly, or any tone you specify | Text + desired tone |
| **Make Shorter** | Condenses your text while preserving the key meaning and message | Text to shorten |
| **Make Longer** | Expands your text with additional detail, examples, and explanation | Text to expand |
| **Liferay Search** | Searches your Liferay portal's content (documents, articles, pages) and returns relevant information | Search query |

#### How to Use an AI Workflow

1. Go to the **AI Hub** and select an agent
2. Enter your text (or query) in the input field
3. Click **Run** (or send via the chat interface)
4. The agent processes your request through its AI workflow
5. The result appears — you can copy it, refine it, or run it again with adjustments

### 16.3 Chat Interface
<!-- module: ai-hub-site-initializer, audience: [content-creators], capabilities: [conversational-ai, multi-turn-dialogue, streaming-responses, chat-history] -->

Each AI agent has a **chat interface** where you interact with it conversationally. This is similar to using a chatbot:

- **Send messages** — Type your request or paste text you want the agent to work with
- **Streaming responses** — The agent's response appears in real time as it generates, rather than waiting for the full response
- **Multi-turn conversation** — The agent remembers previous messages in the conversation, so you can refine results by saying things like "make it more formal" or "focus on the second paragraph"
- **Chat history** — Your conversations are saved so you can revisit previous interactions

The chat interface also supports **content retrieval** — agents can search your Liferay portal for documents and articles to ground their responses in your organization's actual data, rather than generating information from scratch.

### 16.4 MCP Servers
<!-- module: ai-hub-site-initializer, audience: [intranet-admins], capabilities: [mcp-protocol, tool-integration, external-apis, server-configuration] -->

**MCP Servers** (Model Context Protocol Servers) extend what AI agents can do by connecting them to external tools and data sources.

An MCP Server is a connection to an external service that provides "tools" the AI can use during its workflow. For example:
- A search tool that queries your document repository
- A data lookup tool that checks your product catalog
- An API integration that retrieves real-time data from an external system

#### Configuring an MCP Server

| Field | Description |
|---|---|
| **Title** | A name for this server connection |
| **URL** | The endpoint address of the MCP server |
| **Auth Arguments** | Authentication credentials (JSON format) for connecting to the server |

Administrators configure MCP servers, and then any AI workflow can use the tools those servers provide. This is how AI agents go beyond simple text transformation to actually interact with your organization's systems and data.

#### Walkthrough: Using AI Hub to Improve Your Writing

**Goal:** Take a draft article and polish it using the AI Hub.

1. Navigate to the **AI Hub** and browse the available agents
2. Click the **Improve Writing** agent
3. The chat interface opens
4. Paste your draft text into the message field — for example, a rough web content article
5. Click **Send**
6. The agent streams its response — a polished version with filler words removed, passive voice eliminated, and clearer phrasing
7. Review the result — if you want further changes, type a follow-up like "make it more casual" or "shorten the second paragraph"
8. Copy the final text and paste it into your Web Content editor
9. To fix just grammar and spelling, switch to the **Fix Spelling and Grammar** agent and paste your text
10. To adapt the tone for a different audience, use the **Change Tone** agent and specify the tone (e.g., "professional" or "friendly")

---

## 17. Site Templates
<!-- category: site-templates, modules: [site-initializer-welcome, site-initializer-masterclass, site-initializer-team-extranet, site-initializer-jobzz, site-initializer-teaser-showcase, commerce-theme-minium-site-initializer, commerce-theme-speedwell-site-initializer], audience: [intranet-admins], capabilities: [pre-built-sites, quick-start, demo-content, industry-solutions] -->

**Site Templates** (also called Site Initializers) are pre-built, ready-to-use site packages that create a fully configured site with pages, content, navigation, roles, and design — all in one click. Instead of building a site from scratch, you select a template and get a working site immediately.

These templates are ideal for getting started quickly, evaluating Liferay's capabilities, or using as a foundation that you customize for your needs.

### Available Site Templates

#### Welcome (Default)
The **Welcome** template is the default starting point for new Liferay instances. It creates a basic site with:
- A **Home** page
- Essential utility pages: **Sign In**, **Forgot Password**, **Create Account**, **Cookie Policy**
- Error handling pages for **404** (page not found) and **500** (server error) responses

This is the foundation template — minimal but functional.

#### Masterclass (Learning Management)
The **Masterclass** template creates an educational platform for delivering online courses and training. It includes:
- **Home** page with featured content
- **Blog** section for educational articles
- **Apply** page for course registration
- **My Learning** area where students track their progress
- **Notifications** and **Account Settings** pages
- Pre-built content types for **Courses**, **Classrooms**, **Teachers**, and **Blog Articles**
- Collections for featured blog entries, courses, teachers, and classrooms
- Custom roles: **Student** and **Teacher** with appropriate permissions
- A navigation structure with a "My Learning" private area

Best for: Training portals, online learning platforms, employee education programs.

#### Team Extranet (Intranet Collaboration)
The **Team Extranet** template creates an internal collaboration portal for teams. It includes:
- Content types for **Blog Entries**, **Team Members**, and **Positions**
- Collections for featured content, team member directories, and position listings
- Custom roles: **Team Member** and **RH Manager** (HR Manager)
- Navigation and page structure for a team intranet

Best for: Department intranets, team directories, HR information portals.

#### Jobzz (Careers Portal)
The **Jobzz** template creates a job board and careers website. It includes:
- **Home** page with a video hero section
- **Jobs** listing page with department filtering
- **Contact** page
- **Why** page (company value proposition)
- A **Job** content type with 13 pre-populated sample job listings
- Department taxonomy: HR, IT, Marketing, Sales, Support
- Custom fragments: Video banner, Lead section, Job Highlight cards
- Collections for homepage featured jobs and department-filtered job listings

Best for: Careers pages, job boards, recruiting portals.

#### Teaser Showcase (E-Commerce Storefront)
The **Teaser Showcase** template creates a full e-commerce site for an eyewear brand (Clarity Eyewear). It includes:
- 11 pages: Home, Products (with subcategories for eyeglasses, sunglasses, contacts, lenses), Blog, Careers, About Us, Contact Us, FAQ, Calendar, Search, Sign In
- A **Commerce Catalog** with products and specifications
- Product categories: Eyeglasses, Sunglasses, Contacts, Lenses
- FAQ categories: Retail Partners, Products and Services, Pricing and Ordering, Returns and Exchanges
- Multiple blog posts with sample content
- 7 organizational roles: Business Development Manager, Distributor, IT Manager, Marketing Coordinator, Marketing Manager, Supplier, Web Developer
- Footer navigation with Products, About Us, Get In Touch, Legal, and Resources sections

Best for: E-commerce storefronts, product marketing sites, brand showcases.

#### Commerce Minium (B2B Industrial Storefront)
The **Minium** template creates a complete B2B commerce site for industrial parts. It includes:
- A full product catalog with 100+ automotive and industrial parts
- Product categories: Engine, Brake System, Suspension, Exhaust System, Transmission, Turbocharger
- Product specifications: quantity, material, fit, dimensions, weight, warranty
- Multi-warehouse inventory management
- Pricing and discount configurations
- Commerce roles: Category Manager, Discount Manager, Operations Manager, Punch Out, Sales Agent
- Blog content, journal articles, and document library entries

The **Minium Full** variant (enterprise edition) adds machine learning-powered product recommendations and sales forecasting.

Best for: B2B parts distributors, industrial supply chains, wholesale commerce.

#### Commerce Speedwell
The **Speedwell** template is an alternative B2B commerce theme with a lightweight design. It includes similar commerce capabilities to Minium — product catalog, categories, specifications, blog content, and document library — but with a different visual design.

Best for: B2B commerce sites that prefer a cleaner, more minimal visual style.

---

## 18. Sign In and Account Settings
<!-- category: authentication, module: login-web, audience: [all-users], capabilities: [sign-in, forgot-password, create-account, openid, social-login] -->

### Signing In

Navigate to your site's login page. You can sign in with:
- **Email Address** and password
- **Screen Name** and password (if configured)
- **Single Sign-On (SSO)** providers like OpenID Connect (Google, Azure AD, etc.) or Facebook Connect (if configured by your administrator)

#### Forgot Password

Click **Forgot Password** on the login page and enter your email address. You will receive an email with a link to reset your password.

#### Remember Me

Check **Remember Me** to stay signed in across browser sessions (up to the session length configured by your administrator).

#### Multi-Factor Authentication (MFA)

If MFA is required, after entering your password you will be asked for a second factor:
- **Email One-Time Password** — A code sent to your email
- **Time-Based One-Time Password (TOTP)** — A code from an authenticator app (like Google Authenticator or Authy) that you set up in advance in your account settings
- **FIDO2** — A hardware security key (like YubiKey) or device biometric (fingerprint, Face ID) that you register in your account settings

### Creating an Account

If self-registration is enabled, click **Create Account** on the login page to register. You will typically provide:
- First and last name
- Email address
- Screen name (your unique username)
- Password

Some sites require administrator approval before new accounts are activated.

### My Account Settings

After signing in, click your profile picture (or name) in the navigation bar to access **My Account**. From here you can:
- Update your name, profile picture, job title, and other profile information
- Change your time zone and language preference
- Update your email address and screen name (requires password confirmation)
- Change your password
- Manage **Reminder Queries** (security questions for password recovery)
- Manage MFA settings
- View your organization memberships and roles
- Configure which email notifications you receive

### Enterprise Authentication (For Administrators)
<!-- modules: [saml-web, portal-settings-authentication-ldap-web], audience: [intranet-admins], capabilities: [saml-sso, ldap-directory, corporate-authentication] -->

For organizations that use corporate identity systems, Liferay supports several enterprise authentication methods that your administrator can configure:

#### LDAP Directory Integration

**LDAP** (Lightweight Directory Access Protocol) connects Liferay to your organization's user directory (like Microsoft Active Directory). When configured:
- Users sign in with their corporate credentials — no separate Liferay password needed
- New users are automatically imported from the directory
- User profile information (name, email, department) stays synchronized
- Group memberships from the directory can map to Liferay roles

Administrators configure LDAP under **Control Panel > Instance Settings > Authentication > LDAP**.

#### SAML Single Sign-On (DXP)

**SAML** (Security Assertion Markup Language) enables Single Sign-On (SSO) across multiple applications. When configured:
- Users sign in once through a central identity provider (like Okta, Azure AD, or ADFS)
- They are automatically signed into Liferay without entering credentials again
- Signing out of one application signs them out of all connected applications

Administrators configure SAML under **Control Panel > SAML Admin**. Liferay can act as either a **Service Provider** (relying on an external identity provider) or an **Identity Provider** (providing authentication to other applications).

---

*This documentation was produced from analysis of the Liferay DXP codebase. Feature availability may depend on your license, installed apps, and administrator configuration. The exact UI labels shown are drawn directly from the platform's language property files.*
