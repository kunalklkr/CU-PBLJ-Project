package com.resumebuilder.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.resumebuilder.model.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PDFGenerator {
    
    public static byte[] generatePDF(Resume resume) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 36, 36, 54, 54);
        PdfWriter.getInstance(document, baos);
        
        document.open();
        
        if ("modern".equals(resume.getTemplate())) {
            generateModernTemplate(document, resume);
        } else {
            generateClassicTemplate(document, resume);
        }
        
        document.close();
        return baos.toByteArray();
    }
    
    private static void generateClassicTemplate(Document document, Resume resume) throws DocumentException {
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
        Font sectionFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
        Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 11);
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
        
        PersonalInfo pi = resume.getPersonalInfo();
        if (pi != null) {
            Paragraph name = new Paragraph(pi.getFullName(), titleFont);
            name.setAlignment(Element.ALIGN_CENTER);
            document.add(name);
            
            Paragraph contact = new Paragraph();
            contact.setAlignment(Element.ALIGN_CENTER);
            contact.setFont(normalFont);
            if (pi.getEmail() != null) contact.add(pi.getEmail() + " | ");
            if (pi.getPhone() != null) contact.add(pi.getPhone() + " | ");
            if (pi.getAddress() != null) contact.add(pi.getAddress());
            document.add(contact);
            
            if (pi.getLinkedin() != null || pi.getGithub() != null) {
                Paragraph links = new Paragraph();
                links.setAlignment(Element.ALIGN_CENTER);
                links.setFont(normalFont);
                if (pi.getLinkedin() != null) links.add("LinkedIn: " + pi.getLinkedin() + " | ");
                if (pi.getGithub() != null) links.add("GitHub: " + pi.getGithub());
                document.add(links);
            }
            
            document.add(new Paragraph(" "));
            
            if (pi.getSummary() != null && !pi.getSummary().isEmpty()) {
                document.add(new Paragraph("SUMMARY", sectionFont));
                document.add(new LineSeparator());
                document.add(new Paragraph(pi.getSummary(), normalFont));
                document.add(new Paragraph(" "));
            }
        }
        
        if (resume.getEducation() != null && !resume.getEducation().isEmpty()) {
            document.add(new Paragraph("EDUCATION", sectionFont));
            document.add(new LineSeparator());
            for (Education edu : resume.getEducation()) {
                Paragraph eduPara = new Paragraph();
                eduPara.add(new Chunk(edu.getInstitution(), boldFont));
                eduPara.add(new Chunk(" - " + edu.getDegree() + " in " + edu.getFieldOfStudy(), normalFont));
                document.add(eduPara);
                
                Paragraph datePara = new Paragraph(edu.getStartDate() + " - " + edu.getEndDate(), normalFont);
                document.add(datePara);
                
                if (edu.getGrade() != null && !edu.getGrade().isEmpty()) {
                    document.add(new Paragraph("Grade: " + edu.getGrade(), normalFont));
                }
                document.add(new Paragraph(" "));
            }
        }
        
        if (resume.getExperience() != null && !resume.getExperience().isEmpty()) {
            document.add(new Paragraph("EXPERIENCE", sectionFont));
            document.add(new LineSeparator());
            for (Experience exp : resume.getExperience()) {
                Paragraph expPara = new Paragraph();
                expPara.add(new Chunk(exp.getPosition(), boldFont));
                expPara.add(new Chunk(" at " + exp.getCompany(), normalFont));
                document.add(expPara);
                
                Paragraph datePara = new Paragraph(exp.getStartDate() + " - " + exp.getEndDate(), normalFont);
                document.add(datePara);
                
                if (exp.getDescription() != null && !exp.getDescription().isEmpty()) {
                    document.add(new Paragraph(exp.getDescription(), normalFont));
                }
                document.add(new Paragraph(" "));
            }
        }
        
        if (resume.getProjects() != null && !resume.getProjects().isEmpty()) {
            document.add(new Paragraph("PROJECTS", sectionFont));
            document.add(new LineSeparator());
            for (Project proj : resume.getProjects()) {
                Paragraph projPara = new Paragraph();
                projPara.add(new Chunk(proj.getName(), boldFont));
                document.add(projPara);
                
                if (proj.getDescription() != null) {
                    document.add(new Paragraph(proj.getDescription(), normalFont));
                }
                if (proj.getTechnologies() != null) {
                    document.add(new Paragraph("Technologies: " + proj.getTechnologies(), normalFont));
                }
                document.add(new Paragraph(" "));
            }
        }
        
        if (resume.getSkills() != null && !resume.getSkills().isEmpty()) {
            document.add(new Paragraph("SKILLS", sectionFont));
            document.add(new LineSeparator());
            document.add(new Paragraph(String.join(", ", resume.getSkills()), normalFont));
            document.add(new Paragraph(" "));
        }
        
        if (resume.getAchievements() != null && !resume.getAchievements().isEmpty()) {
            document.add(new Paragraph("ACHIEVEMENTS", sectionFont));
            document.add(new LineSeparator());
            for (Achievement ach : resume.getAchievements()) {
                Paragraph achPara = new Paragraph();
                achPara.add(new Chunk(ach.getTitle(), boldFont));
                if (ach.getDate() != null) {
                    achPara.add(new Chunk(" (" + ach.getDate() + ")", normalFont));
                }
                document.add(achPara);
                
                if (ach.getDescription() != null) {
                    document.add(new Paragraph(ach.getDescription(), normalFont));
                }
                document.add(new Paragraph(" "));
            }
        }
    }
    
    private static void generateModernTemplate(Document document, Resume resume) throws DocumentException {
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 26, Font.BOLD, BaseColor.DARK_GRAY);
        Font sectionFont = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD, new BaseColor(41, 128, 185));
        Font normalFont = new Font(Font.FontFamily.HELVETICA, 10);
        Font boldFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        
        PersonalInfo pi = resume.getPersonalInfo();
        if (pi != null) {
            Paragraph name = new Paragraph(pi.getFullName().toUpperCase(), titleFont);
            name.setAlignment(Element.ALIGN_LEFT);
            document.add(name);
            
            Paragraph contact = new Paragraph();
            contact.setFont(normalFont);
            if (pi.getEmail() != null) contact.add(pi.getEmail() + " • ");
            if (pi.getPhone() != null) contact.add(pi.getPhone() + " • ");
            if (pi.getAddress() != null) contact.add(pi.getAddress());
            document.add(contact);
            
            if (pi.getLinkedin() != null || pi.getGithub() != null) {
                Paragraph links = new Paragraph();
                links.setFont(normalFont);
                if (pi.getLinkedin() != null) links.add(pi.getLinkedin() + " • ");
                if (pi.getGithub() != null) links.add(pi.getGithub());
                document.add(links);
            }
            
            document.add(new Paragraph(" "));
            
            if (pi.getSummary() != null && !pi.getSummary().isEmpty()) {
                Paragraph summaryTitle = new Paragraph("PROFESSIONAL SUMMARY", sectionFont);
                document.add(summaryTitle);
                LineSeparator line = new LineSeparator();
                line.setLineColor(new BaseColor(41, 128, 185));
                document.add(line);
                document.add(new Paragraph(pi.getSummary(), normalFont));
                document.add(new Paragraph(" "));
            }
        }
        
        if (resume.getEducation() != null && !resume.getEducation().isEmpty()) {
            Paragraph eduTitle = new Paragraph("EDUCATION", sectionFont);
            document.add(eduTitle);
            LineSeparator line = new LineSeparator();
            line.setLineColor(new BaseColor(41, 128, 185));
            document.add(line);
            
            for (Education edu : resume.getEducation()) {
                Paragraph eduPara = new Paragraph();
                eduPara.add(new Chunk(edu.getDegree() + " in " + edu.getFieldOfStudy(), boldFont));
                document.add(eduPara);
                
                Paragraph instPara = new Paragraph(edu.getInstitution() + " | " + edu.getStartDate() + " - " + edu.getEndDate(), normalFont);
                document.add(instPara);
                
                if (edu.getGrade() != null && !edu.getGrade().isEmpty()) {
                    document.add(new Paragraph("Grade: " + edu.getGrade(), normalFont));
                }
                document.add(new Paragraph(" "));
            }
        }
        
        if (resume.getExperience() != null && !resume.getExperience().isEmpty()) {
            Paragraph expTitle = new Paragraph("EXPERIENCE", sectionFont);
            document.add(expTitle);
            LineSeparator line = new LineSeparator();
            line.setLineColor(new BaseColor(41, 128, 185));
            document.add(line);
            
            for (Experience exp : resume.getExperience()) {
                Paragraph expPara = new Paragraph();
                expPara.add(new Chunk(exp.getPosition(), boldFont));
                document.add(expPara);
                
                Paragraph compPara = new Paragraph(exp.getCompany() + " | " + exp.getStartDate() + " - " + exp.getEndDate(), normalFont);
                document.add(compPara);
                
                if (exp.getDescription() != null && !exp.getDescription().isEmpty()) {
                    document.add(new Paragraph(exp.getDescription(), normalFont));
                }
                document.add(new Paragraph(" "));
            }
        }
        
        if (resume.getProjects() != null && !resume.getProjects().isEmpty()) {
            Paragraph projTitle = new Paragraph("PROJECTS", sectionFont);
            document.add(projTitle);
            LineSeparator line = new LineSeparator();
            line.setLineColor(new BaseColor(41, 128, 185));
            document.add(line);
            
            for (Project proj : resume.getProjects()) {
                Paragraph projPara = new Paragraph();
                projPara.add(new Chunk(proj.getName(), boldFont));
                document.add(projPara);
                
                if (proj.getDescription() != null) {
                    document.add(new Paragraph(proj.getDescription(), normalFont));
                }
                if (proj.getTechnologies() != null) {
                    document.add(new Paragraph("Tech Stack: " + proj.getTechnologies(), normalFont));
                }
                document.add(new Paragraph(" "));
            }
        }
        
        if (resume.getSkills() != null && !resume.getSkills().isEmpty()) {
            Paragraph skillTitle = new Paragraph("SKILLS", sectionFont);
            document.add(skillTitle);
            LineSeparator line = new LineSeparator();
            line.setLineColor(new BaseColor(41, 128, 185));
            document.add(line);
            document.add(new Paragraph(String.join(" • ", resume.getSkills()), normalFont));
            document.add(new Paragraph(" "));
        }
        
        if (resume.getAchievements() != null && !resume.getAchievements().isEmpty()) {
            Paragraph achTitle = new Paragraph("ACHIEVEMENTS & AWARDS", sectionFont);
            document.add(achTitle);
            LineSeparator line = new LineSeparator();
            line.setLineColor(new BaseColor(41, 128, 185));
            document.add(line);
            
            for (Achievement ach : resume.getAchievements()) {
                Paragraph achPara = new Paragraph();
                achPara.add(new Chunk(ach.getTitle(), boldFont));
                if (ach.getDate() != null) {
                    achPara.add(new Chunk(" (" + ach.getDate() + ")", normalFont));
                }
                document.add(achPara);
                
                if (ach.getDescription() != null) {
                    document.add(new Paragraph(ach.getDescription(), normalFont));
                }
                document.add(new Paragraph(" "));
            }
        }
    }
}
