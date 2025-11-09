package com.resumebuilder.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.resumebuilder.model.*;
import com.resumebuilder.util.MongoDBUtil;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResumeDAO {
    private MongoCollection<Document> collection;
    
    public ResumeDAO() {
        collection = MongoDBUtil.getDatabase().getCollection("resumes");
    }
    
    public String createResume(Resume resume) {
        try {
            Document doc = resumeToDocument(resume);
            collection.insertOne(doc);
            return doc.getObjectId("_id").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean updateResume(Resume resume) {
        try {
            resume.setUpdatedAt(new Date());
            Document doc = resumeToDocument(resume);
            collection.replaceOne(Filters.eq("_id", resume.getId()), doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Resume getResumeById(String id) {
        try {
            Document doc = collection.find(Filters.eq("_id", new ObjectId(id))).first();
            return doc != null ? documentToResume(doc) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Resume> getResumesByUserId(String userId) {
        List<Resume> resumes = new ArrayList<>();
        try {
            for (Document doc : collection.find(Filters.eq("userId", userId))) {
                resumes.add(documentToResume(doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resumes;
    }
    
    public boolean deleteResume(String id) {
        try {
            collection.deleteOne(Filters.eq("_id", new ObjectId(id)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private Document resumeToDocument(Resume resume) {
        Document doc = new Document();
        if (resume.getId() != null) doc.append("_id", resume.getId());
        doc.append("userId", resume.getUserId())
           .append("title", resume.getTitle())
           .append("template", resume.getTemplate())
           .append("createdAt", resume.getCreatedAt())
           .append("updatedAt", resume.getUpdatedAt());
        
        if (resume.getPersonalInfo() != null) {
            doc.append("personalInfo", personalInfoToDocument(resume.getPersonalInfo()));
        }
        
        if (resume.getEducation() != null) {
            List<Document> eduDocs = new ArrayList<>();
            for (Education edu : resume.getEducation()) {
                eduDocs.add(educationToDocument(edu));
            }
            doc.append("education", eduDocs);
        }
        
        if (resume.getExperience() != null) {
            List<Document> expDocs = new ArrayList<>();
            for (Experience exp : resume.getExperience()) {
                expDocs.add(experienceToDocument(exp));
            }
            doc.append("experience", expDocs);
        }
        
        if (resume.getProjects() != null) {
            List<Document> projDocs = new ArrayList<>();
            for (Project proj : resume.getProjects()) {
                projDocs.add(projectToDocument(proj));
            }
            doc.append("projects", projDocs);
        }
        
        if (resume.getSkills() != null) {
            doc.append("skills", resume.getSkills());
        }
        
        if (resume.getAchievements() != null) {
            List<Document> achDocs = new ArrayList<>();
            for (Achievement ach : resume.getAchievements()) {
                achDocs.add(achievementToDocument(ach));
            }
            doc.append("achievements", achDocs);
        }
        
        return doc;
    }
    
    private Resume documentToResume(Document doc) {
        Resume resume = new Resume();
        resume.setId(doc.getObjectId("_id"));
        resume.setUserId(doc.getString("userId"));
        resume.setTitle(doc.getString("title"));
        resume.setTemplate(doc.getString("template"));
        resume.setCreatedAt(doc.getDate("createdAt"));
        resume.setUpdatedAt(doc.getDate("updatedAt"));
        
        Document piDoc = (Document) doc.get("personalInfo");
        if (piDoc != null) {
            resume.setPersonalInfo(documentToPersonalInfo(piDoc));
        }
        
        List<Document> eduDocs = (List<Document>) doc.get("education");
        if (eduDocs != null) {
            List<Education> education = new ArrayList<>();
            for (Document eduDoc : eduDocs) {
                education.add(documentToEducation(eduDoc));
            }
            resume.setEducation(education);
        }
        
        List<Document> expDocs = (List<Document>) doc.get("experience");
        if (expDocs != null) {
            List<Experience> experience = new ArrayList<>();
            for (Document expDoc : expDocs) {
                experience.add(documentToExperience(expDoc));
            }
            resume.setExperience(experience);
        }
        
        List<Document> projDocs = (List<Document>) doc.get("projects");
        if (projDocs != null) {
            List<Project> projects = new ArrayList<>();
            for (Document projDoc : projDocs) {
                projects.add(documentToProject(projDoc));
            }
            resume.setProjects(projects);
        }
        
        List<String> skills = (List<String>) doc.get("skills");
        if (skills != null) {
            resume.setSkills(skills);
        }
        
        List<Document> achDocs = (List<Document>) doc.get("achievements");
        if (achDocs != null) {
            List<Achievement> achievements = new ArrayList<>();
            for (Document achDoc : achDocs) {
                achievements.add(documentToAchievement(achDoc));
            }
            resume.setAchievements(achievements);
        }
        
        return resume;
    }
    
    private Document personalInfoToDocument(PersonalInfo pi) {
        return new Document("fullName", pi.getFullName())
                .append("email", pi.getEmail())
                .append("phone", pi.getPhone())
                .append("address", pi.getAddress())
                .append("linkedin", pi.getLinkedin())
                .append("github", pi.getGithub())
                .append("summary", pi.getSummary());
    }
    
    private PersonalInfo documentToPersonalInfo(Document doc) {
        PersonalInfo pi = new PersonalInfo();
        pi.setFullName(doc.getString("fullName"));
        pi.setEmail(doc.getString("email"));
        pi.setPhone(doc.getString("phone"));
        pi.setAddress(doc.getString("address"));
        pi.setLinkedin(doc.getString("linkedin"));
        pi.setGithub(doc.getString("github"));
        pi.setSummary(doc.getString("summary"));
        return pi;
    }
    
    private Document educationToDocument(Education edu) {
        return new Document("institution", edu.getInstitution())
                .append("degree", edu.getDegree())
                .append("fieldOfStudy", edu.getFieldOfStudy())
                .append("startDate", edu.getStartDate())
                .append("endDate", edu.getEndDate())
                .append("grade", edu.getGrade());
    }
    
    private Education documentToEducation(Document doc) {
        Education edu = new Education();
        edu.setInstitution(doc.getString("institution"));
        edu.setDegree(doc.getString("degree"));
        edu.setFieldOfStudy(doc.getString("fieldOfStudy"));
        edu.setStartDate(doc.getString("startDate"));
        edu.setEndDate(doc.getString("endDate"));
        edu.setGrade(doc.getString("grade"));
        return edu;
    }
    
    private Document experienceToDocument(Experience exp) {
        return new Document("company", exp.getCompany())
                .append("position", exp.getPosition())
                .append("startDate", exp.getStartDate())
                .append("endDate", exp.getEndDate())
                .append("description", exp.getDescription())
                .append("type", exp.getType());
    }
    
    private Experience documentToExperience(Document doc) {
        Experience exp = new Experience();
        exp.setCompany(doc.getString("company"));
        exp.setPosition(doc.getString("position"));
        exp.setStartDate(doc.getString("startDate"));
        exp.setEndDate(doc.getString("endDate"));
        exp.setDescription(doc.getString("description"));
        exp.setType(doc.getString("type"));
        return exp;
    }
    
    private Document projectToDocument(Project proj) {
        return new Document("name", proj.getName())
                .append("description", proj.getDescription())
                .append("technologies", proj.getTechnologies())
                .append("link", proj.getLink());
    }
    
    private Project documentToProject(Document doc) {
        Project proj = new Project();
        proj.setName(doc.getString("name"));
        proj.setDescription(doc.getString("description"));
        proj.setTechnologies(doc.getString("technologies"));
        proj.setLink(doc.getString("link"));
        return proj;
    }
    
    private Document achievementToDocument(Achievement ach) {
        return new Document("title", ach.getTitle())
                .append("description", ach.getDescription())
                .append("date", ach.getDate());
    }
    
    private Achievement documentToAchievement(Document doc) {
        Achievement ach = new Achievement();
        ach.setTitle(doc.getString("title"));
        ach.setDescription(doc.getString("description"));
        ach.setDate(doc.getString("date"));
        return ach;
    }
}
