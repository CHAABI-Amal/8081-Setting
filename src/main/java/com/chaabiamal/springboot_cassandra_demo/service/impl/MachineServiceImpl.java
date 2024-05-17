package com.chaabiamal.springboot_cassandra_demo.service.impl;

import com.chaabiamal.springboot_cassandra_demo.model.Machine;
import com.chaabiamal.springboot_cassandra_demo.repository.MachineRepository;
import com.chaabiamal.springboot_cassandra_demo.service.MachineService;
import com.chaabiamal.springboot_cassandra_demo.service.dto.MachineDTO;
import com.chaabiamal.springboot_cassandra_demo.service.mapper.MachineMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class MachineServiceImpl implements MachineService {

    private final MachineRepository machineRepository;
    private final MachineMapper machineMapper;

    public MachineServiceImpl(MachineRepository machineRepository, MachineMapper machineMapper) {
        this.machineRepository = machineRepository;
        this.machineMapper = machineMapper;
    }

    @Override
    public Optional<MachineDTO> findById(UUID id) {
        return machineRepository.findById(id).map(machineMapper::toDto);
    }

    @Override
    public MachineDTO save(MachineDTO machineDTO) {
        Machine machine = machineMapper.toEntity(machineDTO);
        machine.setMachineId(UUID.randomUUID()); // Generating UUID
        machine = machineRepository.save(machine);
        return machineMapper.toDto(machine);
    }

    @Override
    public MachineDTO update(MachineDTO machineDTO) {
        Machine machine = machineMapper.toEntity(machineDTO);
        machine = machineRepository.save(machine);
        return machineMapper.toDto(machine);
    }


    @Override
    public Optional<MachineDTO> partialUpdate(UUID id, MachineDTO machineDTO) {
        return machineRepository.findById(id)
                .map(existingMachine -> {
                    // Map non-null fields from machineDTO to existingMachine
                    if (machineDTO.machineId() != null) {
                        existingMachine.setMachineId(machineDTO.machineId());
                    }
                    if (machineDTO.typeId() != null) {
                        existingMachine.setTypeId(machineDTO.typeId());
                    }
                    if (machineDTO.name() != null) {
                        existingMachine.setName(machineDTO.name());
                    }
                    if (machineDTO.code() != null) {
                        existingMachine.setCode(machineDTO.code());
                    }
                    if (machineDTO.isonline()|| !(machineDTO.isonline())) {
                        existingMachine.setIsonline(machineDTO.isonline());
                    }
                    if (machineDTO.description() != null) {
                        existingMachine.setDescription(machineDTO.description());
                    }
                    if (machineDTO.ipAddress() != null) {
                        existingMachine.setIpAddress(machineDTO.ipAddress());
                    }
                    if (machineDTO.status() != null) {
                        existingMachine.setStatus(machineDTO.status());
                    }

                    Machine updatedMachine = machineRepository.save(existingMachine);
                    return machineMapper.toDto(updatedMachine);
                });
    }

    @Override
    public Page<MachineDTO> findAll(Pageable pageable) {
        return (Page<MachineDTO>) machineRepository.findAll(pageable).map(machineMapper::toDto);
    }

    @Override
    public Optional<MachineDTO> findOne(UUID id) {
        return machineRepository.findById(id).map(machineMapper::toDto);
    }

    @Override
    public void delete(UUID machineId) {
        machineRepository.deleteById(machineId);
    }
}
