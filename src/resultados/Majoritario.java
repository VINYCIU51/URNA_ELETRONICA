package resultados;

import usuarios.Candidato;
import registros.ListaCandidatos;

public class Majoritario {
    private ListaCandidatos listaCandidatos;

    public Majoritario(ListaCandidatos listaCandidatos) {
        this.listaCandidatos = listaCandidatos;
    }

    public Candidato vencedor(String cargo) {
        Candidato vencedor = null;
        int maxVotos = 0;

        // PERCORRE TODOS OS CANDIDATOS DA LISTA E RETORNA O QUE TIVER MAIS VOTOS
        for (Candidato candidato : listaCandidatos.getList().values()) {
            if (candidato.getCargo().equalsIgnoreCase(cargo)) {
                if (candidato.getVotos() > maxVotos) {
                    maxVotos = candidato.getVotos();
                    vencedor = candidato;
                }
            }
        }
        return vencedor;
    }
}
