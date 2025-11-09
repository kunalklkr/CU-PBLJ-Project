package com.resumebuilder.model;

public class PersonalInfo {
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String linkedin;
    private String github;
    private String summary;
    
    public PersonalInfo() {}
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getLinkedin() { return linkedin; }
    public void setLinkedin(String linkedin) { this.linkedin = linkedin; }
    
    public String getGithub() { return github; }
    public void setGithub(String github) { this.github = github; }
    
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
}
