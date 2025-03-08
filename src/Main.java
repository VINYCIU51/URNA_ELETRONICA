import votacao.Votacao;

import java.util.Scanner;

import eleicao.*;
import registros.ListaCandidatos;
import registros.ListaCargos;
import registros.ListaEleitores;
import usuarios.*;
import auxiliares.ContextoSistema;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        ListaCargos cargos = new ListaCargos();
        ListaCandidatos candidatos = new ListaCandidatos();
        ListaEleitores eleitores = new ListaEleitores();

        Eleicao eleicao = new Eleicao();
        Votacao votacao = new Votacao(eleicao, 0);

        ContextoSistema contexto = new ContextoSistema(scan, eleitores, candidatos, eleicao, votacao, cargos);

        Eleitor admin = new Eleitor("admin", 20, "123.123.123-12", "12345");
        eleitores.add(admin);
        Eleitor teste = new Eleitor("teste", 20, "000.000.000-00", "123");
        eleitores.add(teste);

        // ========== ADICAO DE CANDIDATOS PARA EFETUAR OS TESTES =============

        // Presidentes
        Candidato joao = new Candidato("Joao", "presidente", "PR", 12);
        candidatos.add(joao);
        Candidato maria = new Candidato("Maria", "presidente", "PT", 13);
        candidatos.add(maria);

        // Deputados Federais
        Candidato paulo = new Candidato("Paulo", "deputado federal", "PR", 1234);
        candidatos.add(paulo);
        Candidato ana = new Candidato("Ana", "deputado federal", "PSDB", 5678);
        candidatos.add(ana);
        Candidato pietro = new Candidato("Pietro", "deputado federal", "PR", 12345);
        candidatos.add(pietro);
        Candidato carla = new Candidato("Carla", "deputado federal", "PSOL", 67890);
        candidatos.add(carla);

        // Vereadores
        Candidato corsa = new Candidato("Corsa", "vereador", "PC", 12345);
        candidatos.add(corsa);
        Candidato lucas = new Candidato("Lucas", "vereador", "PV", 67890);
        candidatos.add(lucas);
        Candidato fernanda = new Candidato("Fernanda", "vereador", "PDT", 54321);
        candidatos.add(fernanda);
        Candidato roberto = new Candidato("Roberto", "vereador", "MDB", 98765);
        candidatos.add(roberto);

        // =========================== APAGAR DEPOIS =========================

        contexto.exibirMenuLogin();

        scan.close();

    }
}
