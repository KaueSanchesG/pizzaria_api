package pizzaria.pizzaria.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.dto.PedidoDTO;
import pizzaria.pizzaria.dto.ProdutoDTO;
import pizzaria.pizzaria.entity.PedidoEntity;
import pizzaria.pizzaria.repository.PedidoRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public Double calcValorTotal(PedidoDTO dto) {
        double total = 0.0;
        for (ProdutoDTO produto : dto.getProdutos()) {
            total += produto.getValor();
        }
        return total;
    }

    @Transactional(readOnly = true)
    public List<PedidoDTO> getAll() {
        List<PedidoDTO> listDTO = new ArrayList<>();
        for (PedidoEntity entity : repository.findAll()) {
            PedidoDTO map = modelMapper.map(entity, PedidoDTO.class);
            listDTO.add(map);
        }
        return listDTO;
    }

    @Transactional(readOnly = true)
    public PedidoDTO getId(Long id) {
        PedidoEntity entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possivel encontrar o registro"));
        return modelMapper.map(entity, PedidoDTO.class);
    }

    @Transactional
    public PedidoDTO create(PedidoDTO dto) {
        dto.setDataHora(LocalDateTime.now());
        if (dto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deixe o campo Id vago, ele é gerado pelo banco");
        }
        if (!dto.getProdutos().isEmpty()) {
            double total = calcValorTotal(dto);
            dto.setValorTotal(total);
        }
        return modelMapper.map(repository.save(modelMapper.map(dto, PedidoEntity.class)), PedidoDTO.class);
    }

    @Transactional
    public PedidoDTO update(Long id, PedidoDTO dto) {
        PedidoEntity dataBase = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));
        dto.setCadastro(dataBase.getCadastro());
        dto.setDataHora(dataBase.getDataHora());
        if (dto.getProdutos().size() != dataBase.getProdutos().size()) {
            double totalUpdate = calcValorTotal(dto);
            dto.setValorTotal(totalUpdate);
        }
        return modelMapper.map(repository.save(modelMapper.map(dto, PedidoEntity.class)), PedidoDTO.class);
    }
}