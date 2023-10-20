import java.time.LocalDate;

public class PessoaFisica extends Cliente {
    private long cpf;
    private int quantidadeMaximaParcelas;

    public String paraString() {
        return "*PessoaFisica" +
                "\nNome: " + super.getNome() +
                "\nData de Cadastro: " + super.getDataCadastro() +
                "\nCPF: " + cpf +
                "\nQuantidade máxima de parcelas: " + quantidadeMaximaParcelas +
                "\n" +
                "\nEndereço" +
                "\n     CEP: " + getEndereco().getCep() +
                "\n     Estado: " + getEndereco().getEstado() +
                "\n     Cidade: " + getEndereco().getCidade() +
                "\n     Bairro: " + getEndereco().getBairro() +
                "\n     Rua: " + getEndereco().getRua() +
                "\n     Número: " + getEndereco().getNumero();
    }

    public PessoaFisica() {
    }

    public PessoaFisica(String nome, Endereco endereco, LocalDate dataCadastro, long cpf,
            int quantidadeMaximaParcelas) {
        super.setNome(nome);
        super.setEndereco(endereco);
        super.setDataCadastro(dataCadastro);
        this.cpf = cpf;
        this.quantidadeMaximaParcelas = quantidadeMaximaParcelas;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public int getQuantidadeMaximaParcelas() {
        return quantidadeMaximaParcelas;
    }

    public void setQuantidadeMaximaParcelas(int quantidadeMaximaParcelas) {
        this.quantidadeMaximaParcelas = quantidadeMaximaParcelas;
    }

}
