package com.example.infrastructure.adapter.in.rest;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.application.port.in.CreateTaskUseCase;
import com.example.application.port.in.DeleteTaskUseCase;
import com.example.application.port.in.GetTaskUseCase;
import com.example.application.port.in.ListTaskUseCase;
import com.example.application.port.in.UpdateTaskUseCase;
import com.example.application.port.in.UploadImageTaskUse;
import com.example.domain.model.Task;
import com.example.infrastructure.adapter.in.rest.dto.CreateTaskRequest;
import com.example.infrastructure.adapter.in.rest.dto.TaskResponse;
import com.example.infrastructure.adapter.in.rest.dto.UpdateTaskRequest;
import com.example.infrastructure.adapter.out.persistence.TaskPersistenceMapper;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;





@RestController 
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor 
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final GetTaskUseCase getTaskUseCase;
    private final ListTaskUseCase listTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final UploadImageTaskUse uploadImageTaskUse;
    private final TaskPersistenceMapper mapper;

    @PostMapping
    public ResponseEntity<TaskResponse> create(
        @Valid 
        @RequestPart ("Task") CreateTaskRequest request, 
        @RequestPart(value = "Image", required = false) MultipartFile image) throws IOException {


            Task task = Task.builder()
                        .title(request.getTitle())
                        .description(request.getDescription())
                    .build();

            Task saved = task;

            if (image != null && image.isEmpty()) {
                saved = uploadImageTaskUse.SetImage(
                task,
                image.getOriginalFilename(),
                image.getBytes());
            }

            
            saved = createTaskUseCase.create(saved);

             return ResponseEntity.status(HttpStatus.CREATED)
                    .body(mapper.toResponse(saved));

    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable Long id) {
        
        Task task = getTaskUseCase.getById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(mapper.toResponse(task));

    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> listAll() {

        List<TaskResponse> response = listTaskUseCase.listAll()
            .stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional 
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable long id){

        deleteTaskUseCase.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    
    @PutMapping("/{id}")
    @Transactional 
    public ResponseEntity<TaskResponse> updateTask(
        @Valid
        @PathVariable Long id, 
        @RequestBody UpdateTaskRequest request) {

        Task task = mapper.toDomain(request);

        Task updated = updateTaskUseCase.update(id, task);

        return ResponseEntity.ok(mapper.toResponse(updated));

    }

    @PostMapping("/{id}/image")
    public ResponseEntity<TaskResponse> uploadImage(@PathVariable long id, @RequestParam("image") MultipartFile image) throws IOException {

        Task task = uploadImageTaskUse.uploadImage(id, image.getOriginalFilename(), image.getBytes());

        return ResponseEntity.ok(mapper.toResponse(task));
    }

}
