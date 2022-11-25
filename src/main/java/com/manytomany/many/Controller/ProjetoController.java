package com.manytomany.many.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.manytomany.many.Model.Projeto;
import com.manytomany.many.Repository.ProjetoRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/projeto")
@AllArgsConstructor

public class ProjetoController {
    
    private ProjetoRepository projetoRepository;

    @GetMapping
    public List<Projeto> getAllProjetos(){
        return projetoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Projeto> getProjetoById(@PathVariable("id") Long id) {
        return  projetoRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED) 
    public Projeto create (@RequestBody Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    @PutMapping("/{id}")
    Optional<Object> update(@RequestBody Projeto newProjeto, @PathVariable Long id) {
      
      return projetoRepository.findById(id).
        map(projeto -> {
          projeto.setTitulo(newProjeto.getTitulo());
          projeto.setDescricao(newProjeto.getDescricao());
          projeto.setDataInicio(newProjeto.getDataInicio());
          projeto.setDataFim(newProjeto.getDataFim());
          projeto.setValorPrevisto(newProjeto.getValorPrevisto());
          projeto.setValorGasto(newProjeto.getValorGasto());
          return projetoRepository.save(projeto);
        });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        projetoRepository.deleteById(id);
    }
}