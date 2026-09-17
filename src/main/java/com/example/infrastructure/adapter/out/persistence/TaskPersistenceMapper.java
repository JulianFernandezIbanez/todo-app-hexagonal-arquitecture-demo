package com.example.infrastructure.adapter.out.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.domain.model.Task;
import com.example.infrastructure.adapter.in.rest.dto.TaskResponse;
import com.example.infrastructure.adapter.in.rest.dto.UpdateTaskRequest;

@Mapper(componentModel = "spring")
public interface TaskPersistenceMapper {

    Task toDomain(TaskJpaEntity taskJpaEntity);

    /*@Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "completedAt", ignore = true)
    @Mapping(target = "image", ignore = true)*/
    Task toDomain(UpdateTaskRequest updateTaskRequest);

    TaskJpaEntity toJpaEntity(Task task);

    TaskResponse toResponse(Task task);
}