package Interface;

import java.util.Scanner;

public class util {

    // LIMPA O TERMINAL
    public static void clearTermnal() {

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
            System.out.print("\033[K");
            System.out.print("\033[" + range + direction.toUpperCase());
        }
    }

    // PAUSA O FLUXO ATE O ENTER SER PRESSIONADO
    public static void pressEnter(Scanner scan) {
        System.out.print("\nPressione \033[1mENTER\033[0m para voltar ao menu...");
        scan.nextLine();
    }

    // VERIFICA SE HA ESPACOS INVALIDOS OU É VAZIO
    public static boolean hasInvalidSpace(String texto) {
        return texto.isEmpty() || !texto.matches("^\\S.*\\S$");
    }

    // EXIBE UM TEXTO EM NEGRITO
    public static void printBold(Object texto) {
        System.out.print("\033[1m" + texto + "\033[0m");
    }

    // VERIFICA SE HÁ NUMEROS NA STRING
    public static boolean hasNum(String texto) {
        return texto.matches(".*\\d.*");
    }

    // VERIFICA SE O NUMERO É UM INTEIRO SEM ESPACOS
    public static boolean isValidInt(String numero) {
        return numero.matches("^\\d+$");
    }

    // EXIBE MENSAGEM DE ERRO E POSSIBILITA A CORRECAO DE INPUT
    public static void fixError(String msgErro) {
        fixedError();
        System.out.print("\033[1A"); // Sobe para o pedido de input
        System.out.print("\033[K"); // apaga o input
        System.out.print("\033[2B"); // desce 2 linhas
        System.out.println("\033[1mERRO!\033[0m " + msgErro); // exibe o erro
        System.out.print("\033[3A"); // volta para a linha de input
    }

    // APAGA A MSG DE ERRO DO FIXERROR
    public static void fixedError() {
        System.out.print("\033[1B"); // desce para a linha de erro
        System.out.print("\033[K"); // apaga a mensagem
        System.out.print("\033[1A"); // volta para a linha correta
    }

    // MOVE O CURSOR E REALLIZA A ACAO ESPECIFICADA
    public static void controlTerminal(String... acoes) {
        for (String acao : acoes) {
            System.out.print("\033[" + acao.toUpperCase());
        }

    }
}