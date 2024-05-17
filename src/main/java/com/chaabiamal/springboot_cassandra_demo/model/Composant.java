package com.chaabiamal.springboot_cassandra_demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id",
		scope = Composant.class)
@Table(value = "composant")
public class Composant implements Serializable {
	@PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
	@CassandraType(type = CassandraType.Name.UUID)
	@Column(value = "id")
	private UUID id;
	@CassandraType(type = CassandraType.Name.TEXT)
	@Column(value = "name")
	private String name;
	@CassandraType(type = CassandraType.Name.INT)
	@Column(value = "statusid")
	private int statusId;

	@CassandraType(type = CassandraType.Name.TEXT)
	@Column(value = "value")
	private String value;// 80% , 50% ............

	@CassandraType(type = CassandraType.Name.TIMESTAMP)
	@Column(value = "laststatuschangetime")
	private LocalDateTime lastStatusChangeTime;// date de status actuelle

	@CassandraType(type = CassandraType.Name.TEXT)
	@Column(value = "laststatus")
	private String lastStatus;// status actuelle

	@CassandraType(type = CassandraType.Name.TEXT)
	@Column(value = "code")
	private String code;

	@CassandraType(type = CassandraType.Name.UUID)
	@Column(value = "machineid")
	private UUID machineId;


	@CassandraType(type = CassandraType.Name.INT)
	@Column(value = "componenttypeid")
	private int componentTypeId;

	@CassandraType(type = CassandraType.Name.TEXT)
	@Column(value = "model")
	private String model;

	@CassandraType(type = CassandraType.Name.BOOLEAN)
	@Column(value = "isdeleted")
	private boolean isdeleted;

	@CassandraType(type = CassandraType.Name.TIMESTAMP)
	@Column(value = "composantcreateddate")
	private LocalDateTime composantCreatedDate;

	@CassandraType(type = CassandraType.Name.TIMESTAMP)
	@Column(value = "composantmodifieddate")
	private LocalDateTime composantModifiedDate;

	//****************************************
	// Constructeur
	public Composant() {
	}

	public Composant(UUID id, String name, int statusId, String value, LocalDateTime lastStatusChangeTime, String lastStatus, String code, UUID machineId, int componentTypeId, String model, boolean isdeleted, LocalDateTime composantCreatedDate, LocalDateTime composantModifiedDate) {
		this.id = id;
		this.name = name;
		this.statusId = statusId;
		this.value = value;
		this.lastStatusChangeTime = lastStatusChangeTime;
		this.lastStatus = lastStatus;
		this.code = code;
		this.machineId = machineId;
		this.componentTypeId = componentTypeId;
		this.model = model;
		this.isdeleted = isdeleted;
		this.composantCreatedDate = composantCreatedDate;
		this.composantModifiedDate = composantModifiedDate;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public LocalDateTime getLastStatusChangeTime() {
		return lastStatusChangeTime;
	}

	public void setLastStatusChangeTime(LocalDateTime lastStatusChangeTime) {
		this.lastStatusChangeTime = lastStatusChangeTime;
	}

	public String getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(String lastStatus) {
		this.lastStatus = lastStatus;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public UUID getMachineId() {
		return machineId;
	}

	public void setMachineId(UUID machineId) {
		this.machineId = machineId;
	}

	public int getComponentTypeId() {
		return componentTypeId;
	}

	public void setComponentTypeId(int componentTypeId) {
		this.componentTypeId = componentTypeId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}


	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public LocalDateTime getComposantCreatedDate() {
		return composantCreatedDate;
	}

	public void setComposantCreatedDate(LocalDateTime composantCreatedDate) {
		this.composantCreatedDate = composantCreatedDate;
	}

	public LocalDateTime getComposantModifiedDate() {
		return composantModifiedDate;
	}

	public void setComposantModifiedDate(LocalDateTime composantModifiedDate) {
		this.composantModifiedDate = composantModifiedDate;
	}
}
