package com.example.mercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.mercado.model.ProdutoModel;

@Service
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
    
}
