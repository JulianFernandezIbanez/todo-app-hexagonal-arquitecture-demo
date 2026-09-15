package com.example.infrastructure.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.example.domain.model.Task;
import com.example.infrastructure.adapter.in.rest.dto.TaskResponse;
import com.example.infrastructure.adapter.in.rest.dto.UpdateTaskRequest;

@Mapper(componentModel = "spring")
public interface TaskPersistenceMapper {

    Task toDomain(TaskJpaEntity taskJpaEntity);

    Task toDomain(UpdateTaskRequest updateTaskRequest);

    TaskJpaEntity toJpaEntity(Task task);

    TaskResponse toResponse(Task task);
}