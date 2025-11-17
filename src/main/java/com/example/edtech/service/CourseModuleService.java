package com.example.edtech.service;

import com.example.edtech.model.Module;
import com.example.edtech.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseModuleService {

    private final ModuleRepository moduleRepository;

    public List<Module> findAll() {
        return moduleRepository.findAll();
    }

    public Optional<Module> findById(Long id) {
        return moduleRepository.findById(id);
    }

    public List<Module> findByCourseId(Long courseId) {
        return moduleRepository.findByCourseId(courseId);
    }

    public Module save(Module courseModule) {
        return moduleRepository.save(courseModule);
    }

    public void deleteById(Long id) {
        moduleRepository.deleteById(id);
    }
}