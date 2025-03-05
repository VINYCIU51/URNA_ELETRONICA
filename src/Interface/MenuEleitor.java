package Interface;

import java.util.Scanner;
import registros.ListaCandidatos;
import registros.ListaEleitores;
import votacao.Votacao;
import eleicao.Eleicao;
import usuarios.Eleitor;

public class MenuEleitor {
    // @formatter:off
    public MenuEleitor(Scanner scan, Eleitor eleitor, ListaEleitores eleitores, ListaCandidatos candidatos, Eleicao eleicao, Votacao votacao) {

        util.clearTerminal();
        while (true) {
            
            System.out.println("======== MENU ========\n");
            System.out.println(
                "[1] - Votar\n" +
                "[2] - Voltar ao login\n" +
                "[0] - Sair do programa\n");        
    // @formatter:on

            String strOpcao = scan.nextLine();

            if (!util.isValidInt(strOpcao)) {
                util.fixError("Digite apenas números");
                util.clearRange(10, "a");
                continue;
            }

            switch (Integer.parseInt(strOpcao)) {

                case 1 -> {
                    if (eleitor.getJaVotou()) {
                        util.fixError("ERRO! Você não pode votar duas vezes");
                        util.clearRange(10, "a");
                        continue;
                    } else if (candidatos.getList().isEmpty() || !eleicao.isAtiva()) {
                        util.fixError("Eleição desativada ou sem candidatos cadastrados até o momento");
                        util.clearRange(10, "a");
                        continue;
                    } else {
                        new MenuVotacao(scan, eleitor, candidatos, votacao, eleicao);
                    }
                }

                case 2 -> new Login(scan, eleitores, candidatos, eleicao, votacao);

                case 0 -> System.out.println("Saindo...");

                default -> {
                    util.fixError("Opção inválida");
                    util.clearRange(10, "a");
                }

            }

        }
    }
}
