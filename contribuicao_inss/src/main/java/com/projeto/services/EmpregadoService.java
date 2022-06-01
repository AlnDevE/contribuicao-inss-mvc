package com.projeto.services;

import com.projeto.dto.CalcularFormDto;
import com.projeto.dto.EmpregadoDto;
import com.projeto.dto.EmpregadoFormDto;
import com.projeto.dto.PesquisaFormDto;
import com.projeto.entities.Empregado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmpregadoService {
    void cadastrarEmpregado(EmpregadoFormDto empregadoFormDto);

    Page<EmpregadoDto> listaEmpregados(Pageable paginacao);

    Empregado calcularRecolhimento(CalcularFormDto cal);

    Page<EmpregadoDto> pesquisarEmpregado(PesquisaFormDto pesquisaFormDto, Pageable paginacao);

}
