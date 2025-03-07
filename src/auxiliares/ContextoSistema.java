package auxiliares;

import java.util.Scanner;
import eleicao.Eleicao;
import usuarios.*;
import registros.*;
import votacao.Votacao;
import Interface.*;

// Classe responsavel por passar todo o necessario para o sistema
// Evitando o acumulo de parâmetros
public class ContextoSistema {

    // parâmetros
    public Scanner scan;
    public Eleitor eleitor;
    public Candidato candidato;
    public ListaCandidatos listaCandidatos;
    public ListaCargos listaCargos;
    public ListaEleitores listaEleitores;
    public Eleicao eleicao;
    public Votacao votacao;

    // Menus
    public MenuLogin menuLogin;
    public MenuEleitor menuEleitor;
    public MenuCadastroCandidato menuCadastroCandidato;
    public MenuConfigEleicao menuConfigEleicao;
    public MenuAdimin menuAdmin;
    public MenuVotacao menuVotacao;
    public MenuResultados menuResultados;
    public MenuCadastroEleitor menuCadastroEleitor;

    public ContextoSistema(Scanner scan, ListaEleitores listaEleitores, ListaCandidatos listaCandidatos,
            Eleicao eleicao,
            Votacao votacao, ListaCargos listaCargos) {
        this.scan = scan;
        this.listaEleitores = listaEleitores;
        this.listaCandidatos = listaCandidatos;
        this.eleicao = eleicao;
        this.votacao = votacao;
        this.listaCargos = listaCargos;

        this.eleitor = null;

        // Criando os menus
        this.menuLogin = new MenuLogin(this);
        this.menuEleitor = new MenuEleitor(this);
        this.menuCadastroCandidato = new MenuCadastroCandidato(this);
        this.menuConfigEleicao = new MenuConfigEleicao(this);
        this.menuAdmin = new MenuAdimin(this);
        this.menuVotacao = new MenuVotacao(this);
        this.menuResultados = new MenuResultados(this);
        this.menuCadastroEleitor = new MenuCadastroEleitor(this);
    }

    // Métodos para exibir os menus
    public void exibirMenuLogin() {
        menuLogin.exibir();
    }

    public void exibirMenuEleitor() {
        menuEleitor.exibir();
    }

    public void exibirMenuCadastroCandidato() {
        menuCadastroCandidato.exibir();
    }

    public void exibirMenuCadastroEleitor() {
        menuCadastroEleitor.exibir();
    }

    public void exibirMenuConfigEleicao() {
        menuConfigEleicao.exibir();
    }

    public void exibirMenuAdmin() {
        menuAdmin.exibir();
    }

    public void exibirMenuVotacao() {
        menuVotacao.exibir();
    }

    public void exibirMenuResultados() {
        menuResultados.exibir();
    }

    // Método para atualizar o eleitor
    public void setEleitor(Eleitor eleitor) {
        this.eleitor = eleitor;
    }
}
