package com.example.mercado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(@NotBlank String nome,@NotNull Long valor) {
    
}
