//package pizzaria.pizzaria.serviceTest;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.server.ResponseStatusException;
//import pizzaria.pizzaria.dto.SaborDTO;
//import pizzaria.pizzaria.entity.SaborEntity;
//import pizzaria.pizzaria.repository.SaborRepository;
//import pizzaria.pizzaria.service.SaborService;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class SaborServiceTest {
//    @InjectMocks
//    private SaborService service;
//
//    @Mock
//    private SaborService serviceTest;
//
//    @Mock
//    private SaborRepository repository;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    private SaborEntity sabor;
//    private SaborDTO saborDTO;
//
//    @BeforeEach
//    public void setup() {
//        modelMapper = new ModelMapper();
//        sabor = new SaborEntity();
//        sabor.setNome("Peperoni");
//        saborDTO = new SaborDTO("Peperoni");
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testList() {
//        List<SaborEntity> listEntity = new ArrayList<>();
//        listEntity.add(sabor);
//
//        when(repository.findAll()).thenReturn(listEntity);
//
//        List<SaborDTO> resultList = service.getAll();
//        assertEquals(1, resultList.size());
//    }
//
//    @Test
//    void getId() {
//        Long id = 1L;
//        SaborDTO saborNew = new SaborDTO("Peperoni");
//        saborNew.setId(1L);
//
//        when(serviceTest.getId(anyLong())).thenReturn(saborNew);
//        SaborDTO result = serviceTest.getId(id);
//        assertNotNull(result);
//        assertEquals(sabor.getNome(), result.getNome());
//    }
//
//    @Test
//    void testCreateException() {
//        saborDTO.setId(1L);
//        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.create(saborDTO));
//        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
//    }
//
//    @Test
//    void testUpdateException() {
//        Long id = 2L;
//
//        when(repository.findById(id)).thenReturn(Optional.empty());
//        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> service.update(id, saborDTO));
//        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
//    }
//}
