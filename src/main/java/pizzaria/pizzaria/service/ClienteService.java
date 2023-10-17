package pizzaria.pizzaria.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.entity.ClienteEntity;
import pizzaria.pizzaria.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<ClienteEntity> getAll() {
        return this.repository.findAll();
    }

    @Transactional(readOnly = true)
    public ClienteEntity getId(Long id) {
        Optional<ClienteEntity> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ClienteEntity create(ClienteEntity entity) {
        if (entity.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deixe o campo Id vago, ele é gerado pelo banco");
        }
        return this.repository.save(entity);
    }

    @Transactional
    public ClienteEntity update(Long id, ClienteEntity entity) {
        ClienteEntity database = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));
        entity.setCadastro(database.getCadastro());
        modelMapper.map(entity, database);
        return repository.save(database);
    }

    @Transactional(readOnly = true)
    public void delete(Long id){
        this.repository.deleteById(id);
    }
}