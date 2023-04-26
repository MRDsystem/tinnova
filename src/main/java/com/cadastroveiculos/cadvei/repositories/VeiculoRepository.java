package com.cadastroveiculos.cadvei.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.cadastroveiculos.cadvei.entities.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{

	List<Veiculo> findByMarcaAndAnoAndCor(@Param("marca") String marca, 
            @Param("ano") Integer ano, 
            @Param("cor") String cor);
}
