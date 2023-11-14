import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class CompraHandler {

    public static void efetuarCompra(ArrayList<Compra> compras, ArrayList<Produto> produtos,
            ArrayList<Cliente> clientes) {
        String[] nomeProdutos = new String[(produtos.size() + 1)];
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "A lista de produtos está vázia.");
            return;
        }

        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "A lista de clientes está vazia.");
            return;
        }

        int produtoIndex = 0;
        Long cpfCnpj;
        Boolean encontrouCliente = false;
        do {
            cpfCnpj = GlobalHandler.getLong("Informe o CPF ou o CNPJ", "Compra");
            for (int index = 0; index < clientes.size(); index++) {
                if (clientes.get(index) instanceof PessoaFisica
                        && ((PessoaFisica) clientes.get(index)).getCpf() == cpfCnpj) {
                    encontrouCliente = true;
                } else if (clientes.get(index) instanceof PessoaJuridica
                        && ((PessoaJuridica) clientes.get(index)).getCnpj() == cpfCnpj) {
                    encontrouCliente = true;
                } else {
                    int resposta = JOptionPane.showConfirmDialog(null,
                            "CPF/CNPJ não condiz com nenhum cliente cadastrado. Deseja tentar novamente?",
                            "Aviso", JOptionPane.YES_NO_OPTION);

                    if (resposta == JOptionPane.NO_OPTION) {
                        return;
                    } else {
                        encontrouCliente = false;
                    }
                }
            }
        } while (!encontrouCliente);
        String identificador = GlobalHandler.getString("Identificador da compra", "Compra");
        ArrayList<ItemCompra> itens = new ArrayList<ItemCompra>();

        do {
            for (int i = 0; i < produtos.size(); i++) {
                nomeProdutos[i] = produtos.get(i).getNome();
            }
            nomeProdutos[produtos.size()] = "Finalizar";

            produtoIndex = JOptionPane.showOptionDialog(null, "Escolha o produto:", "Produtos",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, nomeProdutos, nomeProdutos[0]);
            if (produtoIndex == produtos.size()) {
                break;
            }
            int quantidade = GlobalHandler.getInt("Unidades: ", "Compra");
            ItemCompra item = new ItemCompra(quantidade, produtos.get(produtoIndex).getNome(),
                    produtos.get(produtoIndex).getPreco());
            itens.add(item);

        } while (true);
        Compra compra = new Compra(itens, identificador, LocalDate.now(), cpfCnpj, 0);
        String valorTotalFormatado = String.format("%.2f", compra.getValorTotal());
        float totalPago = GlobalHandler.getFloat(
                "Deseja realizar o pagamento de quanto?\nValor total da compra: " + valorTotalFormatado,
                "Compra");
        compra.setTotalPago(totalPago);

        compras.add(compra);
    }

    public static void atualizarPrecoCompra(ArrayList<Compra> compras) {
        String identificador = JOptionPane.showInputDialog(null, "Informe o identificador da compra:",
                "Atualização da situação de pagamento", JOptionPane.QUESTION_MESSAGE);
        Compra compraEscolhida = null;
        for (Compra compra : compras) {
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

        float totalPago = GlobalHandler.getFloat("Digite o novo valor total pago", "Atualização de Preço");
        compraEscolhida.setValorTotal(novoPreco);
        compraEscolhida.setTotalPago(totalPago);

        JOptionPane.showMessageDialog(null,
                "Preço atualizado da compra (" + identificador + "): " + novoPreco,
                "Atualização de Preço", JOptionPane.DEFAULT_OPTION);

    }

    // Item E
    public static void listarCompras(ArrayList<Compra> compras) {
        if (compras.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há compras cadastradas.");
            return;
        }
        StringBuilder resultado = new StringBuilder("Lista de compras:\n");
        for (Compra compra : compras) {
            resultado.append(compra.paraString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, resultado.toString());
    }

    // Item F
    public static void buscarCompraPorNumero(ArrayList<Compra> compras) {
        if (compras.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há compras cadastradas.");
            return;
        }
        String identificador = JOptionPane.showInputDialog(null, "Digite o identificador da compra:",
                "Buscar compra pelo identificador",
                JOptionPane.PLAIN_MESSAGE);

        if (identificador == null) {
            JOptionPane.showMessageDialog(null, "A operação foi cancelada.");
            return;
        }

        boolean encontrouCompra = false;
        StringBuilder resultado = new StringBuilder("Resultado da busca:\n");

        for (Compra compra : compras) {
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

    // Item H
    public static void ultimasDezComprasPagas(ArrayList<Compra> compras) {
        List<Compra> comprasPagas = compras.stream()
                .filter(compra -> compra.getTotalPago() > 0)
                .collect(Collectors.toList());

        if (comprasPagas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há compras pagas para exibir.");
            return;
        }

        List<Compra> ultimasDezComprasPagas = comprasPagas.stream()
                .sorted(Comparator.comparing(Compra::getData).reversed())
                .limit(10)
                .collect(Collectors.toList());

        StringBuilder mensagem = new StringBuilder("Últimas 10 compras pagas:\n");
        for (Compra compra : ultimasDezComprasPagas) {
            mensagem.append(compra.paraString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    // Item I
    public static void compraMaisCara(ArrayList<Compra> compras) {

        if (compras.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há compras cadastradas.");
            return;
        }

        Compra compraMaisCara = compras.get(0);

        for (Compra compra : compras) {
            if (compra.getValorTotal() > compraMaisCara.getValorTotal()) {
                compraMaisCara = compra;
            }
        }

        JOptionPane.showMessageDialog(null, "Informações da compra mais cara:\n" + compraMaisCara.paraString());
    }

    // Item J
    public static void compraMaisBarata(ArrayList<Compra> compras) {
        if (compras.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há compras cadastradas.");
            return;
        }

        Compra compraMaisBarata = compras.get(0);

        for (Compra compra : compras) {
            if (compra.getValorTotal() < compraMaisBarata.getValorTotal()) {
                compraMaisBarata = compra;
            }
        }

        JOptionPane.showMessageDialog(null, "Informações da compra mais barata:\n" + compraMaisBarata.paraString());
    }

}
