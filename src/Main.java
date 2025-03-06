import votacao.Votacao;

import java.util.Scanner;

import eleicao.*;
import registros.ListaCandidatos;
import registros.ListaCargos;
import registros.ListaEleitores;
import usuarios.*;
import Interface.Login;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        ListaCargos cargos = new ListaCargos();
        ListaCandidatos candidatos = new ListaCandidatos();
        ListaEleitores eleitores = new ListaEleitores();
        Eleicao eleicao = new Eleicao();
        Votacao votacao = new Votacao(eleicao, 0);

        Eleitor admin = new Eleitor("admin", 20, "12345", "123");
        eleitores.add(admin);
        Eleitor teste = new Eleitor("teste", 20, "123.123.123-12", "123");
        eleitores.add(teste);
        Candidato joao = new Candidato("joao", "presidente", "pr", 12);
        candidatos.add(joao);
        Candidato paulo = new Candidato("paulo", "deputado", "pr", 15);
        candidatos.add(paulo);
        Candidato pietro = new Candidato("pietro", "deputado", "pr", 16);
        candidatos.add(pietro);
        Candidato corsa = new Candidato("corsa", "deputado", "pc", 17);
        candidatos.add(corsa);

        new Login(scan, eleitores, candidatos, eleicao, votacao, cargos);

    }
}
