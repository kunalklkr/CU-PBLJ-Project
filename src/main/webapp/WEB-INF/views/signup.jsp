<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up - Resume Builder</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/material-theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pages.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/refined-design.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/premium-design.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dark-theme.css">
</head>
<body>
    <div class="auth-container">
        <div class="auth-box">
            <h1>Resume Builder</h1>
            <h2>Sign Up</h2>
            
            <% if (request.getAttribute("error") != null) { %>
                <div class="error-message">${error}</div>
            <% } %>
            
            <form method="post" action="${pageContext.request.contextPath}/signup">
                <div class="form-group">
                    <label for="fullName">Full Name</label>
                    <input type="text" id="fullName" name="fullName" required>
                </div>
                
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>
                
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required minlength="6">
                </div>
                
                <button type="submit" class="btn btn-primary">Sign Up</button>
            </form>
            
            <p class="auth-link">Already have an account? <a href="${pageContext.request.contextPath}/login">Login</a></p>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/theme-toggle.js"></script>
    <script src="${pageContext.request.contextPath}/js/animations.js"></script>
</body>
</html>
