package resultados.proporcional;

import java.util.List;
import java.util.Map;
import usuarios.Candidato;
import registros.ListaCandidatos;

public class VencedorProporcional extends Proporcional {

    public VencedorProporcional(ListaCandidatos candidatos) {
        super(candidatos);
    }

    public void exibir(String cargo, int totalVagas) {
        // EFETUA OS CALCULOS PARA DEFINIR OS VENCEDORES
        Map<String, Integer> vagasPorPartido = calcularVagas(cargo, totalVagas);
        Map<String, List<Candidato>> eleitos = preencherVagas(vagasPorPartido, cargo); // Agora passando o cargo aqui

        boolean candidatosEncontrados = false;

        for (Map.Entry<String, List<Candidato>> entry : eleitos.entrySet()) {
            String partido = entry.getKey();
            List<Candidato> candidatosEleitos = entry.getValue();

            // VERIFICA SE O PARTIDO TEM CANDIDATOS ELEITOS
            if (!candidatosEleitos.isEmpty()) {

                // EXIBE O CARGO, PARTIDO, VAGAS QUE O PARTIDO RECEBEU E SEUS OCUPANTES
                if (!candidatosEncontrados) {
                    System.out.println("\nCandidatos eleitos para o cargo de " + cargo + "\n");
                    candidatosEncontrados = true;
                }

                System.out.println("Partido: " + partido);
                System.out.println("Vagas alocadas: " + vagasPorPartido.get(partido));

                for (Candidato eleito : candidatosEleitos) {
                    System.out.println(" - " + eleito.getNome() + " (" + eleito.getVotos() + " votos)");
                }
            }
        }

        // RETORNA A MENSAGEM CASO NAO SEJAM ENCONTRADOS CANDIDATOS ELEITOS
        if (!candidatosEncontrados) {
            System.out.println("ERRO: Nenhum candidato foi eleito para o cargo de " + cargo);
        }
    }

}