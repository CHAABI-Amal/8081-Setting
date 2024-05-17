package com.chaabiamal.springboot_cassandra_demo.service.mapper;

import com.chaabiamal.springboot_cassandra_demo.model.Machine;
import com.chaabiamal.springboot_cassandra_demo.model.TypeMachine;
import com.chaabiamal.springboot_cassandra_demo.service.dto.MachineDTO;
import com.chaabiamal.springboot_cassandra_demo.service.dto.TypeMachineDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TypeMachineMapper {
    @Mapping(target = "machinetypeId", ignore = true)
    TypeMachine toEntity(TypeMachineDTO dto);

    TypeMachineDTO toDto(TypeMachine entity);
}