package com.bananesexport.bananesexport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class Destinataire {

    @Id
    private Long id;

    private String nom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String pays;


    public Destinataire() {

    }


    public Destinataire(String nom, String adresse, String codePostal, String ville, String pays) {
        this.nom = nom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
    }


}
