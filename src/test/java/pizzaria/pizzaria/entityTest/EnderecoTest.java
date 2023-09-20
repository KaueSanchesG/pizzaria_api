package pizzaria.pizzaria.entityTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pizzaria.pizzaria.entity.ClienteEntity;
import pizzaria.pizzaria.entity.EnderecoEntity;

@SpringBootTest
class EnderecoTest {
    EnderecoEntity endereco = new EnderecoEntity();

    @BeforeEach
    public void setup(){
        initClass();
    }

    @Test
    void setterTest(){
        Assertions.assertEquals("Kaue", endereco.getCliente().getNome());
    }

    private void initClass() {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setNome("Kaue");
        endereco.setRua("Marcolina");
        endereco.setNumero(12);
        endereco.setCliente(cliente);
    }
}
