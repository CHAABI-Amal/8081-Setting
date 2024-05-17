package com.chaabiamal.springboot_cassandra_demo.service.mapper;

import com.chaabiamal.springboot_cassandra_demo.model.Machine;
import com.chaabiamal.springboot_cassandra_demo.service.dto.MachineDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MachineMapper {
    @Mapping(target = "machineId", ignore = true)
    Machine toEntity(MachineDTO dto);

    MachineDTO toDto(Machine entity);
}