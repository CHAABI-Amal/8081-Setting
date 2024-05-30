package com.chaabiamal.springboot_cassandra_demo.controller;

import com.chaabiamal.springboot_cassandra_demo.Exception.ResourceNotFoundException;
import com.chaabiamal.springboot_cassandra_demo.model.Machine;
import com.chaabiamal.springboot_cassandra_demo.model.TypeMachine;
import com.chaabiamal.springboot_cassandra_demo.repository.MachineRepository;
import com.chaabiamal.springboot_cassandra_demo.repository.TypeMachineRepository;
import com.chaabiamal.springboot_cassandra_demo.service.TypeMachineService;
import com.chaabiamal.springboot_cassandra_demo.service.dto.ComposantDTO;
import com.chaabiamal.springboot_cassandra_demo.service.dto.TypeMachineDTO;
import com.chaabiamal.springboot_cassandra_demo.service.mapper.TypeMachineMapper;
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
    @CrossOrigin("*")
    @RequestMapping("/amal/typemachines")
    public class TypeMachineController {

        @Autowired
        private TypeMachineService TypemachineService;

        @Autowired
        private TypeMachineRepository TypemachineRepository;

        @Autowired
        private TypeMachineMapper TypemachineMapper;

        @PostMapping("")
        public ResponseEntity<TypeMachineDTO> addTypemachine(@Valid @RequestBody TypeMachineDTO TypemachineDTO) throws URISyntaxException {
            TypeMachine Typemachine = TypemachineMapper.toEntity(TypemachineDTO);
            Typemachine.setMachinetypeId(UUID.randomUUID());
            TypeMachine savedMachine = TypemachineRepository.save(Typemachine);
            TypeMachineDTO savedMachineDTO = TypemachineMapper.toDto(savedMachine);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header(HEADER_NAME, "A new Type machine is created with identifier " + savedMachineDTO.machinetypeId())
                    .body(savedMachineDTO);
        }

        @PatchMapping("{id}")
        public ResponseEntity<TypeMachineDTO> partialUpdateTypeMachine(@PathVariable("id") UUID typeMachineId, @RequestBody TypeMachineDTO typeMachineDTO) {
            Optional<TypeMachineDTO> updatedTypeMachineOptional = TypemachineService.partialUpdate(typeMachineId, typeMachineDTO);
            if (updatedTypeMachineOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            TypeMachineDTO updatedTypeMachineDTO = updatedTypeMachineOptional.get();
            return ResponseEntity.ok().body(updatedTypeMachineDTO);
        }


        /**/
        @GetMapping("{id}")
        public ResponseEntity<TypeMachineDTO> findById(@PathVariable("id") UUID Id) {
            TypeMachine Typemachine = TypemachineRepository.findById(Id).orElseThrow(
                    () -> new ResourceNotFoundException("Type Machine not found" + Id));
            TypeMachineDTO TypemachineDTO = TypemachineMapper.toDto(Typemachine);
            return ResponseEntity.ok().body(TypemachineDTO);
        }

        @GetMapping("")
        public List<TypeMachineDTO> getTypeMachines() {
            List<TypeMachine> Typemachines = TypemachineRepository.findAll();
            return Typemachines.stream()
                    .map(TypemachineMapper::toDto)
                    .collect(Collectors.toList());
        }

        @DeleteMapping("{id}")
        public ResponseEntity<Void> deleteTypeMachine(@PathVariable(value = "id") UUID kioskId) {
            TypemachineRepository.deletebyId(kioskId);
            return ResponseEntity.ok().build();
        }

    }

