package pizzaria.pizzaria.serviceTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.dto.ClienteDTO;
import pizzaria.pizzaria.dto.FuncionarioDTO;
import pizzaria.pizzaria.dto.PedidoDTO;
import pizzaria.pizzaria.dto.ProdutoDTO;
import pizzaria.pizzaria.entity.ClienteEntity;
import pizzaria.pizzaria.entity.FuncionarioEntity;
import pizzaria.pizzaria.entity.PedidoEntity;
import pizzaria.pizzaria.entity.ProdutoEntity;
import pizzaria.pizzaria.repository.PedidoRepository;
import pizzaria.pizzaria.service.PedidoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class PedidoServiceTest {
    @InjectMocks
    private PedidoService service;

    @Mock
    private PedidoService serviceTest;

    @Mock
    private PedidoRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private PedidoEntity pedido;
    private PedidoDTO pedidoDTO;

    @BeforeEach
    public void setup() {
        startClass();
        modelMapper = new ModelMapper();
        pedidoDTO = new PedidoDTO(new ClienteDTO("Kaue", "826.535.545-93", null), null, true, 12.0, null, new FuncionarioDTO("Gusta"));
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        List<PedidoEntity> listEntity = new ArrayList<>();
        listEntity.add(pedido);

        when(repository.findAll()).thenReturn(listEntity);

        List<PedidoDTO> resultList = service.getAll();
        Assertions.assertEquals(1, resultList.size());
    }

    @Test
    void getId() {
        Long id = 1L;
        PedidoDTO pedidoNew = new PedidoDTO(new ClienteDTO("Kaue", "826.535.545-93", null), null, true, 12.0, null, new FuncionarioDTO("Gusta"));
        pedidoNew.setId(1L);

        when(serviceTest.getId(anyLong())).thenReturn(pedidoNew);
        PedidoDTO result = serviceTest.getId(id);
        assertNotNull(result);
        Assertions.assertEquals(pedido.getCliente().getNome(), result.getCliente().getNome());
    }

    @Test
    void testCreateException() {
        pedidoDTO.setId(1L);
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.create(pedidoDTO));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testUpdateException() {
        Long id = 2L;

        when(repository.findById(id)).thenReturn(Optional.empty());
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.update(id, pedidoDTO));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testCalcValorTotal() {
        PedidoDTO pedido = new PedidoDTO();
        ProdutoDTO produto1 = new ProdutoDTO("Pizza Margherita", 30.0, null);
        ProdutoDTO produto2 = new ProdutoDTO("Pizza Pepperoni", 35.0, null);
        List<ProdutoDTO> produtos = new ArrayList<>();
        produtos.add(produto1);
        produtos.add(produto2);
        pedido.setProdutos(produtos);

        Double total = service.calcValorTotal(pedido);

        double result = 30.0 + 35.0;
        Assertions.assertEquals(result, total, 0.01);
    }

    public void startClass() {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setNome("Kaue");
        cliente.setCpf("826.535.545-93");

        ProdutoEntity produto = new ProdutoEntity();
        produto.setNome("Pizza");
        produto.setValor(12.0);

        List<ProdutoEntity> produtoList = new ArrayList<>();
        produtoList.add(produto);

        FuncionarioEntity funcionario = new FuncionarioEntity();
        funcionario.setNome("Gusta");

        pedido = new PedidoEntity();
        pedido.setCliente(cliente);
        pedido.setEntrega(true);
        pedido.setProdutos(produtoList);
        pedido.setFuncionario(funcionario);
    }
}
