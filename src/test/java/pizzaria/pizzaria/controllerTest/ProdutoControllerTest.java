package pizzaria.pizzaria.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.dto.ProdutoDTO;
import pizzaria.pizzaria.service.ProdutoService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProdutoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService service;

    private ObjectMapper objectMapper;
    private ProdutoDTO produtoValido;
    private ProdutoDTO produtoInvalido;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        produtoValido = new ProdutoDTO("Pizza", 12.0, null);
        produtoInvalido = new ProdutoDTO("", 0.0, null);
    }


    @Test
    void testList() throws Exception {
        List<ProdutoDTO> produtoDTOList = new ArrayList<>();
        produtoDTOList.add(produtoValido);
        when(service.getAll()).thenReturn(produtoDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/produto/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nome").value("Pizza"));
    }

    @Test
    void testGetById() throws Exception {
        Long id = 1L;

        when(service.getId(id)).thenReturn(produtoValido);

        mockMvc.perform(MockMvcRequestBuilders.get("/produto?id=" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Pizza"));
    }

    @Test
    void testCreate() throws Exception {
        when(service.create(any(ProdutoDTO.class))).thenReturn(produtoValido);

        mockMvc.perform(MockMvcRequestBuilders.post("/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoValido)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Pizza"))
                .andReturn();

        when(service.create(any(ProdutoDTO.class)))
                .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Test message"));

        mockMvc.perform(MockMvcRequestBuilders.post("/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoInvalido)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        when(service.create(any(ProdutoDTO.class)))
                .thenThrow(new RuntimeException("Algo deu errado"));

        mockMvc.perform(MockMvcRequestBuilders.post("/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoInvalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdate() throws Exception {
        Long id = 1L;
        ProdutoDTO produtoDTO = new ProdutoDTO("Pizza2", 12.5,null);

        when(service.update(id, produtoDTO)).thenReturn(produtoDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/produto/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/produto/{id}", id))
                .andExpect(status().is(405));
    }
}