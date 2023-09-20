package pizzaria.pizzaria.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.dto.EnderecoDTO;
import pizzaria.pizzaria.entity.EnderecoEntity;
import pizzaria.pizzaria.repository.EnderecoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<EnderecoDTO> getAll() {
        List<EnderecoDTO> listDTO = new ArrayList<>();
        for (EnderecoEntity entity : repository.findAll()) {
            EnderecoDTO map = modelMapper.map(entity, EnderecoDTO.class);
            listDTO.add(map);
        }
        return listDTO;
    }

    @Transactional(readOnly = true)
    public EnderecoDTO getId(Long id) {
        EnderecoEntity entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));
        return modelMapper.map(entity, EnderecoDTO.class);
    }

    @Transactional
    public EnderecoDTO create(EnderecoDTO enderecoDTO) {
        if (enderecoDTO.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deixe o campo Id vago, ele é gerado pelo banco");
        }
        return modelMapper.map(repository.save(modelMapper.map(enderecoDTO, EnderecoEntity.class)), EnderecoDTO.class);
    }

    @Transactional
    public EnderecoDTO update(Long id, EnderecoDTO enderecoDTO) {
        EnderecoEntity dataBase = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));
        if (!dataBase.getId().equals(enderecoDTO.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O endereço não consta no banco!!");
        }
        enderecoDTO.setCadastro(dataBase.getCadastro());
        return modelMapper.map(repository.save(modelMapper.map(enderecoDTO, EnderecoEntity.class)), EnderecoDTO.class);
    }
}
