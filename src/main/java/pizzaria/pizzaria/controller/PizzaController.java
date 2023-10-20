package pizzaria.pizzaria.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pizzaria.pizzaria.dto.PizzaDTO;
import pizzaria.pizzaria.entity.PizzaEntity;
import pizzaria.pizzaria.service.PizzaService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pizza")
public class PizzaController {
    @Autowired
    private PizzaService service;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public ResponseEntity<List<PizzaDTO>> list() {
        List<PizzaDTO> array = new ArrayList<>();
        for (PizzaEntity entity : service.getAll()){
            PizzaDTO map = modelMapper.map(entity, PizzaDTO.class);
            array.add(map);
        }
        return new ResponseEntity<>(array, HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<PizzaDTO> getPizzaByNome(@PathVariable("nome") String nome){
        return new ResponseEntity<>(modelMapper.map(service.getPizzaByNome(nome), PizzaDTO.class), HttpStatus.OK);
    }

    @GetMapping("/sabor/{sabor}")
    public ResponseEntity<PizzaDTO> getPizzaBySabor(@PathVariable("sabor") String sabor){
        return new ResponseEntity<>(modelMapper.map(service.getPizzaBySabor(sabor), PizzaDTO.class), HttpStatus.OK);
    }

    @GetMapping("/tamanho/{tamanho}")
    public ResponseEntity<PizzaDTO> getPizzaByTamanho(@PathVariable("tamanho") String tamanho){
        return new ResponseEntity<>(modelMapper.map(service.getPizzaByTamanho(tamanho), PizzaDTO.class), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PizzaDTO> getById(@RequestParam("id") final Long id){
        return new ResponseEntity<>(modelMapper.map(service.getId(id), PizzaDTO.class), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<PizzaDTO> create(@RequestBody @Validated PizzaDTO dto){
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, PizzaEntity.class)), PizzaDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PizzaDTO> update(@PathVariable("id") final Long id, @RequestBody final @Validated PizzaDTO dto){
        return new ResponseEntity<>(modelMapper.map(service.update(id, modelMapper.map(dto, PizzaEntity.class)), PizzaDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") final Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
