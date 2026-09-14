/* Implementa el caso de uso */

package com.example.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.application.port.in.CreateTaskUseCase;
import com.example.application.port.in.DeleteTaskUseCase;
import com.example.application.port.in.GetTaskUseCase;
import com.example.application.port.in.ListTaskUseCase;
import com.example.application.port.in.UpdateTaskUseCase;
import com.example.application.port.out.TaskRepositoryPort;
import com.example.domain.exception.TaskNotFoundException;
import com.example.domain.model.Task;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service 
public class TaskService implements CreateTaskUseCase, GetTaskUseCase, ListTaskUseCase, DeleteTaskUseCase, UpdateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    public Task create(Task task) {
        return taskRepositoryPort.save(task);
    }

    @Override
    public Task getById(Long id) {
        return taskRepositoryPort.findById(id)
                    .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public List<Task> listAll() {

        return taskRepositoryPort.listAll();
    
    }

    @Override
    public void delete(long id) {
        
        taskRepositoryPort.delete(id);

    }

    @Override
    public Task update(Task task) {

        return taskRepositoryPort.save(task);

    }

}
