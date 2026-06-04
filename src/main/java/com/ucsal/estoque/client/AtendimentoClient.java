package com.ucsal.estoque.client;

import com.ucsal.estoque.dto.AtendimentoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class AtendimentoClient {

    private final WebClient webClient;

    public AtendimentoClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<AtendimentoDTO> buscarPorMedicacao(Long medicacaoId) {

        return webClient.get()
                .uri("http://localhost:8085/atendimentos/medicacao/{id}", medicacaoId)
                .retrieve()
                .bodyToFlux(AtendimentoDTO.class)
                .collectList()
                .block();
    }
}