import java.time.LocalDate;

public class ProdutoPerecivel extends Produto {
    private LocalDate validade;

    public ProdutoPerecivel(String codigo, String nome, String descricao, float preco, LocalDate validade) {
        super(codigo, nome, descricao, preco);
        this.validade = validade;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public boolean estaVencido() {
        return validade.isBefore(LocalDate.now());
    }

    @Override
    public String paraString() {
        return "Produto perecivel," + getCodigo() + "," + getNome() + "," + getDescricao() + "," + getPreco() + "," + getValidade();
        /*
        return "*Produto Perecível:" +
                "\nCódigo do produto: " + getCodigo() +
                "\nNome: " + getNome() +
                "\nDescrição: " + getDescricao() +
                "\nPreço: " + getPreco() +
                "\nValidade: " + getValidade();
                 */
    }
}
