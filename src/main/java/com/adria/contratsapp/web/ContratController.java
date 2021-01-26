package com.adria.contratsapp.web;

import com.adria.contratsapp.entities.Contrat;
import com.adria.contratsapp.repositories.ContratRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class ContratController {
    private ContratRepository contratRepository;

    public ContratController(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }
    @GetMapping(path="/index")
    public String index(){
        return "index";
    }
    @GetMapping(path="/contrats")
    public String contrats(Model model,
                           @RequestParam(name="page",defaultValue = "0") int page,
                           @RequestParam(name="size",defaultValue = "5")int size,
                           @RequestParam(name="keyword",defaultValue = "")String kw){
        Page<Contrat> pageContrats=contratRepository.findByTitleContains(kw,PageRequest.of(page,size));
        model.addAttribute("pageContrats",pageContrats);
        model.addAttribute("keyword",kw);
        return "contrats";
    }
    @GetMapping(path = "/delete")
    public String delete(@RequestParam("id") Long id,
                         @RequestParam(name = "keyword",defaultValue = "") String keyword){
        contratRepository.deleteById(id);
        return "redirect:/contrats?keyword="+keyword;
    }
    @GetMapping(path = "/cloturer")
    public String cloturer(@RequestParam("id") Long id, @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Contrat contrat=contratRepository.findById(id).get();
        contrat.setCloture(!contrat.isCloture());
        contratRepository.save(contrat);
        return "redirect:/contrats?keyword="+keyword;
    }
    @GetMapping(path = "/formContrat")
    public String fromContrat(Model model){
        model.addAttribute("contrat",new Contrat());
        return "formContrat";
    }
    @PostMapping(path = "/saveContrat")
    public String fromContrat(@Valid Contrat contrat, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formContrat";
        contratRepository.save(contrat);
        return "redirect:/contrats";
    }
    @GetMapping(path = "/edit")
    public String edit(@RequestParam("id") Long id, Model model){
        Contrat contrat=contratRepository.findById(id).get();
        model.addAttribute("contrat",contrat);
        return "formEditContrat";
    }
}
