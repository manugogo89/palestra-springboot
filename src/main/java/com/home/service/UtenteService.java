package com.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.model.Utente;
import com.home.repository.UtenteRepository;

import java.util.List;
import java.util.Optional;

@Service 
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository; // repository per interagire col db

    // salvo un nuovo utente nel db
    public void salvaUtente(Utente u) {
        utenteRepository.save(u);
    }

    // metodo di login: controlla se username e password corrispondono
    public Utente login(String username, String password) {
        Optional<Utente> opt = utenteRepository.findByUsername(username);
        if (opt.isPresent()) {
            Utente u = opt.get();
            // confronto password "in chiaro" (in un progetto reale andrebbe criptata!)
            if (u.getPassword().equals(password)) {
                return u; // login riuscito
            }
        }
        return null; 
    }

    // controllo se esiste già un utente con lo stesso username o la stessa email
    public boolean utenteEsiste(String username, String email) {
        boolean userExists = utenteRepository.findByUsername(username).isPresent();
        boolean emailExists = utenteRepository.findByEmail(email).isPresent();
        return userExists || emailExists;
    }

    // recupero tutti gli utenti dal db
    public List<Utente> tuttiGliUtenti() {
        return utenteRepository.findAll();
    }
}