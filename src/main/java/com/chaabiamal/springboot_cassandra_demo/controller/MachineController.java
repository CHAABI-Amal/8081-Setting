package com.chaabiamal.springboot_cassandra_demo.controller;

import com.chaabiamal.springboot_cassandra_demo.Exception.ResourceNotFoundException;
import com.chaabiamal.springboot_cassandra_demo.model.Machine;
import com.chaabiamal.springboot_cassandra_demo.repository.MachineRepository;
import com.chaabiamal.springboot_cassandra_demo.service.MachineService;
import com.chaabiamal.springboot_cassandra_demo.service.dto.MachineDTO;
import com.chaabiamal.springboot_cassandra_demo.service.mapper.MachineMapper;
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

import static org.springframework.boot.web.servlet.filter.ApplicationContextHeaderFilter.HEADER_NAME;

@RestController
@RequestMapping("/amal/machines")
@CrossOrigin("*")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private MachineMapper machineMapper;

    @PostMapping("")
    public ResponseEntity<MachineDTO> addmachine(@Valid @RequestBody MachineDTO machineDTO) throws URISyntaxException {
        Machine machine = machineMapper.toEntity(machineDTO);
        machine.setMachineId(UUID.randomUUID());
        Machine savedMachine = machineRepository.save(machine);
        MachineDTO savedMachineDTO = machineMapper.toDto(savedMachine);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HEADER_NAME, "A new machine is created with identifier " + savedMachineDTO.machineId())
                .body(savedMachineDTO);
    }
/**/
    @GetMapping("{id}")
    public ResponseEntity<MachineDTO> findById(@PathVariable("id") UUID kioskId) {
        Machine machine = machineRepository.findByid(kioskId).orElseThrow(
                () -> new ResourceNotFoundException("Machine not found" + kioskId));
        MachineDTO machineDTO = machineMapper.toDto(machine);
        return ResponseEntity.ok().body(machineDTO);
    }

    @GetMapping("")
    public List<MachineDTO> getMachines() {
        List<Machine> machines = machineRepository.findAll();
        return machines.stream()
                .map(machineMapper::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMachine(@PathVariable(value = "id") UUID machineId) {
        machineRepository.deleteByid(machineId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<MachineDTO> partialUpdateMachine(@PathVariable("id") UUID machineId, @Valid @RequestBody MachineDTO machineDTO) {
        Optional<MachineDTO> updatedMachineDTO = machineService.partialUpdate(machineId, machineDTO);
        return updatedMachineDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
