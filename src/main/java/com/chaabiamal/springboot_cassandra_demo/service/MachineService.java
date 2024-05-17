package com.chaabiamal.springboot_cassandra_demo.service;

import com.chaabiamal.springboot_cassandra_demo.service.dto.MachineDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface MachineService {

    MachineDTO save(MachineDTO machineDTO);

    MachineDTO update(MachineDTO machineDTO);

    Optional<MachineDTO> partialUpdate(UUID id, MachineDTO machineDTO);

    Page<MachineDTO> findAll(Pageable pageable);

    Optional<MachineDTO> findOne(UUID id);

    void delete(UUID id);
    Optional<MachineDTO> findById(UUID id);


}
