import java.time.LocalDate;

public class PessoaJuridica extends Cliente {
    private long cnpj;
    private String razaoSocial;
    private int prazoMaximoPagamento;

    // TODO corrigir paraString para printar todas as informações do cliente
    public String paraString() {
        return "*PessoaJuridica" +
                "\nNome: " + super.getNome() +
                "\nData de Cadastro: " + super.getDataCadastro() +
                "\nCNPJ: " + cnpj +
                "\nPrazo máximo para o pagamento: " + prazoMaximoPagamento +
                "\n" +
                "\nEndereço" +
                "\n     CEP: " + getEndereco().getCep() +
                "\n     Estado: " + getEndereco().getEstado() +
                "\n     Cidade: " + getEndereco().getCidade() +
                "\n     Bairro: " + getEndereco().getBairro() +
                "\n     Rua: " + getEndereco().getRua() +
                "\n     Número: " + getEndereco().getNumero();
    }

    public PessoaJuridica() {
    }

    public PessoaJuridica(String nome, Endereco endereco, LocalDate dataCadastro, long cnpj, String razaoSocial,
            int prazoMaximoPagamento) {
        super.setNome(nome);
        super.setEndereco(endereco);
        super.setDataCadastro(dataCadastro);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.prazoMaximoPagamento = prazoMaximoPagamento;
    }

    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(long cnpj) {
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