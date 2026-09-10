/* Implementa el caso de uso */

package com.example.application.service;

import org.springframework.stereotype.Service;

import com.example.application.port.in.CreateTaskUseCase;
import com.example.application.port.out.TaskRepositoryPort;
import com.example.domain.model.Task;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service 
public class TaskService implements CreateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    public Task create(Task task) {
        return taskRepositoryPort.save(task);
    }

}
