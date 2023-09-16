package pizzaria.pizzaria.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.dto.SaborDTO;
import pizzaria.pizzaria.entity.SaborEntity;
import pizzaria.pizzaria.repository.SaborRepository;

@Service
public class SaborService {
    @Autowired
    private SaborRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public SaborDTO create(SaborDTO saborDTO) {
        if (saborDTO.getId() != null) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Deixe o campo Id vago, ele é gerado pelo banco");
        }
        return modelMapper.map(repository.save(modelMapper.map(saborDTO, SaborEntity.class)), SaborDTO.class);
    }

    @Transactional
    public SaborDTO update(Long id, SaborDTO dto) {
        SaborEntity dataBase = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço de id não encontrado!!!"));
        dto.setCadastro(dataBase.getCadastro());
        return modelMapper.map(repository.save(modelMapper.map(dto, SaborEntity.class)), SaborDTO.class);
    }
}
