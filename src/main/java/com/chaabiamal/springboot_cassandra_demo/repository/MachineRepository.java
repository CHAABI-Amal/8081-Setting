package com.chaabiamal.springboot_cassandra_demo.repository;

import com.chaabiamal.springboot_cassandra_demo.model.Machine;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface MachineRepository extends CassandraRepository<Machine, UUID> {

    @Query("SELECT * FROM mykeyspace1.Machine WHERE machineid = ?0") // Specify keyspace and table name
    Optional<Machine> findByid(UUID id);
    @Query("DELETE FROM mykeyspace1.Machine WHERE machineid = ?0")
    void deleteByid(UUID id);
}
