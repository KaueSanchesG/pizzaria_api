package pizzaria.pizzaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.dto.FuncionarioDTO;
import pizzaria.pizzaria.entity.FuncionarioEntity;
import pizzaria.pizzaria.repository.FuncionarioRepository;
import pizzaria.pizzaria.service.FuncionarioService;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;
    @Autowired
    private FuncionarioRepository repository;

    @GetMapping("/list")
    public ResponseEntity<List<FuncionarioDTO>> list() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<FuncionarioDTO> getIdByRequest(@RequestParam("id") final Long id) {
        return new ResponseEntity<>(service.getId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> create(@RequestBody @Validated FuncionarioDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> updateByPath(@PathVariable("id") final Long id, @RequestBody final @Validated FuncionarioDTO dto) {
        return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") final Long id) {
        try {
            FuncionarioEntity entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));
            repository.delete(entity);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
        }
    }
}
