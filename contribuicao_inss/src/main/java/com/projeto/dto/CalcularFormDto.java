package com.projeto.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CalcularFormDto {

    @NotBlank
    private String codEmpregado;

    @NotNull
    private String ano;
}
