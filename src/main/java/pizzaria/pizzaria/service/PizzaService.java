package pizzaria.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaria.pizzaria.config.RegistroNaoEncontradoException;
import pizzaria.pizzaria.entity.PizzaEntity;
import pizzaria.pizzaria.repository.PizzaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository repository;

    @Transactional(readOnly = true)
    public List<PizzaEntity> getAll(){
        return this.repository.findAll();
    }

    @Transactional(readOnly = true)
    public PizzaEntity getId(Long id){
        Optional<PizzaEntity> optional = this.repository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }else{
            throw new RegistroNaoEncontradoException();
        }
    }
    @Transactional(readOnly = true)
    public PizzaEntity getPizzaByNome(String nome){
        PizzaEntity pizza = this.repository.findByNome(nome);
        if (pizza==null){
            throw new RegistroNaoEncontradoException();
        }
        return pizza;
    }

    @Transactional
    public List<PizzaEntity> getPizzaBySabor(String sabor){
        List<PizzaEntity> pizzas = this.repository.findBySabor(sabor);
        if (pizzas.isEmpty()){
            throw new RegistroNaoEncontradoException();
        }
        return pizzas;
    }

    @Transactional
    public List<PizzaEntity> getPizzaByTamanho(String tamanho){
        List<PizzaEntity> pizzas = this.repository.findByTamanho(tamanho);
        if (pizzas.isEmpty()){
            throw new RegistroNaoEncontradoException();
        }
        return pizzas;
    }

    @Transactional
    public PizzaEntity create(PizzaEntity entity){
        return this.repository.save(entity);
    }

    @Transactional
    public PizzaEntity update(Long id, PizzaEntity entity){
        PizzaEntity database = this.repository.findById(id).orElseThrow(RegistroNaoEncontradoException::new);
        copyPropertiesToBlankSpaces(entity, database);

        return this.repository.save(entity);
    }

    @Transactional(readOnly = true)
    public void delete(Long id){this.repository.deleteById(id);}

    private void copyPropertiesToBlankSpaces(PizzaEntity entity, PizzaEntity database) {
        entity.setCadastro(database.getCadastro());
        entity.setId(database.getId());
        if (entity.getNome()==null){
            entity.setNome(database.getNome());
        }
        if (entity.getValor()==null){
            entity.setValor(database.getValor());
        }
        if (entity.getSaborList()==null) {
            entity.setSaborList(database.getSaborList());
        }
        if (entity.getPedido()==null){
            entity.setPedido(database.getPedido());
        }
    }
}
