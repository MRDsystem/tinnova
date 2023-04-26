package com.cadastroveiculos.cadvei.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cadastroveiculos.cadvei.entities.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{

}
