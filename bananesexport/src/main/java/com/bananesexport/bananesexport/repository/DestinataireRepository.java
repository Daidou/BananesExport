package com.bananesexport.bananesexport.repository;

import com.bananesexport.bananesexport.model.Destinataire;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DestinataireRepository extends MongoRepository<Destinataire, Long> {
}
