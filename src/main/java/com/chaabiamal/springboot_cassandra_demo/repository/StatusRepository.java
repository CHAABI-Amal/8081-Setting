package com.chaabiamal.springboot_cassandra_demo.repository;

import com.chaabiamal.springboot_cassandra_demo.model.Status;
import com.chaabiamal.springboot_cassandra_demo.model.TypeStatus;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.UUID;

public interface StatusRepository extends CassandraRepository<Status, Integer> {

    @Query("SELECT * FROM mykeyspace.Status WHERE Value = ?0") // Specify keyspace and table name
    Status findByValue(TypeStatus statu);
}
