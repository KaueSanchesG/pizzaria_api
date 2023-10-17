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

    @GetMapping
    public ResponseEntity<PedidoDTO> getIdByRequest(@RequestParam("id") final long id) {
        return new ResponseEntity<>(modelMapper.map(service.getId(id), PedidoDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> create(@RequestBody @Validated PedidoDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, PedidoEntity.class)), PedidoDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> updateByPath(@PathVariable("id") final Long id, @RequestBody final @Validated PedidoDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.update(id, modelMapper.map(dto, PedidoEntity.class)), PedidoDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") final Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
