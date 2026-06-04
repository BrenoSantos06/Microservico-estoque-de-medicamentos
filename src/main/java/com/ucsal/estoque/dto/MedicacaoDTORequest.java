package com.ucsal.estoque.dto;

import com.ucsal.estoque.enums.FormaArmazenamento;
import com.ucsal.estoque.enums.Status;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MedicacaoDTORequest(

        @NotNull
        String nome,

        @NotNull
        String descricaoCompleta,

        @NotNull
        String fornecedor,

        @NotNull
        FormaArmazenamento formaArmazenamento,

        @NotNull
        Integer quantidadeEstoque,

        @NotNull
        LocalDate dataValidade,

        @NotNull
        LocalDate dataAquisicao,

        @NotNull
        Status status

) {}