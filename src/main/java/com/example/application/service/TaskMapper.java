package com.example.application.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.domain.model.Task;
import com.example.infrastructure.adapter.in.rest.dto.TaskResponse;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "task.id", target = "id")
    @Mapping(source = "task.title", target = "title")
    @Mapping(source = "task.description", target = "description")
    @Mapping(source = "task.status", target = "status")
    @Mapping(source = "task.createdAt", target = "createdAt")
    @Mapping(source = "task.completedAt", target = "completedAt")
    TaskResponse mapTaskTotaskResponse(Task task);

}
