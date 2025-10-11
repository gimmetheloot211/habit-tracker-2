import { api } from "./axiosInstance";

import type { DailyHabitLogRequest } from "../types/dto/logs/request/daily/DailyHabitLogRequest";
import type { DailyHabitLogResponse } from "../types/dto/logs/response/daily/DailyHabitLogResponse";
import type { WeeklyHabitLogRequest } from "../types/dto/logs/request/weekly/WeeklyHabitLogRequest";
import type { WeeklyHabitLogResponse } from "../types/dto/logs/response/weekly/WeeklyHabitLogResponse";
import type { FullHabitLogsForWeek } from "../types/dto/logs/response/FullHabitLogsForWeek";

export async function createOrUpdateDailyHabitLog(
    habitId: number,
    data: DailyHabitLogRequest
): Promise<DailyHabitLogResponse> {
    const res = await api.put<DailyHabitLogResponse>(`/v1/logs/daily/${habitId}`, data);
    return res.data;
}

export async function createOrUpdateWeeklyHabitLog(
    habitId: number,
    data: WeeklyHabitLogRequest
): Promise<WeeklyHabitLogResponse> {
    const res = await api.put<WeeklyHabitLogResponse>(`/v1/logs/weekly/${habitId}`, data);
    return res.data;
}

export async function getDailyHabitLog(
    habitId: number,
    date: string
): Promise<DailyHabitLogResponse> {
    const res = await api.get<DailyHabitLogResponse>(`/v1/logs/daily`, {
        params: { habitId, date },
    });
    return res.data;
}

export async function getFullHabitLogsForWeek(
    habitId: number,
    weekStart: string,
    weekEnd: string
): Promise<FullHabitLogsForWeek> {
    const res = await api.get<FullHabitLogsForWeek>(`/v1/logs/week`, {
        params: { habitId, weekStart, weekEnd },
    });
    return res.data;
}
