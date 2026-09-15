package com.example.infrastructure.adapter.in.rest.dto;

import com.example.domain.model.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class UpdateTaskRequest {

    @NotBlank(message = "The title is mandatory")
    private String title;
    
    @NotBlank(message = "The description is mandatory")
    private String description;

    TaskStatus status;

}
