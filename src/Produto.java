public class Produto {
    private String codigo;
    private String nome;
    private String descricao;
    private float preco;

    public Produto() {
    }

    public Produto(String codigo, String nome, String descricao, float preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public String paraString() {
            return "*Produto:" +
                    "\nCódigo do produto: " + getCodigo() +
                    "\nNome: " + getNome() +
                    "\nDescrição: " + getDescricao() +
                    "\nPreço: " + getPreco();
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

}
