
import { Link, useNavigate } from 'react-router-dom';
import { useState } from 'react';
import axios from 'axios';

export default function Register() {

    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);

    const navigate = useNavigate();


    const handleRegister = async (event) => {
        event.preventDefault();

        setLoading(true);

        const userData = {
            name,
            email,
            password
        }

        try {
            const response = await axios.post(("http://localhost:8080/auth/register"),
                userData
            );

            console.log(response);

            alert(response.data.message);

            navigate("/")

        } catch (error) {

            if (error.response) {
                alert(error.response.data.message);
            } else {
                alert("Registration Failed! Try Again");
            }

        } finally {
            setLoading(false);
        }

    }

    return (
        <>
            <div className="container mt-5">

                <div className="row justify-content-center">

                    <div className="col-md-4">

                        <div className="card shadow p-4">

                            <h2 className="text-center mb-4">
                                Register
                            </h2>

                            <form onSubmit={handleRegister}>

                                <div className="mb-3">

                                    <label className="form-label">
                                        Name
                                    </label>

                                    <input type="text" className="form-control"
                                        placeholder="Enter your Name"
                                        value={name}
                                        onChange={(e) => (
                                            setName(e.target.value)
                                        )} />

                                </div>

                                <div className="mb-3">

                                    <label className="form-label">
                                        Email
                                    </label>

                                    <input type="email" className="form-control"
                                        placeholder="Enter your Email"
                                        value={email}
                                        onChange={(e) => (
                                            setEmail(e.target.value)
                                        )} />

                                </div>

                                <div className="mb-3">

                                    <label className="form-label">
                                        Password
                                    </label>

                                    <input type="password" className="form-control"
                                        placeholder="Enter your password"
                                        value={password}
                                        onChange={(e) => (
                                            setPassword(e.target.value)
                                        )} />

                                </div>

                                <button type='submit'
                                className="btn btn-success w-100"
                                disabled={loading}
                                >
                                    {
                                        loading ? (
                                            <>
                                            <span className='spinner-border spinner-border-sm me-2'
                                            role='status'/>
                                            Registering....
                                            </>
                                            ) : "Register"
                                    }
                                    
                                </button>

                                <p className="text-center mt-3">
                                    Already have an account?{" "}
                                    <Link to="/">
                                        Login</Link>
                                </p>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )

}