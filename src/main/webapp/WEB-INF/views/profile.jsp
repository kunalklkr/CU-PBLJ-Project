<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profile - Resume Builder</title>
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
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-secondary">Logout</a>
            </div>
        </div>
    </nav>
    
    <div class="container">
        <div class="profile-header">
            <div class="profile-avatar">
                <div class="avatar-circle">${user.fullName.substring(0, 1).toUpperCase()}</div>
            </div>
            <div class="profile-info">
                <h2>${user.fullName}</h2>
                <p>${user.email}</p>
            </div>
        </div>
        
        <div class="profile-content">
            <!-- Update Profile Section -->
            <div class="profile-section">
                <h3>Personal Information</h3>
                
                <c:if test="${not empty success}">
                    <div class="success-message">${success}</div>
                </c:if>
                
                <c:if test="${not empty error}">
                    <div class="error-message">${error}</div>
                </c:if>
                
                <form method="post" action="${pageContext.request.contextPath}/profile">
                    <input type="hidden" name="action" value="updateProfile">
                    
                    <div class="form-group">
                        <label for="fullName">Full Name *</label>
                        <input type="text" id="fullName" name="fullName" value="${user.fullName}" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="email">Email *</label>
                        <input type="email" id="email" name="email" value="${user.email}" required>
                    </div>
                    
                    <button type="submit" class="btn btn-primary">Update Profile</button>
                </form>
            </div>
            
            <!-- Change Password Section -->
            <div class="profile-section">
                <h3>Change Password</h3>
                
                <c:if test="${not empty passwordSuccess}">
                    <div class="success-message">${passwordSuccess}</div>
                </c:if>
                
                <c:if test="${not empty passwordError}">
                    <div class="error-message">${passwordError}</div>
                </c:if>
                
                <form method="post" action="${pageContext.request.contextPath}/profile" id="passwordForm">
                    <input type="hidden" name="action" value="changePassword">
                    
                    <div class="form-group">
                        <label for="currentPassword">Current Password *</label>
                        <input type="password" id="currentPassword" name="currentPassword" required minlength="6">
                    </div>
                    
                    <div class="form-group">
                        <label for="newPassword">New Password *</label>
                        <input type="password" id="newPassword" name="newPassword" required minlength="6">
                        <small>Minimum 6 characters</small>
                    </div>
                    
                    <div class="form-group">
                        <label for="confirmPassword">Confirm New Password *</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" required minlength="6">
                    </div>
                    
                    <button type="submit" class="btn btn-primary">Change Password</button>
                </form>
            </div>
            
            <!-- Account Statistics -->
            <div class="profile-section">
                <h3>Account Information</h3>
                
                <div class="info-grid">
                    <div class="info-item">
                        <span class="info-label">User ID:</span>
                        <span class="info-value">${user.id}</span>
                    </div>
                    <div class="info-item">
                        <span class="info-label">Email:</span>
                        <span class="info-value">${user.email}</span>
                    </div>
                    <div class="info-item">
                        <span class="info-label">Full Name:</span>
                        <span class="info-value">${user.fullName}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script>
        // Password confirmation validation
        document.getElementById('passwordForm').addEventListener('submit', function(e) {
            const newPassword = document.getElementById('newPassword').value;
            const confirmPassword = document.getElementById('confirmPassword').value;
            
            if (newPassword !== confirmPassword) {
                e.preventDefault();
                alert('New passwords do not match!');
                return false;
            }
            
            if (newPassword.length < 6) {
                e.preventDefault();
                alert('Password must be at least 6 characters long!');
                return false;
            }
        });
    </script>
    
    <%@ include file="/WEB-INF/includes/footer.jsp" %>
    
    <script src="${pageContext.request.contextPath}/js/theme-toggle.js"></script>
    <script src="${pageContext.request.contextPath}/js/animations.js"></script>
</body>
</html>
