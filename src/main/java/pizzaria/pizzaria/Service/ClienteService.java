package pizzaria.pizzaria.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaria.pizzaria.DTO.ClienteDTO;
import pizzaria.pizzaria.Entity.ClienteEntity;
import pizzaria.pizzaria.Repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public ClienteEntity create(ClienteDTO clienteDTO) {
        if (clienteDTO.getId() != null) {
            throw new RuntimeException("Deixe o campo Id vago, ele é gerado pelo banco");
        }
        ClienteEntity cliente = modelMapper.map(clienteDTO, ClienteEntity.class);
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void update(Long id, ClienteDTO clienteDTO) {
        ClienteEntity clienteBanco = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Endereço de id não encontrado!!!"));
        if (!clienteBanco.getId().equals(clienteDTO.getId())) {
            throw new RuntimeException("Não foi possivel encontrar o registro!!!");
        }
        modelMapper.map(clienteDTO, clienteBanco);
        clienteRepository.save(clienteBanco);
    }

    @Transactional
    public void delete(Long id) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o registro informado"));
        clienteRepository.delete(cliente);
    }
}
