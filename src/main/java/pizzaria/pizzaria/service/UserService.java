package pizzaria.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaria.pizzaria.config.RegistroNaoEncontradoException;
import pizzaria.pizzaria.entity.UserEntity;
import pizzaria.pizzaria.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public UserEntity getId(long id) {
        Optional<UserEntity> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RegistroNaoEncontradoException();
        }
    }

    @Transactional
    public UserEntity create(UserEntity entity) {
        // Encriptar a senha antes de salvar
        String senhaEncriptada = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(senhaEncriptada);

        return this.repository.save(entity);
    }
}
