package com.example.mercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.mercado.model.UsuarioModel;

@Service
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{
    
    UsuarioModel findByLogin(String login);
}
