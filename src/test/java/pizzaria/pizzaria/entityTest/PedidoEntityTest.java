//package pizzaria.pizzaria.entityTest;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import pizzaria.pizzaria.entity.ClienteEntity;
//import pizzaria.pizzaria.entity.FuncionarioEntity;
//import pizzaria.pizzaria.entity.PedidoEntity;
//import pizzaria.pizzaria.entity.ProdutoEntity;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@SpringBootTest
//class PedidoEntityTest {
//    PedidoEntity pedido = new PedidoEntity();
//
//    @BeforeEach
//    public void setup(){
//        initClass();
//    }
//
//    @Test
//    void setterTest(){
//        Assertions.assertEquals("Kaue", pedido.getCliente().getNome());
//        Assertions.assertEquals("Pedro", pedido.getFuncionario().getNome());
//        Assertions.assertEquals(1, pedido.getProdutos().size());
//    }
//
//    private void initClass() {
//        ClienteEntity cliente = new ClienteEntity();
//        cliente.setNome("Kaue");
//        FuncionarioEntity funcionario = new FuncionarioEntity();
//        funcionario.setNome("Pedro");
//        pedido.setCliente(cliente);
//        pedido.setEntrega(true);
//        pedido.setValorTotal(12.0);
//        pedido.setDataHora(LocalDateTime.now());
//        pedido.setFuncionario(funcionario);
//        pedido.setProdutos(List.of(new ProdutoEntity()));
//    }
//}
