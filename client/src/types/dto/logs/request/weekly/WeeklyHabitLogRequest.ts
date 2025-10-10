export interface WeeklyHabitLogRequest {
    minutesDone?: number | null;
    notes?: string | null;
    weekStart: string;
    weekEnd: string
}