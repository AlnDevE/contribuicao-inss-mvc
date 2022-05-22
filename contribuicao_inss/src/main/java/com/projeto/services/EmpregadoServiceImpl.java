package com.projeto.services;

import com.projeto.dto.CalcularFormDto;
import com.projeto.dto.EmpregadoDto;
import com.projeto.dto.EmpregadoFormDto;
import com.projeto.dto.PesquisaFormDto;
import com.projeto.entities.Empregado;
import com.projeto.entities.ParametroInss;
import com.projeto.repositories.EmpregadoRepository;
import com.projeto.repositories.ParametroInssRepository;
import com.projeto.util.empregadoException.EmpregadoNaoEncontradoExeption;
import com.projeto.util.formatExeption.FormatoInvalidoExeception;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpregadoServiceImpl implements EmpregadoService{

    @Autowired
    EmpregadoRepository empregadoRepository;

    @Autowired
    ParametroInssRepository parametroRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public void cadastrarEmpregado(EmpregadoFormDto empregadoFormDto) {
        empregadoRepository.save(mapper.map(empregadoFormDto, Empregado.class));
    }

    @Override
    public Page<EmpregadoDto> listaEmpregados(Pageable paginacao) {
        Page<Empregado> empregado;
        empregado = empregadoRepository.findAll(paginacao);
        Page<EmpregadoDto> empregadoDto = new PageImpl<>(empregado.stream().map(element -> mapper.map(element
                , EmpregadoDto.class)).collect(Collectors.toList()));
        return empregadoDto;
    }

    @Override
    public Empregado calcularRecolhimento(CalcularFormDto calcularFormDto) {
        try {
            Empregado empregado = verificaExistencia(Long.parseLong(calcularFormDto.getCodEmpregado()));
            ParametroInss parametroInss = parametroRepository.findByAno(Integer.parseInt(calcularFormDto.getAno()));
            empregado.setRecInss(calculaValor(empregado, parametroInss));
            empregadoRepository.save(empregado);
            return empregado;

        }catch (Exception e){
            throw new FormatoInvalidoExeception("Formato Inválido");
        }
    }

    @Override
    public Page<EmpregadoDto> pesquisarEmpregado(PesquisaFormDto pesquisaFormDto, Pageable paginacao) {
        Page<Empregado> empregado;
        try {
            empregado = empregadoRepository.findByNomeContaining(pesquisaFormDto.getNome(), paginacao);
        }catch (Exception e){
            throw new EmpregadoNaoEncontradoExeption("Não Encontrado");
        }
        if(empregado.isEmpty()){
            throw new EmpregadoNaoEncontradoExeption("Não Encontrado");
        }
        Page<EmpregadoDto> empregadoDto = new PageImpl<>(empregado.stream().map(element -> mapper.map(element
                , EmpregadoDto.class)).collect(Collectors.toList()));

        return empregadoDto;
    }

    public Empregado verificaExistencia(Long id){
        Optional<Empregado> empregado = empregadoRepository.findById(id);
        if(empregado.isPresent()){
            return empregado.get();
        }
        throw new EmpregadoNaoEncontradoExeption("Não Encontrado");
    }

    public double calculaValor(Empregado empregado, ParametroInss parametroInss){

        if(empregado.getSalarioBruto() <= parametroInss.getLimiteFaixa1()){
            return empregado.getSalarioBruto()*parametroInss.getFaixa1();

        }if(empregado.getSalarioBruto() <= parametroInss.getLimiteFaixa2()){
            return empregado.getSalarioBruto()*parametroInss.getFaixa2();

        }if(empregado.getSalarioBruto() <= parametroInss.getLimiteFaixa3()){
            return empregado.getSalarioBruto()*parametroInss.getFaixa3();

        }
        return empregado.getSalarioBruto()*parametroInss.getFaixa4();

    }


}
