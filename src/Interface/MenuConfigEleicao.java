package Interface;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import auxiliares.*;

// Classe responsável por permitir a configuração da eleição
public class MenuConfigEleicao {
    private ContextoSistema ctx;

    // Constante para o formato do horário
    private static final String FORMATO_HORARIO = "HH:mm";

    public MenuConfigEleicao(ContextoSistema ctx) {
        this.ctx = ctx;
    }

    public void exibir() {
        Auxi.clearTerminal();

        // Exibe o menu de configuração
        exibirMenu();

        while (true) {
            String strOpcao = ctx.scan.nextLine();

            if (!Auxi.isValidInt(strOpcao)) {
                Auxi.fixError("Digite apenas números");
                continue;
            }
            Auxi.fixedError();

            int opcao = Integer.parseInt(strOpcao);

            switch (opcao) {
                case 0:
                    Auxi.clearTerminal();
                    return;

                case 1:
                    alternarEstadoEleicao();
                    break;

                case 2:
                    definirHorarioTermino();
                    break;

                case 3:
                    definirLimiteVotos();
                    break;

                default:
                    Auxi.fixError("Opção inválida");
                    break;
            }
        }
    }

    private void exibirMenu() {
        // @formatter:off
        System.out.println("====== CONFIGURAR ELEIÇÃO ======");
        System.out.print(
                "\033[1m\n[1] - ELEIÇÃO: \033[0m" + (ctx.eleicao.isAtiva() ? "Ativa" : "Desativada") +
                "\033[1m\n[2] - HORÁRIO DE TÉRMINO: \033[0m" + (ctx.eleicao.getHorarioTermino() != null ? ctx.eleicao.getHorarioTermino().toString() : "Não definido") +
                "\033[1m\n[3] - LIMITE DE VOTOS: \033[0m" + (ctx.votacao.getLimiteVotos() <= 0 ? "Não definido" : ctx.votacao.getLimiteVotos()) +
                "\033[1m\n[0] - VOLTAR AO MENU\033[0m\n");
        // @formatter:on
    }

    private void alternarEstadoEleicao() {
        if (!ctx.eleicao.isAtiva()) {
            ctx.eleicao.iniciar();
        } else {
            ctx.eleicao.finalizar();
        }

        Auxi.controlTerminal("1a", "k", "4a", "k"); // Limpa o input e a linha de eleição antiga
        Auxi.printBold("[1] - ELEIÇÃO: ");
        System.out.print(ctx.eleicao.isAtiva() ? "Ativa" : "Desativada");
        Auxi.controlTerminal("4b", "100d"); // Posiciona o cursor à esquerda inferior
    }

    private void definirHorarioTermino() {
        Auxi.controlTerminal("1a", "k", "3a"); // Limpa o input de escolha e sobe para a linha de horário

        while (true) {
            Auxi.printBold("[2] - HORÁRIO DE TÉRMINO: ");
            Auxi.controlTerminal("k");
            String horario = ctx.scan.nextLine();

            try {
                LocalTime.parse(horario, DateTimeFormatter.ofPattern(FORMATO_HORARIO));
                ctx.eleicao.definirHorarioTermino(horario);
                Auxi.controlTerminal("4b", "k", "2a"); // Limpa o erro (se houver) e volta ao local correto
                break;
            } catch (DateTimeParseException e) {
                Auxi.controlTerminal("3b");
                Auxi.fixError("Digite um horário no formato " + FORMATO_HORARIO);
                Auxi.controlTerminal("3a");
            }
        }
    }

    private void definirLimiteVotos() {
        Auxi.controlTerminal("1a", "k", "2a"); // Limpa o input e vai para a linha de limite de votos

        while (true) {
            Auxi.printBold("[3] - LIMITE DE VOTOS: ");
            Auxi.controlTerminal("k");
            String strLimite = ctx.scan.nextLine();

            if (!Auxi.isValidInt(strLimite) || Integer.parseInt(strLimite) <= 0) {
                Auxi.controlTerminal("2b");
                Auxi.fixError("Digite apenas números positivos");
                Auxi.controlTerminal("2a");
                continue;
            }

            ctx.votacao.setLimiteVotos(Integer.parseInt(strLimite));
            Auxi.controlTerminal("3b", "k", "2a"); // Limpa erro e retorna ao local correto
            break;
        }
    }
}