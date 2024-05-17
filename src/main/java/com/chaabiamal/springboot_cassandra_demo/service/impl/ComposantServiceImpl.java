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
    @Autowired
    private  ComposantRepository composantRepository;
    @Autowired
    private ComposantMapper composantMapper;
    @Autowired
    public ComposantServiceImpl(ComposantRepository composantRepository) {
        this.composantRepository = composantRepository;
    }



   //****************************
   @Override
   public Optional<ComposantDTO> findById(UUID id) {
       return composantRepository.findComposantById(id).map(composantMapper::toDto);
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

    public Optional<ComposantDTO> partialUpdate(UUID id, ComposantDTO composantDTO) {
        return composantRepository.findComposantById(id)
                .map(existingComposant -> {
                    if (composantDTO.id() != null) {
                        existingComposant.setId(composantDTO.id());
                    }

                    if (composantDTO.lastStatus() != null) {
                        existingComposant.setLastStatus(composantDTO.lastStatus());
                    }
                    if (composantDTO.isdeleted()) {
                        existingComposant.setIsdeleted(composantDTO.isdeleted());
                    }
                    if (0<composantDTO.statusId()&& composantDTO.statusId()<5) {
                        existingComposant.setStatusId(composantDTO.statusId());
                    }
                    if (composantDTO.name() != null) {
                        existingComposant.setName(composantDTO.name());
                    }
                    if (composantDTO.value() != null) {
                        existingComposant.setValue(composantDTO.value());
                    }
                    if (composantDTO.componentTypeId()>0) {
                        existingComposant.setComponentTypeId(composantDTO.componentTypeId());
                    }
                    if (composantDTO.code() != null) {
                        existingComposant.setCode(composantDTO.code());
                    }
                    if (composantDTO.machineId() != null) {
                        existingComposant.setMachineId(composantDTO.machineId());
                    }
                    if (composantDTO.model() != null) {
                        existingComposant.setModel(composantDTO.model());
                    }

                    if (composantDTO.lastStatusChangeTime() != null) {
                        existingComposant.setLastStatusChangeTime(composantDTO.lastStatusChangeTime());
                    }
                    if (composantDTO.composantCreatedDate() != null) {
                        existingComposant.setComposantCreatedDate(composantDTO.composantCreatedDate());
                    }
                    if (composantDTO.composantModifiedDate()!= null) {
                        existingComposant.setComposantModifiedDate(composantDTO.composantModifiedDate());
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
    public void delete(UUID composantId) {
        composantRepository.deleteByid(composantId);
    }
}
