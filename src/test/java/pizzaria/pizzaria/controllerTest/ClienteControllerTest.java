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
import pizzaria.pizzaria.service.ClienteService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService service;

    private ObjectMapper objectMapper;
    private ClienteDTO clienteValido;
    private ClienteDTO clienteInvalido;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        clienteValido = new ClienteDTO("Gusta", "826.535.545-93", null);
        clienteInvalido = new ClienteDTO("", "826.535.545-93", null);
    }

    @Test
    void testList() throws Exception {
        List<ClienteDTO> clienteDTOList = new ArrayList<>();
        clienteDTOList.add(clienteValido);
        when(service.getAll()).thenReturn(clienteDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nome").value("Gusta"));
    }

    @Test
    void testGetById() throws Exception {
        Long id = 1L;

        when(service.getId(id)).thenReturn(clienteValido);

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente?id=" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Gusta"));
    }

    @Test
    void testCreate() throws Exception {
        when(service.create(any(ClienteDTO.class))).thenReturn(clienteValido);

        mockMvc.perform(MockMvcRequestBuilders.post("/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteValido)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Gusta"))
                .andReturn();

        when(service.create(any(ClienteDTO.class)))
                .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Test message"));

        mockMvc.perform(MockMvcRequestBuilders.post("/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteInvalido)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        when(service.create(any(ClienteDTO.class)))
                .thenThrow(new RuntimeException("Algo deu errado"));

        mockMvc.perform(MockMvcRequestBuilders.post("/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteInvalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdate() throws Exception {
        Long id = 1L;
        ClienteDTO clienteDTO = new ClienteDTO("Gusta1", "826.535.545-93", null);

        when(service.update(id, clienteDTO)).thenReturn(clienteDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/cliente/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/cliente/{id}", id))
                .andExpect(status().is(405));
    }
}
