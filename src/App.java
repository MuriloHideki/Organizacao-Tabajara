import java.time.LocalDate;

import javax.swing.JOptionPane;

public class App {
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
                String[] tipoCliente = { "Físico", "Jurídico" };
                int tipoEscolhido = JOptionPane.showOptionDialog(null, "Escolha o tipo de cliente:", "Tipo de Cliente",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tipoCliente, tipoCliente[0]);
                if (tipoEscolhido >= 0) {
                    if (tipoEscolhido == 0) {
                        String nome = getString("Nome:");
                        Long cpf = getLong("CPF:");
                        int quantidadaeMaximaParcelas = getInt("Quantidade máxima de parcelas: ");

                        Endereco endereco = getEndereco();

                        LocalDate dataCadastro = getData();

                        PessoaFisica pessoa = new PessoaFisica(nome, endereco, dataCadastro, cpf,
                                quantidadaeMaximaParcelas);

                        JOptionPane.showMessageDialog(null, pessoa.paraString());
                    }
                }
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

    private static int getInt(String mensagem) {
        // TODO melhorar os inputs adicionando titulo
        return Integer.parseInt(JOptionPane.showInputDialog(mensagem));
    }

    private static Long getLong(String mensagem) {
        // TODO melhorar os inputs adicionando titulo
        return Long.parseLong(JOptionPane.showInputDialog(mensagem));
    }

    private static String getString(String mensagem) {
        // TODO melhorar os inputs adicionando titulo
        return JOptionPane.showInputDialog(mensagem);
    }

    private static Endereco getEndereco() {
        // TODO melhorar os inputs adicionando titulo
        String rua = JOptionPane.showInputDialog(null, "Rua:", "Endereço", 1);
        int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Número:"));
        String bairro = JOptionPane.showInputDialog(null, "Bairro:");
        Long cep = Long.parseLong(JOptionPane.showInputDialog(null, "CEP:"));
        String cidade = JOptionPane.showInputDialog(null, "Cidade:");
        String estado = JOptionPane.showInputDialog(null, "Estado:");

        return new Endereco(rua, numero, bairro, cep, cidade, estado);
    }

    private static LocalDate getData() {
        // TODO melhorar os inputs adicionando titulo
        int dia = Integer.parseInt(JOptionPane.showInputDialog("Dia: "));
        int mes = Integer.parseInt(JOptionPane.showInputDialog("Mês: "));
        int ano = Integer.parseInt(JOptionPane.showInputDialog("Ano: "));

        return LocalDate.of(ano, mes, dia);
    }
}