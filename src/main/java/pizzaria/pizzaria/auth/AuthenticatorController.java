package pizzaria.pizzaria.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pizzaria.pizzaria.entity.FuncionarioEntity;
import pizzaria.pizzaria.repository.FuncionarioRepository;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class AuthenticatorController {
    @Autowired
    private FuncionarioRepository repository;

    @PostMapping("/auth")
    public ResponseEntity<String> authLogin(@RequestBody Map<String, String> loginRequest){
        String login = loginRequest.get("login");
        String senha = loginRequest.get("senha");

        FuncionarioEntity funcionario = this.repository.findByLogin(login);

        if (funcionario!=null && funcionario.getSenha().equals(senha)){
            return ResponseEntity.ok("Bem vindo " + funcionario.getNome());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }
}
