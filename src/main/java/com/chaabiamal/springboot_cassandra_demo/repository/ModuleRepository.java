package com.chaabiamal.springboot_cassandra_demo.repository;

import com.chaabiamal.springboot_cassandra_demo.model.Modules;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ModuleRepository extends CassandraRepository<Modules, UUID> {
    @Query("SELECT * FROM mykeyspace1.modules WHERE id = ?0 ALLOW FILTERING") // Specify keyspace and table name
    Optional<Modules> findByid(UUID id);
    @Query("DELETE FROM mykeyspace1.modules WHERE id = ?0")
    void deleteByid(UUID id);

    @Query("SELECT * FROM mykeyspace1.modules WHERE id = ?0 ALLOW FILTERING") // Specify keyspace and table name
    Modules findById2(UUID id);
}
