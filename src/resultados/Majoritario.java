package resultados;

import usuarios.Candidato;
import registros.ListaCandidatos;

public class Majoritario {
    private ListaCandidatos listaCandidatos;

    public Majoritario(ListaCandidatos listaCandidatos) {
        this.listaCandidatos = listaCandidatos;
    }

    // Método para calcular os vencedores de eleições majoritárias
    public Candidato vencedor(String cargo) {
        Candidato vencedor = null;
        int maxVotos = 0;

        // percorre todos os candidatos da lista e retorna o com mais votos
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
