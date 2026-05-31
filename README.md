
# AI Resume Analyzer and Job Recommendation System

An AI-powered full-stack web application that analyzes resumes, extracts technical skills, and provides intelligent job recommendations using external API integration.

## Project Overview

The AI Resume Analyzer and Job Recommendation System automates the resume analysis process by extracting skills from uploaded resumes and recommending relevant jobs dynamically.

The system reduces manual job searching by matching extracted skills with job listings fetched from the Adzuna API.

This project demonstrates real-world backend architecture, JWT authentication, resume parsing, REST API integration, and full-stack development practices.

---

## Features

- User Registration & Login
- JWT-based Authentication
- Secure REST APIs
- Resume Upload (PDF)
- Resume Validation
- Resume Parsing using Apache Tika
- Skill Extraction & Matching
- Dynamic Job Recommendations
- Adzuna API Integration
- Global Exception Handling
- DTO-based Response Handling
- Frontend & Backend Integration
- Layered Architecture

---

## Tech Stack

### Backend
- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Maven
- REST APIs

### Frontend
- React.js
- Bootstrap

### Database
- MySQL

### Libraries & Tools
- Apache Tika
- RestTemplate
- Adzuna API
- Postman
- Git & GitHub

---

## System Architecture

```text
Frontend (React UI)
        ↓
REST API Request
        ↓
Spring Boot Controller
        ↓
Authentication Validation
        ↓
Resume Upload Service
        ↓
Apache Tika Resume Parsing
        ↓
Skill Extraction Service
        ↓
Skill Matching Service
        ↓
Adzuna API Integration
        ↓
API Response Mapping
        ↓
Response DTO
        ↓
Frontend UI Display
```

---

## Project Workflow

1. User registers and logs into the system
2. User uploads resume in PDF format
3. Backend validates uploaded resume
4. Resume text is extracted using Apache Tika
5. Skills are extracted from parsed text
6. Skills are matched with predefined skill set
7. Matching skills are sent to Adzuna API
8. Job recommendations are fetched dynamically
9. Recommended jobs are displayed in the frontend

---

## Authentication & Security

- JWT token-based authentication implemented
- Protected REST APIs using Spring Security
- Secure user login and registration
- Token validation for authorized access

---

## Resume Parsing & Skill Extraction

- Apache Tika used for PDF text extraction
- Skill extraction implemented using predefined internal matching logic
- Duplicate skills filtered
- Case normalization handled

---

## External API Integration

The application integrates with the Adzuna Job Search API to fetch real-time job recommendations.

### Features
- Dynamic skill-based job search
- API response mapping using DTOs
- External REST API communication
- Error handling for API failures

---

## Database Design

The database stores:

- User details
- Authentication data
- Resume analysis metadata

Job recommendations are fetched dynamically and are not stored internally.

---

## Architecture Used

- Monolithic Architecture
- Layered Architecture
- RESTful Backend Architecture
- DTO-Based Communication

### Layers
- Controller Layer
- Service Layer
- Repository Layer
- Utility Layer

---

## Error Handling

- Global exception handling implemented
- Validation handling implemented
- API exception handling added
- File validation for resume uploads

Handled cases:
- Invalid resumes
- Empty files
- Unsupported file formats
- Invalid API keys
- API failures
- Duplicate skills

---

## Advantages

- Real-time job recommendations
- Automated resume analysis workflow
- Cleaner backend architecture
- Real-world API integration
- Scalable design for future enhancements
- Better user experience

---

## Limitations

- Dependency on external APIs
- API rate limits
- Rule-based skill extraction
- Internet dependency
- Resume formatting variations may affect extraction accuracy

---

## Future Improvements

- AI/ML-based skill extraction
- ATS Resume Scoring
- Resume improvement suggestions
- Interview question recommendations
- Skill gap analysis
- Docker deployment
- Cloud deployment
- Microservices architecture
- Caching implementation

---

## Installation & Setup

### Backend Setup

```bash
git clone <repository-url>

cd backend

mvn clean install

mvn spring-boot:run
```

### Frontend Setup

```bash
cd frontend

npm install

npm start
```

---

## Environment Variables

Create an `application.properties` file and configure:

```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=

jwt.secret=

adzuna.app.id=
adzuna.api.key=
```

---

## API Testing

API endpoints were tested using Postman.

Example APIs:
- User Registration
- User Login
- Resume Upload
- Job Recommendation APIs

---

## Screenshots

Add screenshots here:

- Login Page
- Registration Page
- Dashboard
- Resume Upload
- Job Recommendation Results

---

## Learning Outcomes

Through this project, I learned:

- Full-stack development
- Spring Security & JWT
- REST API development
- File handling in Spring Boot
- External API integration
- React frontend integration
- Layered architecture
- Exception handling
- Professional project structuring

---

## Author

Manikanta  
MCA Final-Year Student  
Java Full Stack Developer

---

## License

This project is developed for educational and learning purposes.
