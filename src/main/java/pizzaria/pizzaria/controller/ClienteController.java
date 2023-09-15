package pizzaria.pizzaria.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.dto.ClienteDTO;
import pizzaria.pizzaria.entity.ClienteEntity;
import pizzaria.pizzaria.repository.ClienteRepository;
import pizzaria.pizzaria.service.ClienteService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService service;
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public ResponseEntity<List<ClienteDTO>> list() {
        try {
            List<ClienteDTO> listDTO = new ArrayList<>();
            for (ClienteEntity entity : repository.findAll()) {
                ClienteDTO map = modelMapper.map(entity, ClienteDTO.class);
                listDTO.add(map);
            }
            return new ResponseEntity<>(listDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<ClienteDTO> getIdByRequest(@RequestParam("id") final Long id) {
        final ClienteEntity entity = this.repository.findById(id).orElse(null);
        if (entity == null) ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possivel encontrar o registro");
        return ResponseEntity.ok(modelMapper.map(entity, ClienteDTO.class));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody @Validated ClienteDTO dto) {
        try {
            return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateByPath(@PathVariable("id") final Long id, @RequestBody final @Validated ClienteDTO dto) {
        try {
            return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") final Long id) {
        try {
            ClienteEntity entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));
            repository.delete(entity);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
        }
    }
}
