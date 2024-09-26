package Main;

import Models.*;

public class Main {
    public static void main(String[] args) {

        var comida1 = new PratoPrincipal("Ovo Frito","Um dos melhores ovos fritos",10,6);
        var comida2 = new PratoPrincipal("Bifão","Um bife suculento ",35,20);
        var bebida1 = new Bebida("Refrigerante","Um refri de sua escolha",8,true);
        var bebida2 = new Bebida("Chá","Um chá de sua escolha",5,false);

        var cardapio1 = new Cardapio("Restaurante Legal");

        var pedido1 = new Pedido(1);
        var pedido2 = new Pedido(2);

        cardapio1.adicionarProduto(bebida1);
        cardapio1.adicionarProduto(comida1);
        cardapio1.adicionarProduto(comida2);
        cardapio1.adicionarProduto(bebida2);

        cardapio1.exibirInformacoes();

        System.out.println();

        pedido1.adicionarProduto(bebida1);
        pedido1.adicionarProduto(comida2);

        pedido2.adicionarProduto(bebida2);
        pedido2.adicionarProduto(comida1);
        pedido2.setStatusPedido("A caminho");

        pedido1.exibirInformacoes();

        System.out.println();

        pedido2.exibirInformacoes();





    }
}