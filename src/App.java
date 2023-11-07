import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class App {
    /**
     * @param args
     * @throws Exception
     */

     // Criando uma lista de Clientes
    public static ArrayList<Cliente> listClientes = new ArrayList<Cliente>();

    // Criando uma lista de Produtos
    public static ArrayList<Produto> listProdutos = new ArrayList<Produto>();

    public static void main(String[] args) throws Exception {
        

        String[] opcoes = {
                "1 - Cadastros de Clientes",
                "2 - Deletar cliente pelo CPF ou CNPJ",
                "3 - Deletar cliente pelo nome",
                "4 - Cadastro de Produtos",
                "5 - Efetuação de uma compra",
                "6 - Atualização da situação de pagamento de uma compra",
                "7 - Relatórios",
                "Sair" };

        while (true) {
            String escolha = JOptionPane.showInputDialog(null, "Escolha uma opção:", "Menu inicial",
                    JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]).toString();

            if (escolha.equals("Sair") || escolha == null) {
                JOptionPane.showMessageDialog(null, "Fechando");
                break;
            } else if (escolha.equals("1 - Cadastros de Clientes")) {
                CadastrarClientes();
            }
            else if (escolha.equals("2 - Deletar cliente pelo CPF ou CNPJ")) {
                ExcluirClientePorCPFCNPJ();
            }
            else if(escolha.equals("3 - Deletar cliente pelo nome")){
                ExcluirClientePorNome();
            }
            else if(escolha.equals("4 - Cadastro de Produtos")){
                cadastrarProdutos();
            }
            else if (escolha.equals("7 - Relatórios")) {
                String[] subOpcoes = {
                        "(a) Relação de todos os Clientes que possuem o nome iniciado por uma determinada sequência de caracteres",
                        "(b) Relação de todos os Produtos",
                        "(c) Busca de um produto pelo nome",
                        "(d) Relação de produtos que são perecíveis e que estão com a data de validade vencida",
                        "(e) Relação de todas as compras",
                        "(f) Busca de uma compra pelo número",
                        "(g) Relação de todas as compras que não foram pagas ainda",
                        "(h) Relação das 10 últimas compras pagas",
                        "(i) Apresentação das informações da compra mais cara",
                        "(j) Apresentação das informações da compra mais barata",
                        "(k) Relação do valor total de compras feitas em cada mês nos últimos 12 meses",
                        "Voltar" };

                while (true) {
                    String subEscolha = JOptionPane.showInputDialog(null, "Escolha uma opção:", "7 - Relatórios",
                            JOptionPane.PLAIN_MESSAGE, null, subOpcoes, subOpcoes[0]).toString();

                    if (subEscolha.equals("Voltar") || subEscolha == null) {
                        JOptionPane.showMessageDialog(null, "Voltando...");
                        break;
                    }
                }
            }
        }
    }

    public static int getInt(String mensagem, String titulo) {
        while (true) {
            try {
                // Exibe a caixa de diálogo para o usuário inserir um valor do tipo inteiro.
                String inputStr = JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.PLAIN_MESSAGE);

                // TO DO - Implementar um método de retorno ao menu inicial ao clicar em
                // 'cancel'.
                if (inputStr == null) {
                    // Verifica se o usuário clicou em 'cancel'.
                    JOptionPane.showMessageDialog(null, "Cancelando...");
                    System.exit(1);
                }

                // Tenta converter um valor string em um valor do tipo int.
                int numero = Integer.parseInt(inputStr);

                // Caso a conversão funcione corretamente, retorna um número inteiro.
                return numero;

            } catch (NumberFormatException entradaInvalida) {
                // Caso o valor de entrada do usuário for inválido
                JOptionPane.showMessageDialog(null, "Por favor, digite um valor válido.", "Valor inválido",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static Long getLong(String mensagem, String titulo) {
        while (true) {
            try {
                String inputStr = JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.PLAIN_MESSAGE);

                // TO DO - Implementar um método de retorno ao menu inicial ao clicar em
                // 'cancel'.
                if (inputStr == null) {
                    // Verifica se o usuário clicou em 'cancel'.
                    JOptionPane.showMessageDialog(null, "Cancelando...");
                    System.exit(1);
                }
                // Tenta converter um valor string em um valor do tipo long.
                long numero = Long.parseLong(inputStr);

                // Caso a conversão funcione corretamente, retorna um número do tipo longint.
                return numero;

            } catch (NumberFormatException entradaInvalida) {
                // Caso o valor de entrada do usuário for inválido
                JOptionPane.showMessageDialog(null, "Por favor, digite um valor válido.", "Valor inválido",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Método para converter para float, usado no cadastro de produtos
    public static float getFloat(String mensagem, String titulo) {
        while (true) {
            try {
                String inputStr = JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.PLAIN_MESSAGE);

                // TO DO - Implementar um método de retorno ao menu inicial ao clicar em
                // 'cancel'.
                if (inputStr == null) {
                    // Verifica se o usuário clicou em 'cancel'.
                    JOptionPane.showMessageDialog(null, "Cancelando...");
                    System.exit(1);
                }
                // Tenta converter um valor string em um valor do tipo long.
                float numero = Float.parseFloat(inputStr);

                // Caso a conversão funcione corretamente, retorna um número do tipo longint.
                return numero;

            } catch (NumberFormatException entradaInvalida) {
                // Caso o valor de entrada do usuário for inválido
                JOptionPane.showMessageDialog(null, "Por favor, digite um valor válido.", "Valor inválido",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static String getString(String mensagem, String titulo) {
        return (JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.PLAIN_MESSAGE));
    }

    private static Endereco getEndereco() {
        String titulo = "Endereço";

        String rua = getString("Rua:", titulo);
        int numero = getInt("Número:", titulo);
        String bairro = getString("Bairro:", titulo);
        Long cep = getLong("CEP:", titulo);
        String cidade = getString("Cidade:", titulo);
        String estado = getString("Estado:", titulo);

        /*
         * String rua = JOptionPane.showInputDialog(null, "Rua:", "Endereço", 1);
         * int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Número:",
         * "Endereço", 1));
         * String bairro = JOptionPane.showInputDialog(null, "Bairro:");
         * Long cep = Long.parseLong(JOptionPane.showInputDialog(null, "CEP:"));
         * String cidade = JOptionPane.showInputDialog(null, "Cidade:");
         * String estado = JOptionPane.showInputDialog(null, "Estado:");
         */

        return new Endereco(rua, numero, bairro, cep, cidade, estado);
    }

    private static LocalDate getData() {

        String titulo = "Data atual";

        int dia = getInt("Dia:", titulo);
        int mes = getInt("Mês:", titulo);
        int ano = getInt("Ano:", titulo);

        return LocalDate.of(ano, mes, dia);
    }

    // Método para data validade dos produtos
    private static LocalDate getDataValidade() {

        String titulo = "Data de Validade";

        int dia = getInt("Dia:", titulo);
        int mes = getInt("Mês:", titulo);
        int ano = getInt("Ano:", titulo);

        return LocalDate.of(ano, mes, dia);
    }
    //Organizando as ações do menu em métodos específicos
    public static void CadastrarClientes()
    {
        String[] tipoCliente = {"Pessoa Física", "Pessoa Jurídica"};
        int tipoEscolhido = JOptionPane.showOptionDialog(null, "Escolha o tipo de cliente:", "Tipo de Cliente",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tipoCliente, tipoCliente[0]);
        
        if (tipoEscolhido == 0) {
            String nome = getString("Nome:", "Dados pessoais");
            Long cpf = getLong("CPF:", "Dados pessoais");
            int quantidadaeMaximaParcelas = getInt("Quantidade máxima de parcelas: ",
                    "Financeiro");

            Endereco endereco = getEndereco();

            LocalDate dataCadastro = getData();

            PessoaFisica pessoa = new PessoaFisica(nome, endereco, dataCadastro, cpf,
                    quantidadaeMaximaParcelas);

            listClientes.add(pessoa);//adicionando cliente à lista
            JOptionPane.showMessageDialog(null, pessoa.paraString());
        } else {
            String nome = getString("Nome: ", "Dados pessoais");
            long cnpj = getLong("CNPJ: ", "Dados pessoais");
            int prazoMaximoPagamento = getInt("Prazo máximo para o pagamento: ",
                    "Financeiro");
            String razaoSocial = getString("Razão social: ", "Dados pessoais");

            Endereco endereco = getEndereco();
            LocalDate dataCadastro = getData();

            PessoaJuridica pessoa = new PessoaJuridica(nome, endereco, dataCadastro, cnpj, razaoSocial,
                    prazoMaximoPagamento);
            listClientes.add(pessoa);//adicionando cliente à lista
            JOptionPane.showMessageDialog(null, pessoa.paraString());
        }
    }

    public static void ExcluirClientePorCPFCNPJ()
    {
        String[] tipoCliente = { "Pessoa Física", "Pessoa Jurídica" };
        int tipoEscolhido = JOptionPane.showOptionDialog(null, "Escolha o tipo de cliente:", "Tipo de Cliente",
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tipoCliente, tipoCliente[0]);
        if (tipoEscolhido == 0) {
            long cpf = getLong("CPF:","Dados pessoais");
            // Criando variável boolean como false, para que quando encontrar cliente alterar para true
            boolean blnEncontrou = false;
            for (Cliente cliente : listClientes) {
                // Verifica se o cliente é do tipo PessoaFisica e se o CPF corresponde
                if (cliente instanceof PessoaFisica && ((PessoaFisica) cliente).getCpf() == cpf) {
                    // Remove o cliente da lista
                    listClientes.remove(cliente);
                    blnEncontrou = true;
                    JOptionPane.showMessageDialog(null, "Cliente removido com sucesso.");
                    break; // Encerra o loop assim que o cliente é removido
                }
            }
            // Se não encontrou o cliente, exibe mensagem ao usuário
            if(!blnEncontrou)
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
        }
        else{
            long cnpj = getLong("CNPJ:","Dados pessoais");
            boolean blnEncontrou = false;
            for (Cliente cliente : listClientes) {
                // Verifica se o cliente é do tipo PessoaFisica e se o CPF corresponde
                if (cliente instanceof PessoaJuridica && ((PessoaJuridica) cliente).getCnpj() == cnpj) {
                    // Remove o cliente da lista
                    listClientes.remove(cliente);
                    blnEncontrou = true;
                    JOptionPane.showMessageDialog(null, "Cliente removido com sucesso.");
                    break; // Encerra o loop assim que o cliente é removido
                }
            }

            if(!blnEncontrou)
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
        }
    }

    public static void ExcluirClientePorNome()
    {
        String nome = getString("Nome: ", "Dados pessoais");
        boolean blnEncontrou = false;
        for (Cliente cliente : listClientes) {
            // Verifica se o cliente é do tipo PessoaFisica e se o nome corresponde
            if ((cliente instanceof PessoaFisica && ((PessoaFisica) cliente).getNome().equals(nome)) ||
            (cliente instanceof PessoaJuridica && ((PessoaJuridica) cliente).getNome().equals(nome))) {
                // Remove o cliente da lista
                listClientes.remove(cliente);
                blnEncontrou = true;
                JOptionPane.showMessageDialog(null, "Cliente removido com sucesso.");
                break; // Encerra o loop assim que o cliente é removido
            }
        }
        // Se não encontrou o cliente, exibe mensagem ao usuário
        if(!blnEncontrou)
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
    }

    public static void cadastrarProdutos() {
    String[] tipoProduto = { "Produto Comum", "Produto Perecível" };
    int tipoEscolhido = JOptionPane.showOptionDialog(null, "Escolha o tipo de produto:", "Tipo de Produto",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tipoProduto, tipoProduto[0]);

    if (tipoEscolhido == 0) {
        String codigo = getString("Código do produto: ", "Dados do Produto");
        String nome = getString("Nome do produto: ", "Dados do Produto");
        String descricao = getString("Descrição: ", "Dados do Produto");
        float preco = getFloat("Preço: ", "Dados do Produto");

        Produto produto = new Produto(codigo, nome, descricao, preco); // Produtos comuns não possuem validade

        listProdutos.add(produto);
        JOptionPane.showMessageDialog(null, produto.paraString());
    } else {
        String codigo = getString("Código do produto: ", "Dados do Produto");
        String nome = getString("Nome do produto: ", "Dados do Produto");
        String descricao = getString("Descrição: ", "Dados do Produto");
        float preco = getFloat("Preço: ", "Dados do Produto");

        LocalDate dataValidade = getDataValidade();

        ProdutoPerecivel produtoPerecivel = new ProdutoPerecivel(codigo, nome, descricao, preco, dataValidade);

        listProdutos.add(produtoPerecivel);
        JOptionPane.showMessageDialog(null, produtoPerecivel.paraString());
    }
}

}
