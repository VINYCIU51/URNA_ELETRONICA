package Interface;

import java.util.Scanner;
import cargo.Cargo;
import registros.*;
import resultados.Majoritario;
import resultados.proporcional.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import votacao.Votacao;
import usuarios.*;

public class MenuResultados {

    public MenuResultados(Scanner scan, Votacao votacao, ListaCandidatos candidatos, ListaCargos listCargos) {
        util.clearTerminal();
        System.out.println("========== RESULTADOS ===========\n");
        System.out.println(votacao.toString());

        // EXIBICAO DOS VENCEDORES DE VOTACOES MAJORITARIAS
        Majoritario majoritario = new Majoritario(candidatos);
        for (Cargo cargo : listCargos.getList().values()) {
            if (cargo.getTipo().equals("majoritario")) {
                Candidato vencedor = majoritario.vencedor(cargo.getCargo());

                if (vencedor != null) {
                    System.out.println(
                            cargo.getCargo() + ": " + vencedor.getNome() + " (" + vencedor.getVotos() + " votos).");
                }
            }
        }

        // EXIBICAO DOS VENCEDORES DE VOTACOES PROPORCIONAIS
        VencedorProporcional proporcional = new VencedorProporcional(candidatos);
        for (Cargo cargo : listCargos.getList().values()) {
            if (cargo.getTipo().equals("proporcional")) {
                proporcional.exibir(cargo.getCargo(), cargo.getVagas());
            }
        }

        // Exibindo data e hora
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("\n\nRelatório feito em: " + agora.format(formatter));

        util.pressEnter(scan);
        util.clearTerminal();
    }
}
