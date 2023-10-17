package pizzaria.pizzaria.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pizzaria.pizzaria.dto.SaborDTO;
import pizzaria.pizzaria.entity.SaborEntity;
import pizzaria.pizzaria.service.SaborService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sabor")
public class SaborController {
    @Autowired
    private SaborService service;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public ResponseEntity<List<SaborDTO>> list() {
        List<SaborDTO> array = new ArrayList<>();
        for (SaborEntity entity : service.getAll()) {
            SaborDTO map = modelMapper.map(entity, SaborDTO.class);
            array.add(map);
        }
        return new ResponseEntity<>(array, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SaborDTO> getIdByRequest(@RequestParam("id") final long id) {
        return new ResponseEntity<>(modelMapper.map(service.getId(id), SaborDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SaborDTO> create(@RequestBody @Validated SaborDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, SaborEntity.class)), SaborDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaborDTO> updateByPath(@PathVariable("id") final Long id, @RequestBody final @Validated SaborDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.update(id, modelMapper.map(dto, SaborEntity.class)), SaborDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") final Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
