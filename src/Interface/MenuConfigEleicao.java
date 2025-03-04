package Interface;

import eleicao.*;
import votacao.Votacao;

import java.util.Scanner;
import java.time.*;
import java.time.format.*;

public class MenuConfigEleicao {

    public MenuConfigEleicao(Scanner scan, Eleicao eleicao, Votacao votacao) {

        util.clearTermnal();

        // @formatter:off
        System.out.println("====== CONFIGURAR ELEIÇÃO ======");
        System.out.print(
                "\033[1m\n[1] - ELEIÇÃO: \033[0m" + (eleicao.isAtiva() ? "Ativa" : "Desativada") +
                "\033[1m\n[2] - HORÁRIO DE TÉRMINO: \033[0m" + (eleicao.getHorarioTermino() != null ? eleicao.getHorarioTermino().toString() : "Não definido") +
                "\033[1m\n[3] - LIMITE DE VOTOS: \033[0m" + (votacao.getLimiteVotos() <= 0 ? "Não definido" : votacao.getLimiteVotos()) +
                "\033[1m\n[0] - VOLTAR AO MENU\033[0m\n");
        // @formatter:on

        while (true) {
            String strOpcao = scan.nextLine();

            // VERIFICA SE FORAM DIGITADOS APENAS NÚMEROS
            if (!util.isValidInt(strOpcao)) {
                util.fixError("Digite apenas números");
                continue;
            }
            util.fixedError();

            switch (Integer.parseInt(strOpcao)) {

                case 0:
                    util.clearTermnal();
                    return;

                case 1:
                    if (!eleicao.isAtiva()) {
                        eleicao.iniciar();
                    } else {
                        eleicao.finalizar();
                    }

                    util.controlTerminal("1a", "k", "4a", "k"); // limpa o input e a linha de eleição antiga
                    util.printBold("[1] - ELEIÇÃO: ");
                    System.out.print(eleicao.isAtiva() ? "Ativa" : "Desativada");
                    util.controlTerminal("4b", "100d"); // volta o cursor para a linha mais baixa à esquerda
                    break;

                case 2:
                    util.controlTerminal("1a", "k", "3a"); // limpa o input de escolha e sobe para a linha de horário
                    while (true) {
                        util.printBold("[2] - HORÁRIO DE TÉRMINO: ");
                        util.controlTerminal("k");
                        String horario = scan.nextLine();

                        try {
                            LocalTime.parse(horario, DateTimeFormatter.ofPattern("HH:mm"));
                            eleicao.definirHorarioTermino(horario);
                            util.controlTerminal("4b", "k", "2a"); // limpa o erro (se houver) e volta ao local correto
                            break;

                        } catch (DateTimeParseException e) { // controle para manter padrão do erro
                            util.controlTerminal("3b");
                            util.fixError("Digite um horário no formato HH:mm");
                            util.controlTerminal("3a");
                        }
                    }
                    break;

                case 3:
                    util.controlTerminal("1a", "k", "2a"); // limpa o input de escolha e sobe para a linha de limite

                    while (true) {
                        util.printBold("[3] - LIMITE DE VOTOS: ");
                        util.controlTerminal("k");
                        String strlimite = scan.nextLine();

                        if (!util.isValidInt(strlimite) || Integer.parseInt(strlimite) <= 0) { // padrao de erro
                            util.controlTerminal("2b");
                            util.fixError("Digite apenas números positivos");
                            util.controlTerminal("2a");
                            continue;
                        }

                        votacao.setLimiteVotos(Integer.parseInt(strlimite)); // seta o limite
                        util.controlTerminal("3b", "k", "2a"); // limpa erro e retorna ao local correto
                        break;
                    }
                    break;

                default:
                    util.fixError("Opção inválida");
                    break;
            }
        }
    }
}
