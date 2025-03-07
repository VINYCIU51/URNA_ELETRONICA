package Interface;

import usuarios.Eleitor;
import auxiliares.*;

public class MenuCadastroEleitor {

    private ContextoSistema ctx; // Agora usamos apenas o contexto

    public MenuCadastroEleitor(ContextoSistema ctx) {
        this.ctx = ctx; // Recebe o contexto diretamente
    }

    public void exibir() {
        Auxi.clearTerminal();
        System.out.println("===== CADASTRO DE ELEITORES =====\n");

        // PEDIDO DE NOME
        String nome;
        while (true) {
            Auxi.printBold("NOME: ");
            nome = ctx.scan.nextLine(); // Usando ctx.scan

            if (Auxi.hasInvalidSpace(nome)) {
                Auxi.fixError("Uso inválido de espaços vazios");
            } else if (Auxi.hasNum(nome)) {
                Auxi.fixError("Digite apenas letras");
            } else {
                Auxi.fixedError();
                break;
            }
        }

        // PEDIDO DE IDADE
        int idade = 0;
        while (true) {
            Auxi.printBold("IDADE: ");
            String strIdade = ctx.scan.nextLine(); // Usando ctx.scan

            if (!Auxi.isValidInt(strIdade)) {
                Auxi.fixError("Digite apenas números");
                continue;
            }

            idade = Integer.parseInt(strIdade);

            if (idade < 16 || idade > 120) {
                Auxi.fixError("Idade inválida");
            } else {
                Auxi.fixedError();
                break;
            }
        }

        // PEDIDO DE CPF
        String cpf;
        while (true) {
            Auxi.printBold("CPF: ");
            cpf = ctx.scan.nextLine(); // Usando ctx.scan

            if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
                Auxi.fixError("Digite no formato 000.000.000-00");
            } else if (ctx.listaEleitores.getList().containsKey(cpf)) { // Usando ctx.listaEleitores
                Auxi.fixError("CPF já cadastrado");
            } else {
                Auxi.fixedError();
                break;
            }
        }

        // PEDIDO DE SENHA
        String senha;
        while (true) {
            Auxi.printBold("SENHA: ");
            senha = ctx.scan.nextLine(); // Usando ctx.scan

            if (Auxi.hasInvalidSpace(senha)) {
                Auxi.fixError("Uso inválido de espaços vazios");
                continue;
            }
            Auxi.fixedError();
            break;
        }

        // CRIACAO E ADIÇÃO À LISTA
        Eleitor eleitor = new Eleitor(nome, idade, cpf, senha);
        ctx.listaEleitores.add(eleitor); // Usando ctx.listaEleitores
        Auxi.printBold("\nCadastro efetuado com sucesso!");
        Auxi.pressEnter(ctx.scan); // Usando ctx.scan
        Auxi.clearTerminal();
    }
}