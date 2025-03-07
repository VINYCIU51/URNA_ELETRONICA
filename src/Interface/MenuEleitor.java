package Interface;

import auxiliares.*;

public class MenuEleitor {

    private ContextoSistema ctx;

    public MenuEleitor(ContextoSistema ctx) {
        this.ctx = ctx;
    }

    public void exibir() {
        Auxi.clearTerminal();
        while (true) {
            System.out.println("======== MENU ========\n");
            System.out.println(
                    "[1] - Votar\n" +
                            "[2] - Voltar ao login\n" +
                            "[0] - Sair do programa\n");

            String strOpcao = ctx.scan.nextLine();

            if (!Auxi.isValidInt(strOpcao)) {
                Auxi.fixError("Digite apenas números");
                Auxi.clearRange(10, "a");
                continue;
            }

            switch (Integer.parseInt(strOpcao)) {

                case 1 -> {
                    if (ctx.eleitor.getJaVotou()) {
                        Auxi.fixError("ERRO! Você não pode votar duas vezes");
                        Auxi.clearRange(10, "a");
                        continue;
                    } else if (ctx.listaCandidatos.getList().isEmpty() || !ctx.eleicao.isAtiva()) {
                        Auxi.fixError("Eleição desativada ou sem candidatos cadastrados até o momento");
                        Auxi.clearRange(10, "a");
                        continue;
                    } else {
                        ctx.exibirMenuVotacao();
                    }
                }

                case 2 -> ctx.exibirMenuLogin();

                case 0 -> System.out.println("Saindo...");

                default -> {
                    Auxi.fixError("Opção inválida");
                    Auxi.clearRange(10, "a");
                }

            }

        }
    }
}