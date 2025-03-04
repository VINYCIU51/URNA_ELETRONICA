package eleicao;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Eleicao {
    private boolean ativa;
    private LocalTime horarioTermino = null;

    public Eleicao() {
        this.ativa = false;
    }

    // INICIA A ELEICAO PARA RECEBER VOTOS
    public void iniciar() {
        this.ativa = true;
    }

    // FINALIZA A VOTACAO, NAO PERMITIDO MAIS VOTOS
    public void finalizar() {
        this.ativa = false;
    }

    // DEFINE UM HORARIO PARA O TERMINO DA VOTACAO
    public void definirHorarioTermino(String horario) {
        horarioTermino = LocalTime.parse(horario, DateTimeFormatter.ofPattern("HH:mm"));
    }

    public LocalTime getHorarioTermino() {
        return horarioTermino;
    }

    // VERIFICA SE A VOTACAO ESTA ATIVA OU NAO
    public boolean isAtiva() {
        return ativa && (horarioTermino == null || LocalTime.now().isBefore(horarioTermino));
    }

}
