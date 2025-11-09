package com.resumebuilder.servlet;

import com.resumebuilder.dao.ResumeDAO;
import com.resumebuilder.model.Resume;
import com.resumebuilder.util.PDFGenerator;
import com.resumebuilder.util.XMLGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/download/*")
public class DownloadServlet extends HttpServlet {
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
        
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.length() < 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        String[] parts = pathInfo.substring(1).split("/");
        if (parts.length < 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        String format = parts[0];
        String resumeId = parts[1];
        
        Resume resume = resumeDAO.getResumeById(resumeId);
        if (resume == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        try {
            if ("pdf".equals(format)) {
                byte[] pdfBytes = PDFGenerator.generatePDF(resume);
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + resume.getTitle() + ".pdf\"");
                response.setContentLength(pdfBytes.length);
                
                OutputStream out = response.getOutputStream();
                out.write(pdfBytes);
                out.flush();
            } else if ("xml".equals(format)) {
                String xml = XMLGenerator.generateXML(resume);
                response.setContentType("application/xml");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + resume.getTitle() + ".xml\"");
                response.setCharacterEncoding("UTF-8");
                
                response.getWriter().write(xml);
                response.getWriter().flush();
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
