package pizzaria.pizzaria.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.entity.SaborEntity;
import pizzaria.pizzaria.repository.SaborRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SaborService {
    @Autowired
    private SaborRepository repository;
    @Autowired
    private ModelMapper modelMapper;

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

    @Transactional
    public SaborEntity create(SaborEntity entity) {
        if (entity.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deixe o campo Id vago, ele é gerado pelo banco");
        }
        return this.repository.save(entity);
    }

    @Transactional
    public SaborEntity update(Long id, SaborEntity entity) {
        SaborEntity dataBase = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));
        entity.setCadastro(dataBase.getCadastro());
        modelMapper.map(entity, dataBase);
        return this.repository.save(dataBase);
    }

    @Transactional(readOnly = true)
    public void delete(Long id){
        this.repository.deleteById(id);
    }
}
