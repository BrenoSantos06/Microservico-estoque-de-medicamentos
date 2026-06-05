package com.ucsal.estoque.service;

import com.ucsal.estoque.client.ProfissionalClient;
import com.ucsal.estoque.entity.RequisicaoMedicacao;
import com.ucsal.estoque.exception.ItemNaoEncontrado;
import com.ucsal.estoque.exception.ItemNuloException;
import com.ucsal.estoque.dto.RequisicaoMedicacaoDTORequest;
import com.ucsal.estoque.dto.RequisicaoMedicacaoDTOResponse;
import com.ucsal.estoque.exception.ProfissionalNaoEncontradoException;
import com.ucsal.estoque.mapper.RequisicaoMedicacaoMapper;
import com.ucsal.estoque.repository.MedicacaoRepository;
import com.ucsal.estoque.repository.RequisicaoMedicacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequisicaoMedicamentoService {

    private final RequisicaoMedicacaoRepository repository;
    private final RequisicaoMedicacaoMapper mapper;
    private final ProfissionalClient profissionalClient;
    private final MedicacaoRepository medicacaoRepository;

    public RequisicaoMedicamentoService(RequisicaoMedicacaoRepository repository,
                                        RequisicaoMedicacaoMapper mapper, ProfissionalClient profissionalClient, MedicacaoRepository medicacaoRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.profissionalClient = profissionalClient;
        this.medicacaoRepository = medicacaoRepository;
    }

    @Transactional
    public RequisicaoMedicacaoDTOResponse salvar(RequisicaoMedicacaoDTORequest dto) {

        if (dto.medicacaoId() == null ||
                dto.profissionalId() == null ||
                dto.caraterSolicitacao() == null) {
            throw new ItemNuloException(
                    "Medicacao, profissional e caráter da solicitação devem ser informados"
            );
        }


        medicacaoRepository.findById(dto.medicacaoId())
                .orElseThrow(() -> new ItemNaoEncontrado("Medicação não encontrada"));

        profissionalClient.findById(dto.profissionalId())
                .orElseThrow(() -> new ProfissionalNaoEncontradoException("Profissional não encontrado"));

        RequisicaoMedicacao entity = new RequisicaoMedicacao();
        entity.setMedicamentoId(dto.medicacaoId());
        entity.setProfissionalId(dto.profissionalId());
        entity.setCaraterSolicitacao(dto.caraterSolicitacao());
        entity.setDataSolicitacao(LocalDateTime.now());

        RequisicaoMedicacao saved = repository.save(entity);

        return mapper.mapToDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<RequisicaoMedicacaoDTOResponse> listarTodas() {
        return repository.findAll()
                .stream()
                .map(mapper::mapToDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<RequisicaoMedicacaoDTOResponse> listarPorProfissional(Long profissionalId) {

        if (profissionalId == null) {
            throw new ItemNuloException("ProfissionalId deve ser informado");
        }

        return repository.findByProfissionalId(profissionalId)
                .stream()
                .map(mapper::mapToDTO)
                .toList();
    }
}
