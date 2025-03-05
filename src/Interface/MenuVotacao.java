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

        for (Candidato c : candidatos.getList().values()) { // lista de cargos inseridos
            cargosDisponiveis.add(c.getCargo());
        }

        for (String cargoAtual : cargosDisponiveis) {
            util.clearTerminal();
            if (cargosVotados.contains(cargoAtual)) { // verifica se o cargo ja foi votado
                continue;
            }

            // CABECALHO DA ABA VOTACAO
            System.out.println("VOTAÇÃO PARA " + cargoAtual.toUpperCase() + "\n");
            System.out.print("[-1] - Votar Nulo\n[-2] - Votar Branco\n");

            while (true) {
                // PEDIDO DO NUMERO DE VOTO
                System.out.print("Informe o número de seu candidato: ");
                String strNumero = scan.nextLine();

                if (!strNumero.equals("-1") && !strNumero.equals("-2") && !util.isValidInt(strNumero)) {
                    util.fixError("Entrada inválida");
                    continue;
                }

                util.fixedError();
                int numero = Integer.parseInt(strNumero);

                // BLOCO PARA VOTAR BRANCO, NULO OU EM UM CANDIDATO
                String tipoVoto;
                Candidato candidato = null;

                if (numero == -1 || numero == -2) { // verificacao para voto nulo ou branco
                    tipoVoto = (numero == -1) ? "nulo" : "branco";

                } else {
                    candidato = candidatos.buscar(numero);

                    if (candidato == null || !candidato.getCargo().equalsIgnoreCase(cargoAtual)) {
                        util.fixError("Candidato não encontrado para este cargo");
                        continue;
                    }

                    tipoVoto = "valido";
                }

                boolean votoConfirmado = false;
                while (!votoConfirmado) {
                    //@formatter:off
                    System.out.print("Confirmar voto " + (tipoVoto.equalsIgnoreCase("valido") ? ("em " + candidato.getNome()) : tipoVoto) + "? [s/n]: ");
                    //@formatter:on

                    String confirmacao = scan.nextLine();
                    if (confirmacao.equalsIgnoreCase("s")) { // caso confirme voto, encerra processo todo
                        eleitor.setJaVotou();
                        cargosVotados.add(cargoAtual);
                        votoConfirmado = true;

                    } else if (confirmacao.equalsIgnoreCase("n")) { // sai do loop de confirm e volta para o principal
                        util.clearRange(2, "a");
                        break;

                    } else {
                        util.fixError("Entrada inválida. digite 's' para confirmar ou 'n' para cancelar"); // repete
                    }
                }

                if (votoConfirmado) { // caso confirmado, passa para proximo cargo
                    break;
                }
            }
        }

        util.pressEnter(scan);
        util.clearTerminal();
    }
}
