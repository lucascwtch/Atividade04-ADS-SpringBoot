package com.manytomany.many.Repository;

import com.manytomany.many.Model.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
    
}