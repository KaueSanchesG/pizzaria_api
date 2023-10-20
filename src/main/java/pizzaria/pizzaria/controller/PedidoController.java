package pizzaria.pizzaria.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pizzaria.pizzaria.dto.PedidoDTO;
import pizzaria.pizzaria.entity.PedidoEntity;
import pizzaria.pizzaria.service.PedidoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoService service;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public ResponseEntity<List<PedidoDTO>> list() {
        List<PedidoDTO> array = new ArrayList<>();
        for (PedidoEntity entity : service.getAll()){
            PedidoDTO map = modelMapper.map(entity, PedidoDTO.class);
            array.add(map);
        }
        return new ResponseEntity<>(array, HttpStatus.OK);
    }

    @GetMapping("/cliente/{nome}")
    public ResponseEntity<List<PedidoDTO>> getPedidoByNomeCliente(@PathVariable("nome") String nome){
        List<PedidoDTO> array = new ArrayList<>();
        for (PedidoEntity entity : service.getPedidoByNomeCliente(nome)){
            PedidoDTO map = modelMapper.map(entity, PedidoDTO.class);
            array.add(map);
        }
        return new ResponseEntity<>(array, HttpStatus.OK);
    }

    @GetMapping("/funcionario/{nome}")
    public ResponseEntity<List<PedidoDTO>> getPedidoByNomeFuncionario(@PathVariable("nome") String nome){
        List<PedidoDTO> array = new ArrayList<>();
        for (PedidoEntity entity : service.getPedidoByNomeFuncionario(nome)){
            PedidoDTO map = modelMapper.map(entity, PedidoDTO.class);
            array.add(map);
        }
        return new ResponseEntity<>(array, HttpStatus.OK);
    }

    @GetMapping("/entrega/{entrega}")
    public ResponseEntity<List<PedidoDTO>> getPedidoByEntrega(@PathVariable("entrega") boolean entrega){
        List<PedidoDTO> array = new ArrayList<>();
        for (PedidoEntity entity : service.getPedidoByEntrega(entrega)){
            PedidoDTO map = modelMapper.map(entity, PedidoDTO.class);
            array.add(map);
        }
        return new ResponseEntity<>(array, HttpStatus.OK);
    }

    @GetMapping("/data/{data}")
    public ResponseEntity<List<PedidoDTO>> getPedidoByData(@PathVariable("data") LocalDate data){
        List<PedidoDTO> array = new ArrayList<>();
        for (PedidoEntity entity : service.getPedidoByData(data)){
            PedidoDTO map = modelMapper.map(entity, PedidoDTO.class);
            array.add(map);
        }
        return new ResponseEntity<>(array, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PedidoDTO> getById(@RequestParam("id") final long id) {
        return new ResponseEntity<>(modelMapper.map(service.getId(id), PedidoDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> create(@RequestBody @Validated PedidoDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, PedidoEntity.class)), PedidoDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> update(@PathVariable("id") final Long id, @RequestBody final @Validated PedidoDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.update(id, modelMapper.map(dto, PedidoEntity.class)), PedidoDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") final Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
