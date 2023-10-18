//package pizzaria.pizzaria.entityTest;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import pizzaria.pizzaria.entity.ClienteEntity;
//import pizzaria.pizzaria.entity.EnderecoEntity;
//import pizzaria.pizzaria.entity.PedidoEntity;
//
//import java.util.List;
//
//@SpringBootTest
//class ClienteEntityTest {
//    ClienteEntity cliente = new ClienteEntity();
//
//    @BeforeEach
//    public void setup() {
//        initClass();
//    }
//
//    @Test
//    void setterTest() {
//        Assertions.assertEquals(2, cliente.getEnderecos().size() + cliente.getPedidos().size());
//    }
//
//    private void initClass() {
//        cliente.setNome("Kaue");
//        cliente.setCpf("303.453.645-38");
//        cliente.setEnderecos(List.of(new EnderecoEntity()));
//        cliente.setPedidos(List.of(new PedidoEntity()));
//    }
//}
