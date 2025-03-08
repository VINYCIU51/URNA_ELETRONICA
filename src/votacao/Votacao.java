package votacao;

import eleicao.Eleicao;

// Classe responsável por administrar os votos
public class Votacao {
    private int votoBranco;
    private int votoNulo;
    private int votosValidos;
    private int votosTotais;
    private int limiteVotos;
    private Eleicao eleicao;

    public Votacao(Eleicao eleicao, int limiteVotos) {
        this.votoBranco = 0;
        this.votoNulo = 0;
        this.votosValidos = 0;
        this.votosTotais = 0;
        this.limiteVotos = limiteVotos;
        this.eleicao = eleicao;
    }

    public void addBranco() {
        this.votoBranco++;
    }

    public void addNulo() {
        this.votoNulo++;
    }

    public void contabilizarVoto(String voto) {
        if (voto.toLowerCase() == "valido") {
            votosValidos++;
        } else if (voto.toLowerCase() == "nulo") {
            votoNulo++;
        } else if (voto.toLowerCase() == "branco") {
            votoBranco++;
        }
        votosTotais++;
        if (votosTotais >= limiteVotos) {
            eleicao.finalizar();
        }
    }

    public void setLimiteVotos(int limite) {
        limiteVotos = limite;
    }

    public int getBranco() {
        return votoBranco;
    }

    public int getNulo() {
        return votoNulo;
    }

    public int getVotosValidos() {
        return votosValidos;
    }

    public int getVotosTotais() {
        return votosTotais;
    }

    public int getLimiteVotos() {
        return limiteVotos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("VOTOS BRANCOS: ").append(votoBranco).append("\n");
        sb.append("VOTOS NULOS: ").append(votoNulo).append("\n");
        sb.append("VOTOS VALIDOS: ").append(votosValidos).append("\n");
        sb.append("VOTOS TOTAIS: ").append(votosTotais).append("\n");

        return sb.toString();
    }

}
