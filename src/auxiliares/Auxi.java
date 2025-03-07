package auxiliares;

import java.util.Scanner;

public class Auxi {

    // Limpa o terminal
    public static void clearTerminal() {
        ProcessBuilder pB;

        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            pB = new ProcessBuilder("cmd", "/c", "cls");
        } else {
            pB = new ProcessBuilder("clear");
        }
        try {
            pB.inheritIO().start().waitFor();
        } catch (Exception ignored) {
        }
    }

    public static void clearRange(int range, String direction) {
        for (int i = 0; i < range; i++) {
            System.out.print("\033[K"); // Limpa a linha atual

            if (direction.equalsIgnoreCase("a")) {
                System.out.print("\033[A"); // Move para cima

            } else if (direction.equalsIgnoreCase("b")) {
                System.out.print("\033[B"); // Move para baixo
            }
            System.out.print("\033[K"); // Limpa a linha novamente
        }
    }

    // Pausa o fluxo ate pressionar enter( controla o loop por conta do
    // clearTerminal )
    public static void pressEnter(Scanner scan) {
        System.out.print("\nPressione \033[1mENTER\033[0m para voltar ao menu...");
        scan.nextLine();
    }

    // Verifica se há espaços inválidos
    public static boolean hasInvalidSpace(String texto) {
        return texto.isEmpty() || !texto.matches("^\\S.*\\S$");
    }

    // Exibe um texto em negrito
    public static void printBold(Object texto) {
        System.out.print("\033[1m" + texto + "\033[0m");
    }

    // Verifica se há números em uma string
    public static boolean hasNum(String texto) {
        return texto.matches(".*\\d.*");
    }

    // Verifica se a string é composta por apenas numeros( melhoria do hasNextint )
    public static boolean isValidInt(String numero) {
        return numero.matches("^\\d+$");
    }

    // Exibe mensagem de erro e possibilita correção
    public static void fixError(String msgErro) {
        fixedError();
        System.out.print("\033[1A"); // Sobe para o pedido de input
        System.out.print("\033[K"); // apaga o input
        System.out.print("\033[2B"); // desce 2 linhas
        System.out.println("\033[1mERRO!\033[0m " + msgErro); // exibe o erro
        System.out.print("\033[3A"); // volta para a linha de input
    }

    // Apaga a mensagem de erro do fixError
    public static void fixedError() {
        System.out.print("\033[1B"); // desce para a linha de erro
        System.out.print("\033[K"); // apaga a mensagem
        System.out.print("\033[1A"); // volta para a linha correta
    }

    // Move o cursor pelo terminal e realiza as ações especificadas
    public static void controlTerminal(String... acoes) {
        for (String acao : acoes) {
            System.out.print("\033[" + acao.toUpperCase());
        }

    }
}