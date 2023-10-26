package pizzaria.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaria.pizzaria.config.RegistroNaoEncontradoException;
import pizzaria.pizzaria.entity.EnderecoEntity;
import pizzaria.pizzaria.repository.EnderecoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Transactional(readOnly = true)
    public List<EnderecoEntity> getAll() {
        return this.repository.findAll();
    }

    @Transactional(readOnly = true)
    public EnderecoEntity getId(Long id) {
        Optional<EnderecoEntity> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RegistroNaoEncontradoException();
        }
    }

    @Transactional(readOnly = true)
    public List<EnderecoEntity> getEnderecoByClienteNome(String nomeCliente){
        List<EnderecoEntity> enderecos = this.repository.findByClienteNome(nomeCliente);
        if (enderecos.isEmpty()){
            throw new RegistroNaoEncontradoException();
        }
        return enderecos;
    }

    @Transactional
    public EnderecoEntity create(EnderecoEntity entity) {
        return this.repository.save(entity);
    }

    @Transactional
    public EnderecoEntity update(Long id, EnderecoEntity entity) {
        EnderecoEntity dataBase = this.repository.findById(id).orElseThrow(RegistroNaoEncontradoException::new);
        copyPropertiesToBlankSpaces(entity, dataBase);
        return this.repository.save(entity);
    }

    @Transactional(readOnly = true)
    public void delete(Long id){
        this.repository.deleteById(id);
    }

    private void copyPropertiesToBlankSpaces(EnderecoEntity entity, EnderecoEntity dataBase) {
        entity.setCadastro(dataBase.getCadastro());
        entity.setId(dataBase.getId());
        if (entity.getRua()==null){
            entity.setRua(dataBase.getRua());
        }
        if (entity.getCliente()==null){
            entity.setCliente(dataBase.getCliente());
        }
    }
}
