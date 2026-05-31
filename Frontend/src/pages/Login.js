import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import axios from 'axios';

export default function Login() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const [loading, setLoading] = useState(false);

    const navigate = useNavigate();

    const handleLogin = async (event) => {

        event.preventDefault();

        setLoading(true);

        let userData = {
            email,
            password
        }

        try {
            const response = await axios.post("http://localhost:8080/auth/login", userData);

            console.log(response);

            if (response.token) {

                localStorage.setItem(
                    "token",
                    response.data.token
                );

                localStorage.setItem(
                    "userName",
                    response.data.userName
                );

                alert("Logined Successfully");

                navigate("/dashboard");
            } else if(response.data.message){
                alert(response.data.message);
            } else {
                alert("Enter correct credentials. If not logged in user, try to register first.")
            }
        } catch (error) {

            console.error(error);
            alert("Login failed, enter correct credentials");

        } finally {
            setLoading(false);
        }
    }


    return (
        <div className="container mt-5">

            <div className="row justify-content-center">

                <div className="col-md-4">

                    <div className="card shadow p-4">

                        <h2 className="text-center mb-4">
                            Login
                        </h2>

                        <form onSubmit={handleLogin}>

                            <div className="mb-3">

                                <label className="form-label">
                                    Email
                                </label>

                                <input type="email" className="form-control"
                                    placeholder="Enter your email"
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
                                className="btn btn-primary w-100"
                                disabled={loading}>
                                {
                                    loading ? (
                                        <>
                                            <span className='spinner-border spinner-border-sm me-2'
                                                role='status' />
                                            Logging in
                                        </>
                                    ) : (
                                        "Login"
                                    )
                                }


                            </button>

                            <p className="text-center mt-3">
                                Don't have anaccount?{" "}
                                <Link to="/register">
                                    Register</Link>
                            </p>

                        </form>

                    </div>
                </div>
            </div>
        </div>
    )

}