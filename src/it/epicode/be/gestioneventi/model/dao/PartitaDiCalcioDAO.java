package it.epicode.be.gestioneventi.model.dao;

import it.epicode.be.gestioneventi.model.Evento;
import it.epicode.be.gestioneventi.model.PartitaDiCalcio;
import it.epicode.be.gestioneventi.util.JpaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PartitaDiCalcioDAO {

    private static final Logger logger = LoggerFactory.getLogger(EventoDAO.class);

    public void save( PartitaDiCalcio object) {
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

    public PartitaDiCalcio getById(Long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {

            return em.find(PartitaDiCalcio.class, id);

        } finally {
            em.close();
        }

    }
}
