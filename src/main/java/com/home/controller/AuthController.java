package com.home.controller;

import org.springframework.ui.Model;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.home.model.Utente;
import com.home.service.UtenteService;

@Controller // controller che gestisce le pagine di autenticazione e registrazione
public class AuthController {

    @Autowired
    private UtenteService utenteService; // service per gestire gli utenti

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
        // controllo se le credenziali sono corrette
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
        return "register"; // restituisce la pagina di registrazione
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute Utente u, Model model) {
        // controllo se username o email sono già presenti
        if (utenteService.utenteEsiste(u.getUsername(), u.getEmail())) {
            model.addAttribute("errore", "Username o email già esistenti!");
            return "register"; // torno alla pagina di registrazione con l'errore
        }

        // salvo il nuovo utente
        utenteService.salvaUtente(u);

        // dopo la registrazione, reindirizzo al login
        return "redirect:/login";
    }
    
    @GetMapping("/utenti")
    public String listaUtenti(Model model) {
       
        List<Utente> utenti = utenteService.tuttiGliUtenti(); 
        System.out.println("Utenti trovati: " + utenti.size()); // stampa di debug

       
        model.addAttribute("utenti", utenti);
        return "utenti"; 
    }
}