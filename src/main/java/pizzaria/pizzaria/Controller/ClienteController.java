package pizzaria.pizzaria.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping
    public ResponseEntity<?> getIdByRequest(@RequestParam("id") final Long id) {
        final ClienteEntity cliente = this.repository.findById(id).orElse(null);
        return cliente == null ? ResponseEntity.badRequest().body("Sem valores encontrados") : ResponseEntity.ok(cliente);
    }


}
