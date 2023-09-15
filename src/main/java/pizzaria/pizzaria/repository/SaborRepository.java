package pizzaria.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaria.pizzaria.entity.SaborEntity;

public interface SaborRepository extends JpaRepository<SaborEntity, Long> {
}
