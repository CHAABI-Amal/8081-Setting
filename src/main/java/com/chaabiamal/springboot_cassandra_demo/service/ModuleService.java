package com.chaabiamal.springboot_cassandra_demo.service;
import com.chaabiamal.springboot_cassandra_demo.service.dto.ModuleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ModuleService {

    ModuleDTO save(ModuleDTO sDTO);

    ModuleDTO update( ModuleDTO sDTO);

    Optional< ModuleDTO> partialUpdate(UUID id, ModuleDTO sDTO);

    Page< ModuleDTO> findAll(Pageable pageable);

    Optional< ModuleDTO> findOne(UUID id);

    void delete(UUID id);


}