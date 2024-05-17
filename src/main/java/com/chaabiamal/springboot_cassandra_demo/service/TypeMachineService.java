package com.chaabiamal.springboot_cassandra_demo.service;

import com.chaabiamal.springboot_cassandra_demo.service.dto.MachineDTO;
import com.chaabiamal.springboot_cassandra_demo.service.dto.TypeMachineDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface TypeMachineService {

    TypeMachineDTO save(TypeMachineDTO TypemachineDTO);

    TypeMachineDTO update(TypeMachineDTO TypemachineDTO);

    Optional<TypeMachineDTO> partialUpdate(UUID id, TypeMachineDTO TypemachineDTO);

    Page<TypeMachineDTO> findAll(Pageable pageable);

    Optional<TypeMachineDTO> findOne(UUID id);

    void delete(UUID id);
    Optional<TypeMachineDTO> findById(UUID id);


}
