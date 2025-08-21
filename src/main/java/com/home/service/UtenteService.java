package com.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.model.Utente;
import com.home.repository.UtenteRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.home.model.Utente;
import com.home.repository.UtenteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

   
    public void salvaUtente(Utente u) {
     
        utenteRepository.save(u);
    }

  
    public Utente login(String username, String password) {
        Optional<Utente> opt = utenteRepository.findByUsername(username);
        if (opt.isPresent()) {
            Utente u = opt.get();
           
            if (u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public boolean utenteEsiste(String username, String email) {
        boolean userExists = utenteRepository.findByUsername(username).isPresent();
        boolean emailExists = utenteRepository.findByEmail(email).isPresent();
        return userExists || emailExists;
    }
    public List<Utente> tuttiGliUtenti() {
        return utenteRepository.findAll(); 
    }
   
    

	
}