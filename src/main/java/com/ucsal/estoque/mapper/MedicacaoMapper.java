package com.ucsal.estoque.mapper;

import com.ucsal.estoque.dto.MedicacaoDTORequest;
import com.ucsal.estoque.entity.Medicacao;
import com.ucsal.estoque.enums.Status;
import com.ucsal.estoque.dto.MedicacaoDTOResponse;
import org.springframework.stereotype.Component;

@Component
public class MedicacaoMapper {

    public Medicacao mapToModel(MedicacaoDTORequest dto) {
        Medicacao model = new Medicacao();

        model.setNome(dto.nome());
        model.setDescricaoCompleta(dto.descricaoCompleta());
        model.setFornecedor(dto.fornecedor());
        model.setFormaArmazenamento(dto.formaArmazenamento());
        model.setQuantidadeEstoque(dto.quantidadeEstoque());
        model.setDataValidade(dto.dataValidade());
        model.setDataAquisicao(dto.dataAquisicao());
        model.setStatus(dto.status() == null ? Status.ATIVO : dto.status());

        return model;
    }

    public MedicacaoDTOResponse mapToDTO(Medicacao model) {
        return new MedicacaoDTOResponse(
                model.getId(),
                model.getNome(),
                model.getDescricaoCompleta(),
                model.getFornecedor(),
                model.getFormaArmazenamento(),
                model.getQuantidadeEstoque(),
                model.getDataValidade(),
                model.getDataAquisicao(),
                model.getStatus()
        );
    }
}
