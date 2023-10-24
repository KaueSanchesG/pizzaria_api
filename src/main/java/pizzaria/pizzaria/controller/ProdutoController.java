package pizzaria.pizzaria.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pizzaria.pizzaria.dto.ProdutoDTO;
import pizzaria.pizzaria.entity.ProdutoEntity;
import pizzaria.pizzaria.service.ProdutoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService service;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public ResponseEntity<List<ProdutoDTO>> list() {
        List<ProdutoDTO> array = new ArrayList<>();
        for (ProdutoEntity entity : service.getAll()){
            ProdutoDTO map = modelMapper.map(entity, ProdutoDTO.class);
            array.add(map);
        }
        return new ResponseEntity<>(array, HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ProdutoDTO>> getProdutoByNome(@PathVariable("nome") String nome){
        List<ProdutoDTO> array = new ArrayList<>();
        for (ProdutoEntity entity : service.getProdutoByNome(nome)){
            ProdutoDTO map = modelMapper.map(entity, ProdutoDTO.class);
            array.add(map);
        }
        return new ResponseEntity<>(array, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ProdutoDTO> getIdByRequest(@RequestParam("id") final long id) {
        return new ResponseEntity<>(modelMapper.map(service.getId(id), ProdutoDTO.class), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ProdutoDTO> create(@RequestBody @Validated ProdutoDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, ProdutoEntity.class)), ProdutoDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> updateByPath(@PathVariable("id") final Long id, @RequestBody final @Validated ProdutoDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.update(id, modelMapper.map(dto, ProdutoEntity.class)), ProdutoDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") final Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
