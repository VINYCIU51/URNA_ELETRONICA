package Interface;

import java.util.Scanner;
import registros.*;
import usuarios.Eleitor;

public class MenuCadastroEleitor {

    public MenuCadastroEleitor(Scanner scan, ListaEleitores eleitores) {
        util.clearTermnal();

        System.out.println("===== Cadastro de eleitores =====\n");

        // PEDIDO DE NOME
        String nome;
        while (true) {
            util.printBold("NOME: ");
            nome = scan.nextLine();

            if (util.hasInvalidSpace(nome)) {
                util.fixError("Uso inválido de espacos vazios");
                continue;
            }
            if (util.hasNum(nome)) {
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
                util.fixedError();
                util.fixError("Digite apenas números");
                continue;
            }

            idade = Integer.parseInt(strIdade);

            if (idade < 16 || idade > 120) {
                util.fixedError();
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
                util.fixedError();
                util.fixError("Digite no formato 000.000.000-00");
                continue;
            }

            if (eleitores.getList().containsKey(cpf)) {
                util.fixedError();
                util.fixError("CPF já cadastrado");
            } else {
                util.fixedError();
                break;
            }

        }

        // CRIACAO E ADIÇÃO A LISTA
        Eleitor eleitor = new Eleitor(nome, idade, cpf);
        eleitores.add(eleitor);
        util.printBold("\nCadastro efetuado com sucesso!");
        util.pressEnter(scan);
    }
}
