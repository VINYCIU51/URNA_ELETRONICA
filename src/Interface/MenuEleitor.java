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

        util.clearTermnal();
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
                        System.out.println("ERRO! Você só pode votar uma vez");
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
