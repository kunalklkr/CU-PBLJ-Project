<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Resume Builder</title>
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
                <span>Welcome, ${sessionScope.user.fullName}</span>
                <a href="${pageContext.request.contextPath}/profile" class="btn btn-secondary">Profile</a>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-secondary">Logout</a>
            </div>
        </div>
    </nav>
    
    <div class="container">
        <div class="dashboard-header">
            <h2>My Resumes</h2>
            <a href="${pageContext.request.contextPath}/resume/" class="btn btn-primary btn-icon">
                <i class="icon">add_circle</i>
                <span>Create New Resume</span>
            </a>
        </div>
        
        <!-- Statistics Cards -->
        <div class="stats-container">
            <div class="stat-card">
                <div class="stat-icon">
                    <i class="icon">description</i>
                </div>
                <div class="stat-content">
                    <h3>${totalResumes}</h3>
                    <p>Total Resumes</p>
                </div>
            </div>
            
            <c:if test="${lastCreated != null}">
                <div class="stat-card">
                    <div class="stat-icon">
                        <i class="icon">auto_awesome</i>
                    </div>
                    <div class="stat-content">
                        <h3>${lastCreated.title}</h3>
                        <p>Last Created</p>
                        <small>${lastCreated.createdAt}</small>
                    </div>
                </div>
            </c:if>
            
            <c:if test="${lastUpdated != null}">
                <div class="stat-card">
                    <div class="stat-icon">
                        <i class="icon">update</i>
                    </div>
                    <div class="stat-content">
                        <h3>${lastUpdated.title}</h3>
                        <p>Last Updated</p>
                        <small>${lastUpdated.updatedAt}</small>
                    </div>
                </div>
            </c:if>
        </div>
        
        <h3 class="section-title">All Resumes</h3>
        <div class="resume-grid">
            <c:choose>
                <c:when test="${empty resumes}">
                    <div class="empty-state">
                        <p>You haven't created any resumes yet.</p>
                        <a href="${pageContext.request.contextPath}/resume/" class="btn btn-primary btn-icon">
                            <i class="icon">add_circle</i>
                            <span>Create Your First Resume</span>
                        </a>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="resume" items="${resumes}">
                        <div class="resume-card">
                            <h3>${resume.title}</h3>
                            <p class="template-badge">${resume.template} template</p>
                            <p class="date">Last updated: ${resume.updatedAt}</p>
                            <div class="card-actions">
                                <a href="${pageContext.request.contextPath}/resume/preview/${resume.id}" class="btn btn-sm btn-icon">
                                    <i class="icon icon-sm">visibility</i>
                                    <span>Preview</span>
                                </a>
                                <a href="${pageContext.request.contextPath}/resume/edit/${resume.id}" class="btn btn-sm btn-secondary btn-icon">
                                    <i class="icon icon-sm">edit</i>
                                    <span>Edit</span>
                                </a>
                                <a href="${pageContext.request.contextPath}/download/pdf/${resume.id}" class="btn btn-sm btn-success btn-icon">
                                    <i class="icon icon-sm">picture_as_pdf</i>
                                    <span>PDF</span>
                                </a>
                                <a href="${pageContext.request.contextPath}/download/xml/${resume.id}" class="btn btn-sm btn-info btn-icon">
                                    <i class="icon icon-sm">code</i>
                                    <span>XML</span>
                                </a>
                                <button onclick="deleteResume('${resume.id}')" class="btn btn-sm btn-danger btn-icon">
                                    <i class="icon icon-sm">delete</i>
                                    <span>Delete</span>
                                </button>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    
    
    <%@ include file="/WEB-INF/includes/footer.jsp" %>
    
    <script src="${pageContext.request.contextPath}/js/theme-toggle.js"></script>
    <script src="${pageContext.request.contextPath}/js/animations.js"></script>
    <script>
        function deleteResume(id) {
            if (confirm('Are you sure you want to delete this resume? This action cannot be undone.')) {
                // Create a form and submit it
                const form = document.createElement('form');
                form.method = 'POST';
                form.action = '${pageContext.request.contextPath}/resume/delete/' + id;
                document.body.appendChild(form);
                form.submit();
            }
        }
    </script>
</body>
</html>
