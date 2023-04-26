package com.cadastroveiculos.cadvei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.cadastroveiculos.cadvei.entities.Veiculo;
import com.cadastroveiculos.cadvei.entities.Veiculo.Marcas;
import com.cadastroveiculos.cadvei.exception.ResourceNotFoundException;
import com.cadastroveiculos.cadvei.repositories.VeiculoRepository;


@Service
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository repository;
	
	public List<Veiculo> findAll(){
		return repository.findAll();
	}
	
	public List<Veiculo> findByMarcaAndAnoAndCor(String marca, Integer ano, String cor) {
	    return repository.findByMarcaAndAnoAndCor(marca, ano, cor);
	}

	public Veiculo findById(Long id){
		return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cadastro nao encontrado"));
	}
	
	public List<Veiculo> findByVendidoFalse() {
	    return repository.findByVendidoFalse();
	}
		
	public Veiculo insert(@RequestBody Veiculo veiculo){
		Veiculo result = repository.save(veiculo);
		return result;
	}
		
	public Veiculo update(Long id, Veiculo veiculo) {
	    Veiculo existingVeiculo = repository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Cadastro não encontrado"));

	    existingVeiculo.setVeiculo(veiculo.getVeiculo());
	    existingVeiculo.setMarca(veiculo.getMarca());
	    existingVeiculo.setAno(veiculo.getAno());
	    existingVeiculo.setCor(veiculo.getCor());
	    existingVeiculo.setDescricao(veiculo.getDescricao());
	    existingVeiculo.setVendido(veiculo.isVendido());

	    Veiculo updatedVeiculo = repository.save(existingVeiculo);
	    return updatedVeiculo;
	}
	
	public Veiculo updateInstallment(Long id, String veiculo, Marcas marca, String cor, Integer ano, String descricao, boolean vendido) {
	    Veiculo veiculoAtualizado = repository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Cadastro nao encontrado"));

	    if (veiculo != null) {
	        veiculoAtualizado.setVeiculo(veiculo);
	    }

	    if (marca != null) {
	        veiculoAtualizado.setMarca(marca);
	    }

	    if (cor != null) {
	        veiculoAtualizado.setCor(cor);
	    }

	    if (ano != null) {
	        veiculoAtualizado.setAno(ano);
	    }

	    if (descricao != null) {
	        veiculoAtualizado.setDescricao(descricao);
	    }

	    veiculoAtualizado.setVendido(vendido);

	    return repository.save(veiculoAtualizado);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);		
	}
	
	public String quantityNotSold()
	{
		return "Total de veículos não vendidos: " + countVendidoFalse();
	}

	private long countVendidoFalse() {
		return repository.countByVendidoFalse();
	}
	
	

}
