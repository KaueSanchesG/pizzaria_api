package pizzaria.pizzaria.controller;

import org.modelmapper.ModelMapper;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;
    @Autowired
    private FuncionarioRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public ResponseEntity<List<FuncionarioDTO>> list() {
        try {
            List<FuncionarioDTO> listDTO = new ArrayList<>();
            for (FuncionarioEntity entity : repository.findAll()) {
                FuncionarioDTO map = modelMapper.map(entity, FuncionarioDTO.class);
                listDTO.add(map);
            }
            return new ResponseEntity<>(listDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<FuncionarioDTO> getIdByRequest(@RequestParam("id") final Long id) {
        final FuncionarioEntity entity = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possivel encontrar o registro"));
        return ResponseEntity.ok(modelMapper.map(entity, FuncionarioDTO.class));
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> create(@RequestBody @Validated FuncionarioDTO dto) {
        try {
            return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> updateByPath(@PathVariable("id") final Long id, @RequestBody final @Validated FuncionarioDTO dto) {
        try {
            return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
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
