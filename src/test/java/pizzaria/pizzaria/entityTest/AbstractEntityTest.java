//package pizzaria.pizzaria.entityTest;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import pizzaria.pizzaria.entity.AbstractEntity;
//
//import java.time.LocalDateTime;
//
//@SpringBootTest
//class AbstractEntityTest {
//    AbstractEntity anAbstract = new AbstractEntity();
//
//    @BeforeEach
//    public void setup(){
//        initClass();
//    }
//
//    @Test
//    void setterTest(){
//        Assertions.assertTrue(anAbstract.isAtivo());
//        Assertions.assertEquals(LocalDateTime.class, anAbstract.getCadastro().getClass());
//    }
//
//    private void initClass() {
//        anAbstract.setAtivo(true);
//        anAbstract.setCadastro(LocalDateTime.now());
//        anAbstract.setId(1L);
//        anAbstract.setAtualizacao(null);
//    }
//}
