import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../api/api";

export default function Login(){
    const [email,setEmail] = useState("");
    const [password,setPassword] = useState("");
    const navigate = useNavigate();
    const [error,setError] = useState("");

    const login = async (e) =>{
        e.preventDefault();
        if (email.trim() === "" && password.trim() === "") {
            setError("Email is required")
            return
        } else{
            try {
                const res = await api.post("/auth/login", {email, password});
                localStorage.setItem("token",res.data);
                navigate("/");
            }
            catch {
                alert("Invalid credentials");
            }
        }
        setError("");
    };

    return (
        <div className="min-h-screen flex items-center justify-center bg-gradient-to-r from-indigo-500 to-purple-500 bg-red-300">
            <div className="bg-white p-6 rounded-xl shadow-xl w-96">
                <h1 className="text-xl font-bold mb-4">Login</h1>
                {error && <p className="text-red-500" > {error} </p>}
                <input 
                className="border p-2 w-full mb-3" placeholder="Email"
                onChange={e => setEmail(e.target.value)} />
                <input 
                type="password" 
                className="border p-2 w-full mb-3" 
                placeholder="Password"
                onChange={e => setPassword(e.target.value)} />
                <button onClick={login} className="bg-indigo-600 text-white w-full py-2 rounded">Login</button>
                <div className = "flex items-center justify-center gap-1 py-2 my-2">
                    <p className=""> Don't have an account ? </p>
                    <Link to="/signup" className="pr-4" >Sign up</Link>
                </div>
            </div>
        </div>
    );
}