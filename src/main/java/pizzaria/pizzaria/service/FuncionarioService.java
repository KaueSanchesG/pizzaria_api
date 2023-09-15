package pizzaria.pizzaria.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.dto.FuncionarioDTO;
import pizzaria.pizzaria.entity.FuncionarioEntity;
import pizzaria.pizzaria.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public FuncionarioDTO create(FuncionarioDTO dto) {
        if (dto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deixe o campo Id vago, ele é gerado pelo banco");
        }
        return modelMapper.map(repository.save(modelMapper.map(dto, FuncionarioEntity.class)), FuncionarioDTO.class);
    }

    @Transactional
    public FuncionarioDTO update(Long id, FuncionarioDTO dto) {
        FuncionarioEntity dataBase = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço de id não encontrado!!!"));
        dto.setCadastro(dataBase.getCadastro());
        return modelMapper.map(repository.save(modelMapper.map(dto, FuncionarioEntity.class)), FuncionarioDTO.class);
    }
}
