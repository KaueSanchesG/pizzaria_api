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
import pizzaria.pizzaria.dto.EnderecoDTO;
import pizzaria.pizzaria.entity.EnderecoEntity;
import pizzaria.pizzaria.repository.EnderecoRepository;
import pizzaria.pizzaria.service.EnderecoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class EnderecoServiceTest {
    @InjectMocks
    private EnderecoService service;

    @Mock
    private EnderecoService serviceTest;

    @Mock
    private EnderecoRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private EnderecoEntity endereco;
    private EnderecoDTO enderecoDTO;

    @BeforeEach
    public void setup() {
        modelMapper = new ModelMapper();
        endereco = new EnderecoEntity();
        endereco.setRua("Marcolina");
        endereco.setNumero(12);
        enderecoDTO = new EnderecoDTO("Marcolina", 12, new ClienteDTO("Kaue", "826.535.545-93", null));
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        List<EnderecoEntity> listEntity = new ArrayList<>();
        listEntity.add(endereco);

        when(repository.findAll()).thenReturn(listEntity);

        List<EnderecoDTO> resultList = service.getAll();
        Assertions.assertEquals(1, resultList.size());
    }

    @Test
    void getId() {
        Long id = 1L;
        EnderecoDTO enderecoNew = new EnderecoDTO();
        enderecoNew.setId(1L);
        enderecoNew.setRua("Marcolina");
        enderecoNew.setNumero(12);

        when(serviceTest.getId(anyLong())).thenReturn(enderecoNew);
        EnderecoDTO result = serviceTest.getId(id);
        assertNotNull(result);
        Assertions.assertEquals(endereco.getRua(), result.getRua());
    }

    @Test
    void testCreateException() {
        enderecoDTO.setId(1L);
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.create(enderecoDTO));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testUpdateException() {
        Long id = 2L;

        when(repository.findById(id)).thenReturn(Optional.empty());
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.update(id, enderecoDTO));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}
