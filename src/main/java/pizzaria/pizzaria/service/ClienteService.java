package pizzaria.pizzaria.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.dto.ClienteDTO;
import pizzaria.pizzaria.entity.ClienteEntity;
import pizzaria.pizzaria.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public ClienteDTO create(ClienteDTO dto) {
        if (dto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deixe o campo Id vago, ele é gerado pelo banco");
        }
        if (repository.findByCpf(dto.getCpf()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O CPF já existe");
        }
        return modelMapper.map(repository.save(modelMapper.map(dto, ClienteEntity.class)), ClienteDTO.class);
    }

    @Transactional
    public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
        ClienteEntity database = repository.findById(id).orElseThrow(() -> new RuntimeException("Endereço de id não encontrado!!!"));
        if (!database.getId().equals(clienteDTO.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O endereço não consta no banco!!");
        }
        if (repository.findByCpf(clienteDTO.getCpf()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O CPF já existe");
        }
        return modelMapper.map(repository.save(modelMapper.map(clienteDTO, ClienteEntity.class)), ClienteDTO.class);
    }
}