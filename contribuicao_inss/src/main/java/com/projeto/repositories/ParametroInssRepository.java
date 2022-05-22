package com.projeto.repositories;

import com.projeto.entities.ParametroInss;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParametroInssRepository extends JpaRepository<ParametroInss, Long> {
    ParametroInss findByAno(int ano);
}
