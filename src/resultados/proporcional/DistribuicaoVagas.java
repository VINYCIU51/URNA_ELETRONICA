package resultados.proporcional;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;
import usuarios.Candidato;
import registros.ListaCandidatos;

public class DistribuicaoVagas {

    // PREENCHE AS VAGAS COM OS CANDIDATOS MAIS VOTADOS
    public Map<String, List<Candidato>> preencherVagas(Map<String, Integer> vagasPorPartido,
            ListaCandidatos candidatos, String cargo) {
        Map<String, List<Candidato>> eleitosPorPartido = new HashMap<>();
        List<Candidato> candidatosRestantes = new ArrayList<>();

        // intera sobre cada partido e suas vagas
        for (Map.Entry<String, Integer> entry : vagasPorPartido.entrySet()) {
            String partido = entry.getKey();
            int vagas = entry.getValue();

            List<Candidato> candidatosDoPartido = new ArrayList<>();
            for (Candidato candidato : candidatos.getList().values()) { // filtra os candidatos de mesmo partido
                if (candidato.getPartido().equalsIgnoreCase(partido) && candidato.getCargo().equalsIgnoreCase(cargo)) {
                    candidatosDoPartido.add(candidato);
                }
            }

            // Ordena os candidatos pelo número de votos do maior para o menor
            candidatosDoPartido.sort(Comparator.comparingInt(Candidato::getVotos).reversed());

            List<Candidato> eleitos = new ArrayList<>();
            int candidatosEscolhidos = 0;

            // Preenche as vagas do partido com seus candidatos mais votados
            for (int i = 0; i < vagas && i < candidatosDoPartido.size(); i++) {
                eleitos.add(candidatosDoPartido.get(i));
                candidatosEscolhidos++;
            }

            // Se o partido não tiver candidatos suficientes, adiciona as vagas restantes
            // para candidatos de outros partidos
            if (candidatosEscolhidos < vagas) {
                for (Candidato candidato : candidatosDoPartido) {
                    if (candidatosEscolhidos >= vagas) {
                        break;
                    }
                    // Se ainda houver vagas e candidatos não escolhidos do partido
                    if (!eleitos.contains(candidato)) {
                        eleitos.add(candidato);
                        candidatosEscolhidos++;
                    }
                }
            }

            // Adiciona os eleitos ao mapa de eleitos por partido
            if (!eleitos.isEmpty()) {
                eleitosPorPartido.put(partido, eleitos);
            }

            // Adiciona candidatos restantes para a distribuição de outras vagas
            for (Candidato candidato : candidatosDoPartido) {
                if (!eleitos.contains(candidato)) {
                    candidatosRestantes.add(candidato);
                }
            }
        }

        // Ordena todos os candidatos restantes por votos
        candidatosRestantes.sort(Comparator.comparingInt(Candidato::getVotos).reversed());

        // Preenche as vagas restantes
        for (Candidato candidato : candidatosRestantes) {
            if (eleitosPorPartido.size() < vagasPorPartido.size()) {
                // Se ainda houver vagas, distribue entre os candidatos restantes
                String partido = candidato.getPartido();
                eleitosPorPartido.computeIfAbsent(partido, k -> new ArrayList<>()).add(candidato);
            }
        }

        return eleitosPorPartido;
    }
}
