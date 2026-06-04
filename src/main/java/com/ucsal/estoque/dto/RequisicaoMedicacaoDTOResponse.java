package com.ucsal.estoque.dto;

import com.ucsal.estoque.enums.CaraterSolicitacaoMedicacao;

import java.time.LocalDateTime;

public record RequisicaoMedicacaoDTOResponse(

        Long id,

        Long medicacaoId,

        Long profissionalId,

        CaraterSolicitacaoMedicacao caraterSolicitacao,

        LocalDateTime dataSolicitacao

) {}
