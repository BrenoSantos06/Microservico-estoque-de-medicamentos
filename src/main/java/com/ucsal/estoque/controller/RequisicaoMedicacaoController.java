package com.ucsal.estoque.controller;

import com.ucsal.estoque.dto.RequisicaoMedicacaoDTORequest;
import com.ucsal.estoque.dto.RequisicaoMedicacaoDTOResponse;
import com.ucsal.estoque.service.RequisicaoMedicamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requisicoes-medicamento")
public class RequisicaoMedicacaoController {

    private final RequisicaoMedicamentoService requisicaoMedicacaoService;

    public RequisicaoMedicacaoController(RequisicaoMedicamentoService requisicaoMedicacaoService) {
        this.requisicaoMedicacaoService = requisicaoMedicacaoService;
    }

    @PostMapping
    public ResponseEntity<RequisicaoMedicacaoDTOResponse> criar(
            @RequestBody @Valid RequisicaoMedicacaoDTORequest request
    ) {
        RequisicaoMedicacaoDTOResponse response =
                requisicaoMedicacaoService.salvar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<RequisicaoMedicacaoDTOResponse>> listar() {
        return ResponseEntity.ok(
                requisicaoMedicacaoService.listarTodas()
        );
    }

    @GetMapping("/profissional/{profissionalId}")
    public ResponseEntity<List<RequisicaoMedicacaoDTOResponse>> listarPorProfissional(
            @PathVariable Long profissionalId
    ) {
        return ResponseEntity.ok(
                requisicaoMedicacaoService.listarPorProfissional(profissionalId)
        );
    }
}
