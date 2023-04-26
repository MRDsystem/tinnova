package com.cadastroveiculos.cadvei.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cadastroveiculos.cadvei.entities.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{

	List<Veiculo> findByMarcaAndAnoAndCor(String marca, Integer ano, String cor);
	List<Veiculo> findByVendidoFalse();
	long countByVendidoFalse();
}
