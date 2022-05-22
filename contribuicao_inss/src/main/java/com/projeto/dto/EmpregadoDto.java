package com.projeto.dto;

import lombok.Data;

@Data
public class EmpregadoDto {

    private int codEmpregado;
    private String nome;
    private String setor;
    private double salarioBruto;
}
