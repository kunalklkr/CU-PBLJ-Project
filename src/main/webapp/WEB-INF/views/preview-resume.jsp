<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Preview Resume - Resume Builder</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/material-theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pages.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/refined-design.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/premium-design.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dark-theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/preview.css">
</head>
<body>
    <nav class="navbar">
        <div class="nav-container">
            <h1>Resume Builder</h1>
            <div class="nav-links">
                <a href="${pageContext.request.contextPath}/download/pdf/${resume.id}" class="btn btn-success btn-icon">
                    <i class="icon">picture_as_pdf</i>
                    <span>Download PDF</span>
                </a>
                <a href="${pageContext.request.contextPath}/download/xml/${resume.id}" class="btn btn-info btn-icon">
                    <i class="icon">code</i>
                    <span>Download XML</span>
                </a>
                <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary btn-icon">
                    <i class="icon">dashboard</i>
                    <span>Dashboard</span>
                </a>
                <a href="${pageContext.request.contextPath}/profile" class="btn btn-secondary btn-icon">
                    <i class="icon">person</i>
                    <span>Profile</span>
                </a>
            </div>
        </div>
    </nav>
    
    <div class="preview-container">
        <div class="resume-preview ${resume.template}">
            <c:if test="${resume.personalInfo != null}">
                <div class="header">
                    <h1>${resume.personalInfo.fullName}</h1>
                    <div class="contact-info">
                        <c:if test="${not empty resume.personalInfo.email}">
                            <span>${resume.personalInfo.email}</span>
                        </c:if>
                        <c:if test="${not empty resume.personalInfo.phone}">
                            <span>${resume.personalInfo.phone}</span>
                        </c:if>
                        <c:if test="${not empty resume.personalInfo.address}">
                            <span>${resume.personalInfo.address}</span>
                        </c:if>
                    </div>
                    <c:if test="${not empty resume.personalInfo.linkedin || not empty resume.personalInfo.github}">
                        <div class="links">
                            <c:if test="${not empty resume.personalInfo.linkedin}">
                                <span>${resume.personalInfo.linkedin}</span>
                            </c:if>
                            <c:if test="${not empty resume.personalInfo.github}">
                                <span>${resume.personalInfo.github}</span>
                            </c:if>
                        </div>
                    </c:if>
                </div>
                
                <c:if test="${not empty resume.personalInfo.summary}">
                    <div class="section">
                        <h2>Professional Summary</h2>
                        <p>${resume.personalInfo.summary}</p>
                    </div>
                </c:if>
            </c:if>
            
            <c:if test="${not empty resume.education}">
                <div class="section">
                    <h2>Education</h2>
                    <c:forEach var="edu" items="${resume.education}">
                        <div class="item">
                            <div class="item-header">
                                <strong>${edu.degree} in ${edu.fieldOfStudy}</strong>
                                <span class="date">${edu.startDate} - ${edu.endDate}</span>
                            </div>
                            <div>${edu.institution}</div>
                            <c:if test="${not empty edu.grade}">
                                <div>Grade: ${edu.grade}</div>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            
            <c:if test="${not empty resume.experience}">
                <div class="section">
                    <h2>Experience</h2>
                    <c:forEach var="exp" items="${resume.experience}">
                        <div class="item">
                            <div class="item-header">
                                <strong>${exp.position}</strong>
                                <span class="date">${exp.startDate} - ${exp.endDate}</span>
                            </div>
                            <div>${exp.company}</div>
                            <c:if test="${not empty exp.description}">
                                <p>${exp.description}</p>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            
            <c:if test="${not empty resume.projects}">
                <div class="section">
                    <h2>Projects</h2>
                    <c:forEach var="proj" items="${resume.projects}">
                        <div class="item">
                            <strong>${proj.name}</strong>
                            <c:if test="${not empty proj.description}">
                                <p>${proj.description}</p>
                            </c:if>
                            <c:if test="${not empty proj.technologies}">
                                <div><em>Technologies: ${proj.technologies}</em></div>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            
            <c:if test="${not empty resume.skills}">
                <div class="section">
                    <h2>Skills</h2>
                    <div class="skills-list">
                        <c:forEach var="skill" items="${resume.skills}" varStatus="status">
                            ${skill}<c:if test="${!status.last}">, </c:if>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
            
            <c:if test="${not empty resume.achievements}">
                <div class="section">
                    <h2>Achievements & Awards</h2>
                    <c:forEach var="ach" items="${resume.achievements}">
                        <div class="item">
                            <div class="item-header">
                                <strong>${ach.title}</strong>
                                <c:if test="${not empty ach.date}">
                                    <span class="date">${ach.date}</span>
                                </c:if>
                            </div>
                            <c:if test="${not empty ach.description}">
                                <p>${ach.description}</p>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </div>
    
    <script src="${pageContext.request.contextPath}/js/theme-toggle.js"></script>
</body>
</html>
