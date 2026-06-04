package com.ucsal.estoque.repository;

import com.ucsal.estoque.entity.Medicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public interface MedicacaoRepository extends JpaRepository<Medicacao, Long> {
}