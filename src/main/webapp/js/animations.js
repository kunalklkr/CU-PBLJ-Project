/* ============================================
   ENHANCED ANIMATIONS & INTERACTIONS
   ============================================ */

// Scroll animations
document.addEventListener('DOMContentLoaded', function() {
    
    // Add fade-in-on-scroll class to cards
    const cards = document.querySelectorAll('.material-card, .stat-card, .resume-card, .profile-section, .form-section');
    cards.forEach(card => {
        card.classList.add('fade-in-on-scroll');
    });
    
    // Intersection Observer for scroll animations
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };
    
    const observer = new IntersectionObserver(function(entries) {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('visible');
            }
        });
    }, observerOptions);
    
    document.querySelectorAll('.fade-in-on-scroll').forEach(el => {
        observer.observe(el);
    });
    
    // Ripple effect for buttons
    document.querySelectorAll('.btn').forEach(button => {
        button.addEventListener('click', function(e) {
            const ripple = document.createElement('span');
            const rect = this.getBoundingClientRect();
            const size = Math.max(rect.width, rect.height);
            const x = e.clientX - rect.left - size / 2;
            const y = e.clientY - rect.top - size / 2;
            
            ripple.style.width = ripple.style.height = size + 'px';
            ripple.style.left = x + 'px';
            ripple.style.top = y + 'px';
            ripple.classList.add('ripple-effect');
            
            this.appendChild(ripple);
            
            setTimeout(() => ripple.remove(), 600);
        });
    });
    
    // Smooth scroll for anchor links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function(e) {
            e.preventDefault();
            const target = document.querySelector(this.getAttribute('href'));
            if (target) {
                target.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            }
        });
    });
    
    // Add loading overlay
    const loadingOverlay = document.createElement('div');
    loadingOverlay.className = 'loading-overlay';
    loadingOverlay.innerHTML = '<div class="spinner"></div>';
    document.body.appendChild(loadingOverlay);
    
    // Show loading on form submit
    document.querySelectorAll('form').forEach(form => {
        form.addEventListener('submit', function() {
            loadingOverlay.classList.add('active');
        });
    });
    
    // Parallax effect for auth background
    if (document.querySelector('.auth-container')) {
        document.addEventListener('mousemove', function(e) {
            const container = document.querySelector('.auth-container');
            if (container) {
                const x = (e.clientX / window.innerWidth - 0.5) * 20;
                const y = (e.clientY / window.innerHeight - 0.5) * 20;
                container.style.backgroundPosition = `${50 + x}% ${50 + y}%`;
            }
        });
    }
    
    // Card tilt effect
    document.querySelectorAll('.resume-card, .stat-card').forEach(card => {
        card.addEventListener('mousemove', function(e) {
            const rect = this.getBoundingClientRect();
            const x = e.clientX - rect.left;
            const y = e.clientY - rect.top;
            
            const centerX = rect.width / 2;
            const centerY = rect.height / 2;
            
            const rotateX = (y - centerY) / 20;
            const rotateY = (centerX - x) / 20;
            
            this.style.transform = `perspective(1000px) rotateX(${rotateX}deg) rotateY(${rotateY}deg) translateY(-8px)`;
        });
        
        card.addEventListener('mouseleave', function() {
            this.style.transform = 'perspective(1000px) rotateX(0) rotateY(0) translateY(0)';
        });
    });
    
    // Animate numbers in stat cards
    const animateValue = (element, start, end, duration) => {
        let startTimestamp = null;
        const step = (timestamp) => {
            if (!startTimestamp) startTimestamp = timestamp;
            const progress = Math.min((timestamp - startTimestamp) / duration, 1);
            element.textContent = Math.floor(progress * (end - start) + start);
            if (progress < 1) {
                window.requestAnimationFrame(step);
            }
        };
        window.requestAnimationFrame(step);
    };
    
    // Animate stat numbers when visible
    const statObserver = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                const h3 = entry.target.querySelector('h3');
                if (h3 && !isNaN(h3.textContent)) {
                    const endValue = parseInt(h3.textContent);
                    animateValue(h3, 0, endValue, 1000);
                    statObserver.unobserve(entry.target);
                }
            }
        });
    }, { threshold: 0.5 });
    
    document.querySelectorAll('.stat-card').forEach(card => {
        statObserver.observe(card);
    });
    
    // Add gradient animation to buttons
    document.querySelectorAll('.btn-primary').forEach(btn => {
        btn.addEventListener('mouseenter', function() {
            this.style.backgroundSize = '200% 200%';
            this.style.animation = 'gradient-shift 2s ease infinite';
        });
        
        btn.addEventListener('mouseleave', function() {
            this.style.animation = '';
        });
    });
    
    // Form input animations
    document.querySelectorAll('.form-group input, .form-group textarea, .form-group select').forEach(input => {
        input.addEventListener('focus', function() {
            this.parentElement.classList.add('focused');
        });
        
        input.addEventListener('blur', function() {
            this.parentElement.classList.remove('focused');
        });
    });
    
    // Add success animation to messages
    document.querySelectorAll('.success-message, .error-message, .info-message, .warning-message').forEach(msg => {
        msg.style.animation = 'slideInRight 0.5s ease-out, pulse 0.5s ease-out 0.5s';
    });
    
    // Navbar scroll effect
    let lastScroll = 0;
    window.addEventListener('scroll', () => {
        const navbar = document.querySelector('.navbar');
        const currentScroll = window.pageYOffset;
        
        if (currentScroll > lastScroll && currentScroll > 100) {
            navbar.style.transform = 'translateY(-100%)';
        } else {
            navbar.style.transform = 'translateY(0)';
        }
        
        if (currentScroll > 50) {
            navbar.style.boxShadow = '0 8px 32px rgba(0, 0, 0, 0.1)';
        } else {
            navbar.style.boxShadow = '0 4px 30px rgba(0, 0, 0, 0.05)';
        }
        
        lastScroll = currentScroll;
    });
});

// Add gradient shift animation
const style = document.createElement('style');
style.textContent = `
    @keyframes gradient-shift {
        0% { background-position: 0% 50%; }
        50% { background-position: 100% 50%; }
        100% { background-position: 0% 50%; }
    }
    
    @keyframes pulse {
        0%, 100% { transform: scale(1); }
        50% { transform: scale(1.02); }
    }
    
    .ripple-effect {
        position: absolute;
        border-radius: 50%;
        background: rgba(255, 255, 255, 0.6);
        transform: scale(0);
        animation: ripple-animation 0.6s ease-out;
        pointer-events: none;
    }
    
    @keyframes ripple-animation {
        to {
            transform: scale(4);
            opacity: 0;
        }
    }
    
    .focused label {
        color: var(--primary-color);
        transform: translateY(-2px);
    }
`;
document.head.appendChild(style);

// Prevent form resubmission on refresh
if (window.history.replaceState) {
    window.history.replaceState(null, null, window.location.href);
}
