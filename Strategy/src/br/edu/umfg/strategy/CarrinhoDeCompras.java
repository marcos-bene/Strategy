package br.edu.umfg.strategy;

public class CarrinhoDeCompras {
    private PagamentoStrategy estrategiaPagamento;

    public void setEstrategiaPagamento(PagamentoStrategy estrategiaPagamento) {
        this.estrategiaPagamento = estrategiaPagamento;
    }

    public boolean efetuarPagamento(double valor) {
        if (estrategiaPagamento != null) {
            return estrategiaPagamento.pagar(valor);
        }
        return false;
    }
}