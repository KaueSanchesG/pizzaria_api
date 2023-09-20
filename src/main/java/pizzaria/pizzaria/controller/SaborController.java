package pizzaria.pizzaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.dto.SaborDTO;
import pizzaria.pizzaria.entity.SaborEntity;
import pizzaria.pizzaria.repository.SaborRepository;
import pizzaria.pizzaria.service.SaborService;

import java.util.List;

@RestController
@RequestMapping("/sabor")
public class SaborController {
    @Autowired
    private SaborService service;
    @Autowired
    private SaborRepository repository;

    @GetMapping("/list")
    public ResponseEntity<List<SaborDTO>> list() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SaborDTO> getIdByRequest(@RequestParam("id") final long id) {
        return new ResponseEntity<>(service.getId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SaborDTO> create(@RequestBody @Validated SaborDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaborDTO> updateByPath(@PathVariable("id") final Long id, @RequestBody final @Validated SaborDTO dto) {
        return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") final Long id) {
        try {
            SaborEntity entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));
            repository.delete(entity);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
        }
    }
}
