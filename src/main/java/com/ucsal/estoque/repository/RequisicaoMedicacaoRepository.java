package com.ucsal.estoque.repository;

import com.ucsal.estoque.entity.RequisicaoMedicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequisicaoMedicacaoRepository
        extends JpaRepository<RequisicaoMedicacao, Long> {

    List<RequisicaoMedicacao> findByProfissionalId(Long profissionalId);
}