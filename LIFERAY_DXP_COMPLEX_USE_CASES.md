# Liferay DXP: Complex Use Cases and Multi-Feature Workflows

<!--
schema: use-case-guide
version: 1.0
platform: Liferay DXP
audience: [content-creators, marketers, intranet-admins, commerce-managers, decision-makers]
format: markdown
ai-parseable: true
purpose: demonstrate how multiple Liferay features work together to solve real business problems
-->

> These use cases demonstrate how Liferay DXP's features combine to solve complex, real-world business problems. Each use case identifies the business challenge, lists the Liferay features involved, and walks through the implementation step by step. All feature references correspond to sections in the `LIFERAY_DXP_END_USER_GUIDE.md`.

---

## Table of Contents

1. [Customer Self-Service Portal](#1-customer-self-service-portal)
2. [Employee Onboarding Intranet](#2-employee-onboarding-intranet)
3. [B2B Dealer and Distributor Portal](#3-b2b-dealer-and-distributor-portal)
4. [Global Multi-Site, Multi-Language Corporate Website](#4-global-multi-site-multi-language-corporate-website)
5. [Partner Enablement and Training Hub](#5-partner-enablement-and-training-hub)
6. [Internal Policy and Compliance Management](#6-internal-policy-and-compliance-management)
7. [Product Launch Campaign](#7-product-launch-campaign)
8. [IT Service Desk and Help Center](#8-it-service-desk-and-help-center)
9. [B2B Commerce Storefront with Account-Based Pricing](#9-b2b-commerce-storefront-with-account-based-pricing)
10. [AI-Powered Content Operations Center](#10-ai-powered-content-operations-center)

---

## 1. Customer Self-Service Portal
<!-- use-case: customer-self-service, industry: [insurance, telecom, utilities, financial-services], features-used: 11 -->

**Business Challenge:** Your customer support center is overwhelmed with calls. 70% of incoming requests are common questions that could be answered without a live agent. You need a portal where customers can log in, find answers, submit requests, track their cases, and manage their accounts — reducing call volume and improving satisfaction.

**Real-World Example:** Vodafone Business created a self-service portal for 1,500 enterprise customers across 150 countries, significantly boosting satisfaction and reducing support costs.

### Features Used

| Feature | Guide Section | Role in This Use Case |
|---|---|---|
| **Knowledge Base** | 2.4 | Searchable help articles, FAQs, how-to guides |
| **Search** | 4.3 | Full-text search across all knowledge base articles and documents |
| **Forms** | 7 | Submit support tickets, feedback, and service requests |
| **Workflow** | 9 | Route submitted tickets through triage and assignment |
| **Notifications** | 5.4 | Notify customers when their ticket status changes |
| **Documents and Media** | 2.2 | Downloadable product manuals, invoices, and statements |
| **Roles and Permissions** | 10.3 | Secure, role-based access — customers see only their own data |
| **Segments** | 6.1 | Personalize the portal by customer tier (Basic, Premium, Enterprise) |
| **Experiences** | 6.2 | Show different dashboard layouts based on customer segment |
| **Custom Objects** | 11.6 | Track support cases with custom fields (case number, priority, status) |
| **Categories and Tags** | 12.4 | Organize KB articles by product, topic, and issue type |
| **Publications** | 11.2 | Stage portal page designs, KB content, and experience configurations before going live |

### Implementation Walkthrough

#### Phase 1: Build the Knowledge Base

1. Create a **Publication** called "Customer Portal Setup" so page layouts, KB articles, navigation, and experience configurations stay invisible to customers while you build
2. Create a **vocabulary** called "Product Areas" with categories for each product line (e.g., "Billing," "Account Management," "Technical Support")
2. Create a **Knowledge Base** with articles organized into a hierarchy:
   - Getting Started → Account Setup, First Login, Profile Settings
   - Billing → Payment Methods, Invoice History, Subscription Changes
   - Troubleshooting → Common Errors, Connectivity Issues, Reset Procedures
3. Tag each article with relevant categories so the **Search** and **Category Facets** can filter them
4. Set **review dates** on articles so they are flagged for freshness checks quarterly

#### Phase 2: Build the Self-Service Pages

5. Create a **Content Page** for the portal dashboard using the **Content Page Editor**
6. Add a **Search Bar** at the top so customers can instantly search the knowledge base
7. Add a **Collection Display** showing the 5 most recently updated KB articles ("What's New")
8. Add an **Asset Publisher** showing articles tagged with the customer's product (dynamically filtered)
9. Create a "Submit a Request" page with a **Form** containing fields:
   - Subject (text), Description (rich text), Priority (dropdown: Low/Medium/High/Critical), Product Area (category selector), Attachments (file upload)
10. Connect the form to a **Workflow** — new submissions go to the support triage team for assignment

#### Phase 3: Personalize by Customer Tier

11. Create **Segments**: "Basic Customers," "Premium Customers," "Enterprise Customers" based on user role or custom field
12. Create **Experiences** on the dashboard page:
    - **Enterprise**: Show a dedicated account manager contact, priority SLA status, and direct escalation button
    - **Premium**: Show expanded KB access and a live chat widget
    - **Basic**: Show standard KB and community forum links

#### Phase 4: Track and Notify

13. Create a **Custom Object** called "Support Case" with fields: Case Number (auto-generated), Subject, Description, Priority, Status (picklist: Open/In Progress/Waiting/Resolved), Assigned Agent, Resolution Notes
14. Configure **Notifications** so customers receive emails when:
    - Their case status changes
    - An agent adds a comment to their case
    - A KB article related to their issue is published
15. **Review and publish** the Publication — all portal pages, KB articles, navigation, and personalized experiences go live at once. (Custom Object entries like support cases are created by customers in real time and don't need Publications — they're managed by Workflow instead.)

**Outcome:** Customers find answers in the Knowledge Base before ever contacting support. When they do submit tickets, the workflow routes them to the right team. The portal personalizes the experience by customer tier. Support call volume drops significantly.

---

## 2. Employee Onboarding Intranet
<!-- use-case: employee-onboarding, industry: [all], features-used: 13 -->

**Business Challenge:** New employees take weeks to get fully productive because onboarding information is scattered across emails, shared drives, and tribal knowledge. HR needs a single destination where new hires complete required tasks, access training materials, meet their team, and track their onboarding progress.

### Features Used

| Feature | Guide Section | Role in This Use Case |
|---|---|---|
| **Custom Objects** | 11.6 | "Onboarding Checklist" with task status tracking |
| **Forms** | 7 | Collect employee information, tax forms, equipment requests |
| **Workflow** | 9 | Route form submissions for HR and IT approval |
| **Documents and Media** | 2.2 | Employee handbook, policy documents, benefits guides |
| **Knowledge Base** | 2.4 | "New Employee Guide" with step-by-step articles |
| **Web Content** | 2.1 | Welcome message, CEO letter, department overviews |
| **Calendar** | 5.2 | Orientation schedule, training sessions, team meetings |
| **CMP (Projects & Tasks)** | 14 | Track each new hire's onboarding as a project with tasks |
| **Notifications** | 5.4 | Remind new hires of incomplete tasks and upcoming deadlines |
| **Roles and Permissions** | 10.3 | "New Employee" role with time-limited expanded access |
| **Segments** | 6.1 | Segment by department to show relevant content |
| **Experiences** | 6.2 | Personalized dashboard by department |
| **Blogs** | 2.3 | "Life at [Company]" blog with employee stories |
| **Publications** | 11.2 | Stage intranet redesigns and content updates without affecting current employees |

### Implementation Walkthrough

#### Phase 1: Content Foundation

1. Create a **Publication** called "Onboarding Portal Launch" — pages, KB articles, web content, blogs, navigation, segments, and experiences are all staged invisibly while you build
2. Create an **Asset Library** called "Company-Wide Resources" with the employee handbook, benefits guide, IT setup guide, and corporate policies
2. Connect the Asset Library to the Intranet site
3. Create **Web Content** articles with the CEO's welcome letter, department overviews, and company history
4. Build a **Knowledge Base** hierarchy: "Your First Week" → "Your First Month" → "Ongoing Resources"
5. Set up a "Life at [Company]" **Blog** where existing employees share their experiences

#### Phase 2: Onboarding Workflow

6. Create a **Custom Object** called "Onboarding Task" with fields: Task Name, Description, Category (picklist: HR/IT/Team/Training), Due Date, Status (Not Started/In Progress/Done), Assigned To
7. In the **CMP**, create a **Project Template** called "New Employee Onboarding" with standard tasks:
   - Complete tax withholding form (HR, Day 1)
   - Submit equipment request (IT, Day 1)
   - Review employee handbook (HR, Week 1)
   - Complete security training (Compliance, Week 1)
   - Meet with manager for 30-day goals (Team, Week 1)
   - Complete product training modules (Training, Month 1)
8. Create **Forms** for: Tax withholding, Equipment request, Emergency contact, Direct deposit setup
9. Connect each form to a **Workflow**: Tax form → HR review → Payroll; Equipment request → IT approval → Procurement

#### Phase 3: Personalized Experience

10. Create **Segments** by department: "Engineering New Hires," "Marketing New Hires," "Sales New Hires"
11. Create **Experiences** on the onboarding dashboard:
    - **Engineering**: Show dev environment setup guide, code repository links, sprint calendar
    - **Marketing**: Show brand guidelines, campaign calendar, content style guide
    - **Sales**: Show CRM access guide, product pricing sheets, territory map
12. Create a **Navigation Menu** for onboarding with sections: Welcome, Tasks, Documents, Training, My Team

#### Phase 4: Automation

13. Configure **Notifications** to email the new hire:
    - When a new task is assigned
    - 2 days before a task deadline
    - When their equipment request is approved
14. Configure notifications to email the manager when the new hire completes all Week 1 tasks
15. **Publish** the "Onboarding Portal Launch" publication — the entire onboarding experience goes live at once

**Outcome:** Every new hire gets a personalized onboarding experience with a clear checklist, deadline tracking, and automatic reminders. HR can monitor completion rates across all new hires. Managers get notified of progress without chasing updates.

---

## 3. B2B Dealer and Distributor Portal
<!-- use-case: dealer-portal, industry: [manufacturing, automotive, industrial], features-used: 14 -->

**Business Challenge:** You have a network of 500+ dealers and distributors who need to order spare parts, access technical documentation, complete certification training, and manage their accounts. Currently, orders come via phone, fax, and email — leading to errors, delays, and no self-service capability.

**Real-World Example:** Putzmeister connected all 17 national companies and their complete dealer network through a Liferay-based portal. MacDon increased e-commerce transactions by 50% and doubled website visits while decreasing phone and fax orders.

### Features Used

| Feature | Guide Section | Role in This Use Case |
|---|---|---|
| **Commerce: Products & Catalogs** | 8.1 | Parts catalog with SKUs, specifications, and images |
| **Commerce: Orders & Checkout** | 8.2 | Online parts ordering with checkout flow |
| **Commerce: Pricing & Discounts** | 8.3 | Dealer-tier pricing, volume discounts, contract pricing |
| **Commerce: Inventory** | 8.4 | Real-time stock levels across warehouses |
| **Commerce: Accounts** | 8.5 | Dealer accounts with role-based access |
| **Knowledge Base** | 2.4 | Technical manuals, installation guides, troubleshooting |
| **Documents and Media** | 2.2 | CAD drawings, spec sheets, safety data sheets |
| **Web Content** | 2.1 | Product bulletins, announcements, policy updates |
| **Notifications** | 5.4 | Order status updates, new bulletin alerts |
| **Search** | 4.3 | Cross-part-number search across catalogs and documents |
| **Segments** | 6.1 | Segment by dealer tier (Gold, Silver, Bronze) |
| **Roles and Permissions** | 10.3 | Dealer Admin, Dealer Purchaser, Dealer Technician roles |
| **Categories and Tags** | 12.4 | Categorize parts by product line, system, and compatibility |
| **Custom Objects** | 11.6 | Warranty claims tracking, dealer certifications |
| **Publications** | 11.2 | Stage portal pages, product catalog changes, pricing updates, and KB articles before going live |

### Implementation Walkthrough

#### Phase 1: Commerce Foundation

1. Create a **Publication** called "Dealer Portal Launch" — portal pages, product definitions, price lists, and KB articles are all tracked by Publications, so dealers won't see anything until you're ready. (Note: inventory levels and live orders operate in real time and are not staged.)
2. Create a **Commerce Catalog** called "Parts Catalog"
2. Add products organized by **Categories**: Engine Parts, Brake System, Suspension, Electrical, Consumables
3. For each product, define **SKUs** with specifications (dimensions, weight, compatibility, material)
4. Upload product images and attach PDF spec sheets to each product
5. Set up **Warehouses** (e.g., US Central, US East, Europe) with inventory levels per SKU
6. Create **Price Lists**:
   - "Retail Price List" — standard pricing
   - "Gold Dealer Price List" — 25% discount
   - "Silver Dealer Price List" — 15% discount
   - "Bronze Dealer Price List" — 5% discount
7. Configure **Tier Pricing**: 1-9 units at list price, 10-49 at 10% off, 50+ at 20% off

#### Phase 2: Dealer Account Management

8. Create **Commerce Account** entries for each dealer organization
9. Assign accounts to **Account Groups**: "Gold Dealers," "Silver Dealers," "Bronze Dealers"
10. Each group is linked to the appropriate **Price List**
11. Define **Account Roles**: "Dealer Admin" (manage users, view all orders), "Purchaser" (place orders), "Technician" (view documentation only)

#### Phase 3: Knowledge and Documentation

12. Build a **Knowledge Base** with technical documentation organized by product line
13. Upload **Documents and Media**: installation guides (PDF), wiring diagrams (PDF), CAD files, safety data sheets
14. Create **Web Content** for product bulletins and safety advisories
15. Set up **Search** with **Synonym Sets** (e.g., "brake pad" = "brake lining" = "friction material")
16. Add **Category Facets** to search so dealers can filter documentation by product line

#### Phase 4: Self-Service Workflows

17. Create a **Custom Object** "Warranty Claim" with fields: Part Number, Serial Number, Failure Description, Date of Failure, Photos (attachment), Claim Status, Resolution
18. Create a **Form** for submitting warranty claims, connected to a **Workflow** for claims review
19. Create a **Custom Object** "Dealer Certification" to track which dealers are certified for which product lines
20. Configure **Notifications**: order shipped, warranty claim status change, new product bulletin published
21. Review all changes in the Publication and **Publish** — the dealer portal goes live with the complete catalog, documentation, and self-service tools all at once

**Outcome:** Dealers can self-serve for parts ordering, technical documentation, and warranty claims 24/7. Volume discounts and dealer-tier pricing are automatic. Phone and fax orders drop dramatically. Dealers see real-time inventory and order status.

---

## 4. Global Multi-Site, Multi-Language Corporate Website
<!-- use-case: global-website, industry: [enterprise, multinational], features-used: 12 -->

**Business Challenge:** Your company operates in 12 countries with 8 languages. Each regional site needs localized content, but brand consistency must be maintained globally. Marketing teams in each region need autonomy to create local content while reusing global assets (brand images, product descriptions, corporate messaging).

### Features Used

| Feature | Guide Section | Role in This Use Case |
|---|---|---|
| **Sites** | 10.1 | Separate site per region/country |
| **Asset Libraries** | 11.5 | Shared brand assets and global content |
| **Translation** | 11.3 | XLIFF export/import for professional translation |
| **Web Content (Structures)** | 2.1 | Consistent content structures across all sites |
| **Style Books** | 3.4 | Global brand design system applied to all sites |
| **Page Templates** | 3.1 | Shared page layouts across regions |
| **Navigation Menus** | 3.5 | Per-region navigation with global consistency |
| **SEO Settings** | 3.1 | Hreflang tags, canonical URLs, Open Graph per language |
| **Segments** | 6.1 | Segment by language, geography, or user profile |
| **Experiences** | 6.2 | Region-specific homepage variations |
| **Publications** | 11.2 | Coordinate global content launches across regions |
| **Content Dashboard** | 4.4 | Monitor content freshness and translation status |

### Implementation Walkthrough

#### Phase 1: Global Foundation

1. Create an **Asset Library** called "Global Brand Assets" containing: corporate logos, product photography, brand guidelines PDF, approved stock images, and corporate boilerplate web content
2. Create **Web Content Structures** for standard content types used globally: "Press Release," "Product Page," "Office Location," "Leadership Bio"
3. Create a **Style Book** called "Corporate Brand" defining the global color palette, typography, and spacing
4. Create **Page Templates** for common layouts: "Landing Page," "Product Detail," "Contact Us," "About Us"

#### Phase 2: Regional Sites

5. Create a site for each region: "North America," "UK," "Germany," "France," "Japan," "Brazil," etc.
6. Connect every regional site to the "Global Brand Assets" asset library
7. Apply the "Corporate Brand" **Style Book** to all sites
8. Each regional site gets its own **Navigation Menu** but follows a consistent structure (Home, Products, About, Contact, News)
9. Configure **site languages** — e.g., Germany site has German (default) and English; Japan site has Japanese (default) and English

#### Phase 3: Content Creation and Translation

10. The global team creates content in English using the shared **Web Content Structures**
11. Use **Export for Translation** to generate XLIFF files for each target language
12. Send XLIFF files to professional translators
13. Import completed translations — each regional site now has properly localized content
14. Configure **SEO hreflang settings** on each site so search engines serve the correct language version
15. Use the **Content Dashboard** to track which articles have been translated and which are pending

#### Phase 4: Coordinated Launches

16. When launching a new product globally, create a **Publication** called "Q2 Product Launch"
17. All regional teams make their changes within this publication — adding localized product pages, updating navigation, publishing press releases
18. **Review Changes** to verify all regions are ready
19. **Publish** simultaneously across all regions

**Outcome:** 12 regional sites share a single brand design system and asset library. Regional teams have autonomy for local content while reusing global structures. Translation is handled through industry-standard XLIFF workflows. Product launches are coordinated through Publications.

---

## 5. Partner Enablement and Training Hub
<!-- use-case: partner-portal, industry: [technology, manufacturing, financial-services], features-used: 12 -->

**Business Challenge:** You have 200 channel partners who resell your products. Partners need access to sales enablement materials, product training, co-marketing resources, and deal registration — all in one place. Currently, materials are emailed around, training is ad hoc, and there's no visibility into partner engagement.

**Real-World Example:** The Volkswagen Group in France created a partner portal connecting five brand sites to improve coordination with dealerships and workshops.

### Features Used

| Feature | Guide Section | Role in This Use Case |
|---|---|---|
| **Sites** | 10.1 | Dedicated partner portal site |
| **Documents and Media** | 2.2 | Sales decks, battle cards, product sheets, co-marketing templates |
| **Knowledge Base** | 2.4 | Product training modules, certification guides |
| **Web Content** | 2.1 | Product announcements, program updates, success stories |
| **Blogs** | 2.3 | Partner program news, best practices, thought leadership |
| **Forms** | 7 | Deal registration, MDF (marketing development funds) requests |
| **Workflow** | 9 | Deal registration approval, MDF approval |
| **Custom Objects** | 11.6 | Deal registration tracker, partner certifications |
| **Segments** | 6.1 | Segment by partner tier, region, or specialization |
| **Experiences** | 6.2 | Different dashboards for Gold/Silver/Bronze partners |
| **Digital Sales Room** | 15 | Co-selling rooms for joint client proposals |
| **Notifications** | 5.4 | New content alerts, deal status updates, certification expiry reminders |
| **Publications** | 11.2 | Stage updates to partner resources and portal structure |

### Implementation Walkthrough

#### Phase 1: Content and Resources

1. Create a **Publication** called "Partner Hub Launch" — portal pages, KB articles, blogs, documents, navigation, segments, and experiences are all staged invisibly until you publish
2. Organize **Documents and Media** into folders: Sales Tools, Product Sheets, Co-Marketing Templates, Competitive Battle Cards, Case Studies
2. Create a **Knowledge Base** for training: "Product 101," "Sales Methodology," "Technical Certification," "Partner Program Overview"
3. Publish **Web Content** articles: partner program tiers and benefits, quarterly incentive programs, new product announcements
4. Start a **Blog** for partner success stories and best practices

#### Phase 2: Partner Self-Service

5. Create a **Custom Object** "Deal Registration" with fields: Opportunity Name, Customer Name, Estimated Value, Expected Close Date, Product Interest, Status (Submitted/Approved/Won/Lost)
6. Build a **Form** for deal registration, connected to a **Workflow**: Partner submits → Sales Manager reviews → Approve or Reject with comments
7. Create a "MDF Request" **Form** with budget, activity type, and expected ROI — connected to a marketing approval workflow
8. Configure **Notifications**: deal approved/rejected, MDF approved, new sales tools published

#### Phase 3: Personalization

9. Create **Segments**: "Gold Partners," "Silver Partners," "Bronze Partners," and by specialization ("Cloud Partners," "Security Partners")
10. Create **Experiences** on the partner dashboard:
    - **Gold Partners**: Show deal pipeline summary, dedicated partner manager contact, exclusive resources, early access to new products
    - **Silver/Bronze**: Show standard resources, certification progress, program upgrade path

#### Phase 4: Co-Selling

11. Use **Digital Sales Room** to create branded collaboration spaces for joint client proposals
12. Partners create rooms per prospect, upload proposal documents, and invite the client
13. Track engagement via **DSR Analytics** — see which clients are most engaged with proposal materials
14. The partner manager monitors room activity to identify deals that need support
15. **Publish** the publication — the full partner hub goes live with all resources, forms, and personalization in place
16. For future quarterly updates (new sales tools, updated training), create a new Publication, make the changes, and publish when the full update is reviewed

**Outcome:** Partners have 24/7 access to current sales materials and training. Deal registration is automated with clear approval workflows. Personalization by partner tier drives engagement. Co-selling rooms provide a professional client experience with engagement analytics.

---

## 6. Internal Policy and Compliance Management
<!-- use-case: compliance-portal, industry: [financial-services, healthcare, government, regulated], features-used: 10 -->

**Business Challenge:** Your organization has hundreds of internal policies, procedures, and compliance documents. Employees struggle to find the right policy, don't know when policies change, and there's no way to confirm everyone has read critical updates. Auditors need proof of policy acknowledgment.

### Features Used

| Feature | Guide Section | Role in This Use Case |
|---|---|---|
| **Knowledge Base** | 2.4 | Policy articles with hierarchical organization |
| **Web Content** | 2.1 | Policy change announcements and summaries |
| **Workflow** | 9 | Policy review and approval before publication |
| **Documents and Media** | 2.2 | Downloadable policy PDFs, supporting documents |
| **Forms** | 7 | Policy acknowledgment forms with required signature |
| **Custom Objects** | 11.6 | Policy acknowledgment tracker per employee |
| **Notifications** | 5.4 | Alert employees to new and updated policies |
| **Search** | 4.3 | Full-text search across all policies |
| **Categories and Tags** | 12.4 | Organize by department, regulation, and audience |
| **Content Dashboard** | 4.4 | Track policy freshness, identify outdated policies |
| **Publications** | 11.2 | Stage annual policy review cycles — update all KB articles, pages, and announcements together so employees always see a complete, consistent update |

### Implementation Walkthrough

#### Phase 1: Policy Repository

1. Create **vocabularies**: "Policy Type" (HR, IT, Finance, Safety, Legal), "Regulation" (SOX, HIPAA, GDPR), "Audience" (All Employees, Managers, Finance Team)
2. Build the policy repository in the **Knowledge Base** with a clear hierarchy:
   - HR Policies → Leave, Conduct, Benefits, Remote Work
   - IT Policies → Security, Acceptable Use, Data Classification, BYOD
   - Safety Policies → Emergency Procedures, Workplace Safety, Incident Reporting
3. Set **review dates** on every policy (e.g., annual review) and **expiration dates** for time-sensitive policies
4. Upload original policy PDFs to **Documents and Media** and link them from the KB articles

#### Phase 2: Review and Approval Workflow

5. Create a multi-step **Workflow** for policy changes:
   - Author drafts update → Legal review → Department head approval → Compliance sign-off → Published
6. When a policy is published, a **Notification** is sent to all employees in the affected audience segment
7. Publish a **Web Content** announcement summarizing what changed and why
8. For the annual review cycle (updating 20+ policies at once), create a **Publication** called "2026 Policy Review" — update all policies within it, have compliance review the full set, then publish everything together so employees see a consistent, complete update rather than policies changing one at a time over weeks

#### Phase 3: Acknowledgment Tracking

8. Create a **Custom Object** "Policy Acknowledgment" with fields: Policy Name, Employee, Acknowledged Date, Version Acknowledged
9. Create a **Form** for each critical policy with a checkbox "I have read and understood this policy" and an electronic signature field
10. Connect the form to a **Workflow** that records the acknowledgment in the Custom Object
11. Create a **Collection Display** on each employee's dashboard showing "Policies Pending Your Acknowledgment"

#### Phase 4: Auditing and Reporting

12. Use the **Content Dashboard** to identify policies past their review date
13. Export Custom Object data to generate compliance reports: "98% of employees acknowledged the updated Data Security Policy within 30 days"
14. Set up a **Search** page dedicated to policies with **Category Facets** for quick filtering

**Outcome:** Policies are centrally managed with version control and approval workflows. Employees receive notifications for policy changes and can acknowledge them electronically. Compliance teams can generate acknowledgment reports for auditors. Expired policies are flagged automatically.

---

## 7. Product Launch Campaign
<!-- use-case: product-launch, industry: [all], features-used: 13 -->

**Business Challenge:** Your marketing team is launching a new product next month. You need a landing page, blog series, email-coordinated content, targeted personalization for different audiences, A/B testing on the call-to-action, and a way to coordinate all changes without accidentally publishing too early.

### Features Used

| Feature | Guide Section | Role in This Use Case |
|---|---|---|
| **Content Page Editor** | 3.2 | Design the product landing page |
| **Page Fragments** | 3.3 | Reusable hero, feature grid, and CTA components |
| **Web Content** | 2.1 | Product descriptions, feature breakdowns, pricing tables |
| **Blogs** | 2.3 | Pre-launch blog series building anticipation |
| **Collections** | 4.2 | "Product Launch Content" collection for the landing page |
| **Segments** | 6.1 | Existing customers vs. prospects vs. enterprise leads |
| **Experiences** | 6.2 | Different landing page variations per segment |
| **A/B Testing** | 6.3 | Test CTA button copy for conversion optimization |
| **Publications** | 11.2 | Coordinate all launch content without going live early |
| **Forms** | 7 | "Request a Demo" and "Early Access" signup forms |
| **Notifications** | 5.4 | Internal notifications when forms are submitted |
| **Style Books** | 3.4 | Ensure launch pages match brand guidelines |
| **AI Hub** | 16 | Generate and refine copy for the launch content |

### Implementation Walkthrough

#### Phase 1: Content Preparation (Weeks 1-2)

1. Create a **Publication** called "Q2 Product Launch" — all work happens here, invisible to the public
2. Use **AI Hub > Improve Writing** to polish the product description copy
3. Use **AI Hub > Change Tone** to create variations: professional (enterprise), casual (SMB), technical (developers)
4. Create **Web Content** articles: Product Overview, Key Features, Pricing, Comparison Chart
5. Write 4 **Blog** posts as a pre-launch series: "The Problem We're Solving," "A Sneak Peek," "Behind the Design," "Launch Day"
6. Schedule blog posts with future **Display Dates**: one per week leading up to launch

#### Phase 2: Page Design (Weeks 2-3)

7. Design the landing page in the **Content Page Editor**:
   - Hero section with product image, headline, and CTA button
   - Feature grid fragment showing key benefits
   - Customer testimonial section
   - Pricing comparison table (mapped from Web Content)
   - "Request a Demo" form at the bottom
8. Save the hero + CTA as a **Fragment Composition** for reuse on other product pages
9. Create **Segments**: "Existing Customers," "Enterprise Prospects," "SMB Prospects"
10. Create **Experiences** on the landing page:
    - **Existing Customers**: Headline "Upgrade to [Product] Today" with loyalty discount
    - **Enterprise Prospects**: Headline "Enterprise-Grade [Product]" with ROI calculator
    - **SMB Prospects**: Headline "Get Started with [Product]" with free trial CTA

#### Phase 3: Optimization (Week 3)

11. Set up an **A/B Test** on the default experience:
    - Control: "Start Your Free Trial"
    - Variant: "Get Started Free — No Credit Card"
    - Goal: Button click → demo form submission
    - Traffic split: 50/50, Confidence: 95%
12. Create a "Request a Demo" **Form** with: Name, Email, Company, Role, Message
13. Connect the form to a **Workflow** that notifies the sales team immediately

#### Phase 4: Coordinated Launch (Week 4)

14. In the **Publication**, **Review Changes** to verify everything: landing page, blog posts, web content, navigation updates
15. On launch day, **Publish** the publication — everything goes live simultaneously
16. The first blog post's scheduled **Display Date** has already passed, so it appears immediately; remaining posts auto-publish on schedule
17. Monitor the A/B test results over the following weeks; declare a winner when confidence is reached

**Outcome:** The entire product launch is coordinated through a single Publication with no risk of premature exposure. Different audiences see personalized messaging. The CTA is scientifically optimized through A/B testing. AI Hub accelerates content creation.

---

## 8. IT Service Desk and Help Center
<!-- use-case: it-help-center, industry: [all], features-used: 11 -->

**Business Challenge:** Your IT department handles 200+ tickets per week via email. There's no self-service knowledge base, no way for employees to check ticket status, and IT staff spend hours on repetitive "how do I reset my password?" requests.

### Features Used

| Feature | Guide Section | Role in This Use Case |
|---|---|---|
| **Knowledge Base** | 2.4 | IT how-to articles, troubleshooting guides |
| **Search** | 4.3 | Employees search for solutions before submitting tickets |
| **Forms** | 7 | IT service request forms (new equipment, access request, bug report) |
| **Workflow** | 9 | Ticket routing: triage → assignment → resolution → closure |
| **Custom Objects** | 11.6 | "IT Ticket" with priority, status, assigned agent, SLA deadline |
| **CMP (Projects & Tasks)** | 14 | Track large IT projects (migrations, upgrades) |
| **Notifications** | 5.4 | Ticket status updates, SLA breach warnings |
| **Categories and Tags** | 12.4 | Categorize by: Hardware, Software, Network, Access, Security |
| **Asset Publisher** | 4.1 | "Known Issues" banner showing active incidents |
| **AI Hub** | 16 | Help employees describe issues; help agents draft responses |
| **Roles and Permissions** | 10.3 | IT Agent, IT Manager, Employee (requester) roles |
| **Publications** | 11.2 | Stage help center redesigns and bulk KB updates |

### Implementation Walkthrough

#### Phase 1: Self-Service Knowledge Base

1. Build a **Knowledge Base** with categories: "Getting Started" (VPN, email, printer), "Software" (installation guides per app), "Hardware" (laptop setup, monitor, peripherals), "Security" (password reset, MFA, phishing)
2. Publish the top-10 "most asked" articles prominently using a **Collection**
3. Add a **Search Bar** to every page of the help center
4. Configure **Search Synonyms**: "wifi" = "wireless" = "network," "password" = "login" = "credentials"

#### Phase 2: Ticket System

5. Create a **Custom Object** "IT Ticket" with fields: Ticket Number (auto-generated), Subject, Description, Category (picklist), Priority (Low/Medium/High/Critical), Status (Open/Assigned/In Progress/Waiting/Resolved/Closed), Assigned Agent, SLA Deadline, Resolution Notes
6. Create specialized **Forms**:
   - "General IT Request" — generic issue reporting
   - "New Equipment Request" — laptop, monitor, phone with manager approval
   - "Access Request" — system access with security review
7. Connect each form to a **Workflow**:
   - General: Auto-assign based on category (Network issues → Network team)
   - Equipment: Employee submits → Manager approves → IT procurement
   - Access: Employee submits → Manager approves → Security review → IT provisions

#### Phase 3: Agent Experience

8. Create an IT Agent dashboard page showing:
   - **Asset Publisher** filtered to "My Assigned Tickets" (Status = Open or In Progress)
   - Ticket priority breakdown by category
   - SLA countdown for high-priority items
9. Use the **CMP** for larger IT projects: "Office Network Upgrade" as a Project with tasks for each floor
10. **AI Hub** integration: agents use the "Make Shorter" workflow to condense resolution notes, or "Change Tone" to make responses more user-friendly

#### Phase 4: Proactive Communication

11. Use an **Asset Publisher** on the help center homepage to show a "Known Issues" banner — when a system is down, IT publishes a web content article tagged "active-incident" that automatically appears
12. Configure **Notifications**: ticket assigned, status changed, resolved, SLA approaching breach
13. Use the **Content Dashboard** to track which KB articles are most viewed — these indicate the most common issues
14. When restructuring the KB hierarchy or redesigning the help center pages, use a **Publication** so the existing help center stays fully functional while you make changes. KB articles, pages, and navigation are all tracked. (IT tickets created by employees are real-time Custom Object entries managed by Workflow, not staged.)

**Outcome:** Employees resolve common issues via the Knowledge Base before submitting tickets. Tickets are automatically routed to the right team. SLAs are tracked. IT managers have visibility into workload and trends.

---

## 9. B2B Commerce Storefront with Account-Based Pricing
<!-- use-case: b2b-commerce, industry: [manufacturing, wholesale, distribution], features-used: 11 -->

**Business Challenge:** You sell industrial supplies to 3,000+ business accounts. Each account has negotiated pricing, different catalog access, and multi-user purchasing with approval workflows. Buyers need to quickly reorder, check inventory, and track shipments.

### Features Used

| Feature | Guide Section | Role in This Use Case |
|---|---|---|
| **Commerce: Products & Catalogs** | 8.1 | Full product catalog with specifications |
| **Commerce: Orders & Checkout** | 8.2 | Multi-step B2B checkout with PO numbers |
| **Commerce: Pricing & Discounts** | 8.3 | Account-specific price lists, tier pricing, promotions |
| **Commerce: Inventory** | 8.4 | Real-time stock per warehouse |
| **Commerce: Accounts** | 8.5 | Business accounts with hierarchies and roles |
| **Search** | 4.3 | Part number search with synonyms |
| **Segments** | 6.1 | Segment by account tier for personalized merchandising |
| **Experiences** | 6.2 | VIP accounts see exclusive products and promotions |
| **Notifications** | 5.4 | Order confirmation, shipment tracking, low-stock alerts |
| **Content Page Editor** | 3.2 | Custom storefront pages |
| **Workflow** | 9 | Order approval for high-value purchases |
| **Publications** | 11.2 | Stage storefront redesigns, promotions, and catalog restructuring |

### Implementation Walkthrough

#### Phase 1: Catalog and Pricing

1. Create **Commerce Catalogs**: "Full Catalog" (all products), "Restricted Catalog" (controlled substances, restricted items)
2. Add products with full **specifications**, images, and documentation attachments
3. Create **Price Lists**: "Standard," "Preferred" (10% off), "Strategic" (20% off), "Contract-ABC Corp" (custom negotiated)
4. Configure **Tier Pricing** on high-volume items (e.g., 1-99 at $5.00, 100-499 at $4.50, 500+ at $4.00)
5. Set up **Discounts**: "First Order 5% Off" coupon, "Free Shipping over $500" automatic promotion

#### Phase 2: Account Management

6. Create **Commerce Accounts** for each business customer
7. Assign accounts to **Account Groups**: "Standard," "Preferred," "Strategic"
8. Each group links to the appropriate **Price List** and **Catalog** access
9. Define **Account Roles**: "Account Administrator" (manage users, view all orders), "Order Manager" (place orders up to $10,000), "Buyer" (place orders up to $1,000, requires approval above that)
10. Configure **Workflow** for order approval: orders over $5,000 require Account Administrator approval before processing

#### Phase 3: Shopping Experience

11. Design the storefront using the **Content Page Editor** with product grid, category navigation, and quick-reorder section
12. Create **Segments**: "VIP Accounts" (Strategic group), "New Accounts" (registered < 90 days)
13. Create **Experiences**:
    - **VIP**: Show exclusive product section, dedicated account rep contact, and priority shipping option
    - **New Accounts**: Show "Getting Started" guide, popular products, and first-order discount banner
14. Enable **Search** with part-number lookup and synonym sets

#### Phase 4: Order and Fulfillment

15. Configure **Notifications**: order confirmation email, order shipped with tracking number, delivery confirmation, invoice available
16. Enable quick reorder: buyers can view past orders and reorder with one click
17. Show real-time **Inventory** levels per warehouse so buyers know availability before ordering
18. For seasonal promotions or storefront redesigns, use a **Publication** — stage the new storefront pages, update product descriptions, adjust price lists, and publish everything during a planned window so buyers never see a half-built promotion. (Inventory levels and active orders are real-time data and don't need staging.)

**Outcome:** 3,000+ accounts each see their own negotiated pricing automatically. Buyers can self-serve for ordering, reordering, and inventory checks. High-value orders go through approval workflows. VIP accounts get a premium experience.

---

## 10. AI-Powered Content Operations Center
<!-- use-case: content-ops, industry: [media, marketing, enterprise], features-used: 12 -->

**Business Challenge:** Your content team produces 50+ articles per week across multiple sites and languages. Quality is inconsistent, the review process is slow, and translating content into 5 languages creates a bottleneck. You need an efficient content pipeline that leverages AI to accelerate creation, maintain quality, and automate translation workflows.

### Features Used

| Feature | Guide Section | Role in This Use Case |
|---|---|---|
| **CMS** | 13 | Unified content management with bulk operations |
| **AI Hub** | 16 | Polish writing, fix grammar, adjust tone, condense content |
| **Workflow** | 9 | Multi-step review: draft → AI review → editor → publish |
| **Translation** | 11.3 | XLIFF export/import for professional translation |
| **Content Dashboard** | 4.4 | Monitor content production, identify gaps, track translation status |
| **Web Content (Structures)** | 2.1 | Consistent article structures across all content types |
| **Categories and Tags** | 12.4 | Taxonomy for content organization and audit |
| **Collections** | 4.2 | Curated content sets for featured sections |
| **Publications** | 11.2 | Batch publishing for coordinated content releases |
| **Asset Libraries** | 11.5 | Shared images and media across all sites |
| **Bulk Operations (CMS)** | 13.6 | Mass categorization, tagging, and status updates |
| **Notifications** | 5.4 | Review reminders, translation completion alerts |

### Implementation Walkthrough

#### Phase 1: Content Pipeline Setup

1. Define **Web Content Structures** for each content type: "News Article," "How-To Guide," "Product Update," "Case Study," "Press Release"
2. Create **Vocabularies**: "Content Type," "Product Area," "Target Audience," "Content Status" (Draft, In Review, Translated, Published)
3. Set up a multi-step **Workflow**:
   - Writer creates draft → AI polishing step → Editor review → Final approval → Published
4. At the AI step, the reviewer uses **AI Hub**:
   - Run "Improve Writing" on the article body
   - Run "Fix Spelling and Grammar" for cleanup
   - Run "Make Shorter" if the article exceeds target word count
   - Run "Change Tone" if the piece needs to be more formal/casual

#### Phase 2: CMS Workspace

5. Set up the **CMS** with Spaces for each content team: "News," "Product Content," "Marketing," "Training"
6. Each Space has its own content creators and editors
7. Use the **CMS Dashboard** to track recent assets, pending workflow tasks, and quick actions
8. Use **Bulk Operations** for mass maintenance:
   - Select 50 articles → Edit Categories → Add "Q2 Campaign" category
   - Select expired articles → Change Status → Archive
   - Select draft articles past deadline → Edit Tags → Add "overdue"

#### Phase 3: Translation at Scale

9. Use the **Content Dashboard** to filter all published English articles that lack translations
10. Select articles in batch → **Export for Translation** as XLIFF files for 5 target languages
11. Send XLIFF files to translation service (or use **Auto Translate** for first drafts)
12. When translations return, **Import Translations** — the CMS applies them to the correct language versions
13. Use the **Content Dashboard** to verify: filter by "Translation Status = Pending" to see what's still outstanding
14. Use **Bulk Operations** to mark completed translations as "Translated"

#### Phase 4: Coordinated Publishing

15. Create a **Publication** for each major content release (e.g., "Monthly Product Update — April")
16. All articles, translations, and page updates happen inside the publication
17. Use **Collections** to curate "Featured This Month" and "Editor's Picks" content sets
18. **Publish** the publication — all content goes live simultaneously across all sites and languages
19. Use the **Content Dashboard** audit chart to verify content coverage across categories and identify gaps for next month's planning

**Outcome:** Content production is accelerated by AI-assisted writing and grammar checking. A structured workflow ensures consistent quality. Translation is handled in batch through XLIFF workflows. The Content Dashboard provides visibility into production status, coverage gaps, and translation progress. Bulk operations eliminate tedious manual updates.

---

## Feature Frequency Across Use Cases

This matrix shows which Liferay features appear most frequently across all 10 use cases, highlighting the platform's most versatile capabilities:

| Feature | Use Cases | Count |
|---|---|---|
| **Workflow** | 1, 2, 3, 5, 6, 7, 8, 9, 10 | 9 |
| **Notifications** | 1, 2, 3, 5, 6, 7, 8, 9, 10 | 9 |
| **Custom Objects** | 1, 2, 3, 5, 6, 8 | 6 |
| **Knowledge Base** | 1, 2, 3, 5, 6, 8 | 6 |
| **Forms** | 1, 2, 5, 6, 7, 8 | 6 |
| **Segments** | 1, 2, 3, 4, 5, 7, 9 | 7 |
| **Experiences** | 1, 2, 3, 4, 5, 7, 9 | 7 |
| **Search** | 1, 3, 6, 8, 9 | 5 |
| **Categories and Tags** | 1, 3, 6, 8, 10 | 5 |
| **Roles and Permissions** | 1, 2, 3, 8 | 4 |
| **Documents and Media** | 1, 2, 3, 5, 6 | 5 |
| **Web Content** | 1, 2, 3, 4, 5, 6, 7, 10 | 8 |
| **Content Page Editor** | 7, 9 | 2 |
| **Collections** | 7, 10 | 2 |
| **Content Dashboard** | 4, 6, 10 | 3 |
| **Publications** | 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 | **10** |
| **Asset Libraries** | 4, 10 | 2 |
| **Translation** | 4, 10 | 2 |
| **Commerce** | 3, 9 | 2 |
| **AI Hub** | 7, 8, 10 | 3 |
| **CMS** | 10 | 1 |
| **CMP** | 2, 8 | 2 |
| **Digital Sales Room** | 5 | 1 |
| **Blogs** | 2, 5 | 2 |
| **A/B Testing** | 7 | 1 |
| **Style Books** | 4, 7 | 2 |

**Top 5 Most Versatile Features:**
1. **Publications** (10/10 use cases) — the foundation of safe content management; every use case requires the ability to make changes without affecting live users
2. **Workflow** (9/10) — the backbone of any approval or routing process
3. **Notifications** (9/10) — keeping everyone informed across every scenario
4. **Web Content** (8/10) — the fundamental content building block
5. **Segments + Experiences** (7/10 each) — personalization drives engagement in almost every use case

---

*These use cases are based on common Liferay DXP deployment patterns observed across enterprise customers in manufacturing, financial services, technology, healthcare, and government sectors. All feature references map to sections in the LIFERAY_DXP_END_USER_GUIDE.md.*

Sources:
- [Liferay Solutions](https://www.liferay.com/solutions)
- [Liferay Case Studies](https://www.liferay.com/resources/case-studies)
- [Liferay Customer Portals](https://www.liferay.com/solutions/customer-portals)
- [Liferay Partner Portals](https://www.liferay.com/solutions/partner-portals)
- [Liferay Modern Intranets](https://www.liferay.com/solutions/intranets)
- [Liferay Digital Commerce](https://www.liferay.com/solutions/digital-commerce)
- [Liferay Manufacturing](https://www.liferay.com/industries/manufacturing)
- [Liferay Capabilities](https://www.liferay.com/capabilities)
- [Seven Popular Use Cases](https://www.liferay.com/blog/liferay-experience/how-digital-leaders-use-liferay-seven-popular-use-cases)
- [Enhance Efficiency with Customer Self-Service Portal](https://www.liferay.com/resources/product-info/Enhance+Efficiency+with+a+Customer+Self-Service+Portal)
