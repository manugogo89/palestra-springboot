package com.home.controller;

import org.springframework.ui.Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.home.model.Utente;
import com.home.service.UtenteService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.home.model.Utente;
import com.home.service.UtenteService;

@Controller
public class AuthController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username,
                              @RequestParam String password,
                              Model model) {
        Utente u = utenteService.login(username, password);
        if (u != null) {
            model.addAttribute("utente", u);
            return "dashboard";
        }
        model.addAttribute("errore", "Credenziali errate!");
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("utente", new Utente());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute Utente u, Model model) {

        if (utenteService.utenteEsiste(u.getUsername(), u.getEmail())) {
            model.addAttribute("errore", "Username o email già esistenti!");
            return "register";
        }
        utenteService.salvaUtente(u);
        return "redirect:/login";
    }
    
    @GetMapping("/utenti")
    public String listaUtenti(Model model) {
        List<Utente> utenti = utenteService.tuttiGliUtenti(); 
        System.out.println("Utenti trovati: " + utenti.size());
        model.addAttribute("utenti", utenti);
        return "utenti";
    }
}