package Models;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int idPedido;
    private String nomeRestaurante;
    private final List<Produto> produtos = new ArrayList<>();
    private STATUS_PEDIDO statusPedido; //Em preparação / Em entrega / Entregue

    public void exibirInformacoes() {
        System.out.println("\nPedido #" + idPedido + " | Restaurante " + nomeRestaurante + "\n" +
                "Status: " + statusPedido + "\n" +
                "Itens:");
        produtos.forEach(Produto::exibirInformacoes);
        System.out.println("Valor final: R$ " + String.format("%.2f", this.valorPedido()));
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
        statusPedido = STATUS_PEDIDO.Em_Preparacao;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    public void setNomeRestaurante(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
    }

    public STATUS_PEDIDO getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(STATUS_PEDIDO statusPedido) {
        this.statusPedido = statusPedido;
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
