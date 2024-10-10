package Services;

import java.util.Scanner;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void exibirPrincipal() {
        var opcao = -1;
        while (opcao != 0) {
            System.out.println("Digite o nome do restaurante para começar:");
            var cardapio = scanner.next().toLowerCase();
            System.out.println("""
                    BEm vindo menu legal
                    1. Filtrar item carápio
                    2. Ordenar cardapio
                    
                    
                    
                    
                    
                    
                    """);
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    filtrarItensCardapio();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
            }
        }
    }

    private static void filtrarItensCardapio() {


    }

}
