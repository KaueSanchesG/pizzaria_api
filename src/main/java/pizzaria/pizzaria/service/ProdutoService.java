package pizzaria.pizzaria.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pizzaria.pizzaria.dto.ProdutoDTO;
import pizzaria.pizzaria.entity.ProdutoEntity;
import pizzaria.pizzaria.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public ProdutoDTO create(ProdutoDTO dto) {
        if (dto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deixe o campo Id vago, ele é gerado pelo banco");
        }
        return modelMapper.map(repository.save(modelMapper.map(dto, ProdutoEntity.class)), ProdutoDTO.class);
    }

    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto) {
        ProdutoEntity dataBase = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado"));
        dto.setCadastro(dataBase.getCadastro());
        return modelMapper.map(repository.save(modelMapper.map(dto, ProdutoEntity.class)), ProdutoDTO.class);
    }
}
