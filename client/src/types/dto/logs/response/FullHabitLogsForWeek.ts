import { DailyHabitLogResponse } from "./daily/DailyHabitLogResponse";
import { WeeklyHabitLogResponse } from "./weekly/WeeklyHabitLogResponse";
import { HabitResponse } from "../../habit/response/HabitResponse";

export interface FullHabitLogsForWeek {
  dailyHabitLogs: DailyHabitLogResponse[];
  weeklyHabitLog?: WeeklyHabitLogResponse | null;
  habit: HabitResponse;
}