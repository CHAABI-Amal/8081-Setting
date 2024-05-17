package com.chaabiamal.springboot_cassandra_demo.service.dto;

import java.util.UUID;


public record MachineDTO(
        UUID machineId,
        UUID typeId,
        String name,
        String code,
        boolean isonline,
        String description,
        String ipAddress,
        String status
) {}