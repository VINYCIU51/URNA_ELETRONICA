package Interface;

import java.util.Scanner;
import registros.ListaCandidatos;
import usuarios.Candidato;

public class MenuCadastroCandidato {

    public MenuCadastroCandidato(Scanner scan, ListaCandidatos candidatos) {
        util.clearTermnal();

        String nome;
        while (true) {
            System.out.print("NOME: ");
            nome = scan.nextLine();

            if (util.hasNum(nome)) {
                util.fixerror("Utilize apenas letras");

            } else {
                util.fixedError();
                break;
            }
        }

        String cargo;
        while (true) {
            System.out.print("CARGO: ");
            cargo = scan.nextLine();

            if (util.hasNum(cargo)) {
                util.fixerror("Utilize apenas letras");
            } else {
                util.fixedError();
                break;
            }
        }

        String partido;
        while (true) {
            System.out.print("PARTIDO: ");
            partido = scan.nextLine();

            if (util.hasNum(partido)) {
                util.fixerror("Utilize apenas letras");
            } else {
                util.fixedError();
                break;
            }
        }

        int numero;
        while (true) {
            System.out.print("NUMERO: ");

            if (!scan.hasNextInt()) {
                util.fixedError();
                util.fixerror("Apenas números");
                scan.nextLine();
                continue;
            }
            numero = scan.nextInt();
            scan.nextLine();

            if (numero <= 0) {
                util.fixedError();
                util.fixerror("Apenas números positivos");
            } else if (candidatos.getList().containsKey(numero)) {
                util.fixedError();
                util.fixerror("Número já cadastrado");
            } else {
                util.fixedError();
                break;
            }
        }

        Candidato candidato = new Candidato(nome, cargo, partido, numero);
        candidatos.add(candidato);
        System.out.println("Cadastro efetuado com sucesso");
        util.pressEnter(scan);
    }
}
