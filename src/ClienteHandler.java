import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ClienteHandler {

    public static void cadastraCliente(ArrayList<Cliente> clientes) {
        String[] tipoCliente = { "Pessoa Física", "Pessoa Jurídica" };
        int tipoEscolhido = JOptionPane.showOptionDialog(null, "Escolha o tipo de cliente:", "Tipo de Cliente",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tipoCliente, tipoCliente[0]);

        if (tipoEscolhido == 0) {
            String nome = GlobalHandler.getString("Nome:", "Dados pessoais");
            Long cpf = GlobalHandler.getLong("CPF:", "Dados pessoais");
            int quantidadaeMaximaParcelas = GlobalHandler.getInt("Quantidade máxima de parcelas: ",
                    "Financeiro");

            Endereco endereco = App.getEndereco();

            LocalDate dataCadastro = LocalDate.now();

            PessoaFisica pessoa = new PessoaFisica(nome, endereco, dataCadastro, cpf,
                    quantidadaeMaximaParcelas);

            clientes.add(pessoa);
            JOptionPane.showMessageDialog(null, pessoa.paraString());
        } else {
            String nome = GlobalHandler.getString("Nome: ", "Dados pessoais");
            long cnpj = GlobalHandler.getLong("CNPJ: ", "Dados pessoais");
            int prazoMaximoPagamento = GlobalHandler.getInt("Prazo máximo para o pagamento: ",
                    "Financeiro");
            String razaoSocial = GlobalHandler.getString("Razão social: ", "Dados pessoais");

            Endereco endereco = App.getEndereco();
            LocalDate dataCadastro = LocalDate.now();

            PessoaJuridica pessoa = new PessoaJuridica(nome, endereco, dataCadastro, cnpj, razaoSocial,
                    prazoMaximoPagamento);
            clientes.add(pessoa);
            JOptionPane.showMessageDialog(null, pessoa.paraString());
        }
    }

    public static void excluirClientePorCPFCNPJ(ArrayList<Cliente> clientes) {
        String[] tipoCliente = { "Pessoa Física", "Pessoa Jurídica" };
        int tipoEscolhido = JOptionPane.showOptionDialog(null, "Escolha o tipo de cliente:", "Tipo de Cliente",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tipoCliente, tipoCliente[0]);
        if (tipoEscolhido == 0) {
            long cpf = GlobalHandler.getLong("CPF:", "Dados pessoais");
            boolean blnEncontrou = false;
            for (Cliente cliente : clientes) {
                if (cliente instanceof PessoaFisica && ((PessoaFisica) cliente).getCpf() == cpf) {
                    clientes.remove(cliente);
                    blnEncontrou = true;
                    JOptionPane.showMessageDialog(null, "Cliente removido com sucesso.");
                    break;
                }
            }
            if (!blnEncontrou)
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
        } else {
            long cnpj = GlobalHandler.getLong("CNPJ:", "Dados pessoais");
            boolean blnEncontrou = false;
            for (Cliente cliente : clientes) {
                if (cliente instanceof PessoaJuridica && ((PessoaJuridica) cliente).getCnpj() == cnpj) {
                    clientes.remove(cliente);
                    blnEncontrou = true;
                    JOptionPane.showMessageDialog(null, "Cliente removido com sucesso.");
                    break;
                }
            }

            if (!blnEncontrou)
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
        }
    }

    public static void excluirClientePorNome(ArrayList<Cliente> clientes) {
        String nome = GlobalHandler.getString("Nome: ", "Dados pessoais");
        boolean blnEncontrou = false;
        for (Cliente cliente : clientes) {
            // Verifica se o cliente é do tipo PessoaFisica e se o nome corresponde
            if ((cliente instanceof PessoaFisica && ((PessoaFisica) cliente).getNome().equals(nome)) ||
                    (cliente instanceof PessoaJuridica && ((PessoaJuridica) cliente).getNome().equals(nome))) {
                clientes.remove(cliente);
                blnEncontrou = true;
                JOptionPane.showMessageDialog(null, "Cliente removido com sucesso.");
                break;
            }
        }
        // Se não encontrou o cliente, exibe mensagem ao usuário
        if (!blnEncontrou)
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
    }

    // Iniciando Item 7 - Relatório
    public static void buscarClientesPorNome(ArrayList<Cliente> clientes) {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há clientes cadastrados.");
            return;
        }
        String sequencia = JOptionPane.showInputDialog("Digite a sequência de caracteres para buscar clientes:");
        if (sequencia == null) {
            JOptionPane.showMessageDialog(null, "Operação cancelada.");
            return;
        }

        ArrayList<Cliente> clientesEncontrados = new ArrayList<>();

        for (Cliente cliente : clientes) {
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

}
