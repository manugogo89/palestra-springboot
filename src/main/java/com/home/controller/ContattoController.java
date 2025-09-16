package com.home.controller;

import com.home.model.Contatto;
import com.home.repository.ContattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contatto") // il controller risponde agli endpoint che iniziano con /contatto
public class ContattoController {

    private final ContattoRepository contattoRepository;

    @Autowired
    public ContattoController(ContattoRepository contattoRepository) {
        this.contattoRepository = contattoRepository; // repository per salvare e gestire i contatti
    }

    @PostMapping
    public String salvaContatto(@ModelAttribute Contatto contatto) {
        // salvo il contatto nel db
        contattoRepository.save(contatto);
        // dopo il salvataggio reindirizzo di nuovo alla pagina contatto
        return "redirect:/contatto"; 
    }

    @GetMapping
    public String mostraFormContatto() {
        // restituisce la pagina del form contatto (contatto.html)
        return "contatto"; 
    }
}