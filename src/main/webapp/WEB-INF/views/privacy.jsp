<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Help Center - Resume Builder</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/material-theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pages.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/refined-design.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/premium-design.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dark-theme.css">
</head>
<body>
    <nav class="navbar">
        <div class="nav-container">
            <h1>Resume Builder</h1>
            <div class="nav-links">
                <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary">Dashboard</a>
                <a href="${pageContext.request.contextPath}/login" class="btn btn-primary">Login</a>
            </div>
        </div>
    </nav>
    
    <div class="container">
        <div class="page-header">
            <h1><i class="icon icon-lg">help</i> Help Center</h1>
            <p>Find answers to common questions and get help with Resume Builder</p>
        </div>
        
        <div class="help-grid">
            <div class="help-card">
                <div class="help-icon">
                    <i class="icon">rocket_launch</i>
                </div>
                <h3>Getting Started</h3>
                <p>Learn how to create your first resume and navigate the platform.</p>
                <ul class="help-list">
                    <li><i class="icon icon-sm">check_circle</i> Create an account</li>
                    <li><i class="icon icon-sm">check_circle</i> Build your first resume</li>
                    <li><i class="icon icon-sm">check_circle</i> Choose a template</li>
                    <li><i class="icon icon-sm">check_circle</i> Download your resume</li>
                </ul>
            </div>
            
            <div class="help-card">
                <div class="help-icon">
                    <i class="icon">edit</i>
                </div>
                <h3>Editing Resumes</h3>
                <p>Tips for creating and editing professional resumes.</p>
                <ul class="help-list">
                    <li><i class="icon icon-sm">check_circle</i> Add work experience</li>
                    <li><i class="icon icon-sm">check_circle</i> Include education</li>
                    <li><i class="icon icon-sm">check_circle</i> List your skills</li>
                    <li><i class="icon icon-sm">check_circle</i> Showcase projects</li>
                </ul>
            </div>
            
            <div class="help-card">
                <div class="help-icon">
                    <i class="icon">download</i>
                </div>
                <h3>Downloading</h3>
                <p>Export your resume in multiple formats.</p>
                <ul class="help-list">
                    <li><i class="icon icon-sm">check_circle</i> PDF format</li>
                    <li><i class="icon icon-sm">check_circle</i> XML format</li>
                    <li><i class="icon icon-sm">check_circle</i> Preview before download</li>
                    <li><i class="icon icon-sm">check_circle</i> Multiple versions</li>
                </ul>
            </div>
            
            <div class="help-card">
                <div class="help-icon">
                    <i class="icon">person</i>
                </div>
                <h3>Account Management</h3>
                <p>Manage your profile and account settings.</p>
                <ul class="help-list">
                    <li><i class="icon icon-sm">check_circle</i> Update profile</li>
                    <li><i class="icon icon-sm">check_circle</i> Change password</li>
                    <li><i class="icon icon-sm">check_circle</i> Manage resumes</li>
                    <li><i class="icon icon-sm">check_circle</i> Delete account</li>
                </ul>
            </div>
            
            <div class="help-card">
                <div class="help-icon">
                    <i class="icon">palette</i>
                </div>
                <h3>Templates</h3>
                <p>Choose from professional resume templates.</p>
                <ul class="help-list">
                    <li><i class="icon icon-sm">check_circle</i> Classic template</li>
                    <li><i class="icon icon-sm">check_circle</i> Modern template</li>
                    <li><i class="icon icon-sm">check_circle</i> Switch templates</li>
                    <li><i class="icon icon-sm">check_circle</i> Customize design</li>
                </ul>
            </div>
            
            <div class="help-card">
                <div class="help-icon">
                    <i class="icon">contact_support</i>
                </div>
                <h3>Need More Help?</h3>
                <p>Can't find what you're looking for?</p>
                <a href="${pageContext.request.contextPath}/contact" class="btn btn-primary btn-icon">
                    <i class="icon">email</i>
                    <span>Contact Support</span>
                </a>
            </div>
        </div>
        
        <div class="faq-section">
            <h2>Frequently Asked Questions</h2>
            
            <div class="faq-item">
                <h4><i class="icon">help_outline</i> How do I create a resume?</h4>
                <p>Click on "Create New Resume" from your dashboard, fill in your information, choose a template, and click "Create Resume".</p>
            </div>
            
            <div class="faq-item">
                <h4><i class="icon">help_outline</i> Can I edit my resume after creating it?</h4>
                <p>Yes! Go to your dashboard, find the resume you want to edit, and click the "Edit" button.</p>
            </div>
            
            <div class="faq-item">
                <h4><i class="icon">help_outline</i> What formats can I download my resume in?</h4>
                <p>You can download your resume in PDF format (for printing and sharing) or XML format (for data backup).</p>
            </div>
            
            <div class="faq-item">
                <h4><i class="icon">help_outline</i> Can I create multiple resumes?</h4>
                <p>Absolutely! You can create as many resumes as you need for different job applications.</p>
            </div>
        </div>
    </div>
    
    <%@ include file="/WEB-INF/includes/footer.jsp" %>
    
    <script src="${pageContext.request.contextPath}/js/theme-toggle.js"></script>
    <script src="${pageContext.request.contextPath}/js/animations.js"></script>
    
    <style>
        .page-header {
            text-align: center;
            margin-bottom: 48px;
        }
        
        .page-header h1 {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 16px;
            margin-bottom: 16px;
        }
        
        .page-header p {
            color: var(--text-secondary);
            font-size: 1.125rem;
        }
        
        .help-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 24px;
            margin-bottom: 64px;
        }
        
        .help-card {
            background: var(--surface);
            padding: 32px;
            border-radius: 16px;
            box-shadow: var(--shadow-1);
            transition: all 0.3s;
        }
        
        .help-card:hover {
            transform: translateY(-8px);
            box-shadow: var(--shadow-3);
        }
        
        .help-icon {
            width: 64px;
            height: 64px;
            border-radius: 16px;
            background: var(--primary-gradient);
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 24px;
        }
        
        .help-icon i {
            color: white;
            font-size: 32px;
        }
        
        .help-card h3 {
            margin-bottom: 12px;
        }
        
        .help-card p {
            color: var(--text-secondary);
            margin-bottom: 16px;
        }
        
        .help-list {
            list-style: none;
            padding: 0;
        }
        
        .help-list li {
            display: flex;
            align-items: center;
            gap: 8px;
            margin-bottom: 8px;
            color: var(--text-secondary);
        }
        
        .help-list i {
            color: var(--success-color);
        }
        
        .faq-section {
            background: var(--surface);
            padding: 48px;
            border-radius: 16px;
            box-shadow: var(--shadow-1);
            margin-bottom: 48px;
        }
        
        .faq-section h2 {
            text-align: center;
            margin-bottom: 32px;
        }
        
        .faq-item {
            padding: 24px;
            border-left: 4px solid var(--primary-color);
            background: var(--background);
            border-radius: 8px;
            margin-bottom: 16px;
        }
        
        .faq-item h4 {
            display: flex;
            align-items: center;
            gap: 12px;
            margin-bottom: 12px;
        }
        
        .faq-item p {
            color: var(--text-secondary);
            margin: 0;
        }
    </style>
</body>
</html>
