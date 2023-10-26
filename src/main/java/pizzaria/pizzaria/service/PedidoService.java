package pizzaria.pizzaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.config.RegistroNaoEncontradoException;
import pizzaria.pizzaria.entity.PedidoEntity;
import pizzaria.pizzaria.entity.PizzaEntity;
import pizzaria.pizzaria.entity.ProdutoEntity;
import pizzaria.pizzaria.repository.PedidoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;


    @Transactional(readOnly = true)
    public List<PedidoEntity> getAll() {
        return this.repository.findAll();
    }

    @Transactional(readOnly = true)
    public PedidoEntity getId(Long id) {
        Optional<PedidoEntity> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RegistroNaoEncontradoException();
        }
    }

    @Transactional(readOnly = true)
    public List<PedidoEntity> getPedidoByNomeCliente(String nomeCliente) {
        List<PedidoEntity> pedidos = this.repository.findByNomeCliente(nomeCliente);
        if (pedidos.isEmpty()) {
            throw new RegistroNaoEncontradoException();
        }
        return pedidos;
    }

    @Transactional(readOnly = true)
    public List<PedidoEntity> getPedidoByNomeFuncionario(String nomeFuncionario) {
        List<PedidoEntity> pedidos = this.repository.findByNomeFuncionario(nomeFuncionario);
        if (pedidos.isEmpty()) {
            throw new RegistroNaoEncontradoException();
        }
        return pedidos;
    }

    @Transactional(readOnly = true)
    public List<PedidoEntity> getPedidoByEntrega() {
        List<PedidoEntity> pedidos = this.repository.findByEntregaTrueAndAtivoTrue();
        if (pedidos.isEmpty()) {
            throw new RegistroNaoEncontradoException();
        }
        return pedidos;
    }

    @Transactional(readOnly = true)
    public List<PedidoEntity> getPedidoAtivo() {
        List<PedidoEntity> pedidos = this.repository.findByAtivoTrue();
        if (pedidos.isEmpty()) {
            throw new RegistroNaoEncontradoException();
        }
        return pedidos;
    }

    @Transactional(readOnly = true)
    public List<PedidoEntity> getPedidoByData(LocalDate data) {
        List<PedidoEntity> pedidos = this.repository.findByData(data.getYear(), data.getMonthValue(), data.getDayOfMonth());
        if (pedidos.isEmpty()) {
            throw new RegistroNaoEncontradoException();
        }
        return pedidos;
    }

    @Transactional
    public PedidoEntity create(PedidoEntity entity) {
        if (entity.getProdutoList() == null && entity.getPizzaList() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deve conter pelo menos um item para fazer um pedido");
        }
        if (entity.getProdutoList() != null && (!entity.getProdutoList().isEmpty() || !entity.getPizzaList().isEmpty())) {
            double total = calcValorTotal(entity);
            entity.setValorTotal(total);
        }
        entity.setDataHora(LocalDateTime.now());
        return this.repository.save(entity);
    }

    @Transactional
    public PedidoEntity update(Long id, PedidoEntity entity) {
        PedidoEntity dataBase = repository.findById(id).orElseThrow(RegistroNaoEncontradoException::new);
        copyPropertiesToBlankSpaces(entity, dataBase);
        if (entity.getProdutoList() != dataBase.getProdutoList()) {
            double totalUpdate = calcValorTotal(entity);
            entity.setValorTotal(totalUpdate);
        }
        return this.repository.save(entity);
    }

    @Transactional
    public void delete(Long id) {
        PedidoEntity database = this.repository.findById(id).orElseThrow(RegistroNaoEncontradoException::new);
        database.setAtivo(false);
        this.repository.save(database);
    }

    private void copyPropertiesToBlankSpaces(PedidoEntity entity, PedidoEntity dataBase) {
        entity.setCadastro(dataBase.getCadastro());
        entity.setDataHora(dataBase.getDataHora());
        entity.setId(dataBase.getId());
        if (entity.getCliente() == null) {
            entity.setCliente(dataBase.getCliente());
        }
        if (entity.getEntrega() == null) {
            entity.setEntrega(dataBase.getEntrega());
        }
        if (entity.getValorTotal() == null) {
            entity.setValorTotal(dataBase.getValorTotal());
        }
        if (entity.getFuncionario() == null) {
            entity.setFuncionario(dataBase.getFuncionario());
        }
        if (entity.getPizzaList() == null) {
            entity.setPizzaList(dataBase.getPizzaList());
        }
        if (entity.getProdutoList() == null) {
            entity.setProdutoList(dataBase.getProdutoList());
        }
    }

    public Double calcValorTotal(PedidoEntity entity) {
        double total = 0.0;
        for (ProdutoEntity produto : entity.getProdutoList()) {
            total += produto.getValor();
        }
        for (PizzaEntity pizza : entity.getPizzaList()) {
            total += pizza.getValor();
        }
        return total;
    }
}