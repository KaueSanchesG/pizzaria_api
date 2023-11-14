package pizzaria.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pizzaria.pizzaria.entity.ClienteEntity;
import pizzaria.pizzaria.entity.FuncionarioEntity;

@Service
public class UserAuthService implements UserDetailsService {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClienteEntity cliente = clienteService.getClienteByNome(username);
        if (cliente !=null){
            return cliente;
        }

        FuncionarioEntity funcionario = funcionarioService.getFuncionarioByNome(username);
        if (funcionario !=null){
            return funcionario;
        }

        throw new UsernameNotFoundException("Usuário não encontrado com o nome: " + username);
    }
}
