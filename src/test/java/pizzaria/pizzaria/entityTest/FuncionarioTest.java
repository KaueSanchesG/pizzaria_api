package pizzaria.pizzaria.entityTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pizzaria.pizzaria.entity.FuncionarioEntity;
import pizzaria.pizzaria.entity.PedidoEntity;

import java.util.List;

@SpringBootTest
class FuncionarioTest {
    FuncionarioEntity funcionario = new FuncionarioEntity();

    @BeforeEach
    public void setup() {
        initClass();
    }

    @Test
    void setterTest() {
        Assertions.assertEquals(1, funcionario.getPedidos().size());
    }

    private void initClass() {
        PedidoEntity pedido = new PedidoEntity();
        funcionario.setNome("Pedro");
        funcionario.setPedidos(List.of(pedido));
    }
}
