package Services;

import Models.*;

import java.text.Normalizer;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private final List<Cardapio> cardapios;
    private final List<Pedido> pedidos;
    private Cardapio cardapioEscolhido;

    public Menu(Scanner scanner, List<Cardapio> cardapios, List<Pedido> pedidos) {
        this.scanner = scanner;
        this.cardapios = cardapios;
        this.pedidos = pedidos;
    }

    public void iniciarMenuCardapios() {
        cardapioEscolhido = null;
        selecionarRestaurante();
        if (cardapioEscolhido != null) {
            exibirOpcoesMenu();
        }
    }

    public void iniciarMenuPedidos() {
    }

    private void selecionarRestaurante() {
        int escolha;
        while (cardapioEscolhido == null) {
            try {
                System.out.println("\nRestaurantes disponíveis para ver o cardápio:");

                var listaRestaurantes = cardapios.stream()
                        .map(c -> "#" + (cardapios.indexOf(c) + 1) + " - " + c.getNomeRestaurante())
                        .toList();
                listaRestaurantes.forEach(System.out::println);

                System.out.println("Digite o # do restaurante ou 0 para sair:");
                escolha = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\033[91mInsira um valor válido\033[39m");
                scanner.nextLine();
                continue;
            }

            if (1 <= escolha && escolha <= cardapios.size()) {
                cardapioEscolhido = cardapios.get(escolha - 1);
            } else if (escolha == 0) {
                System.out.println("Saindo...");
                break;
            } else {
                System.out.println("\033[91mNúmero inválido\033[39m");
            }
        }
    }

    private void exibirOpcoesMenu() {
        int opcao = -1;
        while (opcao != 0) {
            try {
                System.out.printf("""
                        
                        Bem vindo ao organizador de cardápio do restaurante %s
                        1. Filtrar item cardápio
                        2. Ordenar cardápio
                        3. Listar itens do cardápio
                        0. Selecionar outro restaurante
                        =====================================================
                        Digite a opção desejada:
                        """, cardapioEscolhido.getNomeRestaurante());
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1 -> filtrarItensCardapio();
                    case 2 -> ordenarCardapio();
                    case 3 -> listarItensCardapio();
                    case 0 -> iniciarMenuCardapios();
                    default -> System.out.println("\033[91mNúmero inválido\033[39m");
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[91mInsira um valor válido\033[39m");
                scanner.nextLine();
            }
        }
    }

    public void listarItensCardapio() {
        int totalPratosPrincipais = (int) cardapioEscolhido.getConteudo().stream()
                .filter(p -> p instanceof PratoPrincipal)
                .count();
        int totalBebidas = (int) cardapioEscolhido.getConteudo().stream()
                .filter(p -> p instanceof Bebida)
                .count();
        System.out.printf("""
                        
                        Os restaurante contém:
                        %d %s,
                        %d %s
                        """, totalPratosPrincipais, totalPratosPrincipais > 1 || totalPratosPrincipais == 0 ? "Pratos Principais" : "Prato Principal",
                totalBebidas, totalBebidas > 1 || totalBebidas == 0 ? "Bebidas" : "Bebida");
    }

    private void ordenarCardapio() {
        int opcao = -1;
        while (opcao != 1 & opcao != 2) {
            System.out.println("""
                    
                    Quer ordenar os produtos por qual fator?
                    1. Nome
                    2. Preço
                    0. Voltar
                    =========================================
                    Digite a opção desejada:""");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 0 -> {
                    return;
                }
                case 1 -> ordenarPorNome();
                case 2 -> ordenarPorPreco();
                default -> System.out.println("\033[91mNúmero in\033[39m");
            }
        }
    }

    private void ordenarPorPreco() {
        System.out.printf("\nCardápio do %s ordenado por preço:\n", cardapioEscolhido.getNomeRestaurante());
        cardapioEscolhido.getConteudo().stream()
                .sorted(Comparator.comparing(Produto::getPreco))
                .forEach(Produto::exibirInformacoes);
    }

    private void ordenarPorNome() {
        System.out.printf("\nCardápio do %s ordenado por nome:\n", cardapioEscolhido.getNomeRestaurante());
        cardapioEscolhido.getConteudo().stream()
                .sorted(Comparator.comparing(Produto::getNome))
                .forEach(Produto::exibirInformacoes);
    }

    private void filtrarItensCardapio() {
        int opcao = -1;
        while (opcao != 1 & opcao != 2 & opcao != 3) {
            System.out.println("""
                    
                    Quer filtrar por qual fator?
                    1. Nome
                    2. Preço
                    3. Tipo
                    0. Voltar
                    ===============================
                    Digite a opção desejada:""");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 0 -> {
                    return;
                }
                case 1 -> filtrarPorTexto();
                case 2 -> filtrarPorPreco();
                case 3 -> filtrarPorTipo();
                default -> System.out.println("\033[91mNúmero inválido\033[39m");
            }
        }
    }

    private void filtrarPorTexto() {
        System.out.println("Digite o termo para filtrar:");
        var texto = scanner.nextLine();

        var listaFinal = cardapioEscolhido.getConteudo().stream()
                .filter(p -> Normalizer.normalize(p.getNome(), Normalizer.Form.NFD)
                        .replaceAll("\\p{M}", "")
                        .toLowerCase()
                        .contains(texto.toLowerCase()))
                .toList();

        if (listaFinal.isEmpty()) {
            listaFinal = cardapioEscolhido.getConteudo().stream()
                    .filter(p -> Normalizer.normalize(p.getDescricao(), Normalizer.Form.NFD)
                            .replaceAll("\\p{M}", "")
                            .toLowerCase()
                            .contains(texto.toLowerCase()))
                    .toList();
        }

        if (listaFinal.isEmpty()) {
            System.out.printf("Nenhum resultado encontrado para \"%s\".\n",texto);
        } else {
            listaFinal.forEach(Produto::exibirInformacoes);
        }

    }

    private void filtrarPorPreco() {
        System.out.println("Digite o preço máximo para buscar:");
        var precoLimite = scanner.nextDouble();
        cardapioEscolhido.getConteudo().stream()
                .filter(p -> p.getPreco() <= precoLimite)
                .forEach(Produto::exibirInformacoes);
    }

    private void filtrarPorTipo() {
        int opcao = -1;
        while (opcao != 1 & opcao != 2) {
            System.out.println("""
                    Deseja filtrar em:
                    1. Prato Principal
                    2. Bebida
                    0. Voltar""");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 0 -> {
                    return;
                }
                case 1 -> filtrarPratosPrincipais();
                case 2 -> filtrarBebidas();
                default -> System.out.println("\033[91mNúmero inválido\033[39m");
            }
        }
    }

    private void filtrarBebidas() {
        System.out.printf("\nCardápio do %s filtrado por bebidas:\n", cardapioEscolhido.getNomeRestaurante());
        cardapioEscolhido.getConteudo().stream()
                .filter(p -> p instanceof Bebida)
                .forEach(Produto::exibirInformacoes);
    }

    private void filtrarPratosPrincipais() {
        System.out.printf("\nCardápio do %s filtrado por pratos principais:\n", cardapioEscolhido.getNomeRestaurante());
        cardapioEscolhido.getConteudo().stream()
                .filter(p -> p instanceof PratoPrincipal)
                .forEach(Produto::exibirInformacoes);
    }
}
