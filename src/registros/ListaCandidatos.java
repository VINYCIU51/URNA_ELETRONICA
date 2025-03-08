package registros;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import usuarios.Candidato;

// Classe responsavel por permitir a administração da lista de candidatos
public class ListaCandidatos implements AdmRegistos<Candidato> {
    private final Map<Integer, Candidato> listaCandidato = new HashMap<>();

    // Método para manuseio da lista
    @Override
    public Map<Integer, Candidato> getList() {
        return Collections.unmodifiableMap(listaCandidato);
    }

    // Remove um candidato da lista
    @Override
    public void remove(Object numero) {
        if (numero instanceof Integer) {
            listaCandidato.remove(numero);
        }
    }

    // Adiciona um candidato à lista
    @Override
    public void add(Candidato candidato) {
        listaCandidato.put(candidato.getNumero(), candidato);
    }

    // Retorna um candidato com base no numero de candidatura
    @Override
    public Candidato buscar(Object numero) {
        if (numero instanceof Integer) {
            return listaCandidato.get(numero);
        }
        return null;
    }

    // Exibe as informações do candidato com base no filtro passado
    // (Ex: cargo, presidente)
    public void FiltrarPor(String filtro, String valor) {

        for (Candidato candidato : listaCandidato.values()) {

            // filtra com base no cargo
            if (filtro.equalsIgnoreCase("cargo") && candidato.getCargo().equalsIgnoreCase(valor)) {
                System.out.println(candidato.toString("nome", "partido", "numero", "votos"));
            }

            // filtra com base no partido
            else if (filtro.equalsIgnoreCase("partido") && candidato.getPartido().equalsIgnoreCase(valor)) {
                System.out.println(candidato.toString("nome", "numero", "votos"));
            }
        }
    }
}
