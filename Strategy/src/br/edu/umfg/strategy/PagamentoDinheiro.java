package br.edu.umfg.strategy;

public class PagamentoDinheiro implements PagamentoStrategy {
    @Override
    public boolean pagar(double valor) {
        // Implemente aqui a lógica de pagamento em dinheiro
        // ...

        System.out.println("Pagamento em dinheiro no valor de R$" + valor + " realizado com sucesso.");
        return true;
    }
}
