package pizzaria.pizzaria.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizzaria.pizzaria.DTO.PedidoDTO;
import pizzaria.pizzaria.Entity.PedidoEntity;
import pizzaria.pizzaria.Repository.PedidoRepository;
import pizzaria.pizzaria.Service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoService service;
    @Autowired
    private PedidoRepository repository;
    @GetMapping("/lista")
    public ResponseEntity<?>list(){
        return ResponseEntity.ok(this.repository.findAll());
    }
    @GetMapping
    public ResponseEntity<?> getIdByRequest(@RequestParam("id") final long id){
        final PedidoEntity pedido = this.repository.findById(id).orElse(null);
        return pedido == null ? ResponseEntity.badRequest().body("Nenhum registro encontrado")  : ResponseEntity.ok(pedido);
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PedidoDTO pedidoDTO) {
        try {
            service.create(pedidoDTO);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Cadastro realizado com sucesso");
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateByPath(@PathVariable("id") final Long id, @RequestBody final PedidoDTO pedidoDTO) {
        try {
            service.update(id, pedidoDTO);
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
