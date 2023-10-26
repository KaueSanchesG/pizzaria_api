package pizzaria.pizzaria.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.dto.ClienteDTO;
import pizzaria.pizzaria.entity.ClienteEntity;
import pizzaria.pizzaria.service.ClienteService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService service;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public ResponseEntity<List<ClienteDTO>> list() {
        List<ClienteDTO> array = new ArrayList<>();
        for (ClienteEntity entity : service.getAll()) {
            ClienteDTO map = modelMapper.map(entity, ClienteDTO.class);
            array.add(map);
        }
        return new ResponseEntity<>(array, HttpStatus.OK);
    }
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ClienteDTO>> getClienteByNome(@PathVariable("nome") String nome){
        List<ClienteDTO> array = new ArrayList<>();
        for (ClienteEntity entity : service.getClienteByNome(nome)) {
            ClienteDTO map = modelMapper.map(entity, ClienteDTO.class);
            array.add(map);
        }
        return new ResponseEntity<>(array, HttpStatus.OK);
    }
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteDTO> getClienteByCpf(@PathVariable("cpf") String cpf){
        return new ResponseEntity<>(modelMapper.map(service.getClienteByCpf(cpf), ClienteDTO.class), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getIdByRequest(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(modelMapper.map(service.getId(id), ClienteDTO.class), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ClienteDTO> create(@RequestBody @Validated ClienteDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, ClienteEntity.class)), ClienteDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateByPath(@PathVariable("id") final Long id, @RequestBody final @Validated ClienteDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.update(id, modelMapper.map(dto, ClienteEntity.class)), ClienteDTO.class), HttpStatus.OK);
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
