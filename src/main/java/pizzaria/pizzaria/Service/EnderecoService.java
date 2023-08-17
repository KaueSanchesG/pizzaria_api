package pizzaria.pizzaria.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaria.pizzaria.DTO.EnderecoDTO;
import pizzaria.pizzaria.Entity.EnderecoEntity;
import pizzaria.pizzaria.Repository.EnderecoRepository;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public EnderecoEntity create(EnderecoDTO enderecoDTO) {
        if (enderecoDTO.getId() != null) {
            throw new RuntimeException("Deixe o campo Id vago, ele é gerado pelo banco");
        }
        EnderecoEntity endereco = modelMapper.map(enderecoDTO, EnderecoEntity.class);
        return enderecoRepository.save(endereco);
    }

    @Transactional
    public void update(Long id, EnderecoDTO enderecoDTO) {
        EnderecoEntity enderecoBanco = enderecoRepository.findById(id).orElseThrow(() -> new RuntimeException("Endereço de id não encontrado!!!"));
        if (!enderecoBanco.getId().equals(enderecoDTO.getId())) {
            throw new RuntimeException("Não foi possivel encontrar o registro!!!");
        }
        modelMapper.map(enderecoDTO, enderecoBanco);
        enderecoRepository.save(enderecoBanco);
    }

    @Transactional
    public void delete(Long id) {
        EnderecoEntity endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o registro informado"));
        enderecoRepository.delete(endereco);
    }
}
