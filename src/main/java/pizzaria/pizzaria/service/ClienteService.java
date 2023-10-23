package pizzaria.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.config.RegistroNaoEncontradoException;
import pizzaria.pizzaria.entity.ClienteEntity;
import pizzaria.pizzaria.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

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
            throw new RegistroNaoEncontradoException();
        }
    }

    @Transactional(readOnly = true)
    public ClienteEntity getClienteByCpf(String cpf){
        ClienteEntity database = this.repository.findByCpf(cpf);
        if (database==null){
            throw new RegistroNaoEncontradoException();
        }
        return database;
    }

    @Transactional(readOnly = true)
    public List<ClienteEntity> getClienteByNome(String nome){
        List<ClienteEntity> clientes = this.repository.findByNome(nome);
        if (clientes.isEmpty()){
            throw new RegistroNaoEncontradoException();
        }
        return clientes;
    }

    @Transactional
    public ClienteEntity create(ClienteEntity entity) {
        if (this.repository.findByCpf(entity.getCpf())!=null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O cpf ja existe");
        }
        return this.repository.save(entity);
    }

    @Transactional
    public ClienteEntity update(Long id, ClienteEntity entity) {
        ClienteEntity database = this.repository.findById(id).orElseThrow(RegistroNaoEncontradoException::new);
        if (this.repository.findByCpf(entity.getCpf())!=null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O cpf ja existe");
        }
        copyPropertiesToBlankSpaces(entity, database);
        return repository.save(database);
    }

    @Transactional(readOnly = true)
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    private void copyPropertiesToBlankSpaces(ClienteEntity entity, ClienteEntity database) {
        entity.setCadastro(database.getCadastro());
        entity.setId(database.getId());
        if (entity.getNome() == null) {
            entity.setNome(database.getNome());
        }
        if (entity.getCpf() == null) {
            entity.setCpf(database.getCpf());
        }
        if (entity.getEnderecoList() == null) {
            entity.setEnderecoList(database.getEnderecoList());
        }
        if (entity.getPedidoList() == null) {
            entity.setPedidoList(database.getPedidoList());
        }
    }
}