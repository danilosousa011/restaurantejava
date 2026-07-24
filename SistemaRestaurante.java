import java.util.ArrayList;
import java.util.Scanner;

public class SistemaRestaurante {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<ItemCardapio> cardapio = criarCardapio();
        Pedido pedido = new Pedido();
        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE RESTAURANTE ===");
            System.out.println("1 - Exibir cardápio");
            System.out.println("2 - Adicionar item ao pedido");
            System.out.println("3 - Exibir pedido");
            System.out.println("4 - Exibir total da conta");
            System.out.println("5 - Finalizar conta");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    exibirCardapio(cardapio);
                    break;

                case 2:
                    adicionarAoPedido(scanner, cardapio, pedido);
                    break;

                case 3:
                    pedido.exibirPedido();
                    break;

                case 4:
                    exibirTotal(pedido);
                    break;

                case 5:
                    finalizarConta(pedido);
                    opcao = 0;
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    public static ArrayList<ItemCardapio> criarCardapio() {
        ArrayList<ItemCardapio> cardapio = new ArrayList<>();

        cardapio.add(new ItemCardapio(1, "Hambúrguer", 20.00));
        cardapio.add(new ItemCardapio(2, "Pizza", 35.00));
        cardapio.add(new ItemCardapio(3, "Batata frita", 12.00));
        cardapio.add(new ItemCardapio(4, "Refrigerante", 7.00));
        cardapio.add(new ItemCardapio(5, "Suco natural", 9.00));

        return cardapio;
    }

    public static void exibirCardapio(ArrayList<ItemCardapio> cardapio) {
        System.out.println("\n=== CARDÁPIO ===");

        for (ItemCardapio item : cardapio) {
            item.exibirItem();
        }
    }

    public static void adicionarAoPedido(
            Scanner scanner,
            ArrayList<ItemCardapio> cardapio,
            Pedido pedido) {

        exibirCardapio(cardapio);

        System.out.print("Digite o código do item desejado: ");
        int codigo = lerInteiro(scanner);

        for (ItemCardapio item : cardapio) {
            if (item.getCodigo() == codigo) {
                pedido.adicionarItem(item);
                System.out.println(item.getNome() + " foi adicionado ao pedido.");
                return;
            }
        }

        System.out.println("Código de item inválido.");
    }

    public static void exibirTotal(Pedido pedido) {
        if (pedido.estaVazio()) {
            System.out.println("Nenhum item foi adicionado ao pedido.");
            return;
        }

        System.out.printf("Total da conta: R$ %.2f%n", pedido.calcularTotal());
    }

    public static void finalizarConta(Pedido pedido) {
        if (pedido.estaVazio()) {
            System.out.println("Não é possível finalizar uma conta sem itens.");
            return;
        }

        System.out.println("\n=== CONTA FINAL ===");
        pedido.exibirPedido();
        System.out.println("Conta finalizada. Obrigado pela preferência.");
    }

    public static int lerInteiro(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Digite um número válido: ");
            }
        }
    }
}