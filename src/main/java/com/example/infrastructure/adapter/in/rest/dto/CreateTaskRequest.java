package com.example.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class CreateTaskRequest {

    @NotBlank(message = "The title is mandatory")
    private String title;
    private String description;

}
