// Populate existing data
window.addEventListener('DOMContentLoaded', function() {
    // Load education
    if (resumeData.education && resumeData.education.length > 0) {
        resumeData.education.forEach(edu => {
            addEducation();
            const items = document.querySelectorAll('#educationContainer .dynamic-item');
            const lastItem = items[items.length - 1];
            lastItem.querySelector('input[name="eduInstitution[]"]').value = edu.institution || '';
            lastItem.querySelector('input[name="eduDegree[]"]').value = edu.degree || '';
            lastItem.querySelector('input[name="eduField[]"]').value = edu.fieldOfStudy || '';
            lastItem.querySelector('input[name="eduGrade[]"]').value = edu.grade || '';
            lastItem.querySelector('input[name="eduStart[]"]').value = edu.startDate || '';
            lastItem.querySelector('input[name="eduEnd[]"]').value = edu.endDate || '';
        });
    } else {
        addEducation();
    }
    
    // Load experience
    if (resumeData.experience && resumeData.experience.length > 0) {
        resumeData.experience.forEach(exp => {
            addExperience();
            const items = document.querySelectorAll('#experienceContainer .dynamic-item');
            const lastItem = items[items.length - 1];
            lastItem.querySelector('input[name="expCompany[]"]').value = exp.company || '';
            lastItem.querySelector('input[name="expPosition[]"]').value = exp.position || '';
            lastItem.querySelector('select[name="expType[]"]').value = exp.type || 'work';
            lastItem.querySelector('input[name="expStart[]"]').value = exp.startDate || '';
            lastItem.querySelector('input[name="expEnd[]"]').value = exp.endDate || '';
            lastItem.querySelector('textarea[name="expDesc[]"]').value = exp.description || '';
        });
    } else {
        addExperience();
    }
    
    // Load projects
    if (resumeData.projects && resumeData.projects.length > 0) {
        resumeData.projects.forEach(proj => {
            addProject();
            const items = document.querySelectorAll('#projectsContainer .dynamic-item');
            const lastItem = items[items.length - 1];
            lastItem.querySelector('input[name="projName[]"]').value = proj.name || '';
            lastItem.querySelector('textarea[name="projDesc[]"]').value = proj.description || '';
            lastItem.querySelector('input[name="projTech[]"]').value = proj.technologies || '';
            lastItem.querySelector('input[name="projLink[]"]').value = proj.link || '';
        });
    }
    
    // Load achievements
    if (resumeData.achievements && resumeData.achievements.length > 0) {
        resumeData.achievements.forEach(ach => {
            addAchievement();
            const items = document.querySelectorAll('#achievementsContainer .dynamic-item');
            const lastItem = items[items.length - 1];
            lastItem.querySelector('input[name="achTitle[]"]').value = ach.title || '';
            lastItem.querySelector('input[name="achDate[]"]').value = ach.date || '';
            lastItem.querySelector('textarea[name="achDesc[]"]').value = ach.description || '';
        });
    }
});

// Handle form submission - Use POST with hidden method field
document.getElementById('editResumeForm').addEventListener('submit', function(e) {
    e.preventDefault();
    
    const resumeId = document.getElementById('resumeId').value;
    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf('/', 1));
    
    // Submit form normally to update endpoint
    this.action = contextPath + '/resume/update/' + resumeId;
    this.method = 'POST';
    this.submit();
});
