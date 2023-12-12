package com.example.mercado.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.mercado.dto.ProdutoDTO;
import com.example.mercado.model.ProdutoModel;
import com.example.mercado.repository.ProdutoRepository;
import com.example.mercado.repository.UsuarioRepository;

import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("produtos")
public class ProdutoController {
    
    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<ProdutoModel> saveProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
        var produtoModel= new ProdutoModel();
        BeanUtils.copyProperties(produtoDTO, produtoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoModel));
    }
    
    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listarProdutos() {
        return new ResponseEntity<>(produtoRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Optional<ProdutoModel>> getProduto(@PathVariable Long id) {
        return new ResponseEntity<>(produtoRepository.findById(id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProdutoModel> deleteProduto(@PathVariable Long id){   
        produtoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    

    @GetMapping("/usuario/{login}")
    public ResponseEntity getUsuario(@PathVariable String login) {
        return ResponseEntity.ok().body(usuarioRepository.findByLogin(login));
    }
    
}
