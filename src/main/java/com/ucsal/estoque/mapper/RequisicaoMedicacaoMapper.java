package com.ucsal.estoque.mapper;

import com.ucsal.estoque.dto.RequisicaoMedicacaoDTOResponse;
import com.ucsal.estoque.entity.RequisicaoMedicacao;
import org.springframework.stereotype.Component;

@Component
public class RequisicaoMedicacaoMapper {

    public RequisicaoMedicacaoDTOResponse mapToDTO(RequisicaoMedicacao model) {

        return new RequisicaoMedicacaoDTOResponse(
                model.getId(),
                model.getMedicamentoId(),
                model.getProfissionalId(),
                model.getCaraterSolicitacao(),
                model.getDataSolicitacao()
        );
    }
    }

