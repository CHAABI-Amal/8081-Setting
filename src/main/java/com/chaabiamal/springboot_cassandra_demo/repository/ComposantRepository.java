package com.chaabiamal.springboot_cassandra_demo.repository;

import com.chaabiamal.springboot_cassandra_demo.model.Composant;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ComposantRepository extends CassandraRepository<Composant, UUID> {
    @Query("SELECT * FROM mykeyspace1.composant WHERE id = ?0 ALLOW FILTERING")
    Optional<Composant> findComposantById(UUID id);;

    @Query("SELECT * FROM mykeyspace1.composant WHERE id = ?0 AND lastStatus = ?1 ALLOW FILTERING")
    Optional<Composant> findComposantByIdAndLastStatusNotLike(UUID id, String status);
    void deleteById(UUID id);

    @Query("DELETE FROM mykeyspace1.composant WHERE id = ?0")
    void deleteByid(UUID id);

    @Query("SELECT value FROM mykeyspace1.status WHERE id = ?0 ALLOW FILTERING")
    String findLastStatusByStatusID(int id);

    @Query("SELECT lastStatus FROM mykeyspace1.composant WHERE id = ?0 ALLOW FILTERING")
    String findLastStatusBycomposantID(UUID id);

    @Query("SELECT value FROM mykeyspace1.composant WHERE id = ?0 ALLOW FILTERING")
    String findValueBycomposantID(UUID id);
    @Query("SELECT * FROM mykeyspace1.composant WHERE machineId = ?0 ALLOW FILTERING")
    List<Composant> findByidMachine(UUID machineId);
}
