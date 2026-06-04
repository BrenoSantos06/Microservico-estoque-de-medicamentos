package com.ucsal.estoque.dto;

import com.ucsal.estoque.enums.FormaArmazenamento;
import com.ucsal.estoque.enums.Status;

import java.time.LocalDate;

public record MedicacaoDTOResponse(

        Long id,
        String nome,
        String descricaoCompleta,
        String fornecedor,
        FormaArmazenamento formaArmazenamento,
        Integer quantidadeEstoque,
        LocalDate dataValidade,
        LocalDate dataAquisicao,
        Status status

) {}
