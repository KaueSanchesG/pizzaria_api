package pizzaria.pizzaria.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaria.pizzaria.dto.EnderecoDTO;
import pizzaria.pizzaria.entity.EnderecoEntity;
import pizzaria.pizzaria.repository.EnderecoRepository;

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
        if(enderecoDTO.getRua()==null){
            throw new RuntimeException("não possui um endereco");
        }
        if(enderecoDTO.getRua().length()<3 || enderecoDTO.getRua().length() > 50){
            throw new RuntimeException("Nome da rua está errado (de 3 a 50 caracteres!)");
        }
        if(enderecoDTO.getNumero() == 0){
            throw new RuntimeException(" não possui um Numero");
        }
        if(enderecoDTO.getNumero()<3 || enderecoDTO.getNumero() > 5){
            throw new RuntimeException("Numero do seu endereco está errado (de 3 a 5 caracteres!)");
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
        if(enderecoDTO.getRua()==null){
            throw new RuntimeException(" não possui um endereco");
        }
        if(enderecoDTO.getRua().length()<3 || enderecoDTO.getRua().length() > 50){
            throw new RuntimeException("Nome da rua está errado (de 3 a 50 caracteres!)");
        }
        if(enderecoDTO.getNumero() == 0){
            throw new RuntimeException(" não possui um Numero");
        }
        if(enderecoDTO.getNumero()<3 || enderecoDTO.getNumero() > 5){
            throw new RuntimeException("Numero do seu endereco está errado (de 3 a 5 caracteres!)");
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
