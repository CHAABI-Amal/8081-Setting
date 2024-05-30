package com.chaabiamal.springboot_cassandra_demo.service.dto;

import java.util.UUID;

public record ModuleDTO(
        UUID id,
        String name,
        String description
) {
}