package com.chaabiamal.springboot_cassandra_demo.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table(value = "typemachine") // Nom de la table
public class TypeMachine {
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.UUID)
    @Column("machinetypeid")
    private UUID machinetypeId;
    @Column("name")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String name;
    @Column("code")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String code;

//*********************

    public TypeMachine() {
    }

    public TypeMachine(UUID machinetypeId, String name, String code) {
        this.machinetypeId = machinetypeId;
        this.name = name;
        this.code = code;
    }

    public UUID getMachinetypeId() {
        return machinetypeId;
    }

    public void setMachinetypeId(UUID machinetypeId) {
        this.machinetypeId = machinetypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}