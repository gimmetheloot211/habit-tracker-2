import { api } from "./axiosInstance";
import type { HabitCreateRequest } from "../types/dto/habit/request/HabitCreateRequest";
import type { HabitUpdateRequest} from "../types/dto/habit/request/HabitUpdateRequest";
import type { HabitResponse} from "../types/dto/habit/response/HabitResponse";

export async function createHabit(data: HabitCreateRequest): Promise<HabitResponse> {
    const res = await api.post<HabitResponse>("/v1/habits", data);
    return res.data;
}

export async function updateHabit(habitId: number, data: HabitUpdateRequest): Promise<HabitResponse> {
    const res = await api.patch<HabitResponse>(`/v1/habits/${habitId}`, data);
    return res.data;
}

export async function getHabit(habitId: number): Promise<HabitResponse> {
    const res = await api.get<HabitResponse>(`/v1/habits/${habitId}`);
    return res.data;
}

export async function getAllHabits(): Promise<HabitResponse[]> {
    const res = await api.get<HabitResponse[]>("/v1/habits");
    return res.data;
}