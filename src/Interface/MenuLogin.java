package Interface;

import auxiliares.*;
import usuarios.Eleitor;

// Classe responsável por permitir o login
public class MenuLogin {
    private ContextoSistema ctx;

    // Constante para formato de CPF
    private static final String FORMATO_CPF = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}";
    // Constante para o CPF do administrador
    private static final String CPF_ADMIN = "123.123.123-12";

    public MenuLogin(ContextoSistema ctx) {
        this.ctx = ctx;
    }

    public void exibir() {
        Auxi.clearTerminal();
        System.out.println("======= LOGIN =======\n");

        // Validação do CPF
        Eleitor eleitor = validarCPF();

        // Validação da senha
        validarSenha(eleitor);

        // Redireciona para o menu apropriado
        if (eleitor.getCpf().equals(CPF_ADMIN)) {
            ctx.exibirMenuAdmin(); // Menu de administrador
        } else {
            ctx.exibirMenuEleitor(); // Menu de eleitor
        }
    }

    private Eleitor validarCPF() {
        while (true) {
            Auxi.printBold("Informe seu CPF: ");
            String cpf = ctx.scan.nextLine();

            // Validação do formato do CPF
            if (!cpf.matches(FORMATO_CPF)) {
                Auxi.fixError("Formato inválido. Use o formato 000.000.000-00");
                continue;
            }

            // Busca o eleitor pelo CPF
            Eleitor eleitor = ctx.listaEleitores.buscar(cpf);

            if (eleitor == null) {
                Auxi.fixError("CPF não encontrado, tente novamente!");
            } else {
                Auxi.fixedError();
                return eleitor; // Retorna o eleitor encontrado
            }
        }
    }

    private void validarSenha(Eleitor eleitor) {
        while (true) {
            Auxi.printBold("Informe sua senha: ");
            String senha = ctx.scan.nextLine();

            // Verifica se a senha está correta
            if (eleitor.getSenha().equals(senha)) {
                ctx.setEleitor(eleitor); // Define o eleitor logado no contexto
                Auxi.fixedError();
                break; // Sai do loop após o login bem-sucedido
            } else {
                Auxi.fixError("Senha incorreta, tente novamente!");
            }
        }
    }
}