package com.projeto.controller;

import com.projeto.dto.EmpregadoDto;
import com.projeto.dto.EmpregadoFormDto;
import com.projeto.dto.PesquisaFormDto;
import com.projeto.services.EmpregadoService;
import com.projeto.util.empregadoException.EmpregadoNaoEncontradoExeption;
import com.projeto.util.formatExeption.FormatoInvalidoExeception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/projeto/empregados")
public class EmpregadoController {

    @Autowired
    EmpregadoService empregadoService;

    @GetMapping("/cadastrar")
    public String formulario(EmpregadoFormDto empregadoFormDto){
        return "empregado/cadastrarEmpregadoForm";
    }

    @PostMapping("/cadastrar")
    public String cadastrarEmpregado(@Valid EmpregadoFormDto empregadoFormDto, BindingResult result){
        if(result.hasErrors()){
            return "empregado/cadastrarEmpregadoForm";
        }
        empregadoService.cadastrarEmpregado(empregadoFormDto);
        return "redirect:/projeto/calcular"; //redireciona depois de fazer cadastro
    }

    @GetMapping("/listar")
    public String listarEmpregados(Model model, @PageableDefault(sort = "codEmpregado", direction = Sort.Direction.ASC) Pageable paginacao) {
        Page<EmpregadoDto> lista = empregadoService.listaEmpregados(paginacao);
        model.addAttribute("empregados",lista);

        return "empregado/listarEmpregados";
    }

    @GetMapping("/pesquisar")
    public String pesquisar(PesquisaFormDto pesquisaFormDto){
        return "empregado/pesquisarEmpregadoForm";
    }

    @PostMapping("/pesquisar")
    public String pesquisarEmpregado(@Valid PesquisaFormDto pesquisaFormDto, BindingResult result, Model model,
                                     @PageableDefault(sort = "codEmpregado", direction = Sort.Direction.ASC) Pageable paginacao){
        model.addAttribute("empregados",empregadoService.pesquisarEmpregado(pesquisaFormDto, paginacao));

        return "resultadoPesquisa";
    }

    @ExceptionHandler(EmpregadoNaoEncontradoExeption.class)
    public String onError(){
        return "redirect:/projeto/empregados/pesquisar";
    }

    @ExceptionHandler(FormatoInvalidoExeception.class)
    public String error(){
        return "redirect:/projeto/empregados/cadastrar";
    }
}
