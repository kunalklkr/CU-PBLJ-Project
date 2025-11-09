package com.resumebuilder.servlet;

import com.resumebuilder.dao.UserDAO;
import com.resumebuilder.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private UserDAO userDAO;
    
    @Override
    public void init() {
        userDAO = new UserDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String userId = (String) session.getAttribute("userId");
        User user = userDAO.getUserById(userId);
        
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/dashboard");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String action = request.getParameter("action");
        String userId = (String) session.getAttribute("userId");
        
        if ("updateProfile".equals(action)) {
            handleUpdateProfile(request, response, userId);
        } else if ("changePassword".equals(action)) {
            handleChangePassword(request, response, userId);
        } else {
            response.sendRedirect(request.getContextPath() + "/profile");
        }
    }
    
    private void handleUpdateProfile(HttpServletRequest request, HttpServletResponse response, String userId) 
            throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        
        if (fullName == null || fullName.trim().isEmpty() || email == null || email.trim().isEmpty()) {
            request.setAttribute("error", "Full name and email are required");
            User user = userDAO.getUserById(userId);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
            return;
        }
        
        // Check if email is already taken by another user
        User existingUser = userDAO.getUserByEmail(email);
        if (existingUser != null && !existingUser.getId().toString().equals(userId)) {
            request.setAttribute("error", "Email is already taken by another user");
            User user = userDAO.getUserById(userId);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
            return;
        }
        
        if (userDAO.updateUserProfile(userId, fullName, email)) {
            // Update session
            User updatedUser = userDAO.getUserById(userId);
            request.getSession().setAttribute("user", updatedUser);
            
            request.setAttribute("success", "Profile updated successfully");
            request.setAttribute("user", updatedUser);
            request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Failed to update profile");
            User user = userDAO.getUserById(userId);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
        }
    }
    
    private void handleChangePassword(HttpServletRequest request, HttpServletResponse response, String userId) 
            throws ServletException, IOException {
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        
        if (currentPassword == null || newPassword == null || confirmPassword == null) {
            request.setAttribute("passwordError", "All password fields are required");
            User user = userDAO.getUserById(userId);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
            return;
        }
        
        if (newPassword.length() < 6) {
            request.setAttribute("passwordError", "New password must be at least 6 characters");
            User user = userDAO.getUserById(userId);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
            return;
        }
        
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("passwordError", "New passwords do not match");
            User user = userDAO.getUserById(userId);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
            return;
        }
        
        if (userDAO.changePassword(userId, currentPassword, newPassword)) {
            request.setAttribute("passwordSuccess", "Password changed successfully");
            User user = userDAO.getUserById(userId);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
        } else {
            request.setAttribute("passwordError", "Current password is incorrect");
            User user = userDAO.getUserById(userId);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
        }
    }
}
