package pizzaria.pizzaria.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaria.pizzaria.DTO.ProdutoDTO;
import pizzaria.pizzaria.DTO.SaborDTO;
import pizzaria.pizzaria.Entity.ProdutoEntity;
import pizzaria.pizzaria.Entity.SaborEntity;
import pizzaria.pizzaria.Repository.SaborRepository;
@Service
public class    SaborService {
    @Autowired
    private SaborRepository saborRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Transactional
    public SaborEntity create(SaborDTO saborDTO) {
        if (saborDTO.getId() != null) {
            throw new RuntimeException("Deixe o campo Id vago, ele é gerado pelo banco");
        }
        if(saborDTO.getNome()==null){
            throw new RuntimeException("Sabor não possui um nome");
        }
        if(saborDTO.getNome().length()<3 || saborDTO.getNome().length() > 50){
            throw new RuntimeException("Nome do Sabor está errado (de 3 a 50 caracteres!)");
        }
        SaborEntity sabor = modelMapper.map(saborDTO, SaborEntity.class);
        return saborRepository.save(sabor);
    }

    @Transactional
    public void update(Long id, SaborDTO saborDTO) {
        SaborEntity saborBanco = saborRepository.findById(id).orElseThrow(() -> new RuntimeException("Endereço de id não encontrado!!!"));
        if (!saborBanco.getId().equals(saborDTO.getId())) {
            throw new RuntimeException("Não foi possivel encontrar o registro!!!");
        }
        if(saborDTO.getNome()==null){
            throw new RuntimeException("Sabor não possui um nome");
        }
        if(saborDTO.getNome().length()<3 || saborDTO.getNome().length() > 50){
            throw new RuntimeException("Nome do Sabor está errado (de 3 a 50 caracteres!)");
        }
        modelMapper.map(saborDTO, saborBanco);
        saborRepository.save(saborBanco);
    }

    @Transactional
    public void delete(Long id) {
        SaborEntity sabor = saborRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o registro informado"));
        saborRepository.delete(sabor);
    }
}
