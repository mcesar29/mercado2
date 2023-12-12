package com.example.mercado.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mercado.model.AuthResponse;
import com.example.mercado.model.AuthenticationDTO;
import com.example.mercado.model.RegisterDTO;
import com.example.mercado.model.UsuarioModel;
import com.example.mercado.repository.UsuarioRepository;
import com.example.mercado.service.TokenService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UsuarioModel)auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if(this.usuarioRepository.findByLogin(data.login()) != null){
            return ResponseEntity.badRequest().build();
        } 

        String encryptPassaword = new BCryptPasswordEncoder().encode(data.password());

        UsuarioModel newUsuarioModel = new UsuarioModel(data.login(),encryptPassaword,data.role());
        this.usuarioRepository.save(newUsuarioModel);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/usuario/{login}")
    public ResponseEntity getUsuario(@PathVariable String login) {
        return ResponseEntity.ok().body(usuarioRepository.findByLogin(login));
    }
    
    
}
