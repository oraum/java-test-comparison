package com.github.oraum.service;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.h2.H2Connection;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static org.eclipse.persistence.config.EntityManagerProperties.*;
import static org.eclipse.persistence.config.PersistenceUnitProperties.DDL_GENERATION;
import static org.eclipse.persistence.config.PersistenceUnitProperties.DROP_AND_CREATE;

@ApplicationScoped
public class TestPersistenceProvider {

    // share across tests
    private static EntityManager entityManager;
    private static IDatabaseConnection iDatabaseConnection;

    @PostConstruct
    public void setup() {
        Map<String, String> properties = new HashMap<>();
        properties.put(JDBC_DRIVER, "org.h2.Driver");
        properties.put(JDBC_URL, "jdbc:h2:mem:test");
        properties.put(JDBC_USER, "sa");
        properties.put(JDBC_PASSWORD, "");
        properties.put(DDL_GENERATION, DROP_AND_CREATE);

        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory("defaultPU", properties).createEntityManager();
        }

        if (iDatabaseConnection == null) {
            entityManager.getTransaction().begin();
            try {
                Connection connection = entityManager.unwrap(java.sql.Connection.class);
                iDatabaseConnection = new H2Connection(connection, null);
                entityManager.getTransaction().commit();
            } catch (DatabaseUnitException e) {
                entityManager.getTransaction().rollback();
                throw new IllegalStateException(e);
            }
        }
    }

    @Produces
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Produces
    public IDatabaseConnection getiDatabaseConnection() {
        return iDatabaseConnection;
    }
}
