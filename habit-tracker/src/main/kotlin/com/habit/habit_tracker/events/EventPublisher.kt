package com.habit.habit_tracker.events

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class EventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    fun publishDailyHabitLogUpdated(habitId: Long, minutesDoneChange: Int, date: LocalDate) {
        applicationEventPublisher.publishEvent(DailyHabitLogUpdatedEvent(habitId, minutesDoneChange, date))
    }
}