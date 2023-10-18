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
//import pizzaria.pizzaria.dto.ClienteDTO;
//import pizzaria.pizzaria.dto.FuncionarioDTO;
//import pizzaria.pizzaria.dto.PedidoDTO;
//import pizzaria.pizzaria.dto.ProdutoDTO;
//import pizzaria.pizzaria.service.PedidoService;
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
//class PedidoControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private PedidoService service;
//
//    private ObjectMapper objectMapper;
//    private PedidoDTO pedidoValido;
//    private PedidoDTO pedidoInvalido;
//
//    @BeforeEach
//    public void setup() {
//        objectMapper = new ObjectMapper();
//
//        ProdutoDTO produto = new ProdutoDTO("Banana", 12.0, null);
//        FuncionarioDTO funcionario = new FuncionarioDTO("Funcionario1");
//        ClienteDTO cliente = new ClienteDTO("Kaue", "361.261.706-01", null);
//
//        pedidoValido = new PedidoDTO(cliente, List.of(produto), true, 0.0, null, funcionario);
//        pedidoInvalido = new PedidoDTO(cliente, null, true, 0.0, null, funcionario);
//    }
//
//    @Test
//    void testList() throws Exception {
//        List<PedidoDTO> pedidoDTOList = new ArrayList<>();
//        pedidoDTOList.add(pedidoValido);
//        when(service.getAll()).thenReturn(pedidoDTOList);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/pedido/list"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].cliente.nome").value("Kaue"));
//    }
//
//    @Test
//    void testGetById() throws Exception {
//        Long id = 1L;
//
//        when(service.getId(id)).thenReturn(pedidoValido);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/pedido?id=" + id))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.cliente.nome").value("Kaue"));
//    }
//
//    @Test
//    void testCreate() throws Exception {
//        when(service.create(any(PedidoDTO.class))).thenReturn(pedidoValido);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/pedido")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(pedidoValido)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.cliente.nome").value("Kaue"))
//                .andReturn();
//
//        when(service.create(any(PedidoDTO.class)))
//                .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Test message"));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/pedido")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(pedidoInvalido)))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
//        when(service.create(any(PedidoDTO.class)))
//                .thenThrow(new RuntimeException("Algo deu errado"));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/pedido")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(pedidoInvalido)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void testUpdate() throws Exception {
//        Long id = 1L;
//
//        ProdutoDTO produtoDTO = new ProdutoDTO("Banana", 12.0, null);
//        FuncionarioDTO funcionarioDTO = new FuncionarioDTO("Funcionario1");
//        ClienteDTO clienteDTO = new ClienteDTO("Kaue", "361.261.706-01", null);
//
//        PedidoDTO pedidoDTO = new PedidoDTO(clienteDTO, List.of(produtoDTO), true, 1.0, null, funcionarioDTO);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/pedido/{id}", id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(pedidoDTO)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testDelete() throws Exception {
//        Long id = 1L;
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/pedido/{id}", id))
//                .andExpect(status().is(405));
//    }
//
//}
