package com.chaabiamal.springboot_cassandra_demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "ID",
        scope = Modules.class)
@Table(value = "modules")

public class Modules implements Serializable {
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    @Column("id")
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID id;

    @Column("name")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String name;

    @Column("description")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String description;

// Getters and Setters

    public Modules(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    //*************************************************************

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}