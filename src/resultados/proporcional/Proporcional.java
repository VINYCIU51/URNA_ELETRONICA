package resultados.proporcional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import registros.ListaCandidatos;
import usuarios.Candidato;

// @formatter:off
public class Proporcional {
    private ListaCandidatos candidatos;

    public Proporcional(ListaCandidatos candidatos) {
        this.candidatos = candidatos;
    }

    // CALCULA QUANTAS VAGAS CADA PARTIDO TEM DIREITO
    public Map<String, Integer> calcularVagas(String cargo, int totalVagas) {
        int totalVotos = 0;
        
        Map<String, Integer> votosPorPartido = new HashMap<>();
        for (Candidato candidato : candidatos.getList().values()) { 
            if (!candidato.getCargo().equals(cargo)) {
                continue; // Pula para o próximo candidato se não for o cargo desejado
            }
        
            String partido = candidato.getPartido(); // pega o partido
            int votosAtuais = votosPorPartido.getOrDefault(partido, 0); // ve quantos votos tem no momento
            int novoTotal = votosAtuais + candidato.getVotos(); // soma com os votos do candidato atual
            votosPorPartido.put(partido, novoTotal); // adiciona de forma organizada ao map
            totalVotos += candidato.getVotos();
        }

        Map<String, Integer> vagasPorPartido = new HashMap<>();
        for (Map.Entry<String, Integer> entry : votosPorPartido.entrySet()) { // calcula quantas vagas cada partido tem direito

            String partido = entry.getKey(); // pega um partido
            int votos = entry.getValue(); // pega os votos dele
            int vagas = (int) Math.round(((double) votos / totalVotos) * totalVagas); // calcula as vagas
            vagasPorPartido.put(partido, vagas);
        }

        return vagasPorPartido;
    }

    // PREENCHE AS VAGAS COM OS CANDIDATOS MAIS VOTADOS
    public Map<String, List<Candidato>> preencherVagas(Map<String, Integer> vagasPorPartido, String cargo) {
        Map<String, List<Candidato>> eleitosPorPartido = new HashMap<>();
    
        for (Map.Entry<String, Integer> entry : vagasPorPartido.entrySet()) { // percorre cada partido / suas vagas
            String partido = entry.getKey();
            int vagas = entry.getValue();
    
            List<Candidato> candidatosDoPartido = new ArrayList<>();
            for (Candidato candidato : candidatos.getList().values()) { // filtra os candidatos de mesmo partido
                if (candidato.getPartido().equalsIgnoreCase(partido) && candidato.getCargo().equalsIgnoreCase(cargo)) {
                    candidatosDoPartido.add(candidato);
                }
            }
    
            candidatosDoPartido.sort(Comparator.comparingInt(Candidato::getVotos).reversed()); // ordena do maior para o menor com base nos votos recebidos de cada candidato
    
            List<Candidato> eleitos = new ArrayList<>();
            for (int i = 0; i < vagas && i < candidatosDoPartido.size(); i++) { // cria uma lista dos que serão eleitos
                eleitos.add(candidatosDoPartido.get(i));
            }
    
            if (!eleitos.isEmpty()) { // adiciona ao map, partido / lista de eleitos
                eleitosPorPartido.put(partido, eleitos);
            }
        }
    
        return eleitosPorPartido;
    }
    
}
// @formatter:on