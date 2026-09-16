package com.example.infrastructure;

import org.springframework.context.annotation.Bean;

import com.example.application.port.out.FileStoragePort;
import com.example.application.port.out.TaskRepositoryPort;
import com.example.application.service.TaskService;

@org.springframework.context.annotation.Configuration 
public class Configuration {

    @Bean
    TaskService taskService(TaskRepositoryPort taskRepositoryPort, FileStoragePort fileStoragePort) {

        return new TaskService(taskRepositoryPort, fileStoragePort);
    }

}
