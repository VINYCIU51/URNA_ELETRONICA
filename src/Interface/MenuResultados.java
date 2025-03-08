package Interface;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import auxiliares.*;
import cargo.Cargo;
import resultados.*;
import usuarios.*;

// Classe responsável por permitir ver os resultados da eleição
public class MenuResultados {
    private ContextoSistema ctx;

    public MenuResultados(ContextoSistema ctx) {
        this.ctx = ctx;
    }

    public void exibir() {
        Auxi.clearTerminal();
        System.out.println("========== RESULTADOS ===========\n");
        System.out.println(ctx.votacao.toString());

        // Exibição dos vencedores de votações majoritárias
        Majoritario majoritario = new Majoritario(ctx.listaCandidatos);
        for (Cargo cargo : ctx.listaCargos.getList().values()) {
            if (cargo.getTipo().equals("majoritario")) {
                Candidato vencedor = majoritario.vencedor(cargo.getCargo());

                if (vencedor != null) {
                    Auxi.printBold("\n" + cargo.getCargo().toUpperCase() + ": ");
                    System.out.print(vencedor.getNome() + " (" + vencedor.getVotos() + " votos)\n");
                }
                /*
                 * else {
                 * Auxi.printBold("\n" + cargo.getCargo().toUpperCase() + ": ");
                 * System.out.print("Nenhum vencedor encontrado.");
                 */}
        }

        // Exibição dos vencedores de votações proporcionais
        Proporcional proporcional = new Proporcional();
        for (Cargo cargo : ctx.listaCargos.getList().values()) {
            if (cargo.getTipo().equals("proporcional")) {
                List<Candidato> eleitos = proporcional.calcularVencedores(cargo.getCargo(), cargo.getVagas(),
                        ctx.listaCandidatos);

                if (!eleitos.isEmpty()) {
                    Auxi.printBold("\n" + cargo.getCargo().toUpperCase() + ":\n");
                    for (Candidato eleito : eleitos) {
                        System.out.println(" - " + eleito.getNome() + " (" + eleito.getVotos() + " votos)\n");
                    }
                } /*
                   * else {
                   * Auxi.printBold("\n" + cargo.getCargo().toUpperCase() + ": ");
                   * System.out.print("Nenhum candidato eleito.");
                   * }
                   */
            }
        }

        // Exibindo data e hora do relatório
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("\n\nRelatório feito em: " + agora.format(formatter));

        Auxi.pressEnter(ctx.scan);
        Auxi.clearTerminal();
    }
}