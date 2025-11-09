package com.resumebuilder.model;

public class Project {
    private String name;
    private String description;
    private String technologies;
    private String link;
    
    public Project() {}
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getTechnologies() { return technologies; }
    public void setTechnologies(String technologies) { this.technologies = technologies; }
    
    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}
