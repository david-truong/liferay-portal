# Liferay DXP Documentation Comparison: Our Guide vs. learn.liferay.com

<!--
schema: documentation-comparison
generated: 2026-03-29
our-guide: LIFERAY_DXP_END_USER_GUIDE.md
external-source: learn.liferay.com
purpose: identify-gaps-and-differences
-->

> This document compares our codebase-derived end-user guide (`LIFERAY_DXP_END_USER_GUIDE.md`) against the official Liferay documentation at [learn.liferay.com](https://learn.liferay.com). It identifies topics covered by one source but not the other.

---

## Summary

| Area | Our Guide | learn.liferay.com | Notes |
|---|---|---|---|
| **Audience** | End users, content creators, marketers, intranet admins | Mixed: end users, developers, system admins, cloud operators | Our guide is purely end-user focused |
| **Source** | Codebase analysis only | Official product documentation | Our guide may reflect unreleased/feature-flagged features |
| **Scope** | 18 sections, functional product features | 12+ top-level categories spanning all audiences | learn.liferay.com is much broader |

---

## Part 1: Topics on learn.liferay.com NOT in Our Guide

These are areas documented on learn.liferay.com that our guide does not cover. Many are developer-focused or infrastructure topics intentionally excluded from our end-user guide, but some are end-user-relevant gaps.

### 1.1 Developer and Technical Topics (Intentionally Excluded)
<!-- reason: out-of-scope for end-user documentation -->

| Topic | learn.liferay.com Section | Why Not in Our Guide |
|---|---|---|
| **Client Extensions** | Development > Client Extensions | Developer-only: building custom extensions |
| **Headless APIs / REST APIs** | Integration > Headless APIs | Developer-only: API consumption and integration |
| **Liferay Development** | Development | Developer-only: traditional Java development, OSGi, frameworks |
| **Microservice Client Extensions** | Development > Client Extensions | Developer-only: deploying microservices |
| **Configuration as Code** | Development | Developer/DevOps: managing configs programmatically |
| **API Builder** | Integration | Developer-only: building custom APIs |
| **Fragments (Developer Guide)** | Sites > Page Fragments > Developer Guide | Developer-only: coding custom fragments with HTML/CSS/JS |
| **Theme Development** | Development | Developer-only: building themes |
| **Template Development (FreeMarker)** | Development | Developer-only: writing display templates |
| **Service Builder** | Development | Developer-only: data service generation |
| **Upgrade Processes** | Self-Hosted Installation > Upgrading | Sysadmin: database upgrade procedures |
| **Portal Properties Reference** | Self-Hosted Installation > Reference | Sysadmin: server configuration properties |
| **Gogo Shell Commands** | Security and Administration | Sysadmin: OSGi console commands |

### 1.2 Infrastructure and Deployment (Intentionally Excluded)
<!-- reason: out-of-scope for end-user documentation -->

| Topic | learn.liferay.com Section | Why Not in Our Guide |
|---|---|---|
| **Self-Hosted Installation** | Self-Hosted Installation and Upgrades | Sysadmin: installing Liferay on-premise |
| **Docker Deployment** | Self-Hosted Installation > Docker Images | Sysadmin: container deployment |
| **Kubernetes / Cloud Native** | Self-Hosted Installation > Cloud Native Experience | Sysadmin/DevOps: K8s orchestration |
| **Liferay Cloud (PaaS)** | Cloud | Cloud admin: PaaS management, environments, builds |
| **Auto-Scaling** | Cloud > Manage and Optimize | Cloud admin: scaling configuration |
| **Clustering** | Cloud > Using the Liferay DXP Service | Cloud admin: multi-node setup |
| **Backup and Restore (Cloud)** | Cloud > Manage and Optimize | Cloud admin: backup management |
| **CI/CD and Builds** | Cloud > Platform Services | DevOps: continuous integration |
| **Database Configuration** | Self-Hosted Installation > Setting Up | Sysadmin: database setup |
| **Search Engine Installation** | Self-Hosted Installation > Installing a Search Engine | Sysadmin: Elasticsearch/OpenSearch setup |
| **Mail Server Configuration** | Self-Hosted Installation > Setting Up | Sysadmin: SMTP configuration details |
| **Virtual Instances** | Security and Administration | Sysadmin: multi-tenant configuration |

### 1.3 Security Topics (Partially Covered)
<!-- reason: partially relevant to end users -->

| Topic | learn.liferay.com Section | Gap in Our Guide |
|---|---|---|
| **SAML Configuration** | Security and Administration > SSO | We mention SSO login but don't explain SAML setup |
| **OAuth2 Administration** | Security and Administration > OAuth2 | We don't cover OAuth2 app management for admins |
| **LDAP Integration** | Security and Administration > LDAP | Not covered: connecting to corporate directory |
| **Service Access Policies** | Security and Administration | Not covered: API-level access control |
| **Password Policies** | Security and Administration | Not covered: configuring password rules (length, expiry, history) |
| **Antivirus Scanning** | Security and Administration | Not covered: file upload scanning |
| **Audit Logging** | Security and Administration | Not covered: tracking admin actions |
| **Content Security Policy** | Security and Administration | Not covered: CSP headers |

### 1.4 End-User-Relevant Gaps
<!-- priority: these should potentially be added to our guide -->

| Topic | learn.liferay.com Section | What's Missing |
|---|---|---|
| **Getting Started / Navigating DXP** | Getting Started > Navigating DXP | Our guide jumps into features without explaining the three main UI areas: Site Menu, Personal Menu, Global Menu |
| **Asset Publisher Configuration (Advanced)** | Sites > Displaying Content | learn.liferay.com has detailed configuration reference for display settings, pagination, RSS, email notifications |
| **Content Performance Tool** | Content Management System > Content Dashboard | learn.liferay.com documents content performance metrics in detail; our guide mentions it briefly |
| **Page Audit / Accessibility** | Sites > Optimizing Sites > Page Audit | Not covered: accessibility checking tools |
| **SEO Configuration (Detailed)** | Sites > Site Settings > SEO | Our guide mentions SEO settings but learn.liferay.com has detailed Open Graph, robots.txt, canonical URL, and sitemap configuration |
| **Responsive Design / Viewports (Detailed)** | Sites > Creating Pages > Content Pages | Our guide mentions viewport preview but learn.liferay.com covers per-viewport style overrides in detail |
| **Widget Permissions** | Sites > Site Settings | Not covered: configuring permissions on individual widgets |
| **Content Sets / Collection Providers (Advanced)** | Sites > Displaying Content | learn.liferay.com documents collection providers and advanced filtering in more detail |
| **Questions App Configuration** | Content Management System > Questions | learn.liferay.com has detailed configuration for the Questions widget |
| **Social Tools (Legacy)** | Content Management System > Social Tools | Not covered: social bookmarks, activities, ratings configuration |
| **Alerts and Announcements (Detailed)** | Content Management System > Alerts and Announcements | Our guide covers Announcements briefly; learn.liferay.com has detailed scope and targeting docs |
| **Content Rating Types** | Sites > Site Settings > Content Configurations | Not covered: configuring star ratings, thumbs up/down, etc. |
| **Friendly URL History** | Sites > Site Settings | Not covered: managing URL history and old URLs |
| **Remote Apps** | Development > Remote Apps | learn.liferay.com documents custom elements and iframes as remote apps for extending the platform |

---

## Part 2: Topics in Our Guide NOT on learn.liferay.com

These are features documented in our guide (derived from codebase analysis) that appear to have little or no coverage on learn.liferay.com. Many are newer features behind feature flags.

### 2.1 New Platform Modules (Feature-Flagged)
<!-- note: these may not be on learn.liferay.com because they are new/unreleased -->

| Topic | Our Guide Section | Notes |
|---|---|---|
| **CMS (Content Management System module)** | Section 13 | Full CMS workspace with unified asset management, Spaces, bulk operations, Structure Builder. Feature flag: LPD-17564. No equivalent section on learn.liferay.com. |
| **CMP (Collaborative Management Platform)** | Section 14 | Built-in project/task management with 4-state workflow, completion tracking, notifications. Feature flag: LPD-58677. Not documented on learn.liferay.com. |
| **Digital Sales Room (DSR)** | Section 15 | Branded sales collaboration rooms with analytics, templates, member invitations. Feature flag: LPD-66359. Not documented on learn.liferay.com. |
| **AI Hub** | Section 16 | Enterprise AI platform with agents, LLM workflows, chat interface, MCP servers. Feature flag: LPD-62272. Not documented on learn.liferay.com. |

### 2.2 Detailed Walkthrough Coverage

| Topic | Our Guide Section | Notes |
|---|---|---|
| **Bulk Operations (CMS)** | Section 13.6 | Our guide documents 12 specific bulk actions (delete, copy, edit categories, edit tags, change status, set expiration, set due date, update field values, edit permissions, reset permissions, assign workflow, delete versions). learn.liferay.com does not have equivalent bulk operations documentation for the CMS module. |
| **CMP State Machine** | Section 14.3 | Detailed state transition diagram (Not Started → In Progress → Blocked → Done) with controlled transitions. Not on learn.liferay.com. |
| **CMP Notifications Matrix** | Section 14.4 | 7 specific notification types for projects and tasks. Not on learn.liferay.com. |
| **DSR Room Templates** | Section 15.2 | Template fields (banner, client logo, primary/secondary color). Not on learn.liferay.com. |
| **DSR Analytics** | Section 15.3 | Room engagement tracking, visitor analytics, activity logs. Not on learn.liferay.com. |
| **AI Hub Built-In Workflows** | Section 16.2 | 6 pre-built AI workflows (Improve Writing, Fix Grammar, Change Tone, Make Shorter, Make Longer, Liferay Search). Not on learn.liferay.com. |
| **MCP Server Configuration** | Section 16.4 | Model Context Protocol server setup for AI tool integration. Not on learn.liferay.com. |

### 2.3 Consolidated End-User Perspective

| Topic | Our Guide | learn.liferay.com |
|---|---|---|
| **Unified product overview** | Section 1 provides a single-page overview of what Liferay DXP is and who it's for | Spread across multiple capability pages and marketing content |
| **Inline walkthroughs** | Step-by-step workflows embedded in each feature section | Tutorials exist but are in separate courses/training modules, not inline |
| **Admin Quick Reference** | Section 10.5 consolidates common admin tasks | Scattered across multiple sections (Site Settings, System Settings, Control Panel) |
| **Site Templates catalog** | Section 17 lists all available site templates with what they create | Site Initializers page exists but focuses on the developer/API perspective |
| **Commerce in plain language** | Section 8 explains commerce concepts in layman's terms | Commerce docs are comprehensive but more technical |

---

## Part 3: Structural and Organizational Differences

### 3.1 How learn.liferay.com Organizes Content

learn.liferay.com organizes its DXP documentation into **capability-based categories** (recently reorganized):

| Category | URL Path | Covers |
|---|---|---|
| **AI** | /w/dxp/ai | AI-powered features |
| **Commerce** | /w/dxp/commerce | Full commerce platform |
| **Low-code** | /w/dxp/low-code | Objects, Forms, Workflow |
| **Security and Administration** | /w/dxp/security-and-administration | Users, roles, SSO, system settings |
| **Content Management System** | /w/dxp/content-management-system | Web content, blogs, KB, wiki, tags, categories, dashboard |
| **Digital Asset Management** | /w/dxp/digital-asset-management | Documents and Media, adaptive media |
| **Personalization** | /w/dxp/personalization | Segments, experiences, A/B testing, Analytics Cloud |
| **Sites** | /w/dxp/sites | Pages, fragments, navigation, display pages, publishing tools |
| **Search** | /w/dxp/search | Search widgets, blueprints, synonyms, tuning |
| **Integration** | /w/dxp/integration | Headless APIs, data migration |
| **Self-Hosted Installation** | /w/dxp/self-hosted-installation-and-upgrades | On-premise deployment |
| **Cloud** | /w/dxp/cloud | Liferay Cloud PaaS |
| **Development** | /w/dxp/development | Client extensions, traditional development |
| **Getting Started** | /w/dxp/getting-started | Navigation, first steps |

Additionally, learn.liferay.com has:
- **Courses** (structured learning paths like "Building Enterprise Websites with Liferay")
- **Reference** materials
- **Training exercises**

### 3.2 How Our Guide Organizes Content

Our guide organizes by **user workflow and task**:

1. Product Overview → 2. Content Management → 3. Site Building → 4. Content Discovery → 5. Collaboration → 6. Personalization → 7. Forms → 8. Commerce → 9. Workflow → 10. Administration → 11. Content Operations → 12. Platform Tools → 13. CMS → 14. CMP → 15. DSR → 16. AI Hub → 17. Site Templates → 18. Sign In

### 3.3 Key Naming Differences

| Our Guide Term | learn.liferay.com Term |
|---|---|
| Documents and Media | Digital Asset Management |
| Content Operations | Publishing Tools (Staging, Publications) |
| Platform Tools | Scattered across Security/Administration, Integration |
| Forms | Low-code > Forms |
| Workflow | Low-code > Workflow |
| Custom Objects | Low-code > Objects |
| Data Migration Center | Integration > Data Migration Center |
| Site Templates | Development > Site Initializers |
| Categories and Tags | Content Management System > Tags and Categories |
| Navigation Menus | Sites > Site Navigation |
| Collaboration (wiki, message boards) | Content Management System > Deprecated Content Features (wiki, message boards are marked deprecated) |

---

## Part 4: Notable Deprecations on learn.liferay.com

learn.liferay.com marks several features as **deprecated** that our guide still documents as active:

| Feature | Status on learn.liferay.com | Our Guide |
|---|---|---|
| **Wiki** | Listed under "Deprecated Content Features" | Section 2.5 — documented as active |
| **Message Boards** | Listed under "Deprecated Content Features" | Section 2.6 — documented as active |
| **Web Content Display widget** | Deprecated in favor of Content Display fragment | Not specifically called out |
| **Widget Pages** | Being superseded by Content Pages | Section 3.1 lists it as a page type |

---

## Part 5: Root Cause Analysis — Why We Missed These

### 5.1 Why We Missed Deprecations

**Root cause: We read `bnd.bnd` (module-level) but not `app.bnd` (application-level).**

Liferay's deprecation status lives in `app.bnd` files at the parent application directory, using the `Liferay-Releng-Deprecated: true` property. Our analysis only read the `bnd.bnd` file inside each `-web` module, which doesn't contain deprecation flags.

Additionally, runtime deprecation is signaled through:
- `PortletManager.isDeprecated()` returning `true` (found in Wiki)
- `DeprecatedInfoMessageProductNavigationControlMenuEntry` classes that display a deprecation banner in the UI (found in Wiki and Message Boards)
- Feature flags in `Language.properties` (e.g., `feature.flag.LPD-35013` for Wiki)

**Full deprecation status from `app.bnd` files:**

| Application | `Liferay-Releng-Deprecated` | Notes |
|---|---|---|
| **Wiki** | `true` | Feature flag LPD-35013, migration to Objects recommended |
| **Questions** | `true` | Deprecated — does NOT replace Message Boards |
| **Message Boards** | `false` | But has a `DeprecatedInfoMessageProductNavigationControlMenuEntry` (2026) — deprecation in progress |
| **Bookmarks** | `true` | Not in our guide (correctly excluded) |
| **Dynamic Data Lists** | `true` | Replaced by Objects + Forms, not in our guide (correctly excluded) |
| **Microblogs** | `true` | Not in our guide (correctly excluded) |
| **Push Notifications** | `true` | Not in our guide (correctly excluded) |
| **Journal (Web Content)** | `false` | Still active |
| **Layout (Pages)** | `false` | Still active |

### 5.2 Why We Missed End-User Features

**Root cause: Our scan covered ~35 key directories under `modules/apps/` but missed modules in secondary locations.**

| Missed Feature | Module | Why Missed |
|---|---|---|
| **Password Policies** | `modules/apps/password-policies-admin/password-policies-admin-web` | Not in our initial list of ~35 module areas to scan |
| **LDAP** | `modules/apps/portal-settings/portal-settings-authentication-ldap-web` | Nested under `portal-settings/`, not a standalone app directory |
| **Accessibility** | `modules/apps/accessibility/accessibility-menu-web` | Not in our initial scan list |
| **Content Ratings** | `modules/apps/ratings/ratings-page-ratings-web` | Not in our initial scan list |
| **Navigation UI** | `modules/apps/product-navigation/product-navigation-control-menu-web` | Infrastructure module — didn't appear content-focused |
| **Audit Logging** | `modules/dxp/apps/portal-security-audit/portal-security-audit-web` | Under `modules/dxp/` (enterprise-only), we primarily scanned `modules/apps/` |
| **SAML SSO** | `modules/dxp/apps/saml/saml-web` | Under `modules/dxp/` (enterprise-only) |

**Two systemic gaps:**
1. **We only scanned `modules/apps/`** — enterprise features under `modules/dxp/apps/` (Audit, SAML, Search Tuning, Analytics Reports) were not included
2. **We used a curated list of ~35 key directories** — modules not in that list (password-policies-admin, accessibility, ratings, product-navigation) were skipped

### 5.3 Resolved Gaps

The following gaps have been **fixed** in the updated `LIFERAY_DXP_END_USER_GUIDE.md`:

| Gap | Resolution |
|---|---|
| No "Navigating DXP" section | Added navigation overview to Section 1 (Site Menu, Personal Menu, Global Menu) |
| Wiki not marked deprecated | Added deprecation notice with feature flag LPD-35013 and migration guidance |
| Message Boards not marked deprecated | Added deprecation-pending notice |
| Questions not marked deprecated | Added deprecation notice |
| No Password Policies | Added Section 10.5 with configurable settings table |
| No Accessibility features | Added Section 10.6 with accessibility menu description |
| No LDAP/SAML overview | Added "Enterprise Authentication" subsection to Section 18 |

### 5.4 Remaining Gaps (Not Yet Added)

| Gap | Reason Not Added |
|---|---|
| Audit Logging (DXP) | Enterprise-only feature; would need a DXP-specific section |
| Content Rating Types configuration | Minor feature; low priority for end-user guide |
| Page Audit / SEO detailed configuration | Detailed reference material, better suited for learn.liferay.com |
| Social Tools / Social Bookmarks | No standalone module found; integrated into individual apps |
| Advanced Asset Publisher configuration | Reference-level detail, not walkthrough material |
| Widget Permissions detail | Granular admin topic, low priority |

---

*Comparison conducted on 2026-03-29, updated with root cause analysis. learn.liferay.com content may have changed since this analysis. Our guide reflects codebase state at time of analysis and may include feature-flagged capabilities not yet generally available.*

Sources:
- [learn.liferay.com — DXP Index](https://learn.liferay.com/w/dxp/index)
- [learn.liferay.com — Content Management System](https://learn.liferay.com/w/dxp/content-management-system)
- [learn.liferay.com — Sites](https://learn.liferay.com/w/dxp/sites)
- [learn.liferay.com — Commerce](https://learn.liferay.com/w/dxp/commerce)
- [learn.liferay.com — Search](https://learn.liferay.com/w/dxp/search)
- [learn.liferay.com — Personalization](https://learn.liferay.com/w/dxp/personalization)
- [learn.liferay.com — Digital Asset Management](https://learn.liferay.com/w/dxp/digital-asset-management)
- [learn.liferay.com — Self-Hosted Installation](https://learn.liferay.com/w/dxp/self-hosted-installation-and-upgrades)
- [learn.liferay.com — Cloud](https://learn.liferay.com/w/dxp/cloud)
- [learn.liferay.com — Low-code (Objects/Forms/Workflow)](https://learn.liferay.com/w/dxp/low-code)
- [learn.liferay.com — Security and Administration](https://learn.liferay.com/w/dxp/security-and-administration)
- [learn.liferay.com — Development](https://learn.liferay.com/w/dxp/development)
- [learn.liferay.com — Navigating DXP](https://learn.liferay.com/w/dxp/getting-started/navigating-dxp)
- [learn.liferay.com — Site Initializers](https://learn.liferay.com/w/dxp/development/importing-exporting-data/site-initializers)
- [learn.liferay.com — Search Blueprints](https://learn.liferay.com/w/dxp/search/liferay-enterprise-search/search-blueprints)
