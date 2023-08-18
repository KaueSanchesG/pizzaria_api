package pizzaria.pizzaria.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaria.pizzaria.DTO.FuncionarioDTO;
import pizzaria.pizzaria.DTO.PedidoDTO;
import pizzaria.pizzaria.Entity.FuncionarioEntity;
import pizzaria.pizzaria.Entity.PedidoEntity;
import pizzaria.pizzaria.Repository.PedidoRepository;

import java.time.LocalDateTime;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public PedidoEntity create(PedidoDTO pedidoDTO) {
        if (pedidoDTO.getId() != null) {
            throw new RuntimeException("Deixe o campo Id vago, ele é gerado pelo banco");
        }
        if (pedidoDTO.getDataHora().equals("")){
            pedidoDTO.setDataHora(LocalDateTime.now());
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
        if (pedidoDTO.getDataHora().equals("")){
            pedidoDTO.setDataHora(LocalDateTime.now());
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
