package pizzaria.pizzaria.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizzaria.pizzaria.DTO.EnderecoDTO;
import pizzaria.pizzaria.Entity.EnderecoEntity;
import pizzaria.pizzaria.Repository.EnderecoRepository;
import pizzaria.pizzaria.Service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService service;
    @Autowired
    private EnderecoRepository repository;

    @GetMapping("/lista")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping
    public ResponseEntity<?> getIdByRequest(@RequestParam("id") final Long id) {
        final EnderecoEntity endereco = this.repository.findById(id).orElse(null);
        return endereco == null ? ResponseEntity.badRequest().body("Sem valores encontrados") : ResponseEntity.ok(endereco);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EnderecoDTO enderecoDTO) {
        try {
            service.create(enderecoDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro " + e.getMessage());
        }
        return ResponseEntity.ok().body("Registro cadastrado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateByPath(@PathVariable("id") final Long id, @RequestBody final EnderecoDTO enderecoDTO) {
        try {
            service.update(id, enderecoDTO);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Erro " + e.getCause().getCause().getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro " + e.getMessage());
        }
        return ResponseEntity.ok("Registro editado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) {
        try {
            service.delete(id);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Não foi possivel deletar");
        }
        return ResponseEntity.ok("Registro deletado!");
    }
}
