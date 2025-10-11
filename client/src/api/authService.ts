import { api } from "./axiosInstance";
import type { AuthResponse } from "../types/dto/auth/response/AuthResponse";
import type { RegisterRequest } from "../types/dto/auth/request/RegisterRequest";
import type { LoginRequest } from "../types/dto/auth/request/LoginRequest";

export async function registerUser(data: RegisterRequest): Promise<AuthResponse> {
    const res = await api.post<AuthResponse>("/v1/auth/register", data);
    return res.data;
}

export async function loginUser(data: LoginRequest): Promise<AuthResponse> {
    const res = await api.post<AuthResponse>("/v1/auth/login", data);
    return res.data;
}