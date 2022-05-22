package com.projeto.controller;

import com.projeto.dto.CalcularFormDto;
import com.projeto.services.EmpregadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/projeto/calcular")
public class CalculoController {

    @Autowired
    EmpregadoService empregadoService;

    @GetMapping
    public String calcular(CalcularFormDto calcularFormDto, Model model){
        return "calculoContribuicao/calcularInss";
    }

    @PostMapping
    public String calcularRecolhimento(@Valid CalcularFormDto calcularFormDto, BindingResult result, Model model){
        model.addAttribute("empregado",empregadoService.calcularRecolhimento(calcularFormDto));
        return "calculoContribuicao/resultadoCalculo";
    }

    @ExceptionHandler(Exception.class)
    public String onError(){
        return "redirect:/projeto/calcular";
    }

}
