package com.chaabiamal.springboot_cassandra_demo.service.impl;

import com.chaabiamal.springboot_cassandra_demo.model.Modules;
import com.chaabiamal.springboot_cassandra_demo.repository.ModuleRepository;
import com.chaabiamal.springboot_cassandra_demo.service.ModuleService;
import com.chaabiamal.springboot_cassandra_demo.service.dto.ModuleDTO;
import com.chaabiamal.springboot_cassandra_demo.service.mapper.ModuleMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final ModuleMapper moduleMapper;

    public ModuleServiceImpl(ModuleRepository moduleRepository, ModuleMapper moduleMapper) {
        this.moduleRepository = moduleRepository;
        this.moduleMapper = moduleMapper;
    }

    @Override
    public ModuleDTO save(ModuleDTO moduleDTO) {
        Modules module = moduleMapper.toEntity(moduleDTO);
        module = moduleRepository.save(module);
        return moduleMapper.toDto(module);
    }

    @Override
    public ModuleDTO update(ModuleDTO moduleDTO) {
        Modules module = moduleMapper.toEntity(moduleDTO);
        module = moduleRepository.save(module);
        return moduleMapper.toDto(module);
    }

    public Optional<ModuleDTO> partialUpdate(UUID id,ModuleDTO moduleDTO) {
        return moduleRepository.findByid(id)
                .map(existingModule -> {
                    if (moduleDTO.name() != null) {
                          existingModule.setName(moduleDTO.name());
                    }
                    if (moduleDTO.description()!= null) {
                        existingModule.setDescription(moduleDTO.description());
                    }
                    Modules updatedStatus = moduleRepository.save(existingModule);
                    return moduleMapper.toDto(updatedStatus);
                });
    }


    @Transactional(readOnly = true)
    public Page<ModuleDTO> findAll(Pageable pageable) {
        return (Page<ModuleDTO>) moduleRepository.findAll(pageable)
                .map(moduleMapper::toDto);
    }



    @Transactional(readOnly = true)
    public Optional<ModuleDTO> findOne(UUID id) {
        return moduleRepository.findById(id)
                .map(moduleMapper::toDto);
    }

    public void delete(UUID id) {
        moduleRepository.findById2(id);
    }
}
