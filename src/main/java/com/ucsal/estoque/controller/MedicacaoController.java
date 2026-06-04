package com.ucsal.estoque.controller;

import com.ucsal.estoque.dto.MedicacaoDTORequest;
import com.ucsal.estoque.dto.MedicacaoDTOResponse;
import com.ucsal.estoque.exception.ItemNaoEncontrado;
import com.ucsal.estoque.exception.ItemNuloException;
import com.ucsal.estoque.service.MedicacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicacoes")
public class MedicacaoController {

    @Autowired
    private final MedicacaoService medicacaoService;

    public MedicacaoController(MedicacaoService medicacaoService) {
        this.medicacaoService = medicacaoService;
    }

    @GetMapping
    public ResponseEntity<List<MedicacaoDTOResponse>> listar() {
        return ResponseEntity.ok(medicacaoService.listarTodas());
    }

    @PostMapping
    public ResponseEntity<MedicacaoDTOResponse> cadastrar(
            @RequestBody @Valid MedicacaoDTORequest dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(medicacaoService.salvar(dto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> modificarStatus(@PathVariable Long id) {
        try {
            medicacaoService.modificarStatus(id);
            return ResponseEntity.noContent().build();
        } catch (ItemNaoEncontrado err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
        }
    }

    @PatchMapping("/{id}/estoque")
    public ResponseEntity<?> atualizarEstoque(@PathVariable Long id, @RequestBody Integer novaQuantidade) {
        try {
            return ResponseEntity.ok(medicacaoService.atualizarEstoque(id, novaQuantidade));
        } catch (ItemNaoEncontrado err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
        } catch (ItemNuloException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
        }
    }
}
