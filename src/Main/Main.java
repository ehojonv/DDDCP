package Main;

import Models.*;
import Services.Menu;

import java.util.*;

public class Main {

    private static final List<Cardapio> cardapios = new ArrayList<>();
    private static final List<Pedido> pedidos = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        // Criação de um set de pratos principais
        var comida1 = new PratoPrincipal("Ovo Frito", "Um dos melhores ovos fritos", 10, 6);
        var comida2 = new PratoPrincipal("Bifão", "Um bife suculento", 35, 20);
        var comida3 = new PratoPrincipal("Spaghetti Carbonara", "Massa italiana com molho cremoso e bacon", 40, 25);
        var comida4 = new PratoPrincipal("Salmão Grelhado", "Salmão grelhado com temperos especiais", 60, 30);
        var comida5 = new PratoPrincipal("Risoto de Cogumelos", "Risoto cremoso com cogumelos frescos", 45, 20);

        // Criação de um set de bebidas
        var bebida1 = new Bebida("Refrigerante", "Um refri de sua escolha", 8, true);
        var bebida2 = new Bebida("Chá", "Um chá de sua escolha", 5, false);
        var bebida3 = new Bebida("Suco de Laranja", "Suco natural de laranja", 12, true);
        var bebida4 = new Bebida("Café Expresso", "Café forte e aromático", 6, false);
        var bebida5 = new Bebida("Smoothie de Morango", "Smoothie refrescante com morangos frescos", 15, true);

        // Criação de um set de cardápios
        var cardapio1 = new Cardapio("Comidonas");
        var cardapio2 = new Cardapio("Go Vegan");
        var cardapio3 = new Cardapio("Grelhados da Casa");
        var cardapio4 = new Cardapio("Delícias Italianas");
        var cardapio5 = new Cardapio("Café e Cia");

        // Adicionando os cardápios no sistema
        cardapios.add(cardapio1);
        cardapios.add(cardapio2);
        cardapios.add(cardapio3);
        cardapios.add(cardapio4);
        cardapios.add(cardapio5);

        // Adição dos produtos no cardápio 1
        cardapio1.adicionarProduto(bebida1);
        cardapio1.adicionarProduto(comida1);
        cardapio1.adicionarProduto(comida2);
        cardapio1.adicionarProduto(bebida2);
        cardapio1.adicionarProduto(comida3);
        cardapio1.adicionarProduto(bebida3);

        // Adição dos produtos no cardápio 2
        cardapio2.adicionarProduto(comida5);
        cardapio2.adicionarProduto(bebida2);
        cardapio2.adicionarProduto(bebida4);
        cardapio2.adicionarProduto(bebida5);

        // Adição dos produtos no cardápio 3
        cardapio3.adicionarProduto(comida2);
        cardapio3.adicionarProduto(comida4);
        cardapio3.adicionarProduto(bebida1);
        cardapio3.adicionarProduto(bebida3);

        // Adição dos produtos no cardápio 4
        cardapio4.adicionarProduto(comida3);
        cardapio4.adicionarProduto(comida5);
        cardapio4.adicionarProduto(bebida5);
        cardapio4.adicionarProduto(bebida2);

        // Adição dos produtos no cardápio 5
        cardapio5.adicionarProduto(bebida4);
        cardapio5.adicionarProduto(bebida2);
        cardapio5.adicionarProduto(bebida3);
        cardapio5.adicionarProduto(bebida5);

        // Criação do pedido 1 | Adição do pedido 1 no sistema | Geração de produtos aleatórios para o pedido
        var pedido1 = new Pedido((pedidos.size() + 1));
        pedidos.add(pedido1);
        adicionarProdutosAleatoriosAoPedido(pedido1);

        // Criação do pedido 2 | Adição do pedido 2 no sistema | Geração de produtos aleatórios para o pedido
        var pedido2 = new Pedido((pedidos.size() + 1));
        pedidos.add(pedido2);
        adicionarProdutosAleatoriosAoPedido(pedido2);

        // Criação do pedido 3 | Adição do pedido 3 no sistema | Geração de produtos aleatórios para o pedido | Mudando o status do pedido
        var pedido3 = new Pedido((pedidos.size() + 1));
        pedidos.add(pedido3);
        adicionarProdutosAleatoriosAoPedido(pedido3);
        pedido3.setStatusPedido(STATUS_PEDIDO.PROCURANDO_ENTREGADOR);


        // Criação do pedido 4 | Adição do pedido 4 no sistema | Geração de produtos aleatórios para o pedido | Mudando o status do pedido
        var pedido4 = new Pedido((pedidos.size() + 1));
        pedidos.add(pedido4);
        adicionarProdutosAleatoriosAoPedido(pedido4);
        pedido4.setStatusPedido(STATUS_PEDIDO.ENTREGUE);


        // Criação do pedido 5 | Adição do pedido 5 no sistema | Geração de produtos aleatórios para o pedido | Mudando o status do pedido
        var pedido5 = new Pedido((pedidos.size() + 1));
        pedidos.add(pedido5);
        adicionarProdutosAleatoriosAoPedido(pedido5);
        pedido5.setStatusPedido(STATUS_PEDIDO.A_CAMINHO);


        // Criação do pedido 6 | Adição do pedido 6 no sistema | Geração de produtos aleatórios para o pedido | Mudando o status do pedido
        var pedido6 = new Pedido((pedidos.size() + 1));
        pedidos.add(pedido6);
        adicionarProdutosAleatoriosAoPedido(pedido6);
        pedido6.setStatusPedido(STATUS_PEDIDO.ENTREGUE);

        var menu = new Menu(scanner, cardapios, pedidos);

        menu.menuInicial();
    }

    private static void adicionarProdutosAleatoriosAoPedido(Pedido pedido) {
        Random random = new Random();
        var cardapio = cardapios.get(random.nextInt(cardapios.size() - 1)); // Obtem um cardápio aleatório do sistema
        List<Produto> produtosCardapio = cardapio.getConteudo(); // Obtem a lista de produtos do cardápio

        pedido.setNomeRestaurante(cardapio.getNomeRestaurante()); // Coloca o nome do restaurante no pedido

        // Gera aleatóriamente um número de produtos entre 1 - 10
        for (int i = 0; i < Math.random() * 10 + 1; i++) {
            // Gera um número aleatório dentro do tamanho do cardápio
            int indiceAleatorio = random.nextInt(produtosCardapio.size());

            // Adiciona o produto aleatório ao pedido
            Produto produtoAleatorio = produtosCardapio.get(indiceAleatorio);
            pedido.adicionarProduto(produtoAleatorio);
        }
    }
}