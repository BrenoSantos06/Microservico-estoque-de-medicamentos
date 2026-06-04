package com.ucsal.estoque.entity;

import com.ucsal.estoque.enums.CaraterSolicitacaoMedicacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "requisicoes_medicacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequisicaoMedicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medicacao_id", nullable = false)
    private Long medicamentoId;

    @Column(name = "profissional_id", nullable = false)
    private Long profissionalId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CaraterSolicitacaoMedicacao caraterSolicitacao;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataSolicitacao;
}