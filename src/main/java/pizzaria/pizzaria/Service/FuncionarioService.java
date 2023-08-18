package pizzaria.pizzaria.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaria.pizzaria.DTO.ClienteDTO;
import pizzaria.pizzaria.DTO.FuncionarioDTO;
import pizzaria.pizzaria.Entity.ClienteEntity;
import pizzaria.pizzaria.Entity.FuncionarioEntity;
import pizzaria.pizzaria.Repository.FuncionarioRepository;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public FuncionarioEntity create(FuncionarioDTO funcionarioDTO) {
        if (funcionarioDTO.getId() != null) {
            throw new RuntimeException("Deixe o campo Id vago, ele é gerado pelo banco");
        }
        if(funcionarioDTO.getNome()==null){
            throw new RuntimeException("Funcionario não possui um nome");
        }
        if(funcionarioDTO.getNome().length()<3 || funcionarioDTO.getNome().length() > 50){
            throw new RuntimeException("Nome do Funcionario está errado (de 3 a 50 caracteres!)");
        }
        FuncionarioEntity funcionario = modelMapper.map(funcionarioDTO, FuncionarioEntity.class);
        return funcionarioRepository.save(funcionario);
    }

    @Transactional
    public void update(Long id, FuncionarioDTO funcionarioDTO) {
        FuncionarioEntity funcionarioBanco = funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Endereço de id não encontrado!!!"));
        if (!funcionarioBanco.getId().equals(funcionarioDTO.getId())) {
            throw new RuntimeException("Não foi possivel encontrar o registro!!!");
        }
        if(funcionarioDTO.getNome()==null){
            throw new RuntimeException("Funcionario não possui um nome");
        }
        if(funcionarioDTO.getNome().length()<3 || funcionarioDTO.getNome().length() > 50){
            throw new RuntimeException("Nome do Funcionario está errado (de 3 a 50 caracteres!)");
        }
        modelMapper.map(funcionarioDTO, funcionarioBanco);
        funcionarioRepository.save(funcionarioBanco);
    }

    @Transactional
    public void delete(Long id) {
        FuncionarioEntity funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o registro informado"));
        funcionarioRepository.delete(funcionario);
    }
}
