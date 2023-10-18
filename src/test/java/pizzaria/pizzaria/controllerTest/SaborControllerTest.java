//package pizzaria.pizzaria.controllerTest;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.web.server.ResponseStatusException;
//import pizzaria.pizzaria.dto.SaborDTO;
//import pizzaria.pizzaria.service.SaborService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class SaborControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private SaborService service;
//
//    private ObjectMapper objectMapper;
//    private SaborDTO saborValido;
//    private SaborDTO saborInvalido;
//
//    @BeforeEach
//    public void setup() {
//        objectMapper = new ObjectMapper();
//        saborValido = new SaborDTO("Peperoni");
//        saborInvalido = new SaborDTO("");
//    }
//
//    @Test
//    void testList() throws Exception {
//        List<SaborDTO> saborDTOList = new ArrayList<>();
//        saborDTOList.add(saborValido);
//        when(service.getAll()).thenReturn(saborDTOList);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/sabor/list"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].nome").value("Peperoni"));
//    }
//
//    @Test
//    void testGetFuncionarioById() throws Exception {
//        Long id = 1L;
//
//        when(service.getId(id)).thenReturn(saborValido);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/sabor?id=" + id))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.nome").value("Peperoni"));
//    }
//
//    @Test
//    void testCreateFuncionario() throws Exception {
//        when(service.create(any(SaborDTO.class))).thenReturn(saborValido);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/sabor")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(saborValido)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.nome").value("Peperoni"))
//                .andReturn();
//
//        when(service.create(any(SaborDTO.class)))
//                .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Test message"));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/sabor")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(saborInvalido)))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
//        when(service.create(any(SaborDTO.class)))
//                .thenThrow(new RuntimeException("Algo deu errado"));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/sabor")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(saborInvalido)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void testUpdateFuncionario() throws Exception {
//        Long id = 1L;
//        SaborDTO saborDTO = new SaborDTO("Calabresa");
//
//        when(service.update(id, saborDTO)).thenReturn(saborDTO);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/sabor/{id}", id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(saborDTO)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testDeleteFuncionario() throws Exception {
//        Long id = 1L;
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/sabor/{id}", id))
//                .andExpect(status().is(405));
//    }
//}
