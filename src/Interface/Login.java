package Interface;

import java.util.Scanner;
import eleicao.Eleicao;
import usuarios.Eleitor;
import registros.ListaCandidatos;
import registros.ListaEleitores;
import votacao.Votacao;

public class Login {

    public Login(Scanner scan, ListaEleitores eleitores, ListaCandidatos candidatos, Eleicao eleicao, Votacao votacao) {

        util.clearTerminal();
        System.out.println("=======LOGIN=======\n");
        Eleitor eleitor = null;

        String cpf;
        while (true) {
            util.printBold("Informe seu CPF: ");
            cpf = scan.nextLine();

            eleitor = eleitores.buscar(cpf);

            if (eleitor != null) {
                if (eleitor.getCpf().equals("12345")) {
                    new MenuAdimin(scan, eleitor, votacao, eleitores, candidatos, eleicao);
                    break;
                } else {
                    new MenuEleitor(scan, eleitor, eleitores, candidatos, eleicao, votacao);
                    break;
                }
            } else {
                util.fixError("CPF não encontrado, tente novamente!");
            }
        }
    }
}
