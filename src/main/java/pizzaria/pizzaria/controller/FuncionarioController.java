package pizzaria.pizzaria.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.dto.FuncionarioDTO;
import pizzaria.pizzaria.entity.FuncionarioEntity;
import pizzaria.pizzaria.service.FuncionarioService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public ResponseEntity<List<FuncionarioDTO>> list() {
        List<FuncionarioDTO> array = new ArrayList<>();
        for (FuncionarioEntity entity : service.getAll()) {
            FuncionarioDTO map = modelMapper.map(entity, FuncionarioDTO.class);
            array.add(map);
        }
        return new ResponseEntity<>(array, HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<FuncionarioDTO> getFuncionarioByNome(@PathVariable("nome") String nome){
        return new ResponseEntity<>(modelMapper.map(service.getFuncionarioByNome(nome), FuncionarioDTO.class), HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<FuncionarioDTO> getIdByRequest(@RequestParam("id") final Long id) {
        return new ResponseEntity<>(modelMapper.map(service.getId(id), FuncionarioDTO.class), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FuncionarioDTO> create(@RequestBody @Validated FuncionarioDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, FuncionarioEntity.class)), FuncionarioDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> updateByPath(@PathVariable("id") final Long id,
                                                       @RequestBody final @Validated FuncionarioDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.update(id, modelMapper.map(dto, FuncionarioEntity.class)), FuncionarioDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") final Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
        }
    }
}
