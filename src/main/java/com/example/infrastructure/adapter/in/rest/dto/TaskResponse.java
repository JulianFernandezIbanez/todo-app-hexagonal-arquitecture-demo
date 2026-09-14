package com.example.infrastructure.adapter.in.rest.dto;

import java.time.LocalDateTime;

import com.example.domain.model.Task;
import com.example.domain.model.TaskStatus;

public record TaskResponse(
     long id,
     String title,
     String desc,
     TaskStatus status,
     LocalDateTime created,
     LocalDateTime completed
) {
    public static TaskResponse from(Task task) {
        return new TaskResponse(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getStatus(),
            task.getCreatedAt(),
            task.getCompletedAt()
        );
    }
}
