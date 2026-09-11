package com.example.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.application.port.out.TaskRepositoryPort;
import com.example.domain.model.Task;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor 
public class JpaTaskRepositoryAdapter implements TaskRepositoryPort {

    private final SpringDataTaskRepository dataTaskRepository;
    private final TaskPersistenceMapper mapper;

    @Override
    public Task save(Task task) {

        task.initDefaults();
        TaskJpaEntity entity = mapper.toJpaEntity(task);
        TaskJpaEntity saved = dataTaskRepository.save(entity);
        
        return mapper.toDomain(saved);
    
    }

    @Override
    public Optional<Task> findById(Long id) {

        return dataTaskRepository.findById(id).map(mapper::toDomain);
   
    }

    @Override
    public List<Task> listAll() {

        return dataTaskRepository.findAll()
            .stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());

    }

}
