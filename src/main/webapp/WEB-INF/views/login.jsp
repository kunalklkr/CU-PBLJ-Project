<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Resume Builder</title>
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
            <h2>Login</h2>
            
            <% if (request.getParameter("registered") != null) { %>
                <div class="success-message">Registration successful! Please login.</div>
            <% } %>
            
            <% if (request.getAttribute("error") != null) { %>
                <div class="error-message">${error}</div>
            <% } %>
            
            <form method="post" action="${pageContext.request.contextPath}/login">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>
                
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>
                
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
            
            <p class="auth-link">Don't have an account? <a href="${pageContext.request.contextPath}/signup">Sign up</a></p>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/theme-toggle.js"></script>
    <script src="${pageContext.request.contextPath}/js/animations.js"></script>
</body>
</html>
