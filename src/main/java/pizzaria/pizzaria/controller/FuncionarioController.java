package pizzaria.pizzaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizzaria.pizzaria.dto.FuncionarioDTO;
import pizzaria.pizzaria.entity.FuncionarioEntity;
import pizzaria.pizzaria.repository.FuncionarioRepository;
import pizzaria.pizzaria.service.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;
    @Autowired
    private FuncionarioRepository repository;

    @GetMapping("/lista")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping
    public ResponseEntity<?> getIdByRequest(@RequestParam("id") final Long id) {
        final FuncionarioEntity funcionario = this.repository.findById(id).orElse(null);
        return funcionario == null ? ResponseEntity.badRequest().body("Sem valores encontrados") : ResponseEntity.ok(funcionario);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody FuncionarioDTO funcionarioDTO) {
        try {
            service.create(funcionarioDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro " + e.getMessage());
        }
        return ResponseEntity.ok().body("Registro cadastrado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateByPath(@PathVariable("id") final Long id, @RequestBody final FuncionarioDTO funcionarioDTO) {
        try {
            service.update(id, funcionarioDTO);
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
