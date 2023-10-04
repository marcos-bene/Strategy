package br.edu.umfg.strategy;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PagamentoCartaoCredito implements PagamentoStrategy {
    private String numeroCartao;
    private String dataValidade;
    private String cvv;

    public PagamentoCartaoCredito(String numeroCartao, String dataValidade, String cvv) {
        this.numeroCartao = numeroCartao;
        this.dataValidade = dataValidade;
        this.cvv = cvv;
    }

    @Override
    public boolean pagar(double valor) {
        if (!validarNumeroCartao()) {
            System.out.println("Número de cartão de crédito inválido.");
            return false;
        }

        if (!validarDataValidade()) {
            System.out.println("Data de validade do cartão de crédito inválida ou expirada.");
            return false;
        }

        if (!validarCVV()) {
            System.out.println("CVV do cartão de crédito inválido.");
            return false;
        }

        System.out.println("Pagamento com cartão de crédito no valor de R$" + valor + " realizado com sucesso.");
        return true;
    }

    private boolean validarNumeroCartao() {
        // Implemente a validação do número do cartão de crédito

        // Remova espaços em branco e caracteres não numéricos
        String numeroCartaoLimpo = numeroCartao.replaceAll("[^0-9]", "");

        // Verifique o comprimento mínimo do número do cartão
        if (numeroCartaoLimpo.length() != 16) {
            System.out.println("Número de cartão de crédito inválido. O número deve conter 16 dígitos.");
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
            System.out.println("Número de cartão de crédito inválido. Verifique o número.");
            return false;
        }

        return true;
    }

    private boolean validarDataValidade() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
        dateFormat.setLenient(false);

        try {
            Date dataValidadeCartao = dateFormat.parse(dataValidade);
            Calendar dataAtual = Calendar.getInstance();
            Calendar dataValidadeCalendar = Calendar.getInstance();
            dataValidadeCalendar.setTime(dataValidadeCartao);

            if (dataValidadeCalendar.before(dataAtual)) {
                System.out.println("Data de validade do cartão de crédito expirada.");
                return false;
            }

        } catch (Exception e) {
            System.out.println("Formato inválido da data de validade do cartão de crédito. Use MM/YY.");
            return false;
        }

        return true;
    }

    private boolean validarCVV() {
        if (cvv.length() != 3) {
            System.out.println("CVV inválido. Deve conter exatamente 3 dígitos.");
            return false;
        }
        return true;
    }
}
