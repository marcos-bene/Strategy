package br.edu.umfg.strategy;

public class PagamentoDinheiro implements PagamentoStrategy {
    @Override
    public boolean pagar(double valor) {

        System.out.println("Pagamento em dinheiro no valor de R$" + valor + " realizado com sucesso.");
        return true;
    }
}
