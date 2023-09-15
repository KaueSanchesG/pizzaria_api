package pizzaria.pizzaria.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pizzaria.pizzaria.dto.AbstractDTO;
import pizzaria.pizzaria.entity.AbstractEntity;

@Configuration
public class Mapper {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(AbstractEntity.class, AbstractDTO.class)
                .addMapping(AbstractEntity::getId, AbstractDTO::setId);
        return modelMapper;
    }
}