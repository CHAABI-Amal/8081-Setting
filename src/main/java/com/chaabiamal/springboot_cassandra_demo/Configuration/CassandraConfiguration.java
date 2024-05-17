package com.chaabiamal.springboot_cassandra_demo.Configuration;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = {"com.chaabiamal.springboot_cassandra_demo.repository"})
public class CassandraConfiguration extends AbstractCassandraConfiguration {
//********************************************************************************************
@Value("${spring.data.cassandra.keyspace-name:mykeyspace}")
private String keyspaceName;

    @Value("${spring.data.cassandra.contact-points:localhost}")
    private String contactPoints;

    @Value("${spring.data.cassandra.port:9042}")
    private int port;

    @Bean
    public CqlSessionFactoryBean cassandraSession() {
        CqlSessionFactoryBean factory = new CqlSessionFactoryBean();
        factory.setKeyspaceName(getKeyspaceName());
        return factory;
    }

    @Bean
    public CassandraConverter cassandraConverter() {
        return new MappingCassandraConverter(new BasicCassandraMappingContext());
    }


    @Override
    public String getContactPoints() {
        return contactPoints;
    }

    @Override
    protected int getPort() {
        return port;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"com.chaabiamal.springboot_cassandra_demo.model"};
    }

    @Bean
    @Primary
    public CqlSession session() {
        return CqlSession.builder()
                .withKeyspace(getKeyspaceName())
                .withLocalDatacenter("datacenter1") // Définir le centre de données local
                .build();
    }
    @Bean
    public CassandraTemplate mycassandraTemplate() {
        return new CassandraTemplate(session());
    }
    /*
    @Value("${env.values.cassandra.keyspace.name}")
    private String keyspaceName;

    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }


    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }
    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return List.of(
                CreateKeyspaceSpecification.createKeyspace(keyspaceName)
                        .ifNotExists()
                        .withSimpleReplication(3));
    }

    @Bean
    @Override
    public CqlSessionFactoryBean cassandraSession() {
        CqlSessionFactoryBean factory = new CqlSessionFactoryBean();
        factory.setKeyspaceName(keyspaceName);
        return factory;
    }
    */

}
/*/*
@Configuration
@EnableCassandraRepositories(basePackages = {"com.chaabiamal.springboot_cassandra_demo.repository"})
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Override
    protected String getKeyspaceName() {
        return "mykeyspace";
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Bean
    @Primary
    public CqlSession session() {
        return CqlSession.builder().withKeyspace(getKeyspaceName()).build();
    }

    @Bean
    public CassandraTemplate mycassandraTemplate() {
        return new CassandraTemplate(session());
    }
}
/*
@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    public static final String KEYSPACE = "guru_keyspace";

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(KEYSPACE);

        return Arrays.asList(specification);
    }

    @Override
    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
        return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(KEYSPACE));
    }

    @Override
    protected String getKeyspaceName() {
        return KEYSPACE;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"guru.springframework.domain"};
    }
}
 */