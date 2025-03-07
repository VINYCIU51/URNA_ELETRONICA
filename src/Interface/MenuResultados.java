package Interface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import auxiliares.*;
import cargo.Cargo;
import resultados.*;
import resultados.proporcional.*;
import usuarios.*;

public class MenuResultados {
    private ContextoSistema ctx;

    public MenuResultados(ContextoSistema ctx) {
        this.ctx = ctx;
    }

    // Método para exibir os resultados
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
                    System.out.println(
                            cargo.getCargo() + ": " + vencedor.getNome() + " (" + vencedor.getVotos() + " votos).");
                }
            }
        }

        // Exibição dos vencedores de votações proporcionais
        VencedorProporcional proporcional = new VencedorProporcional(ctx.listaCandidatos);
        for (Cargo cargo : ctx.listaCargos.getList().values()) {
            if (cargo.getTipo().equals("proporcional")) {
                proporcional.exibir(cargo.getCargo(), cargo.getVagas());
            }
        }

        // Exibindo data e hora
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("\n\nRelatório feito em: " + agora.format(formatter));

        Auxi.pressEnter(ctx.scan);
        Auxi.clearTerminal();
    }
}