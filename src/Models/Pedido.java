package Models;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int idPedido;
    private List<Produto> produtos = new ArrayList<>();
    private String statusPedido; //Em preparação / Em entrega / Entregue

    public void exibirInformacoes() {
        System.out.println("Pedido #" + idPedido + "\nStatus: " + statusPedido + "\nItens:");
        for (Produto produto : produtos) {
            produto.exibirInformacoes();
            System.out.println("========================");
        }
        System.out.println("Valor final: R$ " + this.valorPedido());
    }

    public double valorPedido() {
        double valorFinal = 0;
        for (Produto p : produtos) {
            valorFinal += p.getPreco();
        }
        return valorFinal;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }

    //Abaixo funções básicas (Construtores-Getters-Setters-Equals-HashCode-toString)

    public Pedido() {
    }

    public Pedido(int idPedido) {
        this.idPedido = idPedido;
        statusPedido = "Em preparação";
    }

    public int getIdPedido() {
        return idPedido;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        if ("Em preparacao / A caminho / Entregue".toLowerCase().contains(statusPedido.toLowerCase())) {
            this.statusPedido = statusPedido;
            return;
        }
        System.out.println("Valor inválido");
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", produtos=" + produtos +
                ", statusPedido='" + statusPedido + '\'' +
                ", valorFinal=R$" + this.valorPedido() +
                '}';
    }
}
