package pizzaria.pizzaria.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizzaria.pizzaria.DTO.ClienteDTO;
import pizzaria.pizzaria.Entity.ClienteEntity;
import pizzaria.pizzaria.Repository.ClienteRepository;
import pizzaria.pizzaria.Service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService service;
    @Autowired
    private ClienteRepository repository;

    @GetMapping("/lista")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping
    public ResponseEntity<?> getIdByRequest(@RequestParam("id") final Long id) {
        final ClienteEntity cliente = this.repository.findById(id).orElse(null);
        return cliente == null ? ResponseEntity.badRequest().body("Sem valores encontrados") : ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ClienteDTO clienteDTO){
        try {
            service.create(clienteDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Erro " + e.getMessage());
        }
        return ResponseEntity.ok().body("Reistro cadastrado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateByPath(@PathVariable("id") final Long id, @RequestBody final ClienteDTO clienteDTO){
        try {
            service.update(id, clienteDTO);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Erro "+ e.getCause().getCause().getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Erro "+ e.getMessage());
        }
        return ResponseEntity.ok("Registro editado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")final Long id){
        try {
            service.delete(id);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Não foi possivel deletar");
        }
        return ResponseEntity.ok("Registro deletado!");
    }
}
