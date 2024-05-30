package com.chaabiamal.springboot_cassandra_demo.service.mapper;
import com.chaabiamal.springboot_cassandra_demo.model.Modules;
import com.chaabiamal.springboot_cassandra_demo.service.dto.ModuleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ModuleMapper {
    @Mapping(target = "id", ignore = true)
    Modules toEntity(ModuleDTO dto);
    ModuleDTO toDto(Modules entity);
}