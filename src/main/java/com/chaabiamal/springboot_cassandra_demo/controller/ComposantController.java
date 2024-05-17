package com.chaabiamal.springboot_cassandra_demo.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.chaabiamal.springboot_cassandra_demo.Exception.ResourceNotFoundException;
import com.chaabiamal.springboot_cassandra_demo.model.Composant;

import com.chaabiamal.springboot_cassandra_demo.repository.ComposantRepository;

import com.chaabiamal.springboot_cassandra_demo.service.ComposantService;
import com.chaabiamal.springboot_cassandra_demo.service.dto.ComposantDTO;

import com.chaabiamal.springboot_cassandra_demo.service.mapper.ComposantMapper;

import io.swagger.v3.core.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.springframework.boot.web.servlet.filter.ApplicationContextHeaderFilter.HEADER_NAME;

@RestController

@RequestMapping("/amal/composants")
public class ComposantController {
    @Autowired
    private final ComposantService composantService;
    @Autowired
    private ComposantRepository composantRepository;

    @Autowired
    private ComposantMapper composantMapper;
    @Autowired
    public ComposantController(ComposantService composantService) {
        this.composantService = composantService;
    }
//*********************************************
    @GetMapping("/paged")
    public ResponseEntity<Page<ComposantDTO>> getComposantsPaged(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<ComposantDTO> composantsPage = composantService.findAll(pageable);
        return ResponseEntity.ok(composantsPage);
    }
    @GetMapping("")
    public List<ComposantDTO> getComposant() {
        List<Composant> composants = composantRepository.findAll();

        if (composants == null) {
            return Collections.emptyList(); // Retourne une liste vide
        }

        return composants.stream()
                .map(composantMapper::toDto) // Utilisation de la référence de méthode
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<ComposantDTO> findById(@PathVariable("id") UUID composantId) {
        Composant composant = composantRepository.findComposantById(composantId).orElseThrow(
                () -> new ResourceNotFoundException("Composant not found with ID: " + composantId));

        ComposantDTO composantDTO = composantMapper.toDto(composant); // Utilisation de composantMapper
        return ResponseEntity.ok().body(composantDTO);
    }



    @PostMapping("withId")
    public ResponseEntity<ComposantDTO> addComposantID(@Valid @RequestBody ComposantDTO composantDTO) throws URISyntaxException {
        Composant composant = composantMapper.toEntity(composantDTO); // Utilisation de composantMapper
        Composant savedComposant = composantRepository.save(composant); // Utilisation de composantRepository
        ComposantDTO savedComposantDTO = composantMapper.toDto(savedComposant); // Utilisation de composantMapper

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HEADER_NAME, "A new composant is created with identifier " + savedComposantDTO.id()) // Utilisation de getId
                .body(savedComposantDTO); // Utilisation de savedComposantDTO
    }





   @PostMapping("")
   public ResponseEntity<ComposantDTO> addComposant(@Valid @RequestBody ComposantDTO composantDTO) throws URISyntaxException {
       Composant composant = composantMapper.toEntity(composantDTO); // Utilisation de composantMapper
       composant.setId(UUID.randomUUID()); // Correction de setKioskId en setId
       Composant savedComposant = composantRepository.save(composant); // Utilisation de composantRepository
       ComposantDTO savedComposantDTO = composantMapper.toDto(savedComposant); // Utilisation de composantMapper

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .header(HEADER_NAME, "A new composant is created with identifier " + savedComposantDTO.id()) // Utilisation de getId
               .body(savedComposantDTO); // Utilisation de savedComposantDTO
   }
    @PatchMapping("{id}")
    public ResponseEntity<ComposantDTO> partialUpdateComposant(@PathVariable("id") UUID composantId, @RequestBody ComposantDTO composantDTO) {
        Optional<ComposantDTO> updatedComposantOptional = composantService.partialUpdate(composantId, composantDTO);
        if (updatedComposantOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ComposantDTO updatedComposantDTO = updatedComposantOptional.get();
        return ResponseEntity.ok().body(updatedComposantDTO);
    }





    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteComposant(@PathVariable("id") UUID composantId) {
        composantService.delete(composantId);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ComposantDTO> updateComposant(@PathVariable("id") UUID composantId,
                                                        @Valid @RequestBody ComposantDTO composantDTO) {
        if (!composantService.findOne(composantId).isPresent()) {
            throw new ResourceNotFoundException("Composant not found with id: " + composantId);
        }
        composantDTO.id(); // Mise à jour de l'ID dans le DTO
        ComposantDTO updatedComposantDTO = composantService.update(composantDTO);
        return ResponseEntity.ok(updatedComposantDTO);
    }


}
