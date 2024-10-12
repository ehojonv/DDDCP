package Main;

import Models.*;
import Services.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<Cardapio> cardapios = new ArrayList<>();
    private static final List<Pedido> pedidos = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        var comida1 = new PratoPrincipal("Ovo Frito", "Um dos melhores ovos fritos", 10, 6);
        var comida2 = new PratoPrincipal("Bifão", "Um bife suculento", 35, 20);
        var comida3 = new PratoPrincipal("Spaghetti Carbonara", "Massa italiana com molho cremoso e bacon", 40, 25);
        var comida4 = new PratoPrincipal("Salmão Grelhado", "Salmão grelhado com temperos especiais", 60, 30);
        var comida5 = new PratoPrincipal("Risoto de Cogumelos", "Risoto cremoso com cogumelos frescos", 45, 20);

        var bebida1 = new Bebida("Refrigerante", "Um refri de sua escolha", 8, true);
        var bebida2 = new Bebida("Chá", "Um chá de sua escolha", 5, false);
        var bebida3 = new Bebida("Suco de Laranja", "Suco natural de laranja", 12, true);
        var bebida4 = new Bebida("Café Expresso", "Café forte e aromático", 6, false);
        var bebida5 = new Bebida("Smoothie de Morango", "Smoothie refrescante com morangos frescos", 15, true);

        var cardapio1 = new Cardapio("Comidonas");
        var cardapio2 = new Cardapio("Go Vegan");
        var cardapio3 = new Cardapio("Grelhados da Casa");
        var cardapio4 = new Cardapio("Delícias Italianas");
        var cardapio5 = new Cardapio("Café e Cia");

        cardapios.add(cardapio1);
        cardapios.add(cardapio2);
        cardapios.add(cardapio3);
        cardapios.add(cardapio4);
        cardapios.add(cardapio5);

        cardapio1.adicionarProduto(bebida1);
        cardapio1.adicionarProduto(comida1);
        cardapio1.adicionarProduto(comida2);
        cardapio1.adicionarProduto(bebida2);
        cardapio1.adicionarProduto(comida3);
        cardapio1.adicionarProduto(bebida3);

        cardapio2.adicionarProduto(comida5);
        cardapio2.adicionarProduto(bebida2);
        cardapio2.adicionarProduto(bebida4);
        cardapio2.adicionarProduto(bebida5);

        cardapio3.adicionarProduto(comida2);
        cardapio3.adicionarProduto(comida4);
        cardapio3.adicionarProduto(bebida1);
        cardapio3.adicionarProduto(bebida3);

        cardapio4.adicionarProduto(comida3);
        cardapio4.adicionarProduto(comida5);
        cardapio4.adicionarProduto(bebida5);
        cardapio4.adicionarProduto(bebida2);

        cardapio5.adicionarProduto(bebida4);
        cardapio5.adicionarProduto(bebida2);
        cardapio5.adicionarProduto(bebida3);
        cardapio5.adicionarProduto(bebida5);


        var pedido1 = new Pedido(1);
        var pedido2 = new Pedido(2);

        pedidos.add(pedido1);
        pedidos.add(pedido2);

        pedido1.adicionarProduto(bebida1);
        pedido1.adicionarProduto(comida2);

        pedido2.adicionarProduto(bebida2);
        pedido2.adicionarProduto(comida1);
        pedido2.setStatusPedido(STATUS_PEDIDO.A_Caminho);

        var menu = new Menu(scanner, cardapios, pedidos);

        menu.iniciarMenuCardapios();
    }
}