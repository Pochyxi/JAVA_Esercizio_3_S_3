package it.epicode.be.gestioneventi.model.dao;

import ch.qos.logback.classic.Logger;
import it.epicode.be.gestioneventi.model.GaraDiAtletica;
import it.epicode.be.gestioneventi.util.JpaUtil;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class GaraDiAtleticaDAO {

    private static final Logger logger = ( Logger ) LoggerFactory.getLogger(GaraDiAtleticaDAO.class);

    public static void save( GaraDiAtletica object) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            em.persist(object);

            transaction.commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();

            logger.error("Error saving object: " + object.getClass().getSimpleName(), ex);
            throw ex;

        } finally {
            em.close();
        }

    }

    public static void refresh(GaraDiAtletica object) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {

            em.refresh(object);

        } finally {
            em.close();
        }

    }

    public static GaraDiAtletica getById(Long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {

            return em.find(GaraDiAtletica.class, id);

        } finally {
            em.close();
        }

    }

    public static void delete(GaraDiAtletica object) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            em.remove(object);

            transaction.commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            logger.error("Error deleting object: " + object.getClass().getSimpleName(), ex);
            throw ex;

        } finally {
            em.close();
        }

    }

}
