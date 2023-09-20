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
import pizzaria.pizzaria.entity.ClienteEntity;
import pizzaria.pizzaria.repository.ClienteRepository;
import pizzaria.pizzaria.service.ClienteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClienteTest {
    @InjectMocks
    private ClienteService service;

    @Mock
    private ClienteService serviceTest;

    @Mock
    private ClienteRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private ClienteEntity cliente;
    private ClienteDTO clienteDTO;

    @BeforeEach
    public void setup(){
        modelMapper = new ModelMapper();
        cliente = new ClienteEntity();
        cliente.setNome("Kaue");
        cliente.setCpf("826.535.545-93");
        clienteDTO = new ClienteDTO("Kaue", "826.535.545-93", null);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        List<ClienteEntity> listEntity = new ArrayList<>();
        listEntity.add(cliente);

        when(repository.findAll()).thenReturn(listEntity);

        List<ClienteDTO> resultList = service.getAll();
        Assertions.assertEquals(1, resultList.size());
    }

    @Test
    void getId() {
        Long id = 1L;
        ClienteDTO cliente1 = new ClienteDTO();
        cliente1.setId(1L);
        cliente1.setNome("Kaue");

        when(serviceTest.getId(anyLong())).thenReturn(cliente1);
        ClienteDTO result = serviceTest.getId(id);
        assertNotNull(result);
        Assertions.assertEquals(cliente.getNome(), result.getNome());
    }

    @Test
    void testCreateException() {
        clienteDTO.setId(1L);

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.create(clienteDTO));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testUpdateException() {
        Long id = 2L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.update(id, clienteDTO));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}
