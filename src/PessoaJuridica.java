public class PessoaJuridica extends Cliente {
    private int cnpj;
    private String razaoSocial;
    private int prazoMaximoPagamento;

    // TODO corrigir paraString para printar todas as informações do cliente
    public String paraString() {
        return "PessoaJuridica [cnpj=" + cnpj + ", razaoSocial=" + razaoSocial + ", prazoMaximoPagamento="
                + prazoMaximoPagamento + "]";
    }

    public PessoaJuridica() {
    }

    public PessoaJuridica(int cnpj, String razaoSocial, int prazoMaximoPagamento) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.prazoMaximoPagamento = prazoMaximoPagamento;
    }

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public int getPrazoMaximoPagamento() {
        return prazoMaximoPagamento;
    }

    public void setPrazoMaximoPagamento(int prazoMaximoPagamento) {
        this.prazoMaximoPagamento = prazoMaximoPagamento;
    }

}