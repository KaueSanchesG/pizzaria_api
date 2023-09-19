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
import pizzaria.pizzaria.dto.ClienteDTO;
import pizzaria.pizzaria.dto.EnderecoDTO;
import pizzaria.pizzaria.service.EnderecoService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EnderecoTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnderecoService service;

    private ObjectMapper objectMapper;
    private EnderecoDTO enderecoValido;
    private EnderecoDTO enderecoInvalido;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        ClienteDTO cliente = new ClienteDTO("kaue", "826.535.545-93", null);
        enderecoValido = new EnderecoDTO("Marcolina123", 12, cliente);
        enderecoInvalido = new EnderecoDTO("", 0, cliente);
    }


    @Test
    void testList() throws Exception {
        List<EnderecoDTO> enderecoDTOList = new ArrayList<>();
        enderecoDTOList.add(enderecoValido);
        when(service.getAll()).thenReturn(enderecoDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/endereco/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].rua").value("Marcolina123"));
    }

    @Test
    void testGetById() throws Exception {
        Long id = 1L;

        when(service.getId(id)).thenReturn(enderecoValido);

        mockMvc.perform(MockMvcRequestBuilders.get("/endereco?id=" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.rua").value("Marcolina123"));
    }

    @Test
    void testCreate() throws Exception {
        when(service.create(any(EnderecoDTO.class))).thenReturn(enderecoValido);

        mockMvc.perform(MockMvcRequestBuilders.post("/endereco")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(enderecoValido)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.rua").value("Marcolina123"))
                .andReturn();

        when(service.create(any(EnderecoDTO.class)))
                .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Test message"));

        mockMvc.perform(MockMvcRequestBuilders.post("/endereco")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(enderecoInvalido)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        when(service.create(any(EnderecoDTO.class)))
                .thenThrow(new RuntimeException("Algo deu errado"));

        mockMvc.perform(MockMvcRequestBuilders.post("/endereco")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(enderecoInvalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdate() throws Exception {
        Long id = 1L;
        EnderecoDTO enderecoDTO = new EnderecoDTO("Marcolina1234", 12, new ClienteDTO("kaue", "826.535.545-93", null));

        when(service.update(id, enderecoDTO)).thenReturn(enderecoDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/endereco/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(enderecoDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/endereco/{id}", id))
                .andExpect(status().is(405));
    }
}
