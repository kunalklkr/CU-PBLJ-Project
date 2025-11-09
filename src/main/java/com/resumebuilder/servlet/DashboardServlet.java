package com.resumebuilder.servlet;

import com.resumebuilder.dao.ResumeDAO;
import com.resumebuilder.model.Resume;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private ResumeDAO resumeDAO;
    
    @Override
    public void init() {
        resumeDAO = new ResumeDAO();
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
        List<Resume> resumes = resumeDAO.getResumesByUserId(userId);
        
        // Calculate statistics
        int totalResumes = resumes.size();
        Resume lastCreated = null;
        Resume lastUpdated = null;
        
        if (!resumes.isEmpty()) {
            // Find last created
            lastCreated = resumes.get(0);
            for (Resume r : resumes) {
                if (r.getCreatedAt().after(lastCreated.getCreatedAt())) {
                    lastCreated = r;
                }
            }
            
            // Find last updated
            lastUpdated = resumes.get(0);
            for (Resume r : resumes) {
                if (r.getUpdatedAt().after(lastUpdated.getUpdatedAt())) {
                    lastUpdated = r;
                }
            }
        }
        
        request.setAttribute("resumes", resumes);
        request.setAttribute("totalResumes", totalResumes);
        request.setAttribute("lastCreated", lastCreated);
        request.setAttribute("lastUpdated", lastUpdated);
        request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
    }
}
