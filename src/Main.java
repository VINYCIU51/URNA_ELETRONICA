import votacao.Votacao;

import java.util.Scanner;

import eleicao.*;
import registros.ListaCandidatos;
import registros.ListaEleitores;
import usuarios.*;
import Interface.Login;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        ListaCandidatos candidatos = new ListaCandidatos();
        ListaEleitores eleitores = new ListaEleitores();
        Eleicao eleicao = new Eleicao();
        Votacao votacao = new Votacao(eleicao, 0);

        Eleitor admin = new Eleitor("admin", 20, "12345", "123");
        eleitores.add(admin);
        Eleitor teste = new Eleitor("teste", 20, "123.123.123-12", "123");
        eleitores.add(teste);
        Candidato candidato = new Candidato("joao", "prefeito", "pr", 12);
        candidatos.add(candidato);

        new Login(scan, eleitores, candidatos, eleicao, votacao);

    }
}
