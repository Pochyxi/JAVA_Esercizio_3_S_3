package it.epicode.be.gestioneventi.model;

import lombok.*;

import javax.persistence.*;


@ToString
@Entity
public class PartitaDiCalcio extends Evento {
    @Column(name = "squadra_di_casa")
    private String squadraDiCasa;

    @Column(name = "squadra_ospite")
    private String squadraOspite;

    @Column(name = "numero_gold_squadra_di_casa")
    private Integer numeroGoldSquadraDiCasa;

    @Column(name = "numero_gol_squadra_ospite")
    private Integer numeroGolSquadraOspite;

    @OneToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    public PartitaDiCalcio(String squadraDiCasa, String squadraOspite, int numeroGoldSquadraDiCasa,
                           int numeroGolSquadraOspite, Evento evento) {
        this.squadraDiCasa = squadraDiCasa;
        this.squadraOspite = squadraOspite;
        this.numeroGoldSquadraDiCasa = numeroGoldSquadraDiCasa;
        this.numeroGolSquadraOspite = numeroGolSquadraOspite;
        this.evento = evento;
        this.setDataEvento( evento.getDataEvento() );
        this.setDescrizione( evento.getDescrizione() );
        this.setNumeroMassimoPartecipanti( evento.getNumeroMassimoPartecipanti() );
        this.setTipoEvento( evento.getTipoEvento() );
        this.setTitolo( evento.getTitolo() );
        this.setLocation( evento.getLocation() );
    }

    public String getSquadraDiCasa() {
        return squadraDiCasa;
    }

    public void setSquadraDiCasa( String squadraDiCasa ) {
        this.squadraDiCasa = squadraDiCasa;
    }

    public String getSquadraOspite() {
        return squadraOspite;
    }

    public void setSquadraOspite( String squadraOspite ) {
        this.squadraOspite = squadraOspite;
    }

    public Integer getNumeroGoldSquadraDiCasa() {
        return numeroGoldSquadraDiCasa;
    }

    public void setNumeroGoldSquadraDiCasa( Integer numeroGoldSquadraDiCasa ) {
        this.numeroGoldSquadraDiCasa = numeroGoldSquadraDiCasa;
    }

    public Integer getNumeroGolSquadraOspite() {
        return numeroGolSquadraOspite;
    }

    public void setNumeroGolSquadraOspite( Integer numeroGolSquadraOspite ) {
        this.numeroGolSquadraOspite = numeroGolSquadraOspite;
    }

    public String squadraVincente() {
        String risultato = null;

        if(this.numeroGoldSquadraDiCasa > this.numeroGolSquadraOspite) {
            risultato = "La squadra di casa ha vinto";
        }
        if( this.numeroGoldSquadraDiCasa < this.numeroGolSquadraOspite) {
            risultato = "La squadra ospite ha vinto";
        }

        return risultato;
    }

}
