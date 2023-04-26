package com.cadastroveiculos.cadvei;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.cadastroveiculos.cadvei.controllers.VeiculoController;
import com.cadastroveiculos.cadvei.entities.Veiculo;
import com.cadastroveiculos.cadvei.service.VeiculoService;

@SpringBootTest
class CadveiApplicationTests {
    
    @Autowired
    private VeiculoController controller;
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    void findByIdTest() {
        // Cria um veículo e persiste na base de dados utilizando o método save do serviço
        Veiculo veiculo = new Veiculo();
        entityManager.persist(veiculo);
        
        // Busca o veículo utilizando o método findById do controlador
        Veiculo result = controller.findById(veiculo.getId());
        
        // Verifica se o veículo buscado é o mesmo que foi persistido
        assertEquals(veiculo.getId(), result.getId());
        assertEquals(veiculo.getVeiculo(), result.getVeiculo());
        assertEquals(veiculo.getMarca(), result.getMarca());
        assertEquals(veiculo.getCor(), result.getCor());
        assertEquals(veiculo.getAno(), result.getAno());
        assertEquals(veiculo.getDescricao(), result.getDescricao());
        assertEquals(veiculo.isVendido(), result.isVendido());
    }
}
