package Main;

import Models.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<Cardapio> cardapios = new ArrayList<>();

    public static void main(String[] args) {

        var comida1 = new PratoPrincipal("Ovo Frito", "Um dos melhores ovos fritos", 10, 6);
        var comida2 = new PratoPrincipal("Bifão", "Um bife suculento ", 35, 20);
        var bebida1 = new Bebida("Refrigerante", "Um refri de sua escolha", 8, true);
        var bebida2 = new Bebida("Chá", "Um chá de sua escolha", 5, false);

        var cardapio1 = new Cardapio("Restaurante1");

        adicionarCardapio(cardapio1);

        var pedido1 = new Pedido(1);
        var pedido2 = new Pedido(2);

        cardapio1.adicionarProduto(bebida1);
        cardapio1.adicionarProduto(comida1);
        cardapio1.adicionarProduto(comida2);
        cardapio1.adicionarProduto(bebida2);

        cardapio1.exibirInformacoes();

        cardapio1.getConteudo().stream()
                .sorted(Comparator.comparing(Produto::getNome))
                .forEach(System.out::println);

        System.out.println();

        pedido1.adicionarProduto(bebida1);
        pedido1.adicionarProduto(comida2);

        pedido2.adicionarProduto(bebida2);
        pedido2.adicionarProduto(comida1);
        pedido2.setStatusPedido(STATUS_PEDIDO.A_Caminho);

        pedido1.exibirInformacoes();

        System.out.println();

        pedido2.exibirInformacoes();

        //MENU VV

        Scanner scanner = new Scanner(System.in);
        var escolha = "";
        Cardapio cardapioEscolhido = null;

        while (cardapioEscolhido == null) {
            cardapios.forEach(cardapio -> System.out.println(cardapio.getNomeRestaurante()));
            System.out.println("Digite o nome do cardapio ou 0 para sair:");
            escolha = scanner.nextLine();

            if (escolha.equals("0")) {
                System.out.println("Saindo...");
                break;
            }
            for (Cardapio cardapio : cardapios) {
                System.out.println(cardapio.getNomeRestaurante());
                if (cardapio.getNomeRestaurante().equalsIgnoreCase(escolha)) {
                    cardapioEscolhido = cardapio;
                    escolha = "-1";
                    break;
                } else {
                    System.out.println("Nao achou");
                }
            }
        }

        var opcao = Integer.parseInt(escolha);
        while (opcao != 0) {
            System.out.println("""
                    BEm vindo menu legal
                    1. Filtrar item carápio
                    2. Ordenar cardapio
                    0
                    """);
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    filtrarItensCardapio(cardapioEscolhido);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
            }
        }
    }

    private static void adicionarCardapio(Cardapio cardapio) {
        cardapios.add(cardapio);
    }

    private static void filtrarItensCardapio(Cardapio cardapio) {


    }
}