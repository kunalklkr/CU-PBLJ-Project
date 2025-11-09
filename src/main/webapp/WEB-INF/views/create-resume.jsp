<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Resume - Resume Builder</title>
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
                <a href="${pageContext.request.contextPath}/profile" class="btn btn-secondary">Profile</a>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-secondary">Logout</a>
            </div>
        </div>
    </nav>
    
    <div class="container">
        <h2>Create New Resume</h2>
        
        <form method="post" action="${pageContext.request.contextPath}/resume/" id="resumeForm">
            <div class="form-section">
                <h3>Resume Details</h3>
                <div class="form-group">
                    <label for="title">Resume Title *</label>
                    <input type="text" id="title" name="title" required placeholder="e.g., Software Engineer Resume">
                </div>
                
                <div class="form-group">
                    <label for="template">Template *</label>
                    <select id="template" name="template" required>
                        <option value="classic">Classic</option>
                        <option value="modern">Modern</option>
                    </select>
                </div>
            </div>
            
            <div class="form-section">
                <h3>Personal Information</h3>
                <div class="form-row">
                    <div class="form-group">
                        <label for="fullName">Full Name *</label>
                        <input type="text" id="fullName" name="fullName" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email *</label>
                        <input type="email" id="email" name="email" required>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="phone">Phone</label>
                        <input type="tel" id="phone" name="phone">
                    </div>
                    <div class="form-group">
                        <label for="address">Address</label>
                        <input type="text" id="address" name="address">
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="linkedin">LinkedIn</label>
                        <input type="url" id="linkedin" name="linkedin" placeholder="https://linkedin.com/in/yourprofile">
                    </div>
                    <div class="form-group">
                        <label for="github">GitHub</label>
                        <input type="url" id="github" name="github" placeholder="https://github.com/yourusername">
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="summary">Professional Summary</label>
                    <textarea id="summary" name="summary" rows="4" placeholder="Brief overview of your professional background"></textarea>
                </div>
            </div>
            
            <div class="form-section">
                <h3>Education</h3>
                <div id="educationContainer"></div>
                <button type="button" onclick="addEducation()" class="btn btn-secondary">Add Education</button>
            </div>
            
            <div class="form-section">
                <h3>Experience</h3>
                <div id="experienceContainer"></div>
                <button type="button" onclick="addExperience()" class="btn btn-secondary">Add Experience</button>
            </div>
            
            <div class="form-section">
                <h3>Projects</h3>
                <div id="projectsContainer"></div>
                <button type="button" onclick="addProject()" class="btn btn-secondary">Add Project</button>
            </div>
            
            <div class="form-section">
                <h3>Skills</h3>
                <div class="form-group">
                    <label for="skills">Skills (comma-separated)</label>
                    <textarea id="skills" name="skills" rows="3" placeholder="Java, JavaScript, Python, SQL, Git"></textarea>
                </div>
            </div>
            
            <div class="form-section">
                <h3>Achievements</h3>
                <div id="achievementsContainer"></div>
                <button type="button" onclick="addAchievement()" class="btn btn-secondary">Add Achievement</button>
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Create Resume</button>
                <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
    
    
    <%@ include file="/WEB-INF/includes/footer.jsp" %>
    
    <script src="${pageContext.request.contextPath}/js/theme-toggle.js"></script>
    <script src="${pageContext.request.contextPath}/js/resume-form.js"></script>
    <script src="${pageContext.request.contextPath}/js/animations.js"></script>
</body>
</html>
