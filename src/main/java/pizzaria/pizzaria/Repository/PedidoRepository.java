package pizzaria.pizzaria.Repository;

import pizzaria.pizzaria.Entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}
