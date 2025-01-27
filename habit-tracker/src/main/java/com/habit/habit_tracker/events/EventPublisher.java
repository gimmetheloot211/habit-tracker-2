package com.habit.habit_tracker.events;

import java.time.LocalDate;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public EventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishDHLUpdated(Long habitId, Integer minutesDone, LocalDate date) {
        applicationEventPublisher.publishEvent(new DHLUpdatedEvent(habitId, minutesDone, date));
    }
}
