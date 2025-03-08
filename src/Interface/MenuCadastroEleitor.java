package Interface;

import usuarios.Eleitor;
import auxiliares.*;

// Classe responsável por permitir o cadastro de um eleitor
public class MenuCadastroEleitor {
    private ContextoSistema ctx;

    // Constantes para validação
    private static final int IDADE_MINIMA = 16;
    private static final int IDADE_MAXIMA = 120;
    private static final String FORMATO_CPF = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}";

    public MenuCadastroEleitor(ContextoSistema ctx) {
        this.ctx = ctx;
    }

    public void exibir() {
        Auxi.clearTerminal();
        System.out.println("===== CADASTRO DE ELEITORES =====\n");

        // Validação do nome
        String nome = validarNome();

        // Validação da idade
        int idade = validarIdade();

        // Validação do CPF
        String cpf = validarCPF();

        // Validação da senha
        String senha = validarSenha();

        // Criação do eleitor e adição à lista
        Eleitor eleitor = new Eleitor(nome, idade, cpf, senha);
        ctx.listaEleitores.add(eleitor);

        // Mensagem de sucesso
        Auxi.printBold("\n✅ Cadastro efetuado com sucesso!");
        Auxi.pressEnter(ctx.scan);
        Auxi.clearTerminal();
    }

    private String validarNome() {
        while (true) {
            Auxi.printBold("NOME: ");
            String nome = ctx.scan.nextLine();

            if (nome.trim().isEmpty()) {
                Auxi.fixError("O nome não pode ser vazio");
            } else if (Auxi.hasInvalidSpace(nome)) {
                Auxi.fixError("Uso inválido de espaços vazios");
            } else if (Auxi.hasNum(nome)) {
                Auxi.fixError("Digite apenas letras");
            } else {
                Auxi.fixedError();
                return nome;
            }
        }
    }

    private int validarIdade() {
        while (true) {
            Auxi.printBold("IDADE: ");
            String strIdade = ctx.scan.nextLine();

            if (!Auxi.isValidInt(strIdade)) {
                Auxi.fixError("Digite apenas números");
                continue;
            }

            int idade = Integer.parseInt(strIdade);

            if (idade < IDADE_MINIMA || idade > IDADE_MAXIMA) {
                Auxi.fixError("Idade inválida. Deve ser entre " + IDADE_MINIMA + " e " + IDADE_MAXIMA + " anos");
            } else {
                Auxi.fixedError();
                return idade;
            }
        }
    }

    private String validarCPF() {
        while (true) {
            Auxi.printBold("CPF: ");
            String cpf = ctx.scan.nextLine();

            if (!cpf.matches(FORMATO_CPF)) {
                Auxi.fixError("Digite no formato 000.000.000-00 (com pontos e traço)");
            } else if (ctx.listaEleitores.getList().containsKey(cpf)) {
                Auxi.fixError("CPF já cadastrado");
            } else {
                Auxi.fixedError();
                return cpf;
            }
        }
    }

    private String validarSenha() {
        while (true) {
            Auxi.printBold("SENHA: ");
            String senha = ctx.scan.nextLine();

            if (senha.length() < 6) {
                Auxi.fixError("A senha deve ter pelo menos 6 caracteres");
            } else if (Auxi.hasInvalidSpace(senha)) {
                Auxi.fixError("Uso inválido de espaços vazios");
            } else {
                Auxi.fixedError();
                return senha;
            }
        }
    }
}