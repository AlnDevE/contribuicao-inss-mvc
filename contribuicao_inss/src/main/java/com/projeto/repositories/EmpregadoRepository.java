package com.projeto.repositories;

import com.projeto.entities.Empregado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {
    Page<Empregado> findByNomeContaining(String nome, Pageable pageable);
}
