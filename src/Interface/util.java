package Interface;

import java.util.Scanner;

public class util {

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

    public static void pressEnter(Scanner scan) {
        System.out.println("\nPressione ENTER para voltar ao menu...");
        scan.nextLine();
    }

    public static boolean hasNum(String txt) {
        return txt.matches(".*\\d.*");
    }

    public static void fixerror(String erro) {
        System.out.print("\033[1A");
        System.out.print("\033[K");
        System.out.print("\033[2B");
        System.out.println("ERRO! " + erro);
        System.out.print("\033[3A");
    }

    public static void fixedError() {
        System.out.print("\033[1B");
        System.out.print("\033[K");
        System.out.print("\033[1A");
    }
}