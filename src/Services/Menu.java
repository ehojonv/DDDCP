package Services;

import Models.*;

import java.text.Normalizer;
import java.util.*;
import java.util.stream.Collectors;

public class Menu {
    private final Scanner scanner;
    private final List<Cardapio> cardapios;
    private final List<Pedido> pedidos;
    private Cardapio cardapioEscolhido;
    private int opcao;

    public Menu(Scanner scanner, List<Cardapio> cardapios, List<Pedido> pedidos) {
        this.scanner = scanner;
        this.cardapios = cardapios;
        this.pedidos = pedidos;
    }

    // Menu principal
    public void menuInicial() {
        opcao = -1;
        while (opcao != 0) {
            try {
                System.out.println("""

                        Bem vindo ao\033[1m SeuFome\033[22m
                        Selecione o menu que quer utilizar:
                        1. Mostrar todos os cardápios
                        2. Menu de cardápios
                        3. Menu de pedidos
                        0. Sair
                        =========================================
                        Digite a opção desejada:""");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 0 -> System.out.println("\033[92mSaindo...\033[39m");
                    case 1 -> cardapios.forEach(Cardapio::exibirInformacoes);
                    case 2 -> iniciarMenuCardapios();
                    case 3 -> iniciarMenuPedidos();
                    default -> System.out.println("\033[91mOpção inválida\033[39m");
                }

            } catch (InputMismatchException e) {
                System.out.println("\033[91mInsira um valor válido\033[39m");
                scanner.nextLine();
            }
        }
    }

    // Menu de cardápios
    public void iniciarMenuCardapios() {
        while (true) {
            selecionarRestaurante();
            if (cardapioEscolhido != null) {
                exibirOpcoesMenuCardapio();
            } else {
                break;
            }
        }
    }

    private void selecionarRestaurante() {
        cardapioEscolhido = null;
        if (opcao == 0) {
            return;
        }
        opcao = -1;
        List<String> listaRestaurantes = cardapios.stream()
                .map(c -> (cardapios.indexOf(c) + 1) + ". - " + c.getNomeRestaurante())
                .toList();

        while (cardapioEscolhido == null) {
            int ultimaOpcao = listaRestaurantes.size() + 1;

            try {
                System.out.println("\nRestaurantes disponíveis para ver o cardápio:");
                listaRestaurantes.forEach(System.out::println);
                System.out.printf("""
                        %d. Voltar para o menu principal
                        =========================================
                        Digite a opção desejada ou 0 para sair:
                        """, ultimaOpcao);
                opcao = scanner.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("\033[91mInsira um valor válido\033[39m");
                scanner.nextLine();
                continue;
            }

            if (1 <= opcao && opcao <= cardapios.size()) {
                cardapioEscolhido = cardapios.get(opcao - 1);
            } else if (opcao == 0) {
                System.out.println("\033[92mSaindo...\033[39m");
                break;
            } else if (opcao == ultimaOpcao) {
                cardapioEscolhido = null;
                break;
            } else {
                System.out.println("\033[91mOpção inválida\033[39m");
            }
        }
    } // Menu seletor de restaurante

    private void exibirOpcoesMenuCardapio() {
        opcao = -1;
        while (opcao != 0) {
            try {
                System.out.printf("""
                        
                        Bem vindo ao organizador de cardápio do\033[1m %s\033[22m
                        1. Filtrar item cardápio
                        2. Ordenar cardápio
                        3. Listar itens do cardápio
                        4. Selecionar outro restaurante
                        0. Sair
                        =====================================================
                        Digite a opção desejada:
                        """, cardapioEscolhido.getNomeRestaurante());
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 0 -> System.out.println("\033[92mSaindo...\033[39m");
                    case 1 -> filtrarItensCardapio();
                    case 2 -> ordenarCardapio();
                    case 3 -> listarItensCardapio();
                    case 4 -> {
                        return;
                    }
                    default -> System.out.println("\033[91mOpção inválida\033[39m");
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[91mInsira um valor válido\033[39m");
                scanner.nextLine();
            }
        }
    }

    private void filtrarItensCardapio() {
        int opcao = -1;
        while (!(1 <= opcao & opcao <= 3)) {
            System.out.println("""
                    
                    Quer filtrar por qual fator?
                    1. Nome
                    2. Preço
                    3. Tipo
                    0. Voltar
                    =========================================
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
                default -> System.out.println("\033[91mOpção inválida\033[39m");
            }
        }
    } // Menu de filtro

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
            System.out.printf("Nenhum resultado encontrado para \"%s\".\n", texto);
        } else {
            System.out.println("Produtos encontrados:");
            listaFinal.forEach(Produto::exibirInformacoes);
        }

    }

    private void filtrarPorPreco() {
        System.out.println("Digite o preço máximo para buscar:");
        var precoLimite = scanner.nextDouble();

        var listaFinal = cardapioEscolhido.getConteudo().stream()
                .filter(p -> p.getPreco() <= precoLimite)
                .toList();

        if (listaFinal.isEmpty()) {
            System.out.printf("Nenhum produto mais barato que R$ %.2f\n", precoLimite);
        } else {
            System.out.printf("\nAqui estão os produtos mais baratos que R$ %.2f:\n", precoLimite);
            listaFinal.forEach(Produto::exibirInformacoes);
        }
    }

    private void filtrarPorTipo() {
        int opcao = -1;
        while (opcao != 1 & opcao != 2) {
            System.out.println("""
                    
                    Deseja filtrar em:
                    1. Prato Principal
                    2. Bebida
                    0. Voltar
                    =========================================
                    Digite a opção desejada:""");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 0 -> {
                    return;
                }
                case 1 -> filtrarPratosPrincipais();
                case 2 -> filtrarBebidas();
                default -> System.out.println("\033[91mOpção inválida\033[39m");
            }
        }
    } // Menu de filtro por tipo

    private void filtrarBebidas() {
        System.out.printf("\nCardápio do\033[1m %s\033[22m filtrado por\033[4m bebidas\033[24m:\n", cardapioEscolhido.getNomeRestaurante());
        cardapioEscolhido.getConteudo().stream()
                .filter(p -> p instanceof Bebida)
                .forEach(Produto::exibirInformacoes);
    }

    private void filtrarPratosPrincipais() {
        System.out.printf("\nCardápio do\033[1m %s\033[22m filtrado por\033[4m pratos principais\033[24m:\n", cardapioEscolhido.getNomeRestaurante());
        cardapioEscolhido.getConteudo().stream()
                .filter(p -> p instanceof PratoPrincipal)
                .forEach(Produto::exibirInformacoes);
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
                default -> System.out.println("\033[91mOpção inválida\033[39m");
            }
        }
    } // Menu de ordenar cardápio

    private void ordenarPorPreco() {
        System.out.printf("""

                Cardápio do\033[1m %s\033[22m ordenado por \033[4mpreço\033[24m:
                """, cardapioEscolhido.getNomeRestaurante());
        cardapioEscolhido.getConteudo().stream()
                .sorted(Comparator.comparing(Produto::getPreco))
                .forEach(Produto::exibirInformacoes);
    }

    private void ordenarPorNome() {
        System.out.printf("""

                Cardápio do\033[1m %s\033[22m ordenado por \033[4mnome\033[24m:
                """, cardapioEscolhido.getNomeRestaurante());
        cardapioEscolhido.getConteudo().stream()
                .sorted(Comparator.comparing(Produto::getNome))
                .forEach(Produto::exibirInformacoes);
    }

    public void listarItensCardapio() {

        int totalPratosPrincipais = (int) cardapioEscolhido.getConteudo().stream()
                .filter(p -> p instanceof PratoPrincipal)
                .count();

        int totalBebidas = (int) cardapioEscolhido.getConteudo().stream()
                .filter(p -> p instanceof Bebida)
                .count();

        System.out.printf("""

                        O restaurante contém %d %s e %d %s.
                        """, totalPratosPrincipais, totalPratosPrincipais > 1 || totalPratosPrincipais == 0 ? "Pratos Principais" : "Prato Principal",
                totalBebidas, totalBebidas > 1 || totalBebidas == 0 ? "Bebidas" : "Bebida");
    }

    //Menu de Pedidos
    public void iniciarMenuPedidos() {
        exibirOpcoesMenuPedidos();
    }

    private void exibirOpcoesMenuPedidos() {
        opcao = -1;
        while (opcao != 0) {
            try {
                System.out.println("""

                        Bem vindo ao organizador de pedidos
                        1. Listar todos os pedidos
                        2. Calcular o total de vendas geral
                        3. Encontrar produto mais pedido
                        4. Listar pedidos por status
                        5. Voltar ao menu principal
                        0. Sair
                        =========================================
                        Digite a opção desejada:""");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 0 -> System.out.println("\033[92mSaindo...\033[39m");
                    case 1 -> pedidos.forEach(Pedido::exibirInformacoes);
                    case 2 -> totalVendasPedidos();
                    case 3 -> encontrarProdutoMaisPedido();
                    case 4 -> listarPedidosStatus();
                    case 5 -> {
                        return;
                    }
                    default -> System.out.println("\033[91mOpção inválida\033[39m");
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[91mInsira um valor válido\033[39m");
                scanner.nextLine();
            }
        }
    }

    private void encontrarProdutoMaisPedido() {

        var todosProdutos = pedidos.stream()
                .map(Pedido::getProdutos)
                .flatMap(List::stream)
                .toList();

        todosProdutos.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .ifPresent(produtoMaisPedido -> System.out.printf("O produto mais pedido é: \033[1m%s\033[22m\n", produtoMaisPedido.getNome()));
    } // ?

    private void listarPedidosStatus() {
        int opcao = -1;
        while (opcao != 1 & opcao != 2 & opcao != 3 & opcao != 4 & opcao != 5) {
            try {
                System.out.println("""

                        Deseja filtrar por pedidos:
                        1. Em preparação
                        2. Procurando entregador
                        3. A caminho
                        4. Entregue
                        5. Cancelado
                        0. Voltar
                        =========================================
                        Digite a opção desejada:""");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> {
                        var listaPedidosFiltrada = pedidos.stream()
                                .filter(p -> p.getStatusPedido() == STATUS_PEDIDO.EM_PREPARACAO)
                                .toList();
                        if (listaPedidosFiltrada.isEmpty()) {
                            System.out.println("Não há pedidos com esse status");
                        } else {
                            System.out.print("\nPedidos em preparação:");
                            listaPedidosFiltrada.forEach(Pedido::exibirInformacoes);
                        }
                    }
                    case 2 -> {
                        var listaPedidosFiltrada = pedidos.stream()
                                .filter(p -> p.getStatusPedido() == STATUS_PEDIDO.PROCURANDO_ENTREGADOR)
                                .toList();
                        if (listaPedidosFiltrada.isEmpty()) {
                            System.out.println("Não há pedidos com esse status");
                        } else {
                            System.out.print("\nPedidos buscando entregador:");
                            listaPedidosFiltrada.forEach(Pedido::exibirInformacoes);
                        }
                    }
                    case 3 -> {
                        var listaPedidosFiltrada = pedidos.stream()
                                .filter(p -> p.getStatusPedido() == STATUS_PEDIDO.A_CAMINHO)
                                .toList();
                        if (listaPedidosFiltrada.isEmpty()) {
                            System.out.println("Não há pedidos com esse status");
                        } else {
                            System.out.print("\nPedidos a caminho:");
                            listaPedidosFiltrada.forEach(Pedido::exibirInformacoes);
                        }
                    }
                    case 4 -> {
                        var listaPedidosFiltrada = pedidos.stream()
                                .filter(p -> p.getStatusPedido() == STATUS_PEDIDO.ENTREGUE)
                                .toList();
                        if (listaPedidosFiltrada.isEmpty()) {
                            System.out.println("Não há pedidos com esse status");
                        } else {
                            System.out.print("\nPedidos entregues:");
                            listaPedidosFiltrada.forEach(Pedido::exibirInformacoes);
                        }
                    }
                    case 5 -> {
                        var listaPedidosFiltrada = pedidos.stream()
                                .filter(p -> p.getStatusPedido() == STATUS_PEDIDO.CANCELADO)
                                .toList();
                        if (listaPedidosFiltrada.isEmpty()) {
                            System.out.println("Não há pedidos com esse status");
                        } else {
                            System.out.print("\nPedidos cancelados:");
                            listaPedidosFiltrada.forEach(Pedido::exibirInformacoes);
                        }
                    }
                    default -> System.out.println("\033[91mOpção inválida\033[39m");
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[91mInsira um valor válido\033[39m");
                scanner.nextLine();
            }
        }
    } // Menu de lista de status de pedido

    private void totalVendasPedidos() {
        var totalVendas = pedidos.stream()
                .filter(p -> p.getStatusPedido() == STATUS_PEDIDO.ENTREGUE)
                .mapToDouble(Pedido::valorPedido)
                .sum();

        System.out.println("Pedido | Valor");
        pedidos.forEach(p -> System.out.println("#" + p.getIdPedido() + " | R$ " + String.format("%.2f", p.valorPedido()) + " | Status: " + STATUS_PEDIDO.formatarParaString(p.getStatusPedido())));
        System.out.printf("""
                Total dos pedidos entregues: R$ %.2f
                """, totalVendas);
    }
}
