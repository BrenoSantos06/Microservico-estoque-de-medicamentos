package com.ucsal.estoque.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ucsal.estoque.enums.FormaArmazenamento;
import com.ucsal.estoque.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "medicacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricaoCompleta;

    @Column(nullable = false)
    private String fornecedor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormaArmazenamento formaArmazenamento;

    @Column(nullable = false)
    private Integer quantidadeEstoque;

    @Column(nullable = false)
    private LocalDate dataValidade;

    @Column(nullable = false)
    private LocalDate dataAquisicao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}