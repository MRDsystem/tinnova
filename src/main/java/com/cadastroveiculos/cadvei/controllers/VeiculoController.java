package com.cadastroveiculos.cadvei.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cadastroveiculos.cadvei.entities.Veiculo;
import com.cadastroveiculos.cadvei.entities.Veiculo.Marcas;
import com.cadastroveiculos.cadvei.service.VeiculoService;

@RestController
@RequestMapping(value = "/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoService service;
	
	@GetMapping
    public List<Veiculo> findAll(){
        return service.findAll();
    }
		   
	@GetMapping(value = "/quantitynotsold")
	public String quantityNotSold()
	{
		return service.quantityNotSold();
	}
	
	@GetMapping(value = "/veiculos")
	public List<Veiculo> findByMarcaAndAnoAndCor(@RequestParam(name = "marca", required = false) String marca,
	                                             @RequestParam(name = "ano", required = false) Integer ano,
	                                             @RequestParam(name = "cor", required = false) String cor) {
	    return service.findByMarcaAndAnoAndCor(marca, ano, cor);
	}

	
	@GetMapping(value = "/{id}")
	public Veiculo findById(@PathVariable Long id){
		return service.findById(id);
	}
		
	@PostMapping
	public Veiculo Insert(@RequestBody Veiculo veiculo){
		Veiculo result = service.insert(veiculo);
		return result;
	}
	
	@PutMapping("/{id}")
	public Veiculo Update(@PathVariable Long id, @RequestBody Veiculo veiculo) throws Exception, InvocationTargetException{
	    Veiculo result = service.update(id, veiculo);
	    return result;
	}

	
	@PatchMapping("/{id}")
	public Veiculo UpdateInstallment(@PathVariable Long id, @RequestBody Map<String, Object> camposAtualizados) {
	    String veiculo = (String) camposAtualizados.get("veiculo");
	    Marcas marca = (Marcas) camposAtualizados.get("marca");
	    String cor = (String) camposAtualizados.get("cor");
	    Integer ano = (Integer) camposAtualizados.get("ano");
	    String descricao = (String) camposAtualizados.get("descricao");
	    boolean vendido = (boolean) camposAtualizados.get("vendido");

	    return service.updateInstallment(id, veiculo, marca, cor, ano, descricao, vendido);
	}

	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id)
	{
		service.deleteById(id);
	}
}
	
