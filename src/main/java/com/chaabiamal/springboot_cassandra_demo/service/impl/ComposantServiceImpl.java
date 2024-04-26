package com.chaabiamal.springboot_cassandra_demo.service.impl;

import com.chaabiamal.springboot_cassandra_demo.model.Composant;
import com.chaabiamal.springboot_cassandra_demo.repository.ComposantRepository;
import com.chaabiamal.springboot_cassandra_demo.service.ComposantService;
import com.chaabiamal.springboot_cassandra_demo.service.dto.ComposantDTO;
import com.chaabiamal.springboot_cassandra_demo.service.mapper.ComposantMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class ComposantServiceImpl implements ComposantService {

    private final ComposantRepository composantRepository;
    private final ComposantMapper composantMapper;

    @Autowired

    public ComposantServiceImpl(ComposantRepository composantRepository, ComposantMapper composantMapper) {
        this.composantRepository = composantRepository;
        this.composantMapper = composantMapper;
    }

    @Override
    public ComposantDTO save(ComposantDTO composantDTO) {

        Composant composant = (Composant) composantMapper.toEntity(composantDTO);
        composant.setId(UUID.randomUUID());
        composant = composantRepository.save(composant);
        return composantMapper.toDto(composant);
    }

    @Override
    public ComposantDTO update(ComposantDTO composantDTO) {

        Composant composant = (Composant) composantMapper.toEntity(composantDTO);
        composant = composantRepository.save(composant);
        return composantMapper.toDto(composant);
    }



    @Override
    public Optional<ComposantDTO> partialUpdate(UUID id, ComposantDTO composantDTO) {
        return composantRepository.findById(id)
                .map(existingComposant -> {
                    if (composantDTO.id() != null) {
                        existingComposant.setId(composantDTO.id());
                        // Ajouter les autres mises à jour partielles ici
                    }
                    if (composantDTO.historiqueId() != null) {
                        existingComposant.setHistoriqueId(composantDTO.historiqueId());
                        // Ajouter les autres mises à jour partielles ici
                    }
                    if (composantDTO.lastStatus() != null) {
                        existingComposant.setLastStatus(composantDTO.lastStatus());
                        // Ajouter les autres mises à jour partielles ici
                    }
                    if (composantDTO.isDeleted() ) {
                        existingComposant.setDeleted(composantDTO.isDeleted());
                        // Ajouter les autres mises à jour partielles ici
                    }
                    if (composantDTO.instanceName()!= null) {
                        existingComposant.setInstanceName(composantDTO.instanceName());
                    }
                    if (composantDTO.additionalInfo()!= null) {
                        existingComposant.setAdditionalInfo(composantDTO.additionalInfo());
                    }
                    if (composantDTO.componentTypeId()!= null) {
                        existingComposant.setComponentTypeId(composantDTO.componentTypeId());
                    }
                    if (composantDTO.instanceCode()!= null) {
                        existingComposant.setInstanceCode(composantDTO.instanceCode());
                    }
                    Composant updatedComposant = composantRepository.save(existingComposant);
                    return composantMapper.toDto(updatedComposant);
                });
    }

    @Override
    public Page<ComposantDTO> findAll(Pageable pageable) {

        return (Page)composantRepository.findAll(pageable).map(composantMapper::toDto);
    }

    @Override
    public Optional<ComposantDTO> findOne(UUID id) {

        return composantRepository.findById(id).map(composantMapper::toDto);
    }

    @Override
    public void delete(UUID id) {

        composantRepository.deleteById(id);
    }
}
