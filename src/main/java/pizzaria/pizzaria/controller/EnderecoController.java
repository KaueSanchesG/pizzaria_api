package pizzaria.pizzaria.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pizzaria.pizzaria.dto.EnderecoDTO;
import pizzaria.pizzaria.entity.EnderecoEntity;
import pizzaria.pizzaria.service.EnderecoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService service;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public ResponseEntity<List<EnderecoDTO>> list() {
        List<EnderecoDTO> array = new ArrayList<>();
        for (EnderecoEntity entity : service.getAll()) {
            EnderecoDTO map = modelMapper.map(entity, EnderecoDTO.class);
            array.add(map);
        }
        return new ResponseEntity<>(array, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<EnderecoDTO> getIdByRequest(@RequestParam("id") final Long id) {
        return new ResponseEntity<>(modelMapper.map(service.getId(id), EnderecoDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> create(@RequestBody @Validated EnderecoDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, EnderecoEntity.class)), EnderecoDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> updateByPath(@PathVariable("id") final Long id, @RequestBody final @Validated EnderecoDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.update(id, modelMapper.map(dto, EnderecoEntity.class)), EnderecoDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") final Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
