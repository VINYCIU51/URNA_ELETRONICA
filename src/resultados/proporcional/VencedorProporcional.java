package resultados.proporcional;

import auxiliares.Auxi;
import java.util.List;
import java.util.Map;
import usuarios.Candidato;
import registros.ListaCandidatos;

public class VencedorProporcional extends Proporcional {

    public VencedorProporcional(ListaCandidatos candidatos) {
        super(candidatos);
    }

    public void exibir(String cargo, int totalVagas) {
        // EFETUA OS CÁLCULOS PARA DEFINIR OS VENCEDORES
        Map<String, Integer> vagasPorPartido = calcularVagas(cargo, totalVagas);

        // Se nenhuma vaga foi atribuída, retorna
        if (vagasPorPartido.isEmpty()) {
            return;
        }

        // Agora chamando corretamente o método 'preencherVagas' com a Lista de
        // Candidatos
        DistribuicaoVagas distribuidor = new DistribuicaoVagas();
        Map<String, List<Candidato>> eleitos = distribuidor.preencherVagas(vagasPorPartido, candidatos, cargo);

        // Se não houver candidatos eleitos, retorna
        if (eleitos.isEmpty()) {
            return;
        }

        // Exibe o cargo uma única vez
        System.out.println("\nCARGO DE " + cargo.toUpperCase() + "\n");

        // Percorre os partidos e exibe os candidatos eleitos
        for (Map.Entry<String, List<Candidato>> entry : eleitos.entrySet()) {
            String partido = entry.getKey();
            List<Candidato> candidatosEleitos = entry.getValue();

            // Exibe apenas partidos que elegeram candidatos
            if (!candidatosEleitos.isEmpty()) {
                Auxi.printBold("Partido: " + partido + "   (" + vagasPorPartido.get(partido) + " Vagas)\n");

                for (Candidato eleito : candidatosEleitos) {
                    System.out.println(" - " + eleito.getNome() + " (" + eleito.getVotos() + " votos)");
                }
            }
        }
    }
}
