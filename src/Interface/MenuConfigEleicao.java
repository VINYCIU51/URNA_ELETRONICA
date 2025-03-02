package Interface;

import eleicao.*;
import java.util.Scanner;
import java.time.*;
import java.time.format.*;

public class MenuConfigEleicao {

    public MenuConfigEleicao(Scanner scan, Eleicao eleicao) {

        util.clearTermnal();
        int opcao;

        // @formatter:off
        System.out.println("====== CONFIGURAR ELEIÇÃO ======");
        System.out.print(
                "\033[1m\n[1] - ELEIÇÃO: \033[0m" + (eleicao.isAtiva() ? "Ativa" : "Desativada") +
                "\033[1m\n[2] - HORÁRIO DE TÉRMINO: \033[0m" + (eleicao.getHorarioTermino() != null ? eleicao.getHorarioTermino().toString() : "Não definido") +
                "\033[1m\n[0] - VOLTAR AO MENU\033[0m\n");
        // @formatter:on

        while (true) {

            String strOpcao = scan.nextLine();

            // VERIFICA SE FORAM DIGITADOS APENAS NUMEROS
            if (!util.isValidInt(strOpcao)) {
                util.fixedError(1);
                util.fixError("Digite apenas números", 1);
                continue;
            }
            util.fixedError(1);
            opcao = Integer.parseInt(strOpcao);

            if (opcao == 0) {
                return;
            }

            // ATIVAR E DESATIVAR A ELEICAO
            else if (opcao == 1) {

                if (!eleicao.isAtiva()) {
                    eleicao.iniciar();
                } else {
                    eleicao.finalizar();
                }

                // ATUALIZA A LINHA DE ELEICAO NO MENU
                util.controlTerminal("1a", "k", "3a", "k"); // limpa o input e a linha de eleicao antiga
                util.printBold("[1] - ELEIÇÃO: ");
                System.out.print(eleicao.isAtiva() ? "Ativa" : "Desativada");
                util.controlTerminal("3b", "100d"); // volta o cursor para a linha mais baixa a esquerda
            }

            // ALTERA O HORARIO DE TERMINO
            else if (opcao == 2) {
                util.controlTerminal("1a", "k", "2a"); // limpa o input de escolha e sobre para a linha de horario

                while (true) {
                    util.printBold("[2] - HORÁRIO DE TÉRMINO: ");
                    util.controlTerminal("k");
                    String horario = scan.nextLine();

                    try {
                        LocalTime.parse(horario, DateTimeFormatter.ofPattern("HH:mm"));
                        eleicao.definirHorarioTermino(horario);
                        util.controlTerminal("3b", "k", "2a"); // limpa o erro (se houver) e volta ao local correto
                        break;

                    } catch (DateTimeParseException e) { // apenas controle para manter padrao do erro
                        util.controlTerminal("2b");
                        util.fixError("Digite um horário no formato HH:mm", 1);
                        util.controlTerminal("2a");
                    }
                }
            } else {
                util.fixError("Opção inválida", 1);
            }
        }
    }
}