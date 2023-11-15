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
                    String nome = campos[1];
                    long cep = Long.parseLong(campos[2]);
                    String bairro = campos[3];
                    String cidade = campos[4];
                    String estado = campos[5];
                    int numero = Integer.parseInt(campos[6]);
                    String rua = campos[7];
                    LocalDate dataCadastro = LocalDate.parse(campos[8]);
                    long cnpj = Long.parseLong(campos[9]);
                    String razaoSocial = campos[10];
                    int prazoMaximoPagamento = Integer.parseInt(campos[11]);
                    Endereco endereco = new Endereco(rua, numero, bairro, cep, cidade, estado);
                    PessoaJuridica pessoa = new PessoaJuridica(nome, endereco, dataCadastro, cnpj, razaoSocial,
                            prazoMaximoPagamento);
                    listClientes.add(pessoa);
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

        // Leitura do documento baseDados/compas.txt
        try (BufferedReader br = new BufferedReader(new FileReader("baseDados/compras.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(",");
                String identificador = campos[0];
                LocalDate data = LocalDate.parse(campos[1]);
                Long cpfCnpj = Long.parseLong(campos[2]);
                float totalPago = Float.parseFloat(campos[3]);
                ArrayList<ItemCompra> itens = new ArrayList<ItemCompra>();
                for (int i = 4; i < campos.length; i += 4) {
                    String nomeProduto = campos[i];
                    int quantidade = Integer.parseInt(campos[i + 1]);
                    float precoUnitario = Float.parseFloat(campos[i + 2]);
                    ItemCompra item = new ItemCompra(quantidade, nomeProduto, precoUnitario);
                    itens.add(item);
                }
                Compra compra = new Compra(itens, identificador, data, cpfCnpj, totalPago);
                listCompras.add(compra);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                    ClienteHandler.cadastraCliente(listClientes);
                } else if (escolha.equals("2 - Deletar cliente pelo CPF ou CNPJ")) {
                    ClienteHandler.excluirClientePorCPFCNPJ(listClientes);
                } else if (escolha.equals("3 - Deletar cliente pelo nome")) {
                    ClienteHandler.excluirClientePorNome(listClientes);
                } else if (escolha.equals("4 - Cadastro de Produtos")) {
                    ProdutoHandler.cadastraProduto(listProdutos);
                } else if (escolha.equals("5 - Efetuação de uma compra")) {
                    CompraHandler.efetuarCompra(listCompras, listProdutos, listClientes);
                } else if (escolha.equals("6 - Atualização da situação de pagamento de uma compra")) {
                    CompraHandler.atualizarPrecoCompra(listCompras);
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
                            ClienteHandler.buscarClientesPorNome(listClientes);
                        } else if (subEscolha.equals("(b) Relação de todos os Produtos")) {
                            ProdutoHandler.listarProdutos(listProdutos);
                        } else if (subEscolha.equals("(c) Busca de um produto pelo nome")) {
                            ProdutoHandler.buscarProdutoPorNome(listProdutos);
                        } else if (subEscolha.equals(
                                "(d) Relação de produtos que são perecíveis e que estão com a data de validade vencida")) {
                            ProdutoHandler.produtosVencido(listProdutos);
                        } else if (subEscolha.equals("(e) Relação de todas as compras")) {
                            CompraHandler.listarCompras(listCompras);
                        } else if (subEscolha.equals("(f) Busca de uma compra pelo número")) {
                            CompraHandler.buscarCompraPorNumero(listCompras);
                        } else if (subEscolha.equals("(g) Relação de todas as compras que não foram pagas ainda")) {
                            CompraHandler.buscaComprasNaoPagas(listCompras);
                        } else if (subEscolha.equals("(h) Relação das 10 últimas compras pagas")) {
                            CompraHandler.ultimasDezComprasPagas(listCompras);
                        } else if (subEscolha.equals("(i) Apresentação das informações da compra mais cara")) {
                            CompraHandler.compraMaisCara(listCompras);
                        } else if (subEscolha.equals("(j) Apresentação das informações da compra mais barata")) {
                            CompraHandler.compraMaisBarata(listCompras);
                        } else if (subEscolha.equals(
                                "(k) Relação do valor total de compras feitas em cada mês nos últimos 12 meses")) {
                            CompraHandler.comprasAnuaisAgrupadasPorMes(listCompras);
                        } else if (subEscolha.equals("Voltar") || subEscolha == null) {
                            JOptionPane.showMessageDialog(null, "Voltando...");
                            break;
                        }
                    }
                }
            }
        }

        escreveTodosOsArquivos();
    }

    private static void escreveTodosOsArquivos() throws IOException {
        escreveClientes();

        escreveProdutos();

        escreveCompras();
    }

    private static void escreveProdutos() throws IOException {
        File produtoFile = new File("baseDados/produtos.txt");
        if (!produtoFile.exists())
            produtoFile.createNewFile();
        FileWriter produtoWriter = new FileWriter(produtoFile, true);
        BufferedWriter produtoBufferedWriter = new BufferedWriter(produtoWriter);
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

    private static void escreveClientes() throws IOException {
        File clienteFile = new File("baseDados/clientes.txt");
        if (!clienteFile.exists())
            clienteFile.createNewFile();
        FileWriter clienteWriter = new FileWriter(clienteFile, false);
        BufferedWriter clienteBufferedWriter = new BufferedWriter(clienteWriter);
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

    private static void escreveCompras() throws IOException {
        File compraFile = new File("baseDados/compras.txt");
        if (!compraFile.exists())
            compraFile.createNewFile();
        FileWriter compraWriter = new FileWriter(compraFile, false);
        BufferedWriter compraBufferedWriter = new BufferedWriter(compraWriter);
        for (Compra compra : listCompras) {
            compraBufferedWriter.write(compra.paraString() + "\n");
        }
        compraBufferedWriter.close();
    }

    static Endereco getEndereco() {
        String titulo = "Endereço";

        String rua = GlobalHandler.getString("Rua:", titulo);
        int numero = GlobalHandler.getInt("Número:", titulo);
        String bairro = GlobalHandler.getString("Bairro:", titulo);
        Long cep = GlobalHandler.getLong("CEP:", titulo);
        String cidade = GlobalHandler.getString("Cidade:", titulo);
        String estado = GlobalHandler.getString("Estado:", titulo);

        return new Endereco(rua, numero, bairro, cep, cidade, estado);
    }

    // Método para data validade dos produtos
    static LocalDate getDataValidade() {

        String titulo = "Data de Validade";

        int dia = GlobalHandler.getInt("Dia:", titulo);
        int mes = GlobalHandler.getInt("Mês:", titulo);
        int ano = GlobalHandler.getInt("Ano:", titulo);

        return LocalDate.of(ano, mes, dia);
    }

}