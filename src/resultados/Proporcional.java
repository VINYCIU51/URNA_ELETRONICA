package resultados;

import java.util.*;
import usuarios.Candidato;
import registros.ListaCandidatos;

// Classe responsável por determinar os vencedores de votações proporcionais 
public class Proporcional {

    // Método para calcular os vencedores de eleições proporcionais
    public List<Candidato> calcularVencedores(String cargo, int totalVagas, ListaCandidatos candidatos) {
        // PASSO 1: Calcula os votos por partido
        int totalVotos = 0;
        Map<String, Integer> votosPorPartido = new HashMap<>();

        for (Candidato candidato : candidatos.getList().values()) {
            if (!candidato.getCargo().equalsIgnoreCase(cargo)) {
                continue; // Ignora candidatos de outros cargos
            }

            String partido = candidato.getPartido();
            int votosAtuais = votosPorPartido.getOrDefault(partido, 0);
            votosPorPartido.put(partido, votosAtuais + candidato.getVotos());
            totalVotos += candidato.getVotos();
        }

        // Se não houver votos, retorna uma lista vazia
        if (totalVotos == 0) {
            return new ArrayList<>();
        }

        // PASSO 2: Calcula as vagas para cada partido
        Map<String, Integer> vagasPorPartido = new HashMap<>();
        for (Map.Entry<String, Integer> entry : votosPorPartido.entrySet()) {
            String partido = entry.getKey();
            int votos = entry.getValue();
            int vagas = (votos * totalVagas) / totalVotos; // Calcula as vagas proporcionalmente
            vagasPorPartido.put(partido, vagas);
        }

        // PASSO 3: Distribui as vagas entre os candidatos mais votados de cada partido
        List<Candidato> eleitos = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : vagasPorPartido.entrySet()) {
            String partido = entry.getKey();
            int vagas = entry.getValue();

            // Filtra os candidatos do partido e do cargo especificado
            List<Candidato> candidatosDoPartido = new ArrayList<>();
            for (Candidato candidato : candidatos.getList().values()) {
                if (candidato.getPartido().equalsIgnoreCase(partido) && candidato.getCargo().equalsIgnoreCase(cargo)) {
                    candidatosDoPartido.add(candidato);
                }
            }

            // Ordena os candidatos por votos (do maior para o menor)
            candidatosDoPartido.sort(Comparator.comparingInt(Candidato::getVotos).reversed());

            // Adiciona os candidatos mais votados à lista de eleitos
            for (int i = 0; i < vagas && i < candidatosDoPartido.size(); i++) {
                eleitos.add(candidatosDoPartido.get(i));
            }
        }

        return eleitos;
    }
}