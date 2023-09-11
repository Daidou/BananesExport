package com.bananesexport.bananesexport.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Document
public class Commande {

    @Id
    private String id;

    private Destinataire destinataire;
    private LocalDate dateLivraison;
    private int bananesKg;
    private double prix;



    public Commande(Destinataire destinataire, LocalDate dateLivraison, int bananesKg, double prix) {
        this.destinataire = destinataire;
        this.dateLivraison = dateLivraison;
        this.bananesKg = bananesKg;
        this.prix = prix;
    }

    public Commande() {
    }


}