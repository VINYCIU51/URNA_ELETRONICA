package Interface;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import votacao.Votacao;

public class MenuResultados {

    public MenuResultados(Scanner scan, Votacao votacao) {
        util.clearTermnal();
        System.out.println("========== RESULTADOS ===========\n");
        System.out.println(votacao.toString());

        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("Relatório feito em: " + agora.format(formatter));

        util.pressEnter(scan);
    }

}
