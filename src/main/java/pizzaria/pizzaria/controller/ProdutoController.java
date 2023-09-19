package pizzaria.pizzaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.dto.ProdutoDTO;
import pizzaria.pizzaria.entity.ProdutoEntity;
import pizzaria.pizzaria.repository.ProdutoRepository;
import pizzaria.pizzaria.service.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService service;
    @Autowired
    private ProdutoRepository repository;

    @GetMapping("/list")
    public ResponseEntity<List<ProdutoDTO>> list() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ProdutoDTO> getIdByRequest(@RequestParam("id") final long id) {
        return new ResponseEntity<>(service.getId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> create(@RequestBody @Validated ProdutoDTO dto) {
        try {
            return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> updateByPath(@PathVariable("id") final Long id, @RequestBody final @Validated ProdutoDTO dto) {
        try {
            return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") final Long id) {
        try {
            ProdutoEntity entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));
            repository.delete(entity);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
        }
    }
}
