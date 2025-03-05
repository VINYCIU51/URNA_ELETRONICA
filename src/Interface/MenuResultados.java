package Interface;

import java.util.Scanner;
import registros.ListaCandidatos;
import resultados.Majoritario;
import resultados.proporcional.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import votacao.Votacao;
import usuarios.*;

public class MenuResultados {

    public MenuResultados(Scanner scan, Votacao votacao, ListaCandidatos candidatos) {
        util.clearTerminal();
        System.out.println("========== RESULTADOS ===========\n");
        System.out.println(votacao.toString());

        // Chamada ao sistema majoritário
        Majoritario majo = new Majoritario(candidatos);
        Candidato vencedor = majo.vencedor("presidente"); // Agora retorna o vencedor
        if (vencedor != null) {
            System.out.println("Presidente: " + vencedor.getNome() + " (" + vencedor.getVotos() + " votos)");
        }

        // Chamada ao sistema proporcional
        VencedorProporcional propo = new VencedorProporcional(candidatos);
        propo.exibir("deputado", 2);

        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("Relatório feito em: " + agora.format(formatter));

        util.pressEnter(scan);
    }

}
