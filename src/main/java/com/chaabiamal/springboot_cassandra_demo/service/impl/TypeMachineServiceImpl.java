package com.chaabiamal.springboot_cassandra_demo.service.impl;
import com.chaabiamal.springboot_cassandra_demo.model.TypeMachine;
import com.chaabiamal.springboot_cassandra_demo.repository.TypeMachineRepository;
import com.chaabiamal.springboot_cassandra_demo.service.TypeMachineService;
import com.chaabiamal.springboot_cassandra_demo.service.dto.TypeMachineDTO;
import com.chaabiamal.springboot_cassandra_demo.service.mapper.TypeMachineMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TypeMachineServiceImpl implements TypeMachineService {

    private final TypeMachineRepository TypemachineRepository;
    private final TypeMachineMapper TypemachineMapper;

    public TypeMachineServiceImpl(TypeMachineRepository TypemachineRepository, TypeMachineMapper TypemachineMapper) {
        this.TypemachineRepository = TypemachineRepository;
        this.TypemachineMapper = TypemachineMapper;
    }

    @Override
    public Optional<TypeMachineDTO> findById(UUID id) {
        return TypemachineRepository.findById(id).map(TypemachineMapper::toDto);
    }

    @Override
    public TypeMachineDTO save(TypeMachineDTO TypemachineDTO) {
        TypeMachine Typemachine = TypemachineMapper.toEntity(TypemachineDTO);
        Typemachine.setMachinetypeId(UUID.randomUUID()); // Generating UUID
        Typemachine = TypemachineRepository.save(Typemachine);
        return TypemachineMapper.toDto(Typemachine);
    }

    @Override
    public TypeMachineDTO update(TypeMachineDTO TypemachineDTO) {
        TypeMachine Typemachine = TypemachineMapper.toEntity(TypemachineDTO);
        Typemachine = TypemachineRepository.save(Typemachine);
        return TypemachineMapper.toDto(Typemachine);
    }


    @Override
    public Optional<TypeMachineDTO> partialUpdate(UUID id, TypeMachineDTO TypemachineDTO) {
        return TypemachineRepository.findById(id)
                .map(existingMachine -> {
                    // Map non-null fields from machineDTO to existingMachine
                    if (TypemachineDTO.machinetypeId() != null) {
                        existingMachine.setMachinetypeId(TypemachineDTO.machinetypeId());
                    }
                    if (TypemachineDTO.name() != null) {
                        existingMachine.setName(TypemachineDTO.name());
                    }
                    if (TypemachineDTO.code() != null) {
                        existingMachine.setCode(TypemachineDTO.code());
                    }

                    TypeMachine updatedMachine = TypemachineRepository.save(existingMachine);
                    return TypemachineMapper.toDto(updatedMachine);
                });
    }

    @Override
    public Page<TypeMachineDTO> findAll(Pageable pageable) {
        return (Page<TypeMachineDTO>) TypemachineRepository.findAll(pageable).map(TypemachineMapper::toDto);
    }

    @Override
    public Optional<TypeMachineDTO> findOne(UUID id) {
        return TypemachineRepository.findById(id).map(TypemachineMapper::toDto);
    }

    @Override
    public void delete(UUID TypemachineId) {
        TypemachineRepository.deleteById(TypemachineId);
    }
}
