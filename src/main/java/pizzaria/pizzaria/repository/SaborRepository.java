package pizzaria.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaria.pizzaria.entity.SaborEntity;

import java.util.List;

public interface SaborRepository extends JpaRepository<SaborEntity, Long> {
    List<SaborEntity> findByNome(String nome);
}
