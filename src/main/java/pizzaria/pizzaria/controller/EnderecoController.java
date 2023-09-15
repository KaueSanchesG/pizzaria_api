package pizzaria.pizzaria.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.dto.EnderecoDTO;
import pizzaria.pizzaria.entity.EnderecoEntity;
import pizzaria.pizzaria.repository.EnderecoRepository;
import pizzaria.pizzaria.service.EnderecoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService service;
    @Autowired
    private EnderecoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public ResponseEntity<List<EnderecoDTO>> list() {
        try {
            List<EnderecoDTO> listDTO = new ArrayList<>();
            for (EnderecoEntity entity : repository.findAll()) {
                EnderecoDTO map = modelMapper.map(entity, EnderecoDTO.class);
                listDTO.add(map);
            }
            return new ResponseEntity<>(listDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<EnderecoDTO> getIdByRequest(@RequestParam("id") final Long id) {
        final EnderecoEntity entity = this.repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possivel encontrar o registro"));
        return ResponseEntity.ok(modelMapper.map(entity, EnderecoDTO.class));
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> create(@RequestBody @Validated EnderecoDTO dto) {
        try {
            return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> updateByPath(@PathVariable("id") final Long id, @RequestBody final @Validated EnderecoDTO dto) {
        try {
            return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") final Long id) {
        try {
            EnderecoEntity entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));
            repository.delete(entity);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
        }
    }
}
