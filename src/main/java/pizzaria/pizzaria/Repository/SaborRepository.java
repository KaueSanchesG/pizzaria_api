package pizzaria.pizzaria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaria.pizzaria.Entity.SaborEntity;

public interface SaborRepository extends JpaRepository<SaborEntity, Long> {
}
