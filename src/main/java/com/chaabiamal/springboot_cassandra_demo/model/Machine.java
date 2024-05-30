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

    @Column("code")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String code;

    @Column("typeid")
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID typeId;
    @Column("modelid")
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID moduleId;
    @Column("name")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String name;

    @Column("type")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String type;


    @Column("isonline")
    @CassandraType(type = CassandraType.Name.BOOLEAN)
    private boolean isonline;
    @Column("description")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String description;
    @Column("ipaddress")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String ipAddress;

    @CassandraType(type = CassandraType.Name.TEXT)
    private String lastStatus;

    @CassandraType(type = CassandraType.Name.TEXT)
    private String currentStatus;

    //*********************888
    public Machine() {
    }

    public Machine(UUID machineId, String code, UUID typeId, UUID modelId, String name, String type, boolean isonline, String description, String ipAddress, String lastStatus, String currentStatus) {
        this.machineId = machineId;
        this.code = code;
        this.typeId = typeId;
        this.moduleId = modelId;
        this.name = name;
        this.type = type;
        this.isonline = isonline;
        this.description = description;
        this.ipAddress = ipAddress;
        this.lastStatus = lastStatus;
        this.currentStatus = currentStatus;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(String lastStatus) {
        this.lastStatus = lastStatus;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public UUID getModuleId() {
        return moduleId;
    }

    public void setModuleId(UUID moduleId) {
        this.moduleId = moduleId;
    }
}
