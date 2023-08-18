package pizzaria.pizzaria.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizzaria.pizzaria.DTO.ProdutoDTO;
import pizzaria.pizzaria.DTO.SaborDTO;
import pizzaria.pizzaria.Entity.ProdutoEntity;
import pizzaria.pizzaria.Entity.SaborEntity;
import pizzaria.pizzaria.Repository.SaborRepository;
import pizzaria.pizzaria.Service.SaborService;

@RestController
@RequestMapping("/sabor")
public class SaborController {
    @Autowired
    private SaborService service;
    @Autowired
    private SaborRepository repository;

    @GetMapping("/lista")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping
    public ResponseEntity<?> getIdByRequest(@RequestParam("id") final long id) {
        final SaborEntity sabor = this.repository.findById(id).orElse(null);
        return sabor == null ? ResponseEntity.badRequest().body("Nenhum registro encontrado") : ResponseEntity.ok(sabor);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SaborDTO saborDTO) {
        try {
            service.create(saborDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Cadastro realizado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateByPath(@PathVariable("id") final Long id, @RequestBody final SaborDTO saborDTO) {
        try {
            service.update(id, saborDTO);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error:" + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("error:" + e.getMessage());
        }
        return ResponseEntity.ok("Registro atualizado com sucesso");
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
