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

import com.manytomany.many.Model.Funcionario;
import com.manytomany.many.Repository.FuncionarioRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/funcionario")
@AllArgsConstructor

public class FuncionarioController {
    
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public List<Funcionario> getAllFuncionario(){
        return funcionarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Funcionario> getFuncionarioById(@PathVariable("id") Long id) {
        return  funcionarioRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED) 
    public Funcionario create (@RequestBody Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @PutMapping("/{id}")
    Optional<Object> update(@RequestBody Funcionario newFuncionario, @PathVariable Long id) {
      
      return funcionarioRepository.findById(id).
        map(funcionario -> {
          funcionario.setNome(newFuncionario.getNome());
          funcionario.setDataAdmissao(newFuncionario.getDataAdmissao());
          funcionario.setSalario(newFuncionario.getSalario());
          funcionario.setCelular(newFuncionario.getCelular());
          funcionario.setEmail(newFuncionario.getEmail());
          return funcionarioRepository.save(funcionario);
        });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        funcionarioRepository.deleteById(id);
    }
}