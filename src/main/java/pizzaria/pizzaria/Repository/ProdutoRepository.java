package pizzaria.pizzaria.Repository;

import pizzaria.pizzaria.Entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}
