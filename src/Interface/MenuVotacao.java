package Interface;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import usuarios.Candidato;
import usuarios.Eleitor;
import votacao.Votacao;
import registros.ListaCandidatos;
import eleicao.*;

public class MenuVotacao {

    public MenuVotacao(Scanner scan, Eleitor eleitor, ListaCandidatos candidatos, Votacao votacao, Eleicao eleicao) {

        Set<String> cargosVotados = new HashSet<>();
        Set<String> cargosDisponiveis = new HashSet<>();

        util.clearTerminal();

        for (Candidato c : candidatos.getList().values()) {
            cargosDisponiveis.add(c.getCargo());
        }

        for (String cargoAtual : cargosDisponiveis) {
            util.clearTerminal();
            if (cargosVotados.contains(cargoAtual)) {
                continue;
            }

            while (true) {
                System.out.println("VOTAÇÃO PARA " + cargoAtual.toUpperCase() + "\n"); // CABECALHO DA ABA VOTACAO

                // PEDIDO DO NUMERO DE VOTO
                System.out.print("[-1] - Votar Nulo\n[-2] - Votar Branco\n");
                System.out.print("Informe o número de seu candidato: ");
                String strNumero = scan.nextLine();

                if (!strNumero.equals("-1") && !strNumero.equals("-0") && !util.isValidInt(strNumero)) {
                    util.fixError("Entrada inválida");
                    util.clearRange(10, "a");
                    continue;
                }

                util.fixedError();
                int numero = Integer.parseInt(strNumero);

                // BLOCO PARA CONFIRMAR VOTO NULO OU BRANCO
                if (numero == -1 || numero == -2) {
                    String tipoVoto = (numero == -1) ? "nulo" : "branco";

                    System.out.print("Confirmar voto " + tipoVoto + "? [s/n]: ");
                    String confirm = scan.nextLine();

                    if (confirm.equalsIgnoreCase("s")) {
                        votacao.contabilizarVoto(tipoVoto);
                        eleitor.setJaVotou();
                        cargosVotados.add(cargoAtual);
                        break;
                    }

                    // BLOCO PARA CONFIRMAR VOTO EM UM CANDIDATO
                } else {
                    Candidato candidato = candidatos.buscar(numero);

                    if (candidato == null || !candidato.getCargo().equalsIgnoreCase(cargoAtual)) {
                        util.fixError("Candidato não encontrado para este cargo");
                        util.clearRange(10, "a");
                        continue;
                    }

                    System.out.print("Confirmar voto em " + candidato.getNome() + "? [s/n]: ");
                    String confirm = scan.nextLine();

                    if (confirm.equalsIgnoreCase("s")) {
                        candidato.addVoto();
                        votacao.contabilizarVoto("valido");
                        eleitor.setJaVotou();
                        cargosVotados.add(cargoAtual);
                        break;
                    }
                }
                util.clearTerminal();
            }
        }

        util.pressEnter(scan);
    }
}
