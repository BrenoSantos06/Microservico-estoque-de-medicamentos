package com.ucsal.estoque.client;

import com.ucsal.estoque.dto.ProfissionalDTO;
import com.ucsal.estoque.exception.ProfissionalNaoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class ProfissionalClient {

    private final WebClient webClient;

    public ProfissionalClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public ProfissionalDTO findById(Long id) {
        try {
            return webClient.get()
                    .uri("http://localhost:8082/profissionais/{id}", id)
                    .retrieve()
                    .bodyToMono(ProfissionalDTO.class)

                    .block();
        } catch (WebClientResponseException.NotFound e) {
            throw new ProfissionalNaoEncontradoException("Profissional não encontrado");
        }
    }

}
