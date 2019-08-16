package com.github.oraum.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class PersistenceContextProducer {

    @PersistenceContext(unitName = "defaultPU")
    EntityManager entityManager;

    @Produces
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
