package com.chaabiamal.springboot_cassandra_demo.repository;

import com.chaabiamal.springboot_cassandra_demo.model.Composant;
import com.chaabiamal.springboot_cassandra_demo.model.TypeMachine;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TypeMachineRepository extends CassandraRepository<TypeMachine, UUID> {

    @Query("SELECT * FROM mykeyspace1.typemachine WHERE machinetypeid = ?0") // Specify keyspace and table name
    Optional<TypeMachine> findById(UUID id);
    @Query("DELETE FROM mykeyspace1.typemachine WHERE machinetypeid = ?0")
    void deletebyId(UUID id);
}
