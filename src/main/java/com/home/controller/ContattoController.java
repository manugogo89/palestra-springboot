package com.home.controller;

import com.home.model.Contatto;
import com.home.repository.ContattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contatto")
public class ContattoController {

    private final ContattoRepository contattoRepository;

    @Autowired
    public ContattoController(ContattoRepository contattoRepository) {
        this.contattoRepository = contattoRepository;
    }

    @PostMapping
    public String salvaContatto(@ModelAttribute Contatto contatto) {
        contattoRepository.save(contatto);
        return "redirect:/contatto"; 
    }
    @GetMapping
    public String mostraFormContatto() {
        return "contatto"; 
}
}