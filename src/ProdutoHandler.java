import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ProdutoHandler {

    public static void cadastraProduto(ArrayList<Produto> produtos) {
        String[] tipoProduto = { "Produto Comum", "Produto Perecível" };
        int tipoEscolhido = JOptionPane.showOptionDialog(null, "Escolha o tipo de produto:", "Tipo de Produto",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tipoProduto, tipoProduto[0]);

        if (tipoEscolhido == 0) {
            String codigo = GlobalHandler.getString("Código do produto: ", "Dados do Produto");
            String nome = GlobalHandler.getString("Nome do produto: ", "Dados do Produto");
            String descricao = GlobalHandler.getString("Descrição: ", "Dados do Produto");
            float preco = GlobalHandler.getFloat("Preço: ", "Dados do Produto");

            Produto produto = new Produto(codigo, nome, descricao, preco);

            produtos.add(produto);
            JOptionPane.showMessageDialog(null, produto.paraString());
        } else {
            String codigo = GlobalHandler.getString("Código do produto: ", "Dados do Produto");
            String nome = GlobalHandler.getString("Nome do produto: ", "Dados do Produto");
            String descricao = GlobalHandler.getString("Descrição: ", "Dados do Produto");
            float preco = GlobalHandler.getFloat("Preço: ", "Dados do Produto");

            LocalDate dataValidade = App.getDataValidade();

            ProdutoPerecivel produtoPerecivel = new ProdutoPerecivel(codigo, nome, descricao, preco, dataValidade);

            produtos.add(produtoPerecivel);
            JOptionPane.showMessageDialog(null, produtoPerecivel.paraString());
        }
    }

    // Item B
    public static void listarProdutos(ArrayList<Produto> produtos) {
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há produtos cadastrados.");
            return;
        }

        StringBuilder resultado = new StringBuilder("Lista de produtos:\n");
        for (Produto produto : produtos) {
            resultado.append(produto.paraString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, resultado.toString());
    }

    // Item C
    public static void buscarProdutoPorNome(ArrayList<Produto> produtos) {

        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há produtos cadastrados.");
            return;
        }
        String nomeBusca = JOptionPane.showInputDialog(null, "Digite o nome do produto:", "Buscar Produto",
                JOptionPane.PLAIN_MESSAGE);

        if (nomeBusca == null) {
            JOptionPane.showMessageDialog(null, "Operação cancelada.");
            return;
        }

        boolean produtoEncontrado = false;
        StringBuilder resultado = new StringBuilder("Resultado da busca:\n");

        for (Produto produto : produtos) {
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

    // Item D
    public static void produtosVencido(ArrayList<Produto> produtos) {
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há produtos cadastrados.");
            return;
        }

        StringBuilder resultado = new StringBuilder("Produtos vencidos:\n");
        LocalDate dataAtual = LocalDate.now();

        for (Produto produto : produtos) {
            if (produto instanceof ProdutoPerecivel) {
                ProdutoPerecivel produtoPerecivel = (ProdutoPerecivel) produto;
                LocalDate dataValidade = produtoPerecivel.getValidade();

                if (dataValidade.isBefore(dataAtual)) {
                    resultado.append(produto.paraString())
                            .append(" - Vencimento: ").append(dataValidade).append("\n");
                }
            }
        }

        if (resultado.toString().equals("Produtos vencidos:\n")) {
            JOptionPane.showMessageDialog(null, "Não há produtos vencidos.");
        } else {
            JOptionPane.showMessageDialog(null, resultado.toString());
        }
    }

}
