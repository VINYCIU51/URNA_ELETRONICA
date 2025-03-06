package resultados.proporcional;

import java.util.HashMap;
import java.util.Map;
import registros.ListaCandidatos;
import usuarios.Candidato;

public class Proporcional {
    protected ListaCandidatos candidatos;

    public Proporcional(ListaCandidatos candidatos) {
        this.candidatos = candidatos;
    }

    // CALCULA QUANTAS VAGAS CADA PARTIDO TEM DIREITO PELO QUOCIENTE ELEITORAL
    public Map<String, Integer> calcularVagas(String cargo, int totalVagas) {
        int totalVotos = 0;
        Map<String, Integer> votosPorPartido = new HashMap<>();

        // Percorre todos os candidatos e soma os votos por partido
        for (Candidato candidato : candidatos.getList().values()) {
            if (!candidato.getCargo().equalsIgnoreCase(cargo)) {
                continue; // Pula para o próximo candidato se não for do cargo desejado
            }

            String partido = candidato.getPartido(); // Obtém o partido do candidato
            int votosAtuais = votosPorPartido.getOrDefault(partido, 0); // Obtém os votos do partido até o momento
            int novoTotal = votosAtuais + candidato.getVotos(); // Soma os votos do candidato atual
            votosPorPartido.put(partido, novoTotal); // Atualiza os votos do partido
            totalVotos += candidato.getVotos();
        }

        // Se não houver votos, retorna um mapa vazio para evitar exibições
        // desnecessárias
        if (totalVotos == 0) {
            return new HashMap<>();
        }

        Map<String, Integer> vagasPorPartido = new HashMap<>();
        for (Map.Entry<String, Integer> entry : votosPorPartido.entrySet()) {
            String partido = entry.getKey(); // Obtém o partido
            int votos = entry.getValue(); // Obtém a quantidade de votos do partido
            int vagas = (votos * totalVagas) / totalVotos; // Calcula a quantidade de vagas para o partido
            vagasPorPartido.put(partido, vagas); // Adiciona ao mapa de vagas
        }

        return vagasPorPartido;
    }
}
