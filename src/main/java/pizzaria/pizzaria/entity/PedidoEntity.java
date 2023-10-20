    package pizzaria.pizzaria.entity;

    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    import java.time.LocalDateTime;
    import java.util.List;

    @Entity
    @Getter
    @Setter
    @Table(name = "Pedido", schema = "pizzaria")
    public class PedidoEntity extends AbstractEntity {
        @ManyToOne
        @JoinColumn(name = "cliente_id", nullable = false)
        private ClienteEntity cliente;

        @OneToMany
        @JoinColumn(name = "produto_id")
        private List<ProdutoEntity> produtoList;

        @OneToMany
        @JoinColumn(name = "pizza_id")
        private List<PizzaEntity> pizzaList;

        @Column
        private Boolean entrega;

        @Column
        private String formaDePagamento;

        @Column
        private Double valorTotal;

        @Column
        private LocalDateTime dataHora;

        @ManyToOne
        @JoinColumn(name = "funcionario_id", nullable = false)
        private FuncionarioEntity funcionario;


    }
