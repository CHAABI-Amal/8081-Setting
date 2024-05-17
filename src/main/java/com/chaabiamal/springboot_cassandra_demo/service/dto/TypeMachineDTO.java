package com.chaabiamal.springboot_cassandra_demo.service.dto;

import java.util.UUID;

public record TypeMachineDTO(
        UUID machinetypeId,
        String name,
        String code

) {}
