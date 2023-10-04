package br.edu.umfg.strategy;

public class PagamentoCartaoDebito implements PagamentoStrategy {
    private String numeroCartao;
    private String cpf;

    public PagamentoCartaoDebito(String numeroCartao, String cpf) {
        this.numeroCartao = numeroCartao;
        this.cpf = cpf;
    }

    @Override
    public boolean pagar(double valor) {
        if (!validarNumeroCartao()) {
            System.out.println("Número de cartão de débito inválido.");
            return false;
        }

        if (!validarCPF()) {
            System.out.println("CPF inválido.");
            return false;
        }

        System.out.println("Pagamento com cartão de débito no valor de R$" + valor + " realizado com sucesso.");
        return true;
    }

    private boolean validarNumeroCartao() {
        // Implemente a validação do número do cartão de débito

        // Remova espaços em branco e caracteres não numéricos
        String numeroCartaoLimpo = numeroCartao.replaceAll("[^0-9]", "");

        // Verifique o comprimento mínimo do número do cartão
        if (numeroCartaoLimpo.length() != 16) {
            System.out.println("Número de cartão de débito inválido. O número deve conter 16 dígitos.");
            return false;
        }

        // Implemente a validação do algoritmo de Luhn (soma de verificação)
        int sum = 0;
        boolean doubleDigit = false;

        for (int i = numeroCartaoLimpo.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(numeroCartaoLimpo.charAt(i));

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            doubleDigit = !doubleDigit;
        }

        if (sum % 10 != 0) {
            System.out.println("Número de cartão de débito inválido. Verifique o número.");
            return false;
        }

        return true;
    }
    private boolean validarCPF() {
        if (!isValidCPF(cpf)) {
            return false;
        }
        return true;
    }

    private boolean isValidCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", ""); // Remove caracteres não numéricos

        if (cpf.length() != 11) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) {
            primeiroDigito = 0;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) {
            segundoDigito = 0;
        }

        return Character.getNumericValue(cpf.charAt(9)) == primeiroDigito
                && Character.getNumericValue(cpf.charAt(10)) == segundoDigito;
    }
}

