package pizzaria.pizzaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizzaria.pizzaria.dto.ProdutoDTO;
import pizzaria.pizzaria.entity.ProdutoEntity;
import pizzaria.pizzaria.repository.ProdutoRepository;
import pizzaria.pizzaria.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService service;
    @Autowired
    private ProdutoRepository repository;

    @GetMapping("/lista")
    public ResponseEntity<?>list(){
        return ResponseEntity.ok(this.repository.findAll());
    }
    @GetMapping
    public ResponseEntity<?> getIdByRequest(@RequestParam("id") final long id){
        final ProdutoEntity product = this.repository.findById(id).orElse(null);
        return product == null ? ResponseEntity.badRequest().body("Nenhum registro encontrado")  : ResponseEntity.ok(product);
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProdutoDTO produtoDTO) {
        try {
           service.create(produtoDTO);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Cadastro realizado com sucesso");
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateByPath(@PathVariable("id") final Long id, @RequestBody final ProdutoDTO produtoDTO) {
        try {
            service.update(id, produtoDTO);
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error:" + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("error:" + e.getMessage());
        }
        return ResponseEntity.ok("Registro atualizado com sucesso");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") final Long id){
        try{
            service.delete(id);
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Não foi possivel deletar");
        }
        return ResponseEntity.ok("Registro deletado!");
    }
}
