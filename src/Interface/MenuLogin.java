package Interface;

import auxiliares.*;
import usuarios.Eleitor;

public class MenuLogin {

    private ContextoSistema ctx;

    public MenuLogin(ContextoSistema ctx) {
        this.ctx = ctx;
    }

    public void exibir() {
        Auxi.clearTerminal();
        System.out.println("======= LOGIN =======\n");
        Eleitor eleitor = null;

        String cpf;
        while (true) {
            Auxi.printBold("Informe seu CPF: ");
            cpf = ctx.scan.nextLine();

            eleitor = ctx.listaEleitores.buscar(cpf);

            if (eleitor != null) {
                ctx.setEleitor(eleitor);

                if (eleitor.getCpf().equals("12345")) {
                    ctx.exibirMenuAdmin();
                    break;
                } else {
                    ctx.exibirMenuEleitor();
                    break;
                }
            } else {
                Auxi.fixError("CPF não encontrado, tente novamente!");
            }
        }
    }
}