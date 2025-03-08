package Interface;

import java.util.HashSet;
import java.util.Set;
import auxiliares.*;
import usuarios.*;

// Classe responsável por permitir que o usuário vote
public class MenuVotacao {
    private ContextoSistema ctx;

    // Constantes para votos nulo e branco
    private static final int VOTO_NULO = -1;
    private static final int VOTO_BRANCO = -2;

    public MenuVotacao(ContextoSistema ctx) {
        this.ctx = ctx;
    }

    public void exibir() {
        Set<String> cargosVotados = new HashSet<>();
        Set<String> cargosDisponiveis = new HashSet<>();

        // Adiciona cargos disponíveis para voto
        for (Candidato c : ctx.listaCandidatos.getList().values()) {
            cargosDisponiveis.add(c.getCargo());
        }

        // Processo de votação para cada cargo disponível
        for (String cargoAtual : cargosDisponiveis) {
            if (cargosVotados.contains(cargoAtual)) { // Verifica se já houve votação para este cargo
                continue;
            }

            Auxi.clearTerminal();
            System.out.println("VOTAÇÃO PARA " + cargoAtual.toUpperCase() + "\n");
            System.out.print("[-1] - Votar Nulo\n[-2] - Votar Branco\n");

            // Processa o voto para o cargo atual
            processarVoto(cargoAtual, cargosVotados);
        }

        Auxi.pressEnter(ctx.scan);
        Auxi.clearTerminal();
    }

    private void processarVoto(String cargo, Set<String> cargosVotados) {
        while (true) {
            // Solicita o número do candidato
            System.out.print("Informe o número de seu candidato: ");
            String strNumero = ctx.scan.nextLine();

            // Valida a entrada do usuário
            if (!strNumero.equals(String.valueOf(VOTO_NULO)) &&
                    !strNumero.equals(String.valueOf(VOTO_BRANCO)) &&
                    !Auxi.isValidInt(strNumero)) {
                Auxi.fixError("Entrada inválida");
                continue;
            }

            int numero = Integer.parseInt(strNumero);

            // Processa o voto (nulo, branco ou válido)
            if (numero == VOTO_NULO || numero == VOTO_BRANCO) {
                String tipoVoto = (numero == VOTO_NULO) ? "nulo" : "branco";
                if (confirmarVoto(tipoVoto, null)) {
                    ctx.votacao.contabilizarVoto(tipoVoto);
                    ctx.eleitor.setJaVotou();
                    cargosVotados.add(cargo);
                    break;
                }
            } else {
                Candidato candidato = ctx.listaCandidatos.buscar(numero);

                if (candidato == null || !candidato.getCargo().equalsIgnoreCase(cargo)) {
                    Auxi.fixError("Candidato não encontrado para este cargo");
                    continue;
                }

                if (confirmarVoto("válido", candidato.getNome())) {
                    candidato.addVoto();
                    ctx.votacao.contabilizarVoto("valido");
                    ctx.eleitor.setJaVotou();
                    cargosVotados.add(cargo);
                    break;
                }
            }
        }
    }

    private boolean confirmarVoto(String tipoVoto, String nomeCandidato) {
        while (true) {
            Auxi.fixedError();
            String tipoEscolhido = tipoVoto.equalsIgnoreCase("válido")
                    ? "em " + nomeCandidato
                    : tipoVoto;

            System.out.print("Confirmar voto " + tipoEscolhido + "? [s/n]: ");
            String confirmacao = ctx.scan.nextLine();

            if (confirmacao.equalsIgnoreCase("s")) {
                return true;
            } else if (confirmacao.equalsIgnoreCase("n")) {
                Auxi.fixedError();
                Auxi.clearRange(2, "a");
                return false;
            } else {
                Auxi.fixError("Entrada inválida");
            }
        }
    }
}