package com.projeto.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EmpregadoFormDto {
    @NotBlank
    private String nome;
    @NotBlank
    private String setor;
    @NotBlank
    private String salarioBruto;
}

