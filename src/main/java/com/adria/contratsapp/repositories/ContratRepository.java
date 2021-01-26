package com.adria.contratsapp.repositories;

import com.adria.contratsapp.entities.Contrat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratRepository extends JpaRepository<Contrat,Long> {
    public Page<Contrat> findByTitleContains(String kw, Pageable pageable);
}
