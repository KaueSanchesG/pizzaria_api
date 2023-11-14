package pizzaria.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaria.pizzaria.config.RegistroNaoEncontradoException;
import pizzaria.pizzaria.entity.FuncionarioEntity;
import pizzaria.pizzaria.repository.FuncionarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    @Transactional(readOnly = true)
    public List<FuncionarioEntity> getAll() {
        return this.repository.findAll();
    }

    @Transactional(readOnly = true)
    public FuncionarioEntity getId(Long id) {
        Optional<FuncionarioEntity> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RegistroNaoEncontradoException();
        }
    }

    @Transactional(readOnly = true)
    public FuncionarioEntity getFuncionarioByNome(String nome){
        FuncionarioEntity funcionario = this.repository.findByNome(nome);
        if (funcionario==null){
            throw new RegistroNaoEncontradoException();
        }
        return funcionario;
    }

    @Transactional
    public FuncionarioEntity create(FuncionarioEntity entity) {
        return this.repository.save(entity);
    }

    @Transactional
    public FuncionarioEntity update(Long id, FuncionarioEntity entity) {
        FuncionarioEntity dataBase = this.repository.findById(id).orElseThrow(RegistroNaoEncontradoException::new);
        copyPropertiesToBlankSpaces(entity, dataBase);
        return this.repository.save(entity);
    }

    @Transactional(readOnly = true)
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    private void copyPropertiesToBlankSpaces(FuncionarioEntity entity, FuncionarioEntity dataBase) {
        entity.setCadastro(dataBase.getCadastro());
        entity.setId(dataBase.getId());
        if (entity.getNome() == null) {
            entity.setNome(dataBase.getNome());
        }
        if (entity.getPedidoList() == null) {
            entity.setPedidoList(dataBase.getPedidoList());
        }
        if (entity.getLogin()==null){
            entity.setLogin(dataBase.getLogin());
        }
        if (entity.getSenha()==null){
            entity.setSenha(dataBase.getSenha());
        }
    }
}