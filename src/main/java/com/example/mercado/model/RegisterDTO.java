package com.example.mercado.model;

import java.util.Set;

public record RegisterDTO(String login, String password, Set<PermissaoModel> role) {
    
}
