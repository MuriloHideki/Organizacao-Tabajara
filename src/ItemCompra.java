public class ItemCompra {
    private int quantidade;
    private String nomeProduto;
    private float precoUnitario;
    private float valorTotal;

    public ItemCompra() {
    }

    public ItemCompra(int quantidade, String nomeProduto, float precoUnitario) {
        this.quantidade = quantidade;
        this.nomeProduto = nomeProduto;
        this.precoUnitario = precoUnitario;
        this.valorTotal = valorTotal * quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public float getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(float precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

}
