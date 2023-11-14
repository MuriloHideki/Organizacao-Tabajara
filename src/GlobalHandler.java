import javax.swing.JOptionPane;

public class GlobalHandler {

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

    public static String getString(String mensagem, String titulo) {
        return (JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.PLAIN_MESSAGE));
    }

}
