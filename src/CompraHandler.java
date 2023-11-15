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
                if (clientes.get(index) instanceof PessoaFisica) {
                    if (((PessoaFisica) clientes.get(index)).getCpf() == cpfCnpj) {
                        encontrouCliente = true;

                    }
                } else if (clientes.get(index) instanceof PessoaJuridica) {
                    if (((PessoaJuridica) clientes.get(index)).getCnpj() == cpfCnpj) {
                        encontrouCliente = true;

                    }
                } else {
                }
            }

            if (!encontrouCliente) {
                int resposta = JOptionPane.showConfirmDialog(null,
                        "CPF/CNPJ não condiz com nenhum cliente cadastrado. Deseja tentar novamente?",
                        "Aviso", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.NO_OPTION) {
                    return;
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
        if (compraEscolhida.faltaPagar() <= 0) {
            JOptionPane.showMessageDialog(null, "Compra paga!", "Atualização da situação de pagamento",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null,
                "Preço atual da compra (" + identificador + "): " + compraEscolhida.faltaPagar(),
                "Atualização da situação de pagamento", JOptionPane.DEFAULT_OPTION);

        float totalPago = GlobalHandler.getFloat("Informe o quanto deseja pagar: ", "Atualização de Preço");

       
        float novoPreco = compraEscolhida.getValorTotal() - (totalPago + compraEscolhida.getTotalPago());

        if (novoPreco < 0) {
            JOptionPane.showMessageDialog(null, "O preço não pode ser maior que o valor original.",
                    "Atualização de Preço",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        compraEscolhida.setTotalPago(totalPago + compraEscolhida.getTotalPago());

        JOptionPane.showMessageDialog(null,
                "Preço atualizado da compra (" + identificador + "): " + novoPreco,
                "Atualização de Preço", JOptionPane.DEFAULT_OPTION);
        compraEscolhida.setData(LocalDate.now());
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

    public static void buscaComprasNaoPagas(ArrayList<Compra> compras) {
        if (compras.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há compras cadastradas.");
            return;
        }

        ArrayList<Compra> comprasNaoPagas = new ArrayList<>();

        for (Compra compra : compras) {
            if (compra.getTotalPago() < compra.getValorTotal()) {
                comprasNaoPagas.add(compra);
            }
        }

        if (comprasNaoPagas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todas as compras estão pagas.");
        } else {
            StringBuilder comprasTexto = new StringBuilder("Compras não pagas:\n");
            for (Compra compra : comprasNaoPagas) {
                comprasTexto.append(compra.paraString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, comprasTexto.toString());
        }
    }

    public static void comprasAnuaisAgrupadasPorMes(ArrayList<Compra> compras) {
        if (compras.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há compras cadastradas.");
            return;
        }

        double[] valorTotalPorMes = new double[12];
        LocalDate dataAtual = LocalDate.now();

        for (Compra compra : compras) {
            LocalDate dataCompra = compra.getData();

            long diferencaMeses = java.time.temporal.ChronoUnit.MONTHS.between(dataCompra, dataAtual);

            if (diferencaMeses <= 12) {
                int indice = dataCompra.getMonthValue() - 1;
                valorTotalPorMes[indice] += compra.getValorTotal();
            }
        }

        boolean encontrouCompras = false;
        StringBuilder mensagem = new StringBuilder("Valor total das compras nos últimos 12 meses por mês:\n");

        for (int i = 0; i < valorTotalPorMes.length; i++) {
            if (valorTotalPorMes[i] > 0) {
                encontrouCompras = true;
                mensagem.append("Mês ").append(i + 1).append(": R$ ").append(valorTotalPorMes[i]).append("\n");
            }
        }

        if (!encontrouCompras) {
            JOptionPane.showMessageDialog(null, "Não há compras nos últimos 12 meses.");
        } else {
            JOptionPane.showMessageDialog(null, mensagem.toString());
        }
    }
}