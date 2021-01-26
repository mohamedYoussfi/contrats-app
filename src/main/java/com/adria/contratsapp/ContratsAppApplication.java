package com.adria.contratsapp;

import com.adria.contratsapp.entities.Contrat;
import com.adria.contratsapp.repositories.ContratRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class ContratsAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContratsAppApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ContratRepository contratRepository) {
        return args -> {
            for (int i = 0; i <10 ; i++) {
                contratRepository.save(new Contrat(null, "Création de compte", new Date(), 10+Math.random()*50000, "Hassan", false));
                contratRepository.save(new Contrat(null, "Vente", new Date(), 10+Math.random()*100000, "Yassine", false));
                contratRepository.save(new Contrat(null, "Assurance", new Date(), 10+Math.random()*765000, "Yasmine", false));
            }
            contratRepository.findAll().forEach(c -> {
                System.out.println(c.toString());
            });
        };
    }
}
