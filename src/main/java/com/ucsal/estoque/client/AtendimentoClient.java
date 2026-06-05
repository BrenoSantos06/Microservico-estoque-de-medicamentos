package com.ucsal.estoque.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AtendimentoClient {

    private final WebClient webClient;

    public AtendimentoClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public boolean buscarPorMedicacao(Long medicacaoId) {

        Boolean result = webClient.get()
                .uri("http://localhost:8085/atendimentos/medicacao/{id}/exists", medicacaoId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        return Boolean.TRUE.equals(result);
    }
}