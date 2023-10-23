package pizzaria.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.config.RegistroNaoEncontradoException;
import pizzaria.pizzaria.entity.SaborEntity;
import pizzaria.pizzaria.repository.SaborRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SaborService {
    @Autowired
    private SaborRepository repository;

    @Transactional(readOnly = true)
    public List<SaborEntity> getAll() {
        return this.repository.findAll();
    }

    @Transactional(readOnly = true)
    public SaborEntity getId(Long id) {
        Optional<SaborEntity> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional(readOnly = true)
    public List<SaborEntity> getSaborByNome(String nome) {
        List<SaborEntity> sabores = this.repository.findByNome(nome);
        if (sabores.isEmpty()) {
            throw new RegistroNaoEncontradoException();
        }
        return sabores;
    }

    @Transactional
    public SaborEntity create(SaborEntity entity) {

        return this.repository.save(entity);
    }

    @Transactional
    public SaborEntity update(Long id, SaborEntity entity) {
        SaborEntity dataBase = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));
        copyPropertiesToBlankSpaces(entity, dataBase);
        return this.repository.save(dataBase);
    }

    @Transactional(readOnly = true)
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    private void copyPropertiesToBlankSpaces(SaborEntity entity, SaborEntity dataBase) {
        entity.setCadastro(dataBase.getCadastro());
        entity.setId(dataBase.getId());
        if (entity.getNome() == null) {
            entity.setNome(dataBase.getNome());
        }
        if (entity.getPizzaList() == null) {
            entity.setPizzaList(dataBase.getPizzaList());
        }
    }
}
