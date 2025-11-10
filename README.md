# Resume Builder

## Overview

Resume Builder is a comprehensive web application that empowers users to create professional resumes with ease. Built with Java Servlets, JSP, and MongoDB, it features a beautiful Material Design interface with dark theme support, multiple export formats, and complete user management.

## Key Features

* **Secure Authentication:** BCrypt password hashing with session management.
* **Resume Management:** Create, edit, preview, and delete resumes.
* **Material Design UI:** Modern interface with smooth animations and dark theme.
* **Multiple Templates:** Choose from Classic and Modern resume designs.
* **Export Options:** Download resumes as PDF or XML.
* **Profile Management:** Update personal information and change passwords.
* **Responsive Design:** Works seamlessly on desktop, tablet, and mobile.

## Technologies Used

* **Backend:** Java 11+, Servlets 4.0, JSP 2.3
* **Database:** MongoDB 4.0+
* **Frontend:** HTML, CSS, JavaScript (Material Design)
* **Build Tool:** Apache Maven
* **Key Libraries:** iText (PDF Generation), BCrypt (Hashing), Gson (JSON)

### Prerequisites

* Java Development Kit (JDK) 11+
* Apache Maven 3.6+
* MongoDB 4.0+

### Installation & Run

1.  **Clone the repository**
    git clone [https://github.com/yourusername/resume-builder.git](https://github.com/yourusername/resume-builder.git)
    cd resume-builder

2.  **Start MongoDB**
    Ensure your MongoDB service is running
    net start MongoDB

3.  **Build and run the application**
    mvn clean tomcat7:run
    ```

4.  **Access the application**
    Open your browser and go to `http://localhost:8080/resume-builder`

## Project Structure
