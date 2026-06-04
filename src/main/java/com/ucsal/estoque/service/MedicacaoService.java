package com.ucsal.estoque.service;

import com.ucsal.estoque.client.AtendimentoClient;
import com.ucsal.estoque.dto.MedicacaoDTORequest;
import com.ucsal.estoque.entity.Medicacao;
import com.ucsal.estoque.enums.Status;
import com.ucsal.estoque.exception.EstoqueInsuficienteException;
import com.ucsal.estoque.exception.ExclusaoItemUtilizadoException;
import com.ucsal.estoque.exception.ItemNaoEncontrado;
import com.ucsal.estoque.dto.MedicacaoDTOResponse;
import com.ucsal.estoque.exception.ItemNuloException;
import com.ucsal.estoque.mapper.MedicacaoMapper;
import com.ucsal.estoque.repository.MedicacaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicacaoService {

    @Autowired
    private final MedicacaoRepository repository;

    @Autowired
    private final MedicacaoMapper mapper;

    @Autowired
    private final AtendimentoClient atendimentoClient;

    public MedicacaoService(MedicacaoRepository repository,
                            MedicacaoMapper mapper, AtendimentoClient atendimentoClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.atendimentoClient = atendimentoClient;
    }

    @Transactional
    public MedicacaoDTOResponse salvar(@Valid MedicacaoDTORequest dto) {

        Medicacao entity = mapper.mapToModel(dto);

        return mapper.mapToDTO(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public List<MedicacaoDTOResponse> listarTodas() {
        return repository.findAll()
                .stream()
                .map(mapper::mapToDTO)
                .toList();
    }

    @Transactional
    public void modificarStatus(Long id) {
        Medicacao medicacao = repository.findById(id)
                .orElseThrow(() -> new ItemNaoEncontrado("Medicacao nao encontrada"));

        if (medicacao.getStatus() == Status.ATIVO) {
            medicacao.setStatus(Status.INATIVO);
        } else {
            medicacao.setStatus(Status.ATIVO);
        }

        repository.save(medicacao);
    }

    @Transactional
    public MedicacaoDTOResponse atualizarEstoque(Long id, Integer novaQuantidade) {
        Medicacao medicacao = repository.findById(id)
                .orElseThrow(() -> new ItemNaoEncontrado("Medicacao nao encontrada"));

        if (novaQuantidade == null || novaQuantidade < 0) {
            throw new ItemNuloException("A quantidade de estoque deve ser informada e nao pode ser negativa");
        }

        medicacao.setQuantidadeEstoque(novaQuantidade);
        return mapper.mapToDTO(repository.save(medicacao));
    }

    @Transactional
    public Medicacao consumirEstoque(Long id, Integer quantidadeConsumida) {
        Medicacao medicacao = repository.findById(id)
                .orElseThrow(() -> new ItemNaoEncontrado("Medicacao nao encontrada"));

        if (quantidadeConsumida == null || quantidadeConsumida <= 0) {
            return medicacao;
        }

        if (medicacao.getQuantidadeEstoque() < quantidadeConsumida) {
            throw new EstoqueInsuficienteException("Quantidade em estoque insuficiente para registrar o atendimento");
        }

        medicacao.setQuantidadeEstoque(medicacao.getQuantidadeEstoque() - quantidadeConsumida);
        return repository.save(medicacao);
    }

    @Transactional
    public void excluir(Long id) {

        Medicacao medicacao = repository.findById(id)
                .orElseThrow(() -> new ItemNaoEncontrado("Medicacao nao encontrada"));

        if (!atendimentoClient.buscarPorMedicacao(id)) {
            throw new ExclusaoItemUtilizadoException("Medicacao ja foi utilizada em atendimentos");
        }

        repository.delete(medicacao);
    }
}
