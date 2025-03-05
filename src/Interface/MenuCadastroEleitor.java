package Interface;

import java.util.Scanner;
import registros.*;
import usuarios.Eleitor;

public class MenuCadastroEleitor {

    public MenuCadastroEleitor(Scanner scan, ListaEleitores eleitores) {
        util.clearTerminal();

        System.out.println("===== CADASTRO DE ELEITORES =====\n");

        // PEDIDO DE NOME
        String nome;
        while (true) {
            util.printBold("NOME: ");
            nome = scan.nextLine();

            if (util.hasInvalidSpace(nome)) {
                util.fixError("Uso inválido de espacos vazios");

            } else if (util.hasNum(nome)) {
                util.fixError("Digite apenas letras");

            } else {
                util.fixedError();
                break;
            }
        }

        // PEDIDO DE IDADE
        int idade = 0;
        while (true) {
            util.printBold("IDADE: ");
            String strIdade = scan.nextLine();

            if (!util.isValidInt(strIdade)) {
                util.fixError("Digite apenas números");
                continue;
            }

            idade = Integer.parseInt(strIdade);

            if (idade < 16 || idade > 120) {
                util.fixError("Idade inválida");

            } else {
                util.fixedError();
                break;
            }
        }

        // PEDIDO DE CPF
        String cpf;
        while (true) {
            util.printBold("CPF: ");
            cpf = scan.nextLine();

            if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
                util.fixError("Digite no formato 000.000.000-00");

            } else if (eleitores.getList().containsKey(cpf)) {
                util.fixError("CPF já cadastrado");

            } else {
                util.fixedError();
                break;
            }

        }

        String senha;
        while (true) {
            util.printBold("SENHA: ");
            senha = scan.nextLine();

            if (util.hasInvalidSpace(senha)) {
                util.fixError("Uso inválido de espacos vazios");
                continue;
            }
            util.fixedError();
            break;

        }

        // CRIACAO E ADIÇÃO A LISTA
        Eleitor eleitor = new Eleitor(nome, idade, cpf, senha);
        eleitores.add(eleitor);
        util.printBold("\nCadastro efetuado com sucesso!");
        util.pressEnter(scan);
        util.clearTerminal();
    }
}
