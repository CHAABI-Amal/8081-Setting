package com.chaabiamal.springboot_cassandra_demo.repository;

import com.chaabiamal.springboot_cassandra_demo.model.Status;
import com.chaabiamal.springboot_cassandra_demo.enums.TypeStatus;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends CassandraRepository<Status, Integer> {

    @Query("SELECT * FROM mykeyspace1.Status WHERE Value = ?0") // Specify keyspace and table name
    Status findByValue(TypeStatus statu);
}
