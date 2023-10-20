public class Endereco {
    private String rua;
    private int numero;
    private String bairro;
    private long cep;
    private String cidade;
    private String estado;

    public Endereco(String rua, int numero, String bairro, long cep, String cidade, String estado) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    // TODO corrigir paraString para printar as informações do endereço mais
    // bonitinho
    public String paraString() {
        return "Endereco [rua=" + rua + ", numero=" + numero + ", bairro=" + bairro + ", cep=" + cep + ", cidade="
                + cidade + ", estado=" + estado + "]";
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public long getCep() {
        return cep;
    }

    public void setCep(long cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
