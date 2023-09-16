package pizzaria.pizzaria.controller;

import org.modelmapper.ModelMapper;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService service;
    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public ResponseEntity<List<ProdutoDTO>> list() {
        try {
            List<ProdutoDTO> listDTO = new ArrayList<>();
            for (ProdutoEntity entity : repository.findAll()) {
                ProdutoDTO map = modelMapper.map(entity, ProdutoDTO.class);
                listDTO.add(map);
            }
            return new ResponseEntity<>(listDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<ProdutoDTO> getIdByRequest(@RequestParam("id") final long id) {
        final ProdutoEntity entity = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));
        return ResponseEntity.ok(modelMapper.map(entity, ProdutoDTO.class));
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
