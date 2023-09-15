package pizzaria.pizzaria.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaria.pizzaria.dto.PedidoDTO;
import pizzaria.pizzaria.dto.ProdutoDTO;
import pizzaria.pizzaria.entity.PedidoEntity;
import pizzaria.pizzaria.repository.PedidoRepository;

import java.time.LocalDateTime;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ModelMapper modelMapper;

    private Double calcValorTotal(PedidoDTO pedidoDTO) {
        Double vt = 0.0;
        for (ProdutoDTO produto : pedidoDTO.getProdutos()) {
            vt += produto.getValor().doubleValue();
        }
        return vt;
    }

    @Transactional
    public PedidoEntity create(PedidoDTO pedidoDTO) {
        pedidoDTO.setDataHora(LocalDateTime.now());
        if (pedidoDTO.getId() != null) {
            throw new RuntimeException("Deixe o campo Id vago, ele é gerado pelo banco");
        }
        if (pedidoDTO.getCliente() == null) {
            throw new RuntimeException("Você precisa de um cliente para fazer um pedido");
        }
        if (pedidoDTO.getFuncionario() == null) {
            throw new RuntimeException("Você precisa de um funcionario para fazer um pedido");
        }
        if (!pedidoDTO.getProdutos().isEmpty()) {
            Double vt = calcValorTotal(pedidoDTO);
            pedidoDTO.setValorTotal(vt);
        } else {
            throw new RuntimeException("Para fazer um pedido, você precisa de um produto!!!");
        }
        PedidoEntity pedido = modelMapper.map(pedidoDTO, PedidoEntity.class);
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void update(Long id, PedidoDTO pedidoDTO) {
        PedidoEntity pedidoBanco = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Endereço de id não encontrado!!!"));
        if (!pedidoBanco.getId().equals(pedidoDTO.getId())) {
            throw new RuntimeException("Não foi possivel encontrar o registro!!!");
        }
        modelMapper.map(pedidoDTO, pedidoBanco);
        pedidoRepository.save(pedidoBanco);
    }

    @Transactional
    public void delete(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o registro informado"));
        pedidoRepository.delete(pedido);
    }
}
