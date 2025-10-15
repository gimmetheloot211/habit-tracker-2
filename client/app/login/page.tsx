"use client"

import { useState } from "react"
import { useRouter } from "next/navigation"
import { loginUser } from "@/api/authService"
import { useAuth } from "@/context/AuthContext"

export default function LoginPage() {
    const router = useRouter();
    const { login } = useAuth();
  
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);
  
    const handleSubmit = async (e: React.FormEvent) => {
      e.preventDefault();
      setError("");
      setLoading(true);
  
      try {
        const res = await loginUser({ username, password });
        login(res.token);
      } catch (err: any) {
        setError("Invalid username or password");
      } finally {
        setLoading(false);
      }
    };
  
    return (
      <div className="flex min-h-screen items-center justify-center bg-gray-100">
        <form
          onSubmit={handleSubmit}
          className="w-full max-w-sm rounded-2xl bg-white p-6 shadow-md"
        >
          <h1 className="mb-4 text-2xl font-semibold text-center">Login</h1>
  
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="mb-3 w-full rounded-lg border border-gray-300 p-2"
          />
  
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="mb-3 w-full rounded-lg border border-gray-300 p-2"
          />
  
          {error && <p className="mb-3 text-sm text-red-600">{error}</p>}
  
          <button
            type="submit"
            disabled={loading}
            className="w-full rounded-lg bg-blue-600 py-2 text-white hover:bg-blue-700 disabled:opacity-50"
          >
            {loading ? "Logging in..." : "Login"}
          </button>
        </form>
      </div>
    );
  }