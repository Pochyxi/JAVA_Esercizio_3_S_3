package it.epicode.be.gestioneventi;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import it.epicode.be.gestioneventi.model.*;
import it.epicode.be.gestioneventi.model.dao.*;
import it.epicode.be.gestioneventi.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class GestioneEventi {

    public static void main( String[] args ) {

//		Location location = saveLocation();
//		Evento evento = saveEvento("Partita di champions", "giocate per divertirvi", 200, TipoEvento.PRIVATO, location);
//		Persona persona = savePersona("Ciccio", "Pasticcio", "pasticciodiciccio@nzevat.com", Sesso.MASCHIO);
//
//		Partecipazione partecipazione = savePartecipazione(evento,persona);

//        Evento evento = new EventoDAO().getById( 1L );
//        System.out.println( evento );

//        savePartitaDiCalcio(
//                "Francia",
//                "Stati Uniti",
//                6,
//                1,
//                new EventoDAO().getById( 19L )
//        );

//        saveEventi();

        getConcertiInStreaming(false);

        getConcertiPerGenere(Genere.CLASSICO);


    }


    private static Partecipazione savePartecipazione( Evento evento, Persona persona ) {
        Partecipazione part = new Partecipazione();
        part.setEvento( evento );
        part.setPersona( persona );
        part.setStato( StatoPartecipazione.CONFERMATA );

        PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO();
        partecipazioneDAO.save( part );
        return part;
    }

    private static Persona savePersona( String nome, String cognome, String email, Sesso sesso ) {
        Persona per = new Persona();
        per.setNome( nome );
        per.setCognome( cognome );
        per.setEmail( email );
        per.setSesso( sesso );
        per.setDataDiNascita( new GregorianCalendar( 1980, 9, 23 ).getTime() );
        PersonaDAO personaDAO = new PersonaDAO();
        personaDAO.save( per );
        return per;
    }

    private static Location saveLocation() {
        Location loc = new Location();
        loc.setCitta( "Firenze" );
        loc.setNome( "Stadio Franchi" );

        LocationDAO locationDAO = new LocationDAO();
        locationDAO.save( loc );
        return loc;
    }

    private static Evento saveEvento( String titolo, String descrizione, int numeroPartecipanti,
                                      TipoEvento tipoEvento, Location loc ) {
        Evento ev = new Evento();
        ev.setDataEvento( new Date() );
        ev.setTitolo( titolo );
        ev.setDescrizione( descrizione );
        ev.setNumeroMassimoPartecipanti( numeroPartecipanti );
        ev.setTipoEvento( tipoEvento );
        ev.setLocation( loc );

        EventoDAO evDao = new EventoDAO();
        evDao.save( ev );
        return ev;
    }

    private static PartitaDiCalcio savePartitaDiCalcio( String squadraDiCasa, String squadraOspite,
                                               int numeroGolSquadraDiCasa, int numeroGolSquadraOspite, Evento evento ) {
        PartitaDiCalcio ev = new PartitaDiCalcio( squadraDiCasa, squadraOspite, numeroGolSquadraDiCasa, numeroGolSquadraOspite, evento );

        PartitaDiCalcioDAO evDao = new PartitaDiCalcioDAO();
        evDao.save( ev );
        return ev;
    }

    public static void saveEventi() {

        Concerto con1 = new Concerto(true, Genere.CLASSICO);

        Concerto con2 = new Concerto(false, Genere.POP);

        ConcertoDAO.save(con1);

        ConcertoDAO.save(con2);

    }

    public static void getConcertiInStreaming(boolean n){
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        Query q = em.createQuery(
                "SELECT c FROM Concerto c Where c.inStreaming = :n "
        );

        q.setParameter("n", n);

        List<Concerto> r = q.getResultList();
        for (Concerto p : r) {
            System.out.println(p);
        }
        em.close();
    }

    public static void getConcertiPerGenere(Genere n){
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        Query q = em.createQuery(
                "SELECT c FROM Concerto c Where c.genere = :n "
        );

        q.setParameter("n", n);

        List<Concerto> r = q.getResultList();
        for (Concerto p : r) {
            System.out.println(p);
        }
        em.close();
    }




}
