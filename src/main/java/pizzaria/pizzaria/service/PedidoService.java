package pizzaria.pizzaria.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.entity.PedidoEntity;
import pizzaria.pizzaria.entity.ProdutoEntity;
import pizzaria.pizzaria.repository.PedidoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public Double calcValorTotal(PedidoEntity entity) {
        double total = 0.0;
        for (ProdutoEntity produto : entity.getProdutos()) {
            total += produto.getValor();
        }
        return total;
    }

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public PedidoEntity create(PedidoEntity entity) {
        entity.setDataHora(LocalDateTime.now());
        if (entity.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deixe o campo Id vago, ele é gerado pelo banco");
        }
        if (!entity.getProdutos().isEmpty()) {
            double total = calcValorTotal(entity);
            entity.setValorTotal(total);
        }
        return this.repository.save(entity);
    }

    @Transactional
    public PedidoEntity update(Long id, PedidoEntity entity) {
        PedidoEntity dataBase = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));
        entity.setCadastro(dataBase.getCadastro());
        entity.setDataHora(dataBase.getDataHora());
        if (entity.getProdutos() != dataBase.getProdutos()) {
            double totalUpdate = calcValorTotal(entity);
            entity.setValorTotal(totalUpdate);
        }
        return this.repository.save(dataBase);
    }

    @Transactional(readOnly = true)
    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}