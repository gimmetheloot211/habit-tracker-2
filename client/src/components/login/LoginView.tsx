"use client";
import "./LoginView.css";

interface LoginViewProps {
  username: string;
  password: string;
  error: string;
  loading: boolean;
  setUsername: (value: string) => void;
  setPassword: (value: string) => void;
  handleSubmit: (e: React.FormEvent) => void;
}

export default function LoginView({
  username,
  password,
  error,
  loading,
  setUsername,
  setPassword,
  handleSubmit,
}: LoginViewProps) {
  return (
    <div className="login-container">
      <form onSubmit={handleSubmit} className="login-form">
        <h1 className="login-title">Login</h1>

        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          className="login-input"
        />

        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="login-input"
        />

        {error && <p className="login-error">{error}</p>}

        <button type="submit" disabled={loading} className="login-button">
          {loading ? "Logging in..." : "Login"}
        </button>
      </form>
    </div>
  );
}
