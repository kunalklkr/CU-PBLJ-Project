<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Resume - Resume Builder</title>
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
        <h2>Edit Resume</h2>
        
        <form id="editResumeForm">
            <input type="hidden" id="resumeId" value="${resume.id}">
            
            <div class="form-section">
                <h3>Resume Details</h3>
                <div class="form-group">
                    <label for="title">Resume Title *</label>
                    <input type="text" id="title" name="title" required value="${resume.title}">
                </div>
                
                <div class="form-group">
                    <label for="template">Template *</label>
                    <select id="template" name="template" required>
                        <option value="classic" ${resume.template == 'classic' ? 'selected' : ''}>Classic</option>
                        <option value="modern" ${resume.template == 'modern' ? 'selected' : ''}>Modern</option>
                    </select>
                </div>
            </div>
            
            <div class="form-section">
                <h3>Personal Information</h3>
                <div class="form-row">
                    <div class="form-group">
                        <label for="fullName">Full Name *</label>
                        <input type="text" id="fullName" name="fullName" required value="${resume.personalInfo.fullName}">
                    </div>
                    <div class="form-group">
                        <label for="email">Email *</label>
                        <input type="email" id="email" name="email" required value="${resume.personalInfo.email}">
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="phone">Phone</label>
                        <input type="tel" id="phone" name="phone" value="${resume.personalInfo.phone}">
                    </div>
                    <div class="form-group">
                        <label for="address">Address</label>
                        <input type="text" id="address" name="address" value="${resume.personalInfo.address}">
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="linkedin">LinkedIn</label>
                        <input type="url" id="linkedin" name="linkedin" value="${resume.personalInfo.linkedin}">
                    </div>
                    <div class="form-group">
                        <label for="github">GitHub</label>
                        <input type="url" id="github" name="github" value="${resume.personalInfo.github}">
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="summary">Professional Summary</label>
                    <textarea id="summary" name="summary" rows="4">${resume.personalInfo.summary}</textarea>
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
                    <textarea id="skills" name="skills" rows="3"><c:forEach var="skill" items="${resume.skills}" varStatus="status">${skill}<c:if test="${!status.last}">, </c:if></c:forEach></textarea>
                </div>
            </div>
            
            <div class="form-section">
                <h3>Achievements</h3>
                <div id="achievementsContainer"></div>
                <button type="button" onclick="addAchievement()" class="btn btn-secondary">Add Achievement</button>
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Update Resume</button>
                <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
    
    <script>
        const resumeData = ${resumeJson};
    </script>
    
    <%@ include file="/WEB-INF/includes/footer.jsp" %>
    
    <script src="${pageContext.request.contextPath}/js/theme-toggle.js"></script>
    <script src="${pageContext.request.contextPath}/js/resume-form.js"></script>
    <script src="${pageContext.request.contextPath}/js/edit-resume.js"></script>
    <script src="${pageContext.request.contextPath}/js/animations.js"></script>
</body>
</html>
