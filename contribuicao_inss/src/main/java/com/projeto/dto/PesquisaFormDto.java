package com.projeto.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PesquisaFormDto {
    @NotBlank
    private String nome;
}
