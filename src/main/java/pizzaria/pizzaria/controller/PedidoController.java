package pizzaria.pizzaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.dto.PedidoDTO;
import pizzaria.pizzaria.entity.PedidoEntity;
import pizzaria.pizzaria.repository.PedidoRepository;
import pizzaria.pizzaria.service.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoService service;
    @Autowired
    private PedidoRepository repository;

    @GetMapping("/list")
    public ResponseEntity<List<PedidoDTO>> list() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PedidoDTO> getIdByRequest(@RequestParam("id") final long id) {
        return new ResponseEntity<>(service.getId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> create(@RequestBody @Validated PedidoDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> updateByPath(@PathVariable("id") final Long id, @RequestBody final @Validated PedidoDTO dto) {
        return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") final Long id) {
        try {
            PedidoEntity entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));
            repository.delete(entity);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
        }
    }
}
