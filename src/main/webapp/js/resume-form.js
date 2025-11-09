let eduCount = 0;
let expCount = 0;
let projCount = 0;
let achCount = 0;

function addEducation() {
    const container = document.getElementById('educationContainer');
    const item = document.createElement('div');
    item.className = 'dynamic-item';
    item.innerHTML = `
        <button type="button" class="remove-btn" onclick="this.parentElement.remove()">×</button>
        <div class="form-row">
            <div class="form-group">
                <label>Institution *</label>
                <input type="text" name="eduInstitution[]" required>
            </div>
            <div class="form-group">
                <label>Degree *</label>
                <input type="text" name="eduDegree[]" required>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group">
                <label>Field of Study *</label>
                <input type="text" name="eduField[]" required>
            </div>
            <div class="form-group">
                <label>Grade/GPA</label>
                <input type="text" name="eduGrade[]">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group">
                <label>Start Date *</label>
                <input type="text" name="eduStart[]" required placeholder="e.g., Aug 2020">
            </div>
            <div class="form-group">
                <label>End Date *</label>
                <input type="text" name="eduEnd[]" required placeholder="e.g., May 2024 or Present">
            </div>
        </div>
    `;
    container.appendChild(item);
    eduCount++;
}

function addExperience() {
    const container = document.getElementById('experienceContainer');
    const item = document.createElement('div');
    item.className = 'dynamic-item';
    item.innerHTML = `
        <button type="button" class="remove-btn" onclick="this.parentElement.remove()">×</button>
        <div class="form-row">
            <div class="form-group">
                <label>Company/Organization *</label>
                <input type="text" name="expCompany[]" required>
            </div>
            <div class="form-group">
                <label>Position *</label>
                <input type="text" name="expPosition[]" required>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group">
                <label>Type *</label>
                <select name="expType[]" required>
                    <option value="work">Work Experience</option>
                    <option value="internship">Internship</option>
                    <option value="certification">Certification</option>
                </select>
            </div>
            <div class="form-group">
                <label>Duration</label>
                <div style="display: flex; gap: 0.5rem;">
                    <input type="text" name="expStart[]" required placeholder="Start" style="width: 50%;">
                    <input type="text" name="expEnd[]" required placeholder="End" style="width: 50%;">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label>Description</label>
            <textarea name="expDesc[]" rows="3" placeholder="Describe your responsibilities and achievements"></textarea>
        </div>
    `;
    container.appendChild(item);
    expCount++;
}

function addProject() {
    const container = document.getElementById('projectsContainer');
    const item = document.createElement('div');
    item.className = 'dynamic-item';
    item.innerHTML = `
        <button type="button" class="remove-btn" onclick="this.parentElement.remove()">×</button>
        <div class="form-group">
            <label>Project Name *</label>
            <input type="text" name="projName[]" required>
        </div>
        <div class="form-group">
            <label>Description</label>
            <textarea name="projDesc[]" rows="2" placeholder="Brief description of the project"></textarea>
        </div>
        <div class="form-row">
            <div class="form-group">
                <label>Technologies Used</label>
                <input type="text" name="projTech[]" placeholder="e.g., Java, Spring Boot, MySQL">
            </div>
            <div class="form-group">
                <label>Project Link</label>
                <input type="url" name="projLink[]" placeholder="https://github.com/...">
            </div>
        </div>
    `;
    container.appendChild(item);
    projCount++;
}

function addAchievement() {
    const container = document.getElementById('achievementsContainer');
    const item = document.createElement('div');
    item.className = 'dynamic-item';
    item.innerHTML = `
        <button type="button" class="remove-btn" onclick="this.parentElement.remove()">×</button>
        <div class="form-row">
            <div class="form-group">
                <label>Title *</label>
                <input type="text" name="achTitle[]" required>
            </div>
            <div class="form-group">
                <label>Date</label>
                <input type="text" name="achDate[]" placeholder="e.g., 2023">
            </div>
        </div>
        <div class="form-group">
            <label>Description</label>
            <textarea name="achDesc[]" rows="2"></textarea>
        </div>
    `;
    container.appendChild(item);
    achCount++;
}

// Add at least one of each on page load
window.addEventListener('DOMContentLoaded', function() {
    addEducation();
    addExperience();
});
