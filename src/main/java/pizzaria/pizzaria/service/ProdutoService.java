package pizzaria.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaria.pizzaria.config.RegistroNaoEncontradoException;
import pizzaria.pizzaria.entity.ProdutoEntity;
import pizzaria.pizzaria.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    @Transactional(readOnly = true)
    public List<ProdutoEntity> getAll() {
        return this.repository.findAll();
    }

    @Transactional(readOnly = true)
    public ProdutoEntity getId(Long id) {
        Optional<ProdutoEntity> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RegistroNaoEncontradoException();
        }
    }

    @Transactional(readOnly = true)
    public List<ProdutoEntity> getProdutoByNome(String nome){
        List<ProdutoEntity> produtos = this.repository.findByNome(nome);
        if (produtos.isEmpty()){
            throw new RegistroNaoEncontradoException();
        }
        return produtos;
    }

    @Transactional
    public ProdutoEntity create(ProdutoEntity entity) {
        return this.repository.save(entity);
    }

    @Transactional
    public ProdutoEntity update(Long id, ProdutoEntity entity) {
        ProdutoEntity dataBase = this.repository.findById(id).orElseThrow(RegistroNaoEncontradoException::new);
        copyPropertiesToBlankSpaces(entity, dataBase);
        return this.repository.save(dataBase);
    }

    @Transactional(readOnly = true)
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    private void copyPropertiesToBlankSpaces(ProdutoEntity entity, ProdutoEntity dataBase) {
        entity.setCadastro(dataBase.getCadastro());
        entity.setId(dataBase.getId());
        if (entity.getNome() == null) {
            entity.setNome(dataBase.getNome());
        }
        if (entity.getValor() == null) {
            entity.setValor(dataBase.getValor());
        }
        if (entity.getPedido() == null) {
            entity.setPedido(dataBase.getPedido());
        }
    }
}
