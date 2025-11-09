package com.resumebuilder.model;

import org.bson.types.ObjectId;
import java.util.Date;
import java.util.List;

public class Resume {
    private ObjectId id;
    private String userId;
    private String title;
    private PersonalInfo personalInfo;
    private List<Education> education;
    private List<Experience> experience;
    private List<Project> projects;
    private List<String> skills;
    private List<Achievement> achievements;
    private String template;
    private Date createdAt;
    private Date updatedAt;
    
    public Resume() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
    
    // Getters and Setters
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public PersonalInfo getPersonalInfo() { return personalInfo; }
    public void setPersonalInfo(PersonalInfo personalInfo) { this.personalInfo = personalInfo; }
    
    public List<Education> getEducation() { return education; }
    public void setEducation(List<Education> education) { this.education = education; }
    
    public List<Experience> getExperience() { return experience; }
    public void setExperience(List<Experience> experience) { this.experience = experience; }
    
    public List<Project> getProjects() { return projects; }
    public void setProjects(List<Project> projects) { this.projects = projects; }
    
    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }
    
    public List<Achievement> getAchievements() { return achievements; }
    public void setAchievements(List<Achievement> achievements) { this.achievements = achievements; }
    
    public String getTemplate() { return template; }
    public void setTemplate(String template) { this.template = template; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    
    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}
