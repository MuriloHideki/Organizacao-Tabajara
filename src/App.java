import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class App {
    /**
     * @param args
     * @throws Exception
     */

    public static ArrayList<Cliente> listClientes = new ArrayList<Cliente>();
    public static ArrayList<Produto> listProdutos = new ArrayList<Produto>();
    public static ArrayList<Compra> listCompras = new ArrayList<Compra>();

    public static void main(String[] args) throws Exception {

        // Leitura do documento baseDados/clientes.txt
        try (BufferedReader br = new BufferedReader(new FileReader("baseDados/clientes.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(",");
                if (campos[0].equals("Pessoa Física")) {
                    String nome = campos[1];
                    long cep = Long.parseLong(campos[2]);
                    String bairro = campos[3];
                    String cidade = campos[4];
                    String estado = campos[5];
                    int numero = Integer.parseInt(campos[6]);
                    String rua = campos[7];
                    LocalDate dataCadastro = LocalDate.parse(campos[8]);
                    long cpf = Long.parseLong(campos[9]);
                    int quantidadeMaximaParcelas = Integer.parseInt(campos[10]);
                    Endereco endereco = new Endereco(rua, numero, bairro, cep, cidade, estado);
                    PessoaFisica pessoa = new PessoaFisica(nome, endereco, dataCadastro, cpf, quantidadeMaximaParcelas);
                    listClientes.add(pessoa);
                } else {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Leitura do documento baseDados/produtos.txt
        try (BufferedReader br = new BufferedReader(new FileReader("baseDados/produtos.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(",");
                if (campos[0].equals("Produto")) {
                    String codigo = campos[1];
                    String nome = campos[2];
                    String descricao = campos[3];
                    float preco = Float.parseFloat(campos[4]);
                    Produto produto = new Produto(codigo, nome, descricao, preco);
                    listProdutos.add(produto);
                } else {
                    String codigo = campos[1];
                    String nome = campos[2];
                    String descricao = campos[3];
                    float preco = Float.parseFloat(campos[4]);
                    LocalDate validade = LocalDate.parse(campos[5]);
                    ProdutoPerecivel produto = new ProdutoPerecivel(codigo, nome, descricao, preco, validade);
                    listProdutos.add(produto);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        File clienteFile = new File("baseDados/clientes.txt");
        if (!clienteFile.exists())
            clienteFile.createNewFile();
        FileWriter clienteWriter = new FileWriter(clienteFile, false);
        BufferedWriter clienteBufferedWriter = new BufferedWriter(clienteWriter);

        File produtoFile = new File("baseDados/produtos.txt");
        if (!produtoFile.exists())
            produtoFile.createNewFile();
        FileWriter produtoWriter = new FileWriter(produtoFile, false);
        BufferedWriter produtoBufferedWriter = new BufferedWriter(produtoWriter);

        File compraFile = new File("baseDados/compras.txt");
        if (!compraFile.exists())
            compraFile.createNewFile();
        FileWriter compraWriter = new FileWriter(compraFile, false);
        BufferedWriter compraBufferedWriter = new BufferedWriter(compraWriter);

        String[] opcoes = {
                "1 - Cadastros de Clientes",
                "2 - Deletar cliente pelo CPF ou CNPJ",
                "3 - Deletar cliente pelo nome",
                "4 - Cadastro de Produtos",
                "5 - Efetuação de uma compra",
                "6 - Atualização da situação de pagamento de uma compra",
                "7 - Relatórios",
                "Sair"
        };

        boolean voltarMenuInicial = false;

        while (true) {
            if (voltarMenuInicial) {
                voltarMenuInicial = false;
            } else {
                String escolha = JOptionPane.showInputDialog(null, "Escolha uma opção:", "Menu inicial",
                        JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]).toString();

                if (escolha.equals("Sair") || escolha == null) {
                    int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente fechar o programa?",
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmacao == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "Fechando o programa, clique em 'OK'", "Fechando",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                } else if (escolha.equals("1 - Cadastros de Clientes")) {
                    cadastrarClientes();
                } else if (escolha.equals("2 - Deletar cliente pelo CPF ou CNPJ")) {
                    excluirClientePorCPFCNPJ();
                } else if (escolha.equals("3 - Deletar cliente pelo nome")) {
                    excluirClientePorNome();
                } else if (escolha.equals("4 - Cadastro de Produtos")) {
                    cadastrarProdutos();
                } else if (escolha.equals("5 - Efetuação de uma compra")) {
                    efetuarCompra();
                } else if (escolha.equals("6 - Atualização da situação de pagamento de uma compra")) {
                    atualizarPrecoCompra();
                } else if (escolha.equals("7 - Relatórios")) {
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
                            "Voltar"
                    };

                    while (true) {
                        String subEscolha = JOptionPane.showInputDialog(null, "Escolha uma opção:", "7 - Relatórios",
                                JOptionPane.PLAIN_MESSAGE, null, subOpcoes, subOpcoes[0]).toString();

                        if (subEscolha.equals(
                                "(a) Relação de todos os Clientes que possuem o nome iniciado por uma determinada sequência de caracteres")) {
                            buscarClientesPorNome();
                        } else if (subEscolha.equals("(b) Relação de todos os Produtos")) {
                            listarProdutos();
                        } else if (subEscolha.equals("(c) Busca de um produto pelo nome")) {
                            buscarProdutoPorNome();
                        } else if (subEscolha.equals("(e) Relação de todas as compras")) {
                            listarCompras();
                        } else if (subEscolha.equals("(f) Busca de uma compra pelo número")) {
                            buscarCompraPorNumero();
                        } else if (subEscolha.equals("(i) Apresentação das informações da compra mais cara")) {
                            compraMaisCara();
                        } else if (subEscolha.equals("(j) Apresentação das informações da compra mais barata")) {
                            compraMaisBarata();
                        } else if (subEscolha.equals("Voltar") || subEscolha == null) {
                            JOptionPane.showMessageDialog(null, "Voltando...");
                            break;
                        }
                    }
                }
            }
        }

        escreveClientes(clienteBufferedWriter);

        escreveProdutos(produtoBufferedWriter);

        escreveCompras(compraBufferedWriter);
    }

    private static void escreveProdutos(BufferedWriter produtoBufferedWriter) throws IOException {
        for (Produto produto : listProdutos) {
            if (produto instanceof Produto) {
                produtoBufferedWriter.write(produto.paraString() + "\n");
            } else if (produto instanceof ProdutoPerecivel) {
                ProdutoPerecivel produtoPerecivel = (ProdutoPerecivel) produto;
                produtoBufferedWriter.write(produtoPerecivel.paraString() + "\n");
            }
        }
        produtoBufferedWriter.close();
    }

    private static void escreveClientes(BufferedWriter clienteBufferedWriter) throws IOException {
        for (Cliente cliente : listClientes) {
            if (cliente instanceof PessoaFisica) {
                PessoaFisica pessoaFisica = (PessoaFisica) cliente;
                clienteBufferedWriter.write(pessoaFisica.paraString() + "\n");
            } else if (cliente instanceof PessoaJuridica) {
                PessoaJuridica pessoaJuridica = (PessoaJuridica) cliente;
                clienteBufferedWriter.write(pessoaJuridica.paraString() + "\n");
            }
        }
        clienteBufferedWriter.close();
    }

    private static void escreveCompras(BufferedWriter compraBufferedWriter) throws IOException {
        for (Compra compra : listCompras) {
            compraBufferedWriter.write(compra.paraString() + "\n");
        }
        compraBufferedWriter.close();
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

    // Método para converter para float, usado no cadastro de produtos
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

        return new Endereco(rua, numero, bairro, cep, cidade, estado);
    }

    // Método para data validade dos produtos
    private static LocalDate getDataValidade() {

        String titulo = "Data de Validade";

        int dia = getInt("Dia:", titulo);
        int mes = getInt("Mês:", titulo);
        int ano = getInt("Ano:", titulo);

        return LocalDate.of(ano, mes, dia);
    }

    // Organizando as ações do menu em métodos específicos
    public static void cadastrarClientes() {
        String[] tipoCliente = { "Pessoa Física", "Pessoa Jurídica" };
        int tipoEscolhido = JOptionPane.showOptionDialog(null, "Escolha o tipo de cliente:", "Tipo de Cliente",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tipoCliente, tipoCliente[0]);

        if (tipoEscolhido == 0) {
            String nome = getString("Nome:", "Dados pessoais");
            Long cpf = getLong("CPF:", "Dados pessoais");
            int quantidadaeMaximaParcelas = getInt("Quantidade máxima de parcelas: ",
                    "Financeiro");

            Endereco endereco = getEndereco();

            LocalDate dataCadastro = LocalDate.now();

            PessoaFisica pessoa = new PessoaFisica(nome, endereco, dataCadastro, cpf,
                    quantidadaeMaximaParcelas);

            listClientes.add(pessoa);// adicionando cliente à lista
            JOptionPane.showMessageDialog(null, pessoa.paraString());
        } else {
            String nome = getString("Nome: ", "Dados pessoais");
            long cnpj = getLong("CNPJ: ", "Dados pessoais");
            int prazoMaximoPagamento = getInt("Prazo máximo para o pagamento: ",
                    "Financeiro");
            String razaoSocial = getString("Razão social: ", "Dados pessoais");

            Endereco endereco = getEndereco();
            LocalDate dataCadastro = LocalDate.now();

            PessoaJuridica pessoa = new PessoaJuridica(nome, endereco, dataCadastro, cnpj, razaoSocial,
                    prazoMaximoPagamento);
            listClientes.add(pessoa);// adicionando cliente à lista
            JOptionPane.showMessageDialog(null, pessoa.paraString());
        }
    }

    public static void excluirClientePorCPFCNPJ() {
        String[] tipoCliente = { "Pessoa Física", "Pessoa Jurídica" };
        int tipoEscolhido = JOptionPane.showOptionDialog(null, "Escolha o tipo de cliente:", "Tipo de Cliente",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tipoCliente, tipoCliente[0]);
        if (tipoEscolhido == 0) {
            long cpf = getLong("CPF:", "Dados pessoais");
            // Criando variável boolean como false, para que quando encontrar cliente
            // alterar para true
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
            if (!blnEncontrou)
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
        } else {
            long cnpj = getLong("CNPJ:", "Dados pessoais");
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

            if (!blnEncontrou)
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
        }
    }

    public static void excluirClientePorNome() {
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
        if (!blnEncontrou)
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

    public static void efetuarCompra() {
        String[] nomeProdutos = new String[(listProdutos.size() + 1)];
        if (listProdutos.isEmpty()) {
            // TODO Murilo Adicionar mensagem informando que a lista de produto está vazia.
            return;
        }

        int produtoIndex = 0;
        Long cpfCnpj;
        Boolean encontrouCliente = false;
        do {
            cpfCnpj = getLong("Informe o CPF ou o CNPJ", "Compra");
            for (int index = 0; index < listClientes.size(); index++) {
                if (listClientes.get(index) instanceof PessoaFisica
                        && ((PessoaFisica) listClientes.get(index)).getCpf() == cpfCnpj) {
                    encontrouCliente = true;
                } else if (listClientes.get(index) instanceof PessoaJuridica
                        && ((PessoaJuridica) listClientes.get(index)).getCnpj() == cpfCnpj) {
                    encontrouCliente = true;
                } else {
                    // TODO Murilo adicionar uma mensagem informando que o Cpf/Cnpj não condiz com
                    // nenhum cliente cadastrado e perguntar se deseja tentar novamente
                }
            }
        } while (!encontrouCliente);

        String identificador = getString("Identificador da compra", "Compra");
        ArrayList<ItemCompra> itens = new ArrayList<ItemCompra>();

        do {
            for (int i = 0; i < listProdutos.size(); i++) {
                nomeProdutos[i] = listProdutos.get(i).getNome();
            }
            nomeProdutos[listProdutos.size()] = "Finalizar";

            produtoIndex = JOptionPane.showOptionDialog(null, "Escolha o produto:", "Produtos",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, nomeProdutos, nomeProdutos[0]);
            if (produtoIndex == listProdutos.size()) {
                break;
            }
            int quantidade = getInt("Unidades: ", "Compra");
            ItemCompra item = new ItemCompra(quantidade, listProdutos.get(produtoIndex).getNome(),
                    listProdutos.get(produtoIndex).getPreco());
            itens.add(item);

        } while (true);

        Compra compra = new Compra(itens, identificador, LocalDate.now(), cpfCnpj, 0);
        String valorTotalFormatado = String.format("%.2f", compra.getValorTotal());
        float totalPago = getFloat(
                "Deseja realizar o pagamento de quanto?\nValor total da compra: " + valorTotalFormatado,
                "Compra");
        compra.setTotalPago(totalPago);

        listCompras.add(compra);
    }

    public static void atualizarPrecoCompra() {
        String identificador = JOptionPane.showInputDialog(null, "Informe o identificador da compra:",
                "Atualização da situação de pagamento", JOptionPane.QUESTION_MESSAGE);
        Compra compraEscolhida = null;
        for (Compra compra : listCompras) {
            if (compra.getIdentificador().equals(identificador)) {
                compraEscolhida = compra;
                break;
            }
        }
        if (compraEscolhida == null) {
            JOptionPane.showMessageDialog(null, "Compra não encontrada com o identificador informado.",
                    "Erro encontrado!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null,
                "Preço atual da compra (" + identificador + "): " + compraEscolhida.getValorTotal(),
                "Atualização da situação de pagamento", JOptionPane.DEFAULT_OPTION);
        String precoSubtraido = JOptionPane.showInputDialog(null, "Digite o preço a ser abatido",
                "Atualização de preço", JOptionPane.QUESTION_MESSAGE);
        float precoAbatido = Float.parseFloat(precoSubtraido);
        float novoPreco = compraEscolhida.getValorTotal() - precoAbatido;

        if (novoPreco < 0) {
            JOptionPane.showMessageDialog(null, "O preço não pode ser maior que o valor original.",
                    "Atualização de Preço",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        float totalPago = getFloat("Digite o novo valor total pago", "Atualização de Preço");
        compraEscolhida.setValorTotal(novoPreco);
        compraEscolhida.setTotalPago(totalPago);

        JOptionPane.showMessageDialog(null,
                "Preço atualizado da compra (" + identificador + "): " + novoPreco,
                "Atualização de Preço", JOptionPane.DEFAULT_OPTION);

    }

    // Iniciando Item 7 - Relatório
    public static void buscarClientesPorNome() {
        String sequencia = JOptionPane.showInputDialog("Digite a sequência de caracteres para buscar clientes:");
        if (sequencia == null) {
            JOptionPane.showMessageDialog(null, "Operação cancelada.");
            return;
        }

        ArrayList<Cliente> clientesEncontrados = new ArrayList<>();

        for (Cliente cliente : listClientes) {
            if (cliente.getNome().toLowerCase().startsWith(sequencia.toLowerCase())) {
                clientesEncontrados.add(cliente);
            }
        }

        // Resultados da busca
        if (!clientesEncontrados.isEmpty()) {
            StringBuilder resultado = new StringBuilder("Clientes encontrados:\n");
            for (Cliente cliente : clientesEncontrados) {
                resultado.append(cliente.getNome()).append("\n");
            }
            JOptionPane.showMessageDialog(null, resultado.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum cliente encontrado!");
        }
    }

    // Item B
    public static void listarProdutos() {
        if (listProdutos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há produtos cadastrados.");
            return;
        }

        StringBuilder resultado = new StringBuilder("Lista de produtos:\n");
        for (Produto produto : listProdutos) {
            resultado.append(produto.paraString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, resultado.toString());
    }

    // Item C
    public static void buscarProdutoPorNome() {
        String nomeBusca = JOptionPane.showInputDialog(null, "Digite o nome do produto:", "Buscar Produto",
                JOptionPane.PLAIN_MESSAGE);

        if (nomeBusca == null) {
            JOptionPane.showMessageDialog(null, "Operação cancelada.");
            return;
        }

        boolean produtoEncontrado = false;
        StringBuilder resultado = new StringBuilder("Resultado da busca:\n");

        for (Produto produto : listProdutos) {
            if (produto.getNome().toLowerCase().startsWith(nomeBusca.toLowerCase())) {
                resultado.append(produto.paraString()).append("\n");
                produtoEncontrado = true;
            }
        }

        if (!produtoEncontrado) {
            JOptionPane.showMessageDialog(null, "Nenhum produto encontrado com o nome iniciado por: " + nomeBusca);
        } else {
            JOptionPane.showMessageDialog(null, resultado.toString());
        }
    }
    // Item D *HÁ FAZER

    // Item E
    public static void listarCompras() {
        if (listCompras.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há compras cadastradas.");
            return;
        }
        StringBuilder resultado = new StringBuilder("Lista de compras:\n");
        for (Compra compra : listCompras) {
            resultado.append(compra.paraString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, resultado.toString());
    }

    // Item F
    public static void buscarCompraPorNumero() {
        String identificador = JOptionPane.showInputDialog(null, "Digite o identificador da compra:",
                "Buscar compra pelo identificador",
                JOptionPane.PLAIN_MESSAGE);

        if (identificador == null) {
            JOptionPane.showMessageDialog(null, "A operação foi cancelada.");
            return;
        }

        boolean encontrouCompra = false;
        StringBuilder resultado = new StringBuilder("Resultado da busca:\n");

        for (Compra compra : listCompras) {
            if (compra.getIdentificador().equalsIgnoreCase(identificador)) {
                resultado.append(compra.paraString()).append("\n");
                encontrouCompra = true;
                break;
            }
        }

        if (!encontrouCompra) {
            JOptionPane.showMessageDialog(null, "Nenhuma compra: [" + identificador + "] foi encontrada.");
        } else {
            JOptionPane.showMessageDialog(null, resultado.toString());
        }
    }

    public static void compraMaisCara() {
        
        if (listCompras.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há compras cadastradas.");
            return;
        }

        Compra compraMaisCara = listCompras.get(0);

        for (Compra compra : listCompras) {
            if (compra.getValorTotal() > compraMaisCara.getValorTotal()) {
                compraMaisCara = compra;
            }
        }

        JOptionPane.showMessageDialog(null, "Informações da compra mais cara:\n" + compraMaisCara.paraString());
    }

    public static void compraMaisBarata() {
        if (listCompras.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há compras cadastradas.");
            return;
        }

        Compra compraMaisBarata = listCompras.get(0);

        for (Compra compra : listCompras) {
            if (compra.getValorTotal() < compraMaisBarata.getValorTotal()) {
                compraMaisBarata = compra;
            }
        }

        JOptionPane.showMessageDialog(null, "Informações da compra mais barata:\n" + compraMaisBarata.paraString());
    }

}