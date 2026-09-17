/* Implementa el caso de uso */

package com.example.application.service;

import java.util.List;


import com.example.application.port.in.CreateTaskUseCase;
import com.example.application.port.in.DeleteTaskUseCase;
import com.example.application.port.in.GetTaskUseCase;
import com.example.application.port.in.ListTaskUseCase;
import com.example.application.port.in.UpdateTaskUseCase;
import com.example.application.port.in.UploadImageTaskUse;
import com.example.application.port.out.FileStoragePort;
import com.example.application.port.out.TaskRepositoryPort;
import com.example.domain.exception.TaskNotFoundException;
import com.example.domain.model.Task;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TaskService implements CreateTaskUseCase, GetTaskUseCase, ListTaskUseCase, DeleteTaskUseCase, UpdateTaskUseCase, UploadImageTaskUse {

    private final TaskRepositoryPort taskRepositoryPort;
    private final FileStoragePort fileStoragePort;

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
        
        Task task = taskRepositoryPort.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        fileStoragePort.delete(task.getImage());
        taskRepositoryPort.delete(id);
        fileStoragePort.delete(task.getImage());

    }

    @Override
    public Task update(Long id, Task task) {
        
        Task foundedTask = taskRepositoryPort.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        foundedTask.update(task.getTitle(), task.getDescription());

        foundedTask.changeStatusTo(task.getStatus());

        return taskRepositoryPort.save(foundedTask);

    }

    @Override
    public Task uploadImage(long id, String fileName, byte[] content) {

        Task task = taskRepositoryPort.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        String previousImage = task.getImage();
        
        String imagePath = fileStoragePort.store(fileName, content);

        task.attachImage(imagePath);

        Task saved = taskRepositoryPort.save(task);

        fileStoragePort.delete(previousImage);

        return saved;

    }

    @Override
    public Task SetImage(Task task, String fileName, byte[] content) {

        String imagePath = fileStoragePort.store(fileName, content);

        task.attachImage(imagePath);

        return taskRepositoryPort.save(task);

    }


}
