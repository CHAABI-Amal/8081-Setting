package com.chaabiamal.springboot_cassandra_demo.service.dto;


import java.time.LocalDateTime;
import java.util.UUID;


public record ComposantDTO(

        UUID id,
        String name,
        int statusId,
        String value,
        LocalDateTime lastStatusChangeTime,
        String lastStatus,
        String currentStatus,
        String code,
        UUID machineId,
        int componentTypeId,
        String model,
        boolean isdeleted,
        LocalDateTime composantCreatedDate,
        LocalDateTime composantModifiedDate
) {}