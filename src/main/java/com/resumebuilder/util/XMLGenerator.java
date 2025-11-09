package com.resumebuilder.util;

import com.resumebuilder.model.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class XMLGenerator {
    
    public static String generateXML(Resume resume) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        
        Element root = doc.createElement("resume");
        doc.appendChild(root);
        
        root.setAttribute("id", resume.getId().toString());
        root.setAttribute("title", resume.getTitle());
        root.setAttribute("template", resume.getTemplate());
        
        if (resume.getPersonalInfo() != null) {
            Element piElement = createPersonalInfoElement(doc, resume.getPersonalInfo());
            root.appendChild(piElement);
        }
        
        if (resume.getEducation() != null && !resume.getEducation().isEmpty()) {
            Element eduElement = doc.createElement("education");
            for (Education edu : resume.getEducation()) {
                eduElement.appendChild(createEducationElement(doc, edu));
            }
            root.appendChild(eduElement);
        }
        
        if (resume.getExperience() != null && !resume.getExperience().isEmpty()) {
            Element expElement = doc.createElement("experience");
            for (Experience exp : resume.getExperience()) {
                expElement.appendChild(createExperienceElement(doc, exp));
            }
            root.appendChild(expElement);
        }
        
        if (resume.getProjects() != null && !resume.getProjects().isEmpty()) {
            Element projElement = doc.createElement("projects");
            for (Project proj : resume.getProjects()) {
                projElement.appendChild(createProjectElement(doc, proj));
            }
            root.appendChild(projElement);
        }
        
        if (resume.getSkills() != null && !resume.getSkills().isEmpty()) {
            Element skillsElement = doc.createElement("skills");
            for (String skill : resume.getSkills()) {
                Element skillElement = doc.createElement("skill");
                skillElement.setTextContent(skill.trim());
                skillsElement.appendChild(skillElement);
            }
            root.appendChild(skillsElement);
        }
        
        if (resume.getAchievements() != null && !resume.getAchievements().isEmpty()) {
            Element achElement = doc.createElement("achievements");
            for (Achievement ach : resume.getAchievements()) {
                achElement.appendChild(createAchievementElement(doc, ach));
            }
            root.appendChild(achElement);
        }
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        
        return writer.toString();
    }
    
    private static Element createPersonalInfoElement(Document doc, PersonalInfo pi) {
        Element element = doc.createElement("personalInfo");
        
        addChildElement(doc, element, "fullName", pi.getFullName());
        addChildElement(doc, element, "email", pi.getEmail());
        addChildElement(doc, element, "phone", pi.getPhone());
        addChildElement(doc, element, "address", pi.getAddress());
        addChildElement(doc, element, "linkedin", pi.getLinkedin());
        addChildElement(doc, element, "github", pi.getGithub());
        addChildElement(doc, element, "summary", pi.getSummary());
        
        return element;
    }
    
    private static Element createEducationElement(Document doc, Education edu) {
        Element element = doc.createElement("item");
        
        addChildElement(doc, element, "institution", edu.getInstitution());
        addChildElement(doc, element, "degree", edu.getDegree());
        addChildElement(doc, element, "fieldOfStudy", edu.getFieldOfStudy());
        addChildElement(doc, element, "startDate", edu.getStartDate());
        addChildElement(doc, element, "endDate", edu.getEndDate());
        addChildElement(doc, element, "grade", edu.getGrade());
        
        return element;
    }
    
    private static Element createExperienceElement(Document doc, Experience exp) {
        Element element = doc.createElement("item");
        
        addChildElement(doc, element, "company", exp.getCompany());
        addChildElement(doc, element, "position", exp.getPosition());
        addChildElement(doc, element, "startDate", exp.getStartDate());
        addChildElement(doc, element, "endDate", exp.getEndDate());
        addChildElement(doc, element, "description", exp.getDescription());
        addChildElement(doc, element, "type", exp.getType());
        
        return element;
    }
    
    private static Element createProjectElement(Document doc, Project proj) {
        Element element = doc.createElement("item");
        
        addChildElement(doc, element, "name", proj.getName());
        addChildElement(doc, element, "description", proj.getDescription());
        addChildElement(doc, element, "technologies", proj.getTechnologies());
        addChildElement(doc, element, "link", proj.getLink());
        
        return element;
    }
    
    private static Element createAchievementElement(Document doc, Achievement ach) {
        Element element = doc.createElement("item");
        
        addChildElement(doc, element, "title", ach.getTitle());
        addChildElement(doc, element, "description", ach.getDescription());
        addChildElement(doc, element, "date", ach.getDate());
        
        return element;
    }
    
    private static void addChildElement(Document doc, Element parent, String name, String value) {
        if (value != null && !value.isEmpty()) {
            Element child = doc.createElement(name);
            child.setTextContent(value);
            parent.appendChild(child);
        }
    }
}
