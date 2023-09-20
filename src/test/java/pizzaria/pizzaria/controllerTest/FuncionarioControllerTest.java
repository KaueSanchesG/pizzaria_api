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
import pizzaria.pizzaria.dto.FuncionarioDTO;
import pizzaria.pizzaria.service.FuncionarioService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FuncionarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FuncionarioService service;

    private ObjectMapper objectMapper;
    private FuncionarioDTO funcionarioValido;
    private FuncionarioDTO funcionarioInvalido;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        funcionarioValido = new FuncionarioDTO("Kaue");
        funcionarioInvalido = new FuncionarioDTO("");
    }

    @Test
    void testListFuncionarios() throws Exception {
        List<FuncionarioDTO> funcionarioDTOList = new ArrayList<>();
        funcionarioDTOList.add(funcionarioValido);

        when(service.getAll()).thenReturn(funcionarioDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/funcionario/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nome").value("Kaue"));
    }

    @Test
    void testGetFuncionarioById() throws Exception {
        Long funcionarioId = 1L;

        when(service.getId(funcionarioId)).thenReturn(funcionarioValido);

        mockMvc.perform(MockMvcRequestBuilders.get("/funcionario?id=" + funcionarioId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Kaue"));
    }

    @Test
    void testCreateFuncionario() throws Exception {
        when(service.create(any(FuncionarioDTO.class))).thenReturn(funcionarioValido);

        mockMvc.perform(MockMvcRequestBuilders.post("/funcionario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionarioValido)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Kaue"))
                .andReturn();

        when(service.create(any(FuncionarioDTO.class)))
                .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Test message"));

        mockMvc.perform(MockMvcRequestBuilders.post("/funcionario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionarioInvalido)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        when(service.create(any(FuncionarioDTO.class)))
                .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Algo deu errado"));

        mockMvc.perform(MockMvcRequestBuilders.post("/funcionario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionarioValido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateFuncionario() throws Exception {
        Long funcionarioId = 1L;
        FuncionarioDTO funcionarioUpdated = new FuncionarioDTO("Leo");

        when(service.update(funcionarioId, funcionarioUpdated)).thenReturn(funcionarioUpdated);

        mockMvc.perform(MockMvcRequestBuilders.put("/funcionario/{id}", funcionarioId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionarioUpdated)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteFuncionario() throws Exception {
        Long funcionarioId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/funcionario/{id}", funcionarioId))
                .andExpect(status().is(405));
    }
}
