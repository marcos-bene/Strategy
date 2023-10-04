package br.edu.umfg.strategy;

import java.util.Scanner;

// ... (Códigos de classe do pagamento e do carrinho de compras permanecem os mesmos)

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();


        Produto produto1 = new Produto("Coca 2l", 10.0);
        Produto produto2 = new Produto("X-Bacon", 20.0);

        while (true) {
            System.out.println("Escolha um produto:");
            System.out.println("1 - " + produto1.getDescricao() + " - R$" + produto1.getValor());
            System.out.println("2 - " + produto2.getDescricao() + " - R$" + produto2.getValor());
            System.out.println("0 - Sair");

            System.out.print("Digite o número da opção desejada: ");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            if (escolha == 0) {
                System.out.println("Saindo do sistema de pagamento.");
                break; // Sai do loop se a escolha for 0
            }

            Produto produtoSelecionado = null;

            switch (escolha) {
                case 1:
                    produtoSelecionado = produto1;
                    break;

                case 2:
                    produtoSelecionado = produto2;
                    break;

                default:
                    System.out.println("Opção inválida.");
                    continue; // Continua o loop se a escolha for inválida
            }

            System.out.println("Produto selecionado: " + produtoSelecionado.getDescricao());
            System.out.println("Valor do produto: R$" + produtoSelecionado.getValor());

            while (true) {
                System.out.println("Bem-vindo ao sistema de pagamento. Escolha o método de pagamento:");
                System.out.println("1 - Cartão de Crédito");
                System.out.println("2 - Cartão de Débito");
                System.out.println("3 - Dinheiro");
                System.out.println("0 - Sair");

                System.out.print("Digite o número da opção desejada: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer do scanner

                if (opcao == 0) {
                    System.out.println("Saindo do sistema de pagamento.");
                    break; // Sai do loop se a escolha for 0
                }

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o número do cartão de crédito: ");
                        String numeroCartaoCredito = scanner.nextLine();
                        System.out.print("Digite a data de validade (MM/AA): ");
                        String dataValidadeCredito = scanner.nextLine();
                        System.out.print("Digite o CVV: ");
                        String cvvCredito = scanner.nextLine();

                        PagamentoStrategy pagamentoCartaoCredito = new PagamentoCartaoCredito(numeroCartaoCredito, dataValidadeCredito, cvvCredito);
                        carrinho.setEstrategiaPagamento(pagamentoCartaoCredito);
                        break;

                    case 2:
                        System.out.print("Digite o número do cartão de débito: ");
                        String numeroCartaoDebito = scanner.nextLine();
                        System.out.print("Digite o CPF: ");
                        String cpfDebito = scanner.nextLine();

                        PagamentoStrategy pagamentoCartaoDebito = new PagamentoCartaoDebito(numeroCartaoDebito, cpfDebito);
                        carrinho.setEstrategiaPagamento(pagamentoCartaoDebito);
                        break;

                    case 3:
                        PagamentoStrategy pagamentoDinheiro = new PagamentoDinheiro();
                        carrinho.setEstrategiaPagamento(pagamentoDinheiro);
                        break;

                    default:
                        System.out.println("Opção inválida.");
                        continue; // Continua o loop se a escolha for inválida
                }

                System.out.print("Digite o valor a ser pago: R$ ");
                double valor = scanner.nextDouble();

                boolean pagamentoRealizado = carrinho.efetuarPagamento(valor);

                if (pagamentoRealizado) {
                    System.out.println("Pagamento realizado com sucesso!");
                    break; // Sai do loop após o pagamento bem-sucedido
                } else {
                    System.out.println("Falha no pagamento. Verifique os dados e tente novamente.");
                }
            }
        }

        scanner.close();
    }
}
