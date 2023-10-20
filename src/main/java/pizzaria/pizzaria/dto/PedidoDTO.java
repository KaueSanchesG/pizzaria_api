    package pizzaria.pizzaria.dto;

    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.NotNull;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import org.hibernate.annotations.CreationTimestamp;

    import java.time.LocalDateTime;
    import java.util.List;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class PedidoDTO extends AbstractDTO{
        @JsonIgnoreProperties("pedidoList")
        @NotNull(message = "Deve conter um cliente")
        private ClienteDTO cliente;
        @JsonIgnoreProperties("pedidoList")
        private List<ProdutoDTO> produtoList;
        @JsonIgnoreProperties("pedidoList")
        private List<PizzaDTO> pizzaList;
        private Boolean entrega;
        @NotBlank(message = "Informe a forma de pagamento")
        private String formaDePagamento;
        private Double valorTotal;
        @CreationTimestamp
        private LocalDateTime dataHora;
        @JsonIgnoreProperties("pedidoList")
        @NotNull(message = "Deve conter um funcionario")
        private FuncionarioDTO funcionario;
    }
