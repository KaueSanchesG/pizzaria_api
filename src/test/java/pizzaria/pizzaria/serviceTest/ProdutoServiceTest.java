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
import pizzaria.pizzaria.dto.ProdutoDTO;
import pizzaria.pizzaria.entity.ProdutoEntity;
import pizzaria.pizzaria.repository.ProdutoRepository;
import pizzaria.pizzaria.service.ProdutoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProdutoServiceTest {
    @InjectMocks
    private ProdutoService service;

    @Mock
    private ProdutoService serviceTest;

    @Mock
    private ProdutoRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private ProdutoEntity produto;
    private ProdutoDTO produtoDTO;

    @BeforeEach
    public void setup(){
        modelMapper = new ModelMapper();
        produto = new ProdutoEntity();
        produto.setNome("Pizza");
        produto.setValor(12.0);
        produtoDTO = new ProdutoDTO("Pizza", 12.0, null);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        List<ProdutoEntity> listEntity = new ArrayList<>();
        listEntity.add(produto);

        when(repository.findAll()).thenReturn(listEntity);

        List<ProdutoDTO> resultList = service.getAll();
        Assertions.assertEquals(1, resultList.size());
    }

    @Test
    void getId() {
        Long id = 1L;
        ProdutoDTO produtoNew = new ProdutoDTO();
        produtoNew.setId(1L);
        produtoNew.setNome("Pizza");
        produtoNew.setValor(12.0);

        when(serviceTest.getId(anyLong())).thenReturn(produtoNew);
        ProdutoDTO result = serviceTest.getId(id);
        assertNotNull(result);
        Assertions.assertEquals(produto.getNome(), result.getNome());
    }

    @Test
    void testCreateException() {
        produtoDTO.setId(1L);

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.create(produtoDTO));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testUpdateException() {
        Long id = 2L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.update(id, produtoDTO));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}
