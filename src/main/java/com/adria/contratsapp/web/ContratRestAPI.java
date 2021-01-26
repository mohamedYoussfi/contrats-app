package com.adria.contratsapp.web;

import com.adria.contratsapp.entities.Contrat;
import com.adria.contratsapp.repositories.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContratRestAPI {
    @Autowired
    private ContratRepository contratRepository;
    @GetMapping(path = "/contrats")
    public List<Contrat> contrats(){
        return contratRepository.findAll();
    }
    @GetMapping(path = "/contrats/{id}")
    public Contrat contrats(@PathVariable(name = "id") Long id){
        return contratRepository.findById(id).get();
    }
    @PostMapping(path = "/contrats")
    public Contrat save(@RequestBody Contrat contrat){
        return contratRepository.save(contrat);
    }
    @PutMapping(path = "/contrats/{id}")
    public Contrat update(@RequestBody Contrat contrat, @PathVariable Long id){
        contrat.setId(id);
        return contratRepository.save(contrat);
    }
    @DeleteMapping(path = "/contrats/{id}")
    public void delete(@PathVariable Long id){
      contratRepository.deleteById(id);
    }
}
