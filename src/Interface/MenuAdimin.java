package Interface;

import java.util.Scanner;
import eleicao.*;
import usuarios.*;
import votacao.Votacao;
import registros.*;

public class MenuAdimin {

    // @formatter:off
    public MenuAdimin(Scanner scan, Eleitor eleitor, Votacao votacao, ListaEleitores eleitores, ListaCandidatos candidatos, Eleicao eleicao) {
        
        util.clearTermnal();
        while (true) {
            
            System.out.println("======== MENU ========\n");
            System.out.println(
                "[1] - Cadastrar candidato\n" +
                "[2] - Cadastrar eleitor\n" +
                "[3] - Votar\n" +
                "[4] - Configurações da eleição\n" +
                "[5] - Exibir resultados\n" +
                "[6] - Voltar ao login\n" +
                "[0] - Sair do programa");        
    // @formatter:on

            String strOpcao = scan.nextLine();

            if (!util.isValidInt(strOpcao)) {
                util.fixError("Digite apenas números");
                util.clearRange(10, "a");
                continue;
            }

            switch (Integer.parseInt(strOpcao)) {

                case 1 -> new MenuCadastroCandidato(scan, candidatos);

                case 2 -> new MenuCadastroEleitor(scan, eleitores);

                case 3 -> {
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

                case 4 -> new MenuConfigEleicao(scan, eleicao, votacao);

                case 5 -> new MenuResultados(scan, votacao);

                case 6 -> new Login(scan, eleitores, candidatos, eleicao, votacao);

                case 7 -> new Login(scan, eleitores, candidatos, eleicao, votacao);

                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }

                default -> {
                    util.fixError("Opção inválida");
                    util.clearRange(10, "a");
                }

            }

        }

    }
}
