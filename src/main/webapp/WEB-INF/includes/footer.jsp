<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer class="footer">
    <div class="footer-container">
        <div class="footer-content">
            <!-- About Section -->
            <div class="footer-section">
                <div class="footer-logo">Resume Builder</div>
                <p>Create professional resumes with ease. Build, customize, and download your perfect resume in minutes.</p>
                <div class="footer-social">
                    <a href="#" aria-label="GitHub">
                        <i class="icon">code</i>
                    </a>
                    <a href="#" aria-label="LinkedIn">
                        <i class="icon">business</i>
                    </a>
                    <a href="#" aria-label="Twitter">
                        <i class="icon">tag</i>
                    </a>
                    <a href="#" aria-label="Email">
                        <i class="icon">email</i>
                    </a>
                </div>
            </div>
            
            <!-- Quick Links -->
            <div class="footer-section">
                <h4>Quick Links</h4>
                <ul class="footer-links">
                    <li>
                        <a href="${pageContext.request.contextPath}/dashboard">
                            <i class="icon">dashboard</i>
                            <span>Dashboard</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/resume/">
                            <i class="icon">add_circle</i>
                            <span>Create Resume</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/profile">
                            <i class="icon">person</i>
                            <span>Profile</span>
                        </a>
                    </li>
                </ul>
            </div>
            
            <!-- Resources -->
            <div class="footer-section">
                <h4>Resources</h4>
                <ul class="footer-links">
                    <li>
                        <a href="${pageContext.request.contextPath}/help">
                            <i class="icon">help</i>
                            <span>Help Center</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/tips">
                            <i class="icon">article</i>
                            <span>Resume Tips</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/tutorials">
                            <i class="icon">school</i>
                            <span>Tutorials</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/contact">
                            <i class="icon">contact_support</i>
                            <span>Contact Us</span>
                        </a>
                    </li>
                </ul>
            </div>
            
            <!-- Legal -->
            <div class="footer-section">
                <h4>Legal</h4>
                <ul class="footer-links">
                    <li>
                        <a href="${pageContext.request.contextPath}/privacy">
                            <i class="icon">policy</i>
                            <span>Privacy Policy</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/terms">
                            <i class="icon">gavel</i>
                            <span>Terms of Service</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/cookies">
                            <i class="icon">cookie</i>
                            <span>Cookie Policy</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        
        <!-- Footer Bottom -->
        <div class="footer-bottom">
            <p>&copy; 2025 Resume Builder. All rights reserved.</p>
            <div class="footer-bottom-links">
                <a href="${pageContext.request.contextPath}/privacy">Privacy</a>
                <a href="${pageContext.request.contextPath}/terms">Terms</a>
                <a href="${pageContext.request.contextPath}/contact">Contact</a>
            </div>
        </div>
    </div>
</footer>
