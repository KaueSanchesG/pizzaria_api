package pizzaria.pizzaria.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pizzaria.pizzaria.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    List<PedidoEntity> findByAtivoTrue();

    @Query("SELECT p FROM PedidoEntity p WHERE p.cliente.nome = :nome")
    List<PedidoEntity> findByNomeCliente(String nome);

    @Query("SELECT p FROM PedidoEntity p WHERE p.funcionario.nome = :nome")
    List<PedidoEntity> findByNomeFuncionario(String nome);

    List<PedidoEntity> findByEntregaTrueAndAtivoTrue();

    @Query("SELECT p FROM PedidoEntity p WHERE " +
            "EXTRACT(YEAR FROM p.dataHora) = :ano AND " +
            "EXTRACT(MONTH FROM p.dataHora) = :mes AND " +
            "EXTRACT(DAY FROM p.dataHora) = :dia")
    List<PedidoEntity> findByData(@Param("ano") int ano, @Param("mes") int mes, @Param("dia") int dia);
}
