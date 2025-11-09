/* ============================================
   THEME TOGGLE - Dark/Light Mode
   ============================================ */

(function() {
    'use strict';
    
    // Get theme from localStorage or default to light
    const getTheme = () => localStorage.getItem('theme') || 'light';
    
    // Set theme
    const setTheme = (theme) => {
        document.documentElement.setAttribute('data-theme', theme);
        localStorage.setItem('theme', theme);
        updateThemeIcon(theme);
    };
    
    // Update theme toggle icon
    const updateThemeIcon = (theme) => {
        const icon = document.querySelector('.theme-toggle i');
        if (icon) {
            icon.textContent = theme === 'dark' ? 'light_mode' : 'dark_mode';
        }
    };
    
    // Toggle theme
    const toggleTheme = () => {
        const currentTheme = getTheme();
        const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
        setTheme(newTheme);
        
        // Add animation class
        document.body.classList.add('theme-transitioning');
        setTimeout(() => {
            document.body.classList.remove('theme-transitioning');
        }, 300);
    };
    
    // Initialize theme on page load
    document.addEventListener('DOMContentLoaded', () => {
        // Set initial theme
        setTheme(getTheme());
        
        // Create theme toggle button if it doesn't exist
        if (!document.querySelector('.theme-toggle')) {
            const themeToggle = document.createElement('button');
            themeToggle.className = 'theme-toggle';
            themeToggle.setAttribute('aria-label', 'Toggle theme');
            themeToggle.setAttribute('data-tooltip', 'Toggle theme');
            themeToggle.innerHTML = '<i class="icon">dark_mode</i>';
            document.body.appendChild(themeToggle);
            
            // Update icon based on current theme
            updateThemeIcon(getTheme());
            
            // Add click event
            themeToggle.addEventListener('click', toggleTheme);
        }
        
        // Keyboard shortcut: Ctrl/Cmd + Shift + D
        document.addEventListener('keydown', (e) => {
            if ((e.ctrlKey || e.metaKey) && e.shiftKey && e.key === 'D') {
                e.preventDefault();
                toggleTheme();
            }
        });
    });
    
    // Detect system theme preference
    if (window.matchMedia) {
        const darkModeQuery = window.matchMedia('(prefers-color-scheme: dark)');
        
        // Set theme based on system preference if no saved preference
        if (!localStorage.getItem('theme')) {
            setTheme(darkModeQuery.matches ? 'dark' : 'light');
        }
        
        // Listen for system theme changes
        darkModeQuery.addEventListener('change', (e) => {
            if (!localStorage.getItem('theme')) {
                setTheme(e.matches ? 'dark' : 'light');
            }
        });
    }
})();
