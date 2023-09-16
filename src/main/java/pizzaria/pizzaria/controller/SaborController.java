package pizzaria.pizzaria.controller;

import org.modelmapper.ModelMapper;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sabor")
public class SaborController {
    @Autowired
    private SaborService service;
    @Autowired
    private SaborRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public ResponseEntity<List<SaborDTO>> list() {
        try {
            List<SaborDTO> listDTO = new ArrayList<>();
            for (SaborEntity entity : repository.findAll()) {
                SaborDTO map = modelMapper.map(entity, SaborDTO.class);
                listDTO.add(map);
            }
            return new ResponseEntity<>(listDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<SaborDTO> getIdByRequest(@RequestParam("id") final long id) {
        final SaborEntity entity = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));
        return ResponseEntity.ok(modelMapper.map(entity, SaborDTO.class));
    }

    @PostMapping
    public ResponseEntity<SaborDTO> create(@RequestBody @Validated SaborDTO dto) {
        try {
            return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaborDTO> updateByPath(@PathVariable("id") final Long id, @RequestBody final @Validated SaborDTO dto) {
        try {
            return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
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
