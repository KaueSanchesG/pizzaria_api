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

import java.util.ArrayList;
import java.util.List;

@Service
public class SaborService {
    @Autowired
    private SaborRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<SaborDTO> getAll() {
        List<SaborDTO> listDTO = new ArrayList<>();
        for (SaborEntity entity : repository.findAll()) {
            SaborDTO map = modelMapper.map(entity, SaborDTO.class);
            listDTO.add(map);
        }
        return listDTO;
    }

    @Transactional(readOnly = true)
    public SaborDTO getId(Long id) {
        SaborEntity entity = this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));
        return modelMapper.map(entity, SaborDTO.class);
    }

    @Transactional
    public SaborDTO create(SaborDTO saborDTO) {
        if (saborDTO.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deixe o campo Id vago, ele é gerado pelo banco");
        }
        return modelMapper.map(repository.save(modelMapper.map(saborDTO, SaborEntity.class)), SaborDTO.class);
    }

    @Transactional
    public SaborDTO update(Long id, SaborDTO dto) {
        SaborEntity dataBase = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));
        dto.setCadastro(dataBase.getCadastro());
        return modelMapper.map(repository.save(modelMapper.map(dto, SaborEntity.class)), SaborDTO.class);
    }
}
