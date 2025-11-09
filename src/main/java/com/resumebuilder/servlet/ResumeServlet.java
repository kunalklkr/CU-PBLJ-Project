package com.resumebuilder.servlet;

import com.google.gson.Gson;
import com.resumebuilder.dao.ResumeDAO;
import com.resumebuilder.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/resume/*")
public class ResumeServlet extends HttpServlet {
    private ResumeDAO resumeDAO;
    private Gson gson;
    
    @Override
    public void init() {
        resumeDAO = new ResumeDAO();
        gson = new Gson();
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
        
        if (pathInfo == null || pathInfo.equals("/")) {
            request.getRequestDispatcher("/WEB-INF/views/create-resume.jsp").forward(request, response);
        } else if (pathInfo.startsWith("/edit/")) {
            String resumeId = pathInfo.substring(6);
            
            // Validate ObjectId format
            if (resumeId == null || resumeId.trim().isEmpty() || resumeId.length() != 24) {
                response.sendRedirect(request.getContextPath() + "/dashboard");
                return;
            }
            
            try {
                Resume resume = resumeDAO.getResumeById(resumeId);
                if (resume != null) {
                    request.setAttribute("resume", resume);
                    request.setAttribute("resumeJson", gson.toJson(resume));
                    request.getRequestDispatcher("/WEB-INF/views/edit-resume.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/dashboard");
                }
            } catch (IllegalArgumentException e) {
                response.sendRedirect(request.getContextPath() + "/dashboard");
            }
        } else if (pathInfo.startsWith("/preview/")) {
            String resumeId = pathInfo.substring(9);
            Resume resume = resumeDAO.getResumeById(resumeId);
            if (resume != null) {
                request.setAttribute("resume", resume);
                request.getRequestDispatcher("/WEB-INF/views/preview-resume.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/dashboard");
            }
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
        
        String pathInfo = request.getPathInfo();
        
        // Handle update via POST
        if (pathInfo != null && pathInfo.startsWith("/update/")) {
            String resumeId = pathInfo.substring(8);
            
            if (resumeId == null || resumeId.trim().isEmpty() || resumeId.length() != 24) {
                response.sendRedirect(request.getContextPath() + "/dashboard");
                return;
            }
            
            try {
                String userId = (String) session.getAttribute("userId");
                Resume resume = buildResumeFromRequest(request, userId);
                resume.setId(new org.bson.types.ObjectId(resumeId));
                
                if (resumeDAO.updateResume(resume)) {
                    response.sendRedirect(request.getContextPath() + "/dashboard");
                } else {
                    request.setAttribute("error", "Failed to update resume");
                    request.getRequestDispatcher("/WEB-INF/views/edit-resume.jsp").forward(request, response);
                }
            } catch (IllegalArgumentException e) {
                response.sendRedirect(request.getContextPath() + "/dashboard");
            }
            return;
        }
        
        // Handle delete via POST
        if (pathInfo != null && pathInfo.startsWith("/delete/")) {
            String resumeId = pathInfo.substring(8);
            
            if (resumeId != null && resumeId.length() == 24) {
                try {
                    resumeDAO.deleteResume(resumeId);
                } catch (Exception e) {
                    // Log error but continue
                    e.printStackTrace();
                }
            }
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return;
        }
        
        // Default: Create new resume
        String userId = (String) session.getAttribute("userId");
        Resume resume = buildResumeFromRequest(request, userId);
        
        String resumeId = resumeDAO.createResume(resume);
        if (resumeId != null) {
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            request.setAttribute("error", "Failed to create resume");
            request.getRequestDispatcher("/WEB-INF/views/create-resume.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.startsWith("/")) {
            String resumeId = pathInfo.substring(1);
            
            // Validate ObjectId format
            if (resumeId == null || resumeId.trim().isEmpty() || resumeId.length() != 24) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid resume ID");
                return;
            }
            
            String userId = (String) session.getAttribute("userId");
            
            try {
                Resume resume = buildResumeFromRequest(request, userId);
                resume.setId(new org.bson.types.ObjectId(resumeId));
                
                if (resumeDAO.updateResume(resume)) {
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            } catch (IllegalArgumentException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid resume ID format");
            }
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.startsWith("/")) {
            String resumeId = pathInfo.substring(1);
            if (resumeDAO.deleteResume(resumeId)) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }
    
    private Resume buildResumeFromRequest(HttpServletRequest request, String userId) {
        Resume resume = new Resume();
        resume.setUserId(userId);
        resume.setTitle(request.getParameter("title"));
        resume.setTemplate(request.getParameter("template"));
        
        PersonalInfo pi = new PersonalInfo();
        pi.setFullName(request.getParameter("fullName"));
        pi.setEmail(request.getParameter("email"));
        pi.setPhone(request.getParameter("phone"));
        pi.setAddress(request.getParameter("address"));
        pi.setLinkedin(request.getParameter("linkedin"));
        pi.setGithub(request.getParameter("github"));
        pi.setSummary(request.getParameter("summary"));
        resume.setPersonalInfo(pi);
        
        String[] eduInstitutions = request.getParameterValues("eduInstitution[]");
        if (eduInstitutions != null) {
            List<Education> education = new ArrayList<>();
            String[] eduDegrees = request.getParameterValues("eduDegree[]");
            String[] eduFields = request.getParameterValues("eduField[]");
            String[] eduStarts = request.getParameterValues("eduStart[]");
            String[] eduEnds = request.getParameterValues("eduEnd[]");
            String[] eduGrades = request.getParameterValues("eduGrade[]");
            
            for (int i = 0; i < eduInstitutions.length; i++) {
                Education edu = new Education();
                edu.setInstitution(eduInstitutions[i]);
                edu.setDegree(eduDegrees[i]);
                edu.setFieldOfStudy(eduFields[i]);
                edu.setStartDate(eduStarts[i]);
                edu.setEndDate(eduEnds[i]);
                edu.setGrade(eduGrades[i]);
                education.add(edu);
            }
            resume.setEducation(education);
        }
        
        String[] expCompanies = request.getParameterValues("expCompany[]");
        if (expCompanies != null) {
            List<Experience> experience = new ArrayList<>();
            String[] expPositions = request.getParameterValues("expPosition[]");
            String[] expStarts = request.getParameterValues("expStart[]");
            String[] expEnds = request.getParameterValues("expEnd[]");
            String[] expDescs = request.getParameterValues("expDesc[]");
            String[] expTypes = request.getParameterValues("expType[]");
            
            for (int i = 0; i < expCompanies.length; i++) {
                Experience exp = new Experience();
                exp.setCompany(expCompanies[i]);
                exp.setPosition(expPositions[i]);
                exp.setStartDate(expStarts[i]);
                exp.setEndDate(expEnds[i]);
                exp.setDescription(expDescs[i]);
                exp.setType(expTypes[i]);
                experience.add(exp);
            }
            resume.setExperience(experience);
        }
        
        String[] projNames = request.getParameterValues("projName[]");
        if (projNames != null) {
            List<Project> projects = new ArrayList<>();
            String[] projDescs = request.getParameterValues("projDesc[]");
            String[] projTechs = request.getParameterValues("projTech[]");
            String[] projLinks = request.getParameterValues("projLink[]");
            
            for (int i = 0; i < projNames.length; i++) {
                Project proj = new Project();
                proj.setName(projNames[i]);
                proj.setDescription(projDescs[i]);
                proj.setTechnologies(projTechs[i]);
                proj.setLink(projLinks[i]);
                projects.add(proj);
            }
            resume.setProjects(projects);
        }
        
        String skillsStr = request.getParameter("skills");
        if (skillsStr != null && !skillsStr.trim().isEmpty()) {
            resume.setSkills(Arrays.asList(skillsStr.split(",")));
        }
        
        String[] achTitles = request.getParameterValues("achTitle[]");
        if (achTitles != null) {
            List<Achievement> achievements = new ArrayList<>();
            String[] achDescs = request.getParameterValues("achDesc[]");
            String[] achDates = request.getParameterValues("achDate[]");
            
            for (int i = 0; i < achTitles.length; i++) {
                Achievement ach = new Achievement();
                ach.setTitle(achTitles[i]);
                ach.setDescription(achDescs[i]);
                ach.setDate(achDates[i]);
                achievements.add(ach);
            }
            resume.setAchievements(achievements);
        }
        
        return resume;
    }
}
