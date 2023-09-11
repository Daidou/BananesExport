package com.bananesexport.bananesexport.controllerTests;

 import java.time.LocalDate;

 import com.bananesexport.bananesexport.model.Commande;
 import org.junit.jupiter.api.Test;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
 import org.springframework.boot.test.context.SpringBootTest;
 import org.springframework.http.MediaType;
 import org.springframework.test.web.servlet.MockMvc;
 import org.springframework.test.web.servlet.MvcResult;
 import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
 import static org.assertj.core.api.Assertions.*;

 import com.fasterxml.jackson.databind.ObjectMapper;


 @SpringBootTest
 @AutoConfigureMockMvc
 public class CommandeControllerTests {

     @Autowired
     private MockMvc mock;

     @Autowired
     private ObjectMapper objectMapper;

     @Test
     public void testCreateCommande() throws Exception {

         Commande commande = new Commande();
         commande.setDateLivraison(LocalDate.now().plusDays(7));
         commande.setBananesKg(100);
         commande.setPrix(0);

         String jsonCommande = objectMapper.writeValueAsString(commande);

         MvcResult result = mock.perform(MockMvcRequestBuilders.post("api/v1/commandes")
         .contentType(MediaType.APPLICATION_JSON)
         .content(jsonCommande))
         .andReturn();


         String contentResponse = result.getResponse().getContentAsString();
         Commande createdCommande = objectMapper.readValue(contentResponse, Commande.class);

         assertThat(createdCommande).isNotNull();
         assertThat(createdCommande.getId()).isNotNull();
         assertThat(createdCommande.getPrix()).isEqualTo(100 * 2.50);
     }

 }
