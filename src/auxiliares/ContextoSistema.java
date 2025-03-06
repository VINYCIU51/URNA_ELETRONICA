package auxiliares;

import java.util.Scanner;
import eleicao.Eleicao;
import usuarios.*;
import registros.*;
import votacao.Votacao;

public class ContextoSistema {
    public Scanner scan;
    public Eleitor eleitor;
    public Candidato canjdidato;
    public ListaCandidatos candidatos;
    public ListaCargos cargos;
    public ListaEleitores eleitores;
    public Eleicao eleicao;
    public Votacao votacao;

    public ContextoSistema(Scanner scan, ListaEleitores eleitores, ListaCandidatos candidatos, Eleicao eleicao,
            Votacao votacao, ListaCargos cargos) {
        this.scan = scan;
        this.eleitores = eleitores;
        this.candidatos = candidatos;
        this.eleicao = eleicao;
        this.votacao = votacao;
        this.cargos = cargos;
    }
}
