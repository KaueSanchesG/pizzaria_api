package pizzaria.pizzaria.entityTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pizzaria.pizzaria.entity.ProdutoEntity;
import pizzaria.pizzaria.entity.SaborEntity;

import java.util.List;

@SpringBootTest
class SaborTest {
    private SaborEntity sabor;
    @BeforeEach
    public void setup(){
        initClass();
    }

    @Test
    void setterTest(){
        Assertions.assertEquals(1, sabor.getProdutos().size());
    }

    public void initClass(){
        ProdutoEntity produto = new ProdutoEntity();
        produto.setNome("Pizza");
        produto.setValor(12.0);
        sabor = new SaborEntity();
        sabor.setNome("Peperoni");
        sabor.setProdutos(List.of(produto));
    }
}
