import { useState } from "react";
import Navbar from "../components/Navbar";
import axios from 'axios'
import { Navigate } from "react-router-dom";

export default function Dashboard() {


    const [resumeFile, setResumeFile] = useState(null);

    const [jobs, setJobs] = useState([]);

    const [loading, setLoading] = useState(false);


    const uploadResume = async () => {

        setLoading(true);
        if (!resumeFile) {
            alert("Please select a file");
            return;
        }

        const formData = new FormData();

        formData.append("File", resumeFile);

        try {

            const response = await axios.post(
                "http://localhost:8080/auth/uploadResume",
                formData
            );

            console.log(response.data);
            setJobs(response.data);

            alert("Uploaded Successfully");

        } catch (error) {

            if(error.response) {
                alert(error.response.data.message)
            } else alert("Something went wrong, try again!");
            
        } finally {
            setLoading(false);
        }
    };

    //Securing dashboard page. So, that non loggedin user can't open
    const token = localStorage.getItem("token");
    if (!token) {
        return <Navigate to="/" />
    }


    return (

        <>
            <Navbar />

            <div className="container py-5">

                {/* Page Header */}
                <div className="text-center mb-5">

                    <h1 className="fw-bold text-primary">
                        Resume Analyzer Dashboard
                    </h1>

                    <p className="text-muted">
                        Upload your resume and get AI-powered analysis with job recommendations
                    </p>

                </div>

                {/* Upload Section */}

                <div className="card border-0 shadow-lg mb-5">

                    <div className="card-body p-4">

                        <div className="d-flex justify-content-between align-items-center mb-4">

                            <div>

                                <h4 className="fw-bold mb-1">
                                    Upload Resume
                                </h4>

                                <p className="text-muted mb-0">
                                    Supported formats: PDF, DOC, DOCX
                                </p>

                            </div>

                        </div>

                        <input
                            type="file"
                            className="form-control form-control-lg mb-3"
                            onChange={(e) => {
                                setResumeFile(e.target.files[0]);

                                console.log(e.target.files[0]);
                            }}
                        />

                        {
                            resumeFile && (
                                <div className="alert alert-info">

                                    <strong>Selected File:</strong> {resumeFile.name}

                                </div>
                            )
                        }

                        <button
                            className="btn btn-primary btn-lg w-100"
                            onClick={uploadResume}
                            disabled={loading}
                        >
                            {
                                loading ? (
                                    <>
                                        <span
                                            className="spinner-border spinner-border-sm me-2"
                                            role="status"
                                        ></span>

                                        Uploading...
                                    </>
                                ) : (
                                    "Analyze Resume"
                                )
                            }
                        </button>

                    </div>

                </div>

                {/* Analysis Section */}

                <div className="row g-4 mb-5">

                    {/* Matched Role */}

                    <div className="col-lg-6">

                        <div className="card border-0 shadow-sm h-100">

                            <div className="card-body p-4">

                                <div className="d-flex justify-content-between align-items-center mb-3">

                                    <h5 className="fw-bold text-secondary mb-0">
                                        Matched Role
                                    </h5>

                                    <span className="badge bg-primary">
                                        AI Prediction
                                    </span>

                                </div>

                                <div className="bg-light rounded-3 p-4 text-center">

                                    <h2 className="fw-bold text-success mb-0">
                                        {jobs?.predictedRole || "Not Available"}
                                    </h2>

                                </div>

                            </div>

                        </div>

                    </div>

                    {/* Resume Score */}

                    <div className="col-lg-6">

                        <div className="card border-0 shadow-sm h-100">

                            <div className="card-body p-4">

                                <div className="d-flex justify-content-between align-items-center mb-3">

                                    <h5 className="fw-bold text-secondary mb-0">
                                        Resume Score
                                    </h5>

                                    <span className="badge bg-success">
                                        ATS Score
                                    </span>

                                </div>

                                <div className="bg-light rounded-3 p-4 text-center">

                                    <h1 className="fw-bold text-success mb-0">
                                        {jobs?.atsScore || 0}%
                                    </h1>

                                </div>

                            </div>

                        </div>

                    </div>

                    {/* Matched Skills */}

                    <div className="col-lg-6">

                        <div className="card border-0 shadow-sm h-100">

                            <div className="card-body p-4">

                                <div className="d-flex justify-content-between align-items-center mb-4">

                                    <h5 className="fw-bold text-secondary mb-0">
                                        Matched Skills
                                    </h5>

                                    <span className="badge bg-success">
                                        Strong Skills
                                    </span>

                                </div>

                                {
                                    jobs?.matchedSkills?.length > 0 ? (

                                        <div className="d-flex flex-wrap gap-2">

                                            {
                                                jobs.matchedSkills.map((skill, index) => (

                                                    <span
                                                        key={index}
                                                        className="badge rounded-pill bg-success-subtle text-success border border-success px-3 py-2"
                                                    >
                                                        {skill}
                                                    </span>

                                                ))
                                            }

                                        </div>

                                    ) : (

                                        <div className="text-center text-muted py-4">

                                            No matched skills found

                                        </div>

                                    )
                                }

                            </div>

                        </div>

                    </div>

                    {/* Missing Skills */}

                    <div className="col-lg-6">

                        <div className="card border-0 shadow-sm h-100">

                            <div className="card-body p-4">

                                <div className="d-flex justify-content-between align-items-center mb-4">

                                    <h5 className="fw-bold text-secondary mb-0">
                                        Missing Skills
                                    </h5>

                                    <span className="badge bg-danger">
                                        Need Improvement
                                    </span>

                                </div>

                                {
                                    jobs?.missingSkills?.length > 0 ? (

                                        <div className="d-flex flex-wrap gap-2">

                                            {
                                                jobs.missingSkills.map((skill, index) => (

                                                    <span
                                                        key={index}
                                                        className="badge rounded-pill bg-danger-subtle text-danger border border-danger px-3 py-2"
                                                    >
                                                        {skill}
                                                    </span>

                                                ))
                                            }

                                        </div>

                                    ) : (

                                        <div className="text-center text-muted py-4">

                                            No missing skills found

                                        </div>

                                    )
                                }

                            </div>

                        </div>

                    </div>

                </div>

                {/* Job Recommendation Section */}

                <div className="mb-4">

                    <div className="d-flex justify-content-between align-items-center mb-4">

                        <div>

                            <h3 className="fw-bold mb-1">
                                Recommended Jobs
                            </h3>

                            <p className="text-muted mb-0">
                                Jobs recommended based on your resume analysis
                            </p>

                        </div>

                    </div>

                    {
                        jobs.length === 0 ? (

                            <div className="card border-0 shadow-sm">

                                <div className="card-body text-center py-5">

                                    <h5 className="text-muted">
                                        No Job Recommendations Yet
                                    </h5>

                                    <p className="text-muted mb-0">
                                        Upload your resume to get personalized recommendations
                                    </p>

                                </div>

                            </div>

                        ) : (

                            <div className="row g-4">

                                {
                                    jobs.jobRecommendations.map((job, index) => (

                                        <div
                                            className="col-lg-6"
                                            key={index}
                                        >

                                            <div className="card border-0 shadow-sm h-100">

                                                <div className="card-body p-4">

                                                    <div className="d-flex justify-content-between align-items-start mb-3">

                                                        <div>

                                                            <h5 className="fw-bold mb-1">
                                                                {job.title}
                                                            </h5>

                                                            <p className="text-muted mb-0">
                                                                {job.company}
                                                            </p>

                                                        </div>

                                                        <span className="badge bg-primary">
                                                            Recommended
                                                        </span>

                                                    </div>

                                                    <div className="mb-3">

                                                        <p className="mb-2">
                                                            <strong>Location:</strong> {job.location}
                                                        </p>

                                                        <p className="mb-0">
                                                            <strong>Salary:</strong> {job.salary || "Not Provided"}
                                                        </p>

                                                    </div>

                                                    <a
                                                        href={job.applyUrl}
                                                        target="_blank"
                                                        rel="noreferrer"
                                                        className="btn btn-success w-100"
                                                    >
                                                        Apply Now
                                                    </a>

                                                </div>

                                            </div>

                                        </div>

                                    ))
                                }

                            </div>

                        )
                    }

                </div>

            </div>

        </>
    )

}