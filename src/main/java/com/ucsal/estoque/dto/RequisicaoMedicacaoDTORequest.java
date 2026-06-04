package com.ucsal.estoque.dto;

import com.ucsal.estoque.enums.CaraterSolicitacaoMedicacao;
import jakarta.validation.constraints.NotNull;

public record RequisicaoMedicacaoDTORequest(

        @NotNull
        Long medicacaoId,

        @NotNull
        Long profissionalId,

        @NotNull
        CaraterSolicitacaoMedicacao caraterSolicitacao

) {}