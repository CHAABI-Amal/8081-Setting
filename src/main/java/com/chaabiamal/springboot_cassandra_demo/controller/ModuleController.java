package com.chaabiamal.springboot_cassandra_demo.controller;

import com.chaabiamal.springboot_cassandra_demo.model.Modules;
import com.chaabiamal.springboot_cassandra_demo.repository.ModuleRepository;
import com.chaabiamal.springboot_cassandra_demo.service.ModuleService;
import com.chaabiamal.springboot_cassandra_demo.service.dto.ModuleDTO;
import com.chaabiamal.springboot_cassandra_demo.service.mapper.ModuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/amal/Module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ModuleMapper moduleMapper;

    @PostMapping("")
    public ResponseEntity<ModuleDTO> addModule(@Valid @RequestBody ModuleDTO moduleDTO) throws URISyntaxException {
        Modules modules = moduleMapper.toEntity(moduleDTO);
        modules.setId(UUID.randomUUID());
        Modules savedModules = moduleRepository.save(modules);
        ModuleDTO savedModuleDTO = moduleMapper.toDto(savedModules);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("A new Module is created with identifier " + savedModuleDTO.id())
                .body(savedModuleDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<ModuleDTO> findById(@PathVariable("id") UUID moduleId) {
        Optional<ModuleDTO> moduleOptional = moduleService.findOne(moduleId);
        return moduleOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public List<ModuleDTO> getModules() {
        List<Modules> kiosks = moduleRepository.findAll();
        return kiosks.stream()
                .map(moduleMapper::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable(value = "id") UUID moduleId) {
        moduleService.delete(moduleId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<ModuleDTO> partialUpdateModule(@PathVariable("id") UUID moduleId, @RequestBody ModuleDTO moduleDTO) {
        Optional<ModuleDTO> updatedModuleOptional = moduleService.partialUpdate(moduleId, moduleDTO);
        return updatedModuleOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
