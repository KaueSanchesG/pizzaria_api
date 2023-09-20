package pizzaria.pizzaria.controller;

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

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService service;
    @Autowired
    private ClienteRepository repository;

    @GetMapping("/list")
    public ResponseEntity<List<ClienteDTO>> list() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ClienteDTO> getIdByRequest(@RequestParam("id") final Long id) {
        return new ResponseEntity<>(service.getId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody @Validated ClienteDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateByPath(@PathVariable("id") final Long id, @RequestBody final @Validated ClienteDTO dto) {
        return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
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
