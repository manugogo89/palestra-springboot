

package com.home.repository;

import com.home.model.Contatto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//questo è il repository per la tabella Contatto
@Repository
public interface ContattoRepository extends JpaRepository<Contatto, Long> {
}