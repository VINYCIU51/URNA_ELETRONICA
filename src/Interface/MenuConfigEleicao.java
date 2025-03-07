package Interface;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import auxiliares.*;

public class MenuConfigEleicao {

    private ContextoSistema ctx;

    public MenuConfigEleicao(ContextoSistema ctx) {
        this.ctx = ctx;
    }

    public void exibir() {
        Auxi.clearTerminal();

        // @formatter:off
        System.out.println("====== CONFIGURAR ELEIÇÃO ======");
        System.out.print(
                "\033[1m\n[1] - ELEIÇÃO: \033[0m" + (ctx.eleicao.isAtiva() ? "Ativa" : "Desativada") +
                "\033[1m\n[2] - HORÁRIO DE TÉRMINO: \033[0m" + (ctx.eleicao.getHorarioTermino() != null ? ctx.eleicao.getHorarioTermino().toString() : "Não definido") +
                "\033[1m\n[3] - LIMITE DE VOTOS: \033[0m" + (ctx.votacao.getLimiteVotos() <= 0 ? "Não definido" : ctx.votacao.getLimiteVotos()) +
                "\033[1m\n[0] - VOLTAR AO MENU\033[0m\n");
        // @formatter:on

        while (true) {
            String strOpcao = ctx.scan.nextLine();

            if (!Auxi.isValidInt(strOpcao)) {
                Auxi.fixError("Digite apenas números");
                continue;
            }
            Auxi.fixedError();

            switch (Integer.parseInt(strOpcao)) {

                case 0:
                    Auxi.clearTerminal();
                    return;

                case 1:
                    if (!ctx.eleicao.isAtiva()) {
                        ctx.eleicao.iniciar();
                    } else {
                        ctx.eleicao.finalizar();
                    }

                    Auxi.controlTerminal("1a", "k", "4a", "k"); // limpa o input e a linha de eleição antiga
                    Auxi.printBold("[1] - ELEIÇÃO: ");
                    System.out.print(ctx.eleicao.isAtiva() ? "Ativa" : "Desativada");
                    Auxi.controlTerminal("4b", "100d"); // volta o cursor para a linha mais baixa à esquerda
                    break;

                case 2:
                    Auxi.controlTerminal("1a", "k", "3a"); // limpa o input de escolha e sobe para a linha de horário
                    while (true) {
                        Auxi.printBold("[2] - HORÁRIO DE TÉRMINO: ");
                        Auxi.controlTerminal("k");
                        String horario = ctx.scan.nextLine();

                        try {
                            LocalTime.parse(horario, DateTimeFormatter.ofPattern("HH:mm"));
                            ctx.eleicao.definirHorarioTermino(horario);
                            Auxi.controlTerminal("4b", "k", "2a"); // limpa o erro (se houver) e volta ao local correto
                            break;

                        } catch (DateTimeParseException e) {
                            Auxi.controlTerminal("3b");
                            Auxi.fixError("Digite um horário no formato HH:mm");
                            Auxi.controlTerminal("3a");
                        }
                    }
                    break;

                case 3:
                    Auxi.controlTerminal("1a", "k", "2a"); // limpa o input de escolha e sobe para a linha de limite

                    while (true) {
                        Auxi.printBold("[3] - LIMITE DE VOTOS: ");
                        Auxi.controlTerminal("k");
                        String strlimite = ctx.scan.nextLine();

                        if (!Auxi.isValidInt(strlimite) || Integer.parseInt(strlimite) <= 0) {
                            Auxi.controlTerminal("2b");
                            Auxi.fixError("Digite apenas números positivos");
                            Auxi.controlTerminal("2a");
                            continue;
                        }

                        ctx.votacao.setLimiteVotos(Integer.parseInt(strlimite)); // Usando ctx.votacao
                        Auxi.controlTerminal("3b", "k", "2a"); // limpa erro e retorna ao local correto
                        break;
                    }
                    break;

                default:
                    Auxi.fixError("Opção inválida");
                    break;
            }
        }
    }
}