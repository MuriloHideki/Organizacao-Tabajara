import java.time.LocalDate;

public class Produto {
    private String codigo;
    private String nome;
    private String descricao;
    private float preco;
    private LocalDate validade;
    private boolean estaVencido;

    public Produto() {
    }

    public Produto(String codigo, String nome, String descricao, float preco, LocalDate validade) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.validade = validade;
    }

    public String paraString() {
            return "*Produto:" +
                    "\nCódigo do produto: " + getCodigo() +
                    "\nNome: " + getNome() +
                    "\nDescrição: " + getDescricao() +
                    "\nPreço: " + getPreco() +
                    "\nValidade: " + getValidade();
        }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    //Copiado de ProdutoPerecível
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
