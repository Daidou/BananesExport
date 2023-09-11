package com.bananesexport.bananesexport.repository;

import com.bananesexport.bananesexport.model.Commande;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommandeRepository extends MongoRepository<Commande, Long> {
}
