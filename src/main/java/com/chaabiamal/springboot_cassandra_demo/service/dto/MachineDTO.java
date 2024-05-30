package com.chaabiamal.springboot_cassandra_demo.service.dto;

import java.util.UUID;


public record MachineDTO(
        UUID machineId,
        String name,
        UUID typeId,
        UUID moduleId,
        String type,
        String code,
        boolean isonline,
        String description,
        String ipAddress,
        String lastStatus,
        String currentStatus

) {}