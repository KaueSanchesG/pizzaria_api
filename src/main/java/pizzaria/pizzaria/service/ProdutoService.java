package pizzaria.pizzaria.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaria.pizzaria.dto.ProdutoDTO;
import pizzaria.pizzaria.entity.ProdutoEntity;
import pizzaria.pizzaria.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public ProdutoEntity create(ProdutoDTO produtoDTO) {
        if (produtoDTO.getId() != null) {
            throw new RuntimeException("Deixe o campo Id vago, ele é gerado pelo banco");
        }
        if(produtoDTO.getValor() != 0) {
            throw new RuntimeException("Valor nao pode ser 0");
        }
        ProdutoEntity produto = modelMapper.map(produtoDTO, ProdutoEntity.class);
        return produtoRepository.save(produto);
    }

    @Transactional
    public void update(Long id, ProdutoDTO produtoDTO) {
        ProdutoEntity produtoBanco = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Endereço de id não encontrado!!!"));
        if (!produtoBanco.getId().equals(produtoDTO.getId())) {
            throw new RuntimeException("Não foi possivel encontrar o registro!!!");
        }
        if(produtoDTO.getValor() != 0) {
            throw new RuntimeException("Valor nao pode ser 0");
        }
        modelMapper.map(produtoDTO, produtoBanco);
        produtoRepository.save(produtoBanco);
    }

    @Transactional
    public void delete(Long id) {
        ProdutoEntity produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o registro informado"));
        produtoRepository.delete(produto);
    }
}
