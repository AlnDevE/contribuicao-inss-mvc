package com.projeto.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class ParametroInss {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int ano;
    private double faixa1;
    private double limiteFaixa1;
    private double faixa2;
    private double limiteFaixa2;
    private double faixa3;
    private double limiteFaixa3;
    private double faixa4;
    private double limiteFaixa4;

}
