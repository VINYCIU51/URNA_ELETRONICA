package Interface;

import java.util.HashSet;
import java.util.Set;
import auxiliares.*;
import usuarios.*;

public class MenuVotacao {

    private ContextoSistema ctx;

    public MenuVotacao(ContextoSistema ctx) {
        this.ctx = ctx;
    }

    public void exibir() {
        Set<String> cargosVotados = new HashSet<>();
        Set<String> cargosDisponiveis = new HashSet<>();

        Auxi.clearTerminal();

        // Adicionando cargos disponíveis
        for (Candidato c : ctx.listaCandidatos.getList().values()) {
            cargosDisponiveis.add(c.getCargo());
        }

        // Processo de votação
        for (String cargoAtual : cargosDisponiveis) {
            Auxi.clearTerminal();
            if (cargosVotados.contains(cargoAtual)) {
                continue;
            }

            // Cabeçalho da aba de votação
            System.out.println("VOTAÇÃO PARA " + cargoAtual.toUpperCase() + "\n");
            System.out.print("[-1] - Votar Nulo\n[-2] - Votar Branco\n");

            while (true) {
                // Pedindo o número de voto
                System.out.print("Informe o número de seu candidato: ");
                String strNumero = ctx.scan.nextLine();

                if (!strNumero.equals("-1") && !strNumero.equals("-2") && !Auxi.isValidInt(strNumero)) {
                    Auxi.fixError("Entrada inválida");
                    continue;
                }

                Auxi.fixedError();
                int numero = Integer.parseInt(strNumero);

                // Bloco para votar nulo, branco ou em um candidato
                String tipoVoto;
                Candidato candidato = null;

                if (numero == -1 || numero == -2) {
                    tipoVoto = (numero == -1) ? "nulo" : "branco";
                } else {
                    candidato = ctx.listaCandidatos.buscar(numero);

                    if (candidato == null || !candidato.getCargo().equalsIgnoreCase(cargoAtual)) {
                        Auxi.fixError("Candidato não encontrado para este cargo");
                        continue;
                    }

                    tipoVoto = "valido";
                }

                // Loop de confirmação do voto
                boolean votoConfirmado = false;
                while (!votoConfirmado) {
                    // Verifica se o voto é válido antes de acessar candidato.getNome()
                    String mensagemConfirmacao = tipoVoto.equalsIgnoreCase("valido")
                            ? "em " + candidato.getNome()
                            : tipoVoto;

                    System.out.print("Confirmar voto " + mensagemConfirmacao + "? [s/n]: ");

                    String confirmacao = ctx.scan.nextLine();
                    if (confirmacao.equalsIgnoreCase("s")) {
                        votoConfirmado = true;
                    } else if (confirmacao.equalsIgnoreCase("n")) {
                        Auxi.clearRange(2, "a");
                        break;
                    } else {
                        Auxi.fixError("Entrada inválida");
                    }
                }

                // Computa o voto se confirmado
                if (votoConfirmado) {
                    if (candidato != null) {
                        candidato.addVoto(); // Adiciona voto apenas se o candidato não for nulo
                    }
                    ctx.votacao.contabilizarVoto(tipoVoto);
                    ctx.eleitor.setJaVotou();
                    cargosVotados.add(cargoAtual);
                    break;
                }
            }
        }

        Auxi.pressEnter(ctx.scan);
        Auxi.clearTerminal();
    }
}