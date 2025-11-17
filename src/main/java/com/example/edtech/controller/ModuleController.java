package com.example.edtech.controller;

import com.example.edtech.model.Module;
import com.example.edtech.service.CourseModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/modules")
@RequiredArgsConstructor
public class ModuleController {

    private final CourseModuleService moduleService;

    @GetMapping
    public List<Module> getAllModules() {
        return moduleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Module> getModuleById(@PathVariable Long id) {
        Optional<Module> module = moduleService.findById(id);
        return module.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/course/{courseId}")
    public List<Module> getModulesByCourse(@PathVariable Long courseId) {
        return moduleService.findByCourseId(courseId);
    }

    @PostMapping
    public Module createModule(@RequestBody Module module) {
        return moduleService.save(module);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Module> updateModule(@PathVariable Long id, @RequestBody Module module) {
        if (moduleService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        module.setId(id);
        return ResponseEntity.ok(moduleService.save(module));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable Long id) {
        if (moduleService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        moduleService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}