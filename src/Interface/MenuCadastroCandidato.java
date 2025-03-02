package Interface;

import java.util.Scanner;
import registros.ListaCandidatos;
import usuarios.Candidato;

public class MenuCadastroCandidato {

    public MenuCadastroCandidato(Scanner scan, ListaCandidatos candidatos) {
        util.clearTermnal();

        System.out.println("===== Cadastro de candidatos =====\n");

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
                util.fixError("Utilize apenas letras");

            } else {
                util.fixedError();
                break;
            }
        }

        // PEDIDO DE CARGO
        String cargo;
        while (true) {
            util.printBold("CARGO: ");
            cargo = scan.nextLine();

            if (util.hasInvalidSpace(cargo)) {
                util.fixError("Uso inválido de espacos vazios");
                continue;
            }
            if (util.hasNum(cargo)) {
                util.fixError("Utilize apenas letras");
            } else {
                util.fixedError();
                break;
            }
        }

        // PEDIDO DE PARTIDO
        String partido;
        while (true) {
            util.printBold("PARTIDO: ");
            partido = scan.nextLine();

            if (util.hasInvalidSpace(partido)) {
                util.fixError("Uso inválido de espacos vazios");
                continue;
            }
            if (util.hasNum(partido)) {
                util.fixError("Utilize apenas letras");
            } else {
                util.fixedError();
                break;
            }
        }

        // PEDIDO DE NUMERO DE CANDIDATURA
        int numero = 0;
        while (true) {
            util.printBold("NÚMERO: ");
            String strNum = scan.nextLine();

            if (!util.isValidInt(strNum)) {
                util.fixedError();
                util.fixError("Apenas números");
                continue;
            }

            numero = Integer.parseInt(strNum);

            if (numero <= 0) {
                util.fixedError();
                util.fixError("Apenas números positivos");

            } else if (candidatos.getList().containsKey(numero)) {
                util.fixedError();
                util.fixError("Número já cadastrado");

            } else {
                util.fixedError();
                break;
            }
        }

        // CRIACAO E ADIÇÃO A LISTA
        Candidato candidato = new Candidato(nome, cargo, partido, numero);
        candidatos.add(candidato);
        util.printBold("\nCadastro efetuado com sucesso!");
        util.pressEnter(scan);
    }
}
