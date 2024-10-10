package Main;

import Models.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<Cardapio> cardapios = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static int escolha = -1;
    private static Cardapio cardapioEscolhido = null;

    public static void main(String[] args) {

        var comida1 = new PratoPrincipal("Ovo Frito", "Um dos melhores ovos fritos", 10, 6);
        var comida2 = new PratoPrincipal("Bifão", "Um bife suculento ", 35, 20);
        var bebida1 = new Bebida("Refrigerante", "Um refri de sua escolha", 8, true);
        var bebida2 = new Bebida("Chá", "Um chá de sua escolha", 5, false);

        var cardapio1 = new Cardapio("Comida boa!");
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

        while (cardapioEscolhido == null) {
            System.out.println("Restaurantes disponiveis:");
            cardapios.forEach(c -> System.out.println("#" + cardapios.indexOf(c) + 1 + " - " + c.getNomeRestaurante()));
            System.out.println("Digite o # do restaurante ou 0 para sair:");
            escolha = scanner.nextInt();

            if (escolha == 0) {
                System.out.println("Saindo...");
                break;
            } else if (escolha > cardapios.size() || escolha < 0) {
                System.out.println("Valor inválido");
            } else {
                cardapioEscolhido = cardapios.get(escolha - 1);
            }
        }

        var opcao = escolha;
        while (opcao != 0) {
            System.out.printf("""
                    Bem vindo ao organizador de cardápio do restaurante %s
                    1. Filtrar item carápio
                    2. Ordenar cardapio
                    0
                    """, cardapioEscolhido.getNomeRestaurante());
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    filtrarItensCardapio(cardapioEscolhido);
                    break;
                case 2:
                    ordenarCardapio(cardapioEscolhido);
                case 0:
                    System.out.println("Saindo...");
                    break;
            }
        }
    }

    private static void ordenarCardapio(Cardapio cardapio) {

        var opcao = -1;
        while (opcao != 0 && opcao != 1 && opcao != 2) {
            System.out.println("""
                    Quer ordenar os produtos por que fator?
                    1. Nome
                    2. Preço
                    Digite a opção desejada:
                    """);
            opcao = scanner.nextInt();
        }

        if (opcao == 1) {
            cardapio.getConteudo().stream()
                    .sorted(Comparator.comparing(Produto::getNome))
                    .forEach(System.out::println);
        }

        if (opcao == 2) {
            cardapio.getConteudo().stream()
                    .sorted(Comparator.comparing(Produto::getPreco))
                    .forEach(System.out::println);
        }
    }

    private static void adicionarCardapio(Cardapio cardapio) {
        cardapios.add(cardapio);
    }

    private static void filtrarItensCardapio(Cardapio cardapio) {
        System.out.println(cardapioEscolhido);

    }
}