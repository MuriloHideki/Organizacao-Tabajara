import java.time.LocalDate;

public class ProdutoPerecivel extends Produto {
    private LocalDate validade;

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public boolean estaVencido() {
        return validade.isBefore(LocalDate.now());
    }
}
