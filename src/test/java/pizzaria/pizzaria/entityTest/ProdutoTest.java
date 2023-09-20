package pizzaria.pizzaria.entityTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pizzaria.pizzaria.entity.PedidoEntity;
import pizzaria.pizzaria.entity.ProdutoEntity;
import pizzaria.pizzaria.entity.SaborEntity;

import java.util.List;

@SpringBootTest
class ProdutoTest {
    ProdutoEntity produto = new ProdutoEntity();

    @BeforeEach
    public void setup() {
        initClass();
    }

    @Test
    void setterTest() {
        Assertions.assertEquals(2, produto.getPedidos().size() + produto.getPizzaSabor().size());
    }

    private void initClass() {
        produto.setNome("Pizza");
        produto.setValor(12.0);
        produto.setPedidos(List.of(new PedidoEntity()));
        produto.setPizzaSabor(List.of(new SaborEntity()));
    }
}
