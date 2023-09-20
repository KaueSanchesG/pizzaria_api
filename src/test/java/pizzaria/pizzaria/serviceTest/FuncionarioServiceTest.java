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
import pizzaria.pizzaria.dto.FuncionarioDTO;
import pizzaria.pizzaria.entity.FuncionarioEntity;
import pizzaria.pizzaria.repository.FuncionarioRepository;
import pizzaria.pizzaria.service.FuncionarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class FuncionarioServiceTest {
    @InjectMocks
    private FuncionarioService service;

    @Mock
    private FuncionarioService serviceTest;

    @Mock
    private FuncionarioRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private FuncionarioEntity funcionario;
    private FuncionarioDTO funcionarioDTO;

    @BeforeEach
    public void setup() {
        modelMapper = new ModelMapper();
        funcionario = new FuncionarioEntity();
        funcionario.setNome("Kaue");
        funcionarioDTO = new FuncionarioDTO("Kaue");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        List<FuncionarioEntity> listEntity = new ArrayList<>();
        listEntity.add(funcionario);

        when(repository.findAll()).thenReturn(listEntity);

        List<FuncionarioDTO> resultList = service.getAll();
        Assertions.assertEquals(1, resultList.size());
    }

    @Test
    void getId() {
        Long id = 1L;
        FuncionarioDTO funcionarioNew = new FuncionarioDTO();
        funcionarioNew.setId(1L);
        funcionarioNew.setNome("Kaue");

        when(serviceTest.getId(anyLong())).thenReturn(funcionarioNew);
        FuncionarioDTO result = serviceTest.getId(id);
        assertNotNull(result);
        Assertions.assertEquals(funcionario.getNome(), result.getNome());
    }

    @Test
    void testCreateException() {
        funcionarioDTO.setId(1L);
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.create(funcionarioDTO));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testUpdateException() {
        Long id = 2L;

        when(repository.findById(id)).thenReturn(Optional.empty());
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.update(id, funcionarioDTO));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}
