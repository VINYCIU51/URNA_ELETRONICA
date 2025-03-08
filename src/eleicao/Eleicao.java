package eleicao;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// Classe responsável por criar a eleição e administrala
public class Eleicao {
    private boolean ativa;
    private LocalTime horarioTermino = null;

    public Eleicao() {
        this.ativa = false;
    }

    // Ativa a eleição
    public void iniciar() {
        this.ativa = true;
    }

    // Finaliza a eleição, negando novos votos
    public void finalizar() {
        this.ativa = false;
    }

    // Define um horário para o termino da eleição
    public void definirHorarioTermino(String horario) {
        horarioTermino = LocalTime.parse(horario, DateTimeFormatter.ofPattern("HH:mm"));
    }

    public LocalTime getHorarioTermino() {
        return horarioTermino;
    }

    // Verifica se a eleição está ativa
    public boolean isAtiva() {
        return ativa && (horarioTermino == null || LocalTime.now().isBefore(horarioTermino));
    }

}
