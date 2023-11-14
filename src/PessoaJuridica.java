import java.time.LocalDate;

public class PessoaJuridica extends Cliente {
    private long cnpj;
    private String razaoSocial;
    private int prazoMaximoPagamento;

    // Corrigindo o T O D O
     public String paraString() {
        return "Pessoa Juridica," + super.getNome() + "," + super.getEndereco().getCep() + "," + super.getEndereco().getBairro() + "," + super.getEndereco().getCidade() + "," + super.getEndereco().getEstado() + "," + super.getEndereco().getNumero() + "," + super.getEndereco().getRua() + "," + super.getDataCadastro() + "," + getCnpj() + "," + getRazaoSocial() + "," + getPrazoMaximoPagamento();
        /*
            return "*Pessoa Jurídica:" +
                    "\nNome Fantasia: " + super.getNome() +
                    "\nData de Cadastro: " + super.getDataCadastro() +
                    "\nCNPJ: " + cnpj +
                    "\nRazão Social: " + razaoSocial +
                    "\nPrazo máximo para pagamento: " + prazoMaximoPagamento +
                    "\n" +
                    "\nEndereço" +
                    "\n     CEP: " + getEndereco().getCep() +
                    "\n     Estado: " + getEndereco().getEstado() +
                    "\n     Cidade: " + getEndereco().getCidade() +
                    "\n     Bairro: " + getEndereco().getBairro() +
                    "\n     Rua: " + getEndereco().getRua() +
                    "\n     Número: " + getEndereco().getNumero();
        */
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