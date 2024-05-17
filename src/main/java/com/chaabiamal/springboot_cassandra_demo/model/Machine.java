package com.chaabiamal.springboot_cassandra_demo.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.UUID;

@Table(value = "machine") // Nom de la table
public class Machine {
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.UUID)
    @Column("machineid")
    private UUID machineId;
    @Column("typeid")
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID typeId;
    @Column("name")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String name;
    @Column("code")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String code;
    @Column("isonline")
    @CassandraType(type = CassandraType.Name.BOOLEAN)
    private boolean isonline;
    @Column("description")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String description;
    @Column("ipaddress")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String ipAddress;
    @Column("status")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String status;
//*********************888
    public Machine() {
    }
    public Machine(UUID machineId, UUID typeId, String name, String code, boolean isonline, String description, String ipAddress, String status) {
        this.machineId = machineId;
        this.typeId = typeId;
        this.name = name;
        this.code = code;
        this.isonline = isonline;
        this.description = description;
        this.ipAddress = ipAddress;
        this.status = status;
    }
    public boolean isIsonline() {
        return isonline;
    }
    public void setIsonline(boolean isonline) {
        this.isonline = isonline;
    }
    public UUID getMachineId() {
        return machineId;
    }
    public void setMachineId(UUID machineId) {
        this.machineId = machineId;
    }

    public UUID getTypeId() {
        return typeId;
    }

    public void setTypeId(UUID typeId) {
        this.typeId = typeId;
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


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
