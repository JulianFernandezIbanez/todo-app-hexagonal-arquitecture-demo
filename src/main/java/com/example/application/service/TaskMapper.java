package com.example.application.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.domain.model.Task;
import com.example.infrastructure.adapter.in.rest.dto.TaskResponse;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "task.id", target = "id")
    @Mapping(source = "task.title", target = "title")
    @Mapping(source = "task.description", target = "desc")
    @Mapping(source = "task.status", target = "status")
    @Mapping(source = "task.createdAt", target = "created")
    @Mapping(source = "task.completedAt", target = "completed")
    TaskResponse mapTaskTotaskResponse(Task task);

}
