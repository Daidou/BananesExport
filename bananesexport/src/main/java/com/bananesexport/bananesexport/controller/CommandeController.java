package com.bananesexport.bananesexport.controller;


import com.bananesexport.bananesexport.model.Commande;
import com.bananesexport.bananesexport.repository.CommandeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/commande")
public class CommandeController {

    private CommandeRepository commandeRepository;

    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {

        if (ValideDate(commande.getDateLivraison()) || ValideQuantite(commande.getBananesKg())) {
            return ResponseEntity.badRequest().build();
        }

        double price = calculationPrice(commande.getBananesKg());
        commande.setPrix(price);

        Commande newCommande = commandeRepository.save(commande);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCommande);
    }

    @GetMapping
    public List<Commande> getCommandes() {
        Iterable<Commande> iterable = commandeRepository.findAll();
        List<Commande> commandes = new ArrayList<>();
        iterable.forEach(commandes::add);
        return commandes;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody Commande commande) {

        if (ValideDate(commande.getDateLivraison()) || ValideQuantite(commande.getBananesKg())) {
            return ResponseEntity.badRequest().build();
        }



        double price = calculationPrice(commande.getBananesKg());
        commande.setPrix(price);

        Commande updateCommande = commandeRepository.findById(id).orElse(null);
        if (updateCommande != null) {
            updateCommande.setDateLivraison(commande.getDateLivraison());
            updateCommande.setBananesKg(commande.getBananesKg());
            updateCommande.setPrix(commande.getPrix());
            commandeRepository.save(updateCommande);
            return ResponseEntity.ok(updateCommande);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        commandeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }




    private double calculationPrice (int quantityBananesKg) {
        double priceForKg = 2.50;
        return quantityBananesKg * priceForKg;
    }

    private boolean ValideDate(LocalDate dateLivraison) {
        LocalDate dDate = LocalDate.now();
        return dateLivraison == null || !dateLivraison.isAfter(dDate.plusWeeks(1));
    }
    private boolean ValideQuantite(int quantiteBananesKg) {
        return quantiteBananesKg >= 0 && quantiteBananesKg <= 10000 && quantiteBananesKg % 25 == 0;
    }
}
