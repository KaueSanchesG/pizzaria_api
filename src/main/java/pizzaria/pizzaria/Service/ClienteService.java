package pizzaria.pizzaria.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaria.pizzaria.Config.ValidaCPF;
import pizzaria.pizzaria.DTO.ClienteDTO;
import pizzaria.pizzaria.Entity.ClienteEntity;
import pizzaria.pizzaria.Repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ValidaCPF validaCPF;


    @Transactional
    public ClienteEntity create(ClienteDTO clienteDTO) {
        if (clienteDTO.getId() != null) {
            throw new RuntimeException("Deixe o campo Id vago, ele é gerado pelo banco");
        }
        if (!this.validaCPF.isCPF(clienteDTO.getCpf())) {
            throw new RuntimeException("Cpf do cliente está incorreto");
        }
        if(clienteDTO.getCpf()==null){
            throw new RuntimeException("Cliente não possui um CPF");
        }
        if(clienteDTO.getNome()==null){
            throw new RuntimeException("Cliente não possui um nome");
        }
        if(clienteDTO.getNome().length()<3 || clienteDTO.getNome().length() > 50){
            throw new RuntimeException("Nome do cliente está errado (de 3 a 50 caracteres!)");
        }
        if(clienteRepository.findByCpf(clienteDTO.getCpf())!=null){
            throw new RuntimeException("O CPF já existe");
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
        if(clienteDTO.getCpf()==null){
            throw new RuntimeException("Cliente não possui um CPF");
        }
        if (!this.validaCPF.isCPF(clienteDTO.getCpf())) {
            throw new RuntimeException("Cpf do cliente está incorreto");
        }
        if(clienteDTO.getNome()==null){
            throw new RuntimeException("cliente não possui um nome");
        }
        if(clienteDTO.getNome().length()<3 || clienteDTO.getNome().length() > 50){
            throw new RuntimeException("Nome do cliente está errado (de 3 a 50 caracteres!)");
        }
        if(clienteRepository.findByCpf(clienteDTO.getCpf())!=null){
            throw new RuntimeException("O CPF já existe");
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
