import java.util.ArrayList;

public class Pedido {
    private ArrayList<ItemCardapio> itens;

    public Pedido() {
        itens = new ArrayList<>();
    }

    public void adicionarItem(ItemCardapio item) {
        itens.add(item);
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }

    public double calcularTotal() {
        double total = 0;

        for (ItemCardapio item : itens) {
            total += item.getPreco();
        }

        return total;
    }

    public void exibirPedido() {
        if (estaVazio()) {
            System.out.println("Nenhum item foi adicionado ao pedido.");
            return;
        }

        System.out.println("\n=== PEDIDO ATUAL ===");

        for (ItemCardapio item : itens) {
            System.out.printf("%s - R$ %.2f%n", item.getNome(), item.getPreco());
        }

        System.out.printf("Total: R$ %.2f%n", calcularTotal());
    }
}