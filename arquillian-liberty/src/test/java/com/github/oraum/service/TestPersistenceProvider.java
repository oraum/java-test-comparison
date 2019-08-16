package com.github.oraum.service;

import com.github.oraum.persistence.entity.User;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.SQLException;

@ApplicationScoped
public class TestPersistenceProvider {

    @PersistenceContext(unitName = "defaultPU")
    EntityManager entityManager;
    @Resource(name = "jndi/defaultPU")
    DataSource dataSource;
    private IDatabaseConnection iDatabaseConnection;

    @PostConstruct
    public void setup() {
        try {
            entityManager.find(User.class, 0L);
            iDatabaseConnection = new DatabaseDataSourceConnection(dataSource, null);
        } catch (SQLException e) {
            entityManager.getTransaction().rollback();
            throw new IllegalStateException(e);
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
