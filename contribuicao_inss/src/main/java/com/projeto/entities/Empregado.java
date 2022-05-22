package com.projeto.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Empregado {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codEmpregado;
    private String nome;
    private String setor;
    private double salarioBruto;
    private double recInss;
}
