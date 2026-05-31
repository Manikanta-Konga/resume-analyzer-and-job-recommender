import { useNavigate } from "react-router-dom";


export default function Navbar() {

    const userName = localStorage.getItem("userName");

    const navbar = useNavigate();

    const handleLogut = () => {
        localStorage.removeItem("token");
        localStorage.removeItem("userName");
        navbar("/");
        alert("You have logged out successfully");
        
    }


    return (
        <>
        
        <nav className="navbar navbar-dark bg-dark">

            <div className="container d-flex justify-content-between">

                <span className="navbar-brand mb-0 h1 text-center">
                    AI Resume Analyzer and Job Recommendation System
                </span>

                <span className="text-white">
                    Welcome, {userName}
                </span>

                <button className="btn btn-danger" onClick={handleLogut}>
                    Logout
                </button>

            </div>
            
        </nav>

        </>
    )

}