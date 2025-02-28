package registros;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import usuarios.Candidato;

public class ListaCandidatos implements AdmRegistos<Candidato> {
    private final Map<Integer, Candidato> listaCandidato = new HashMap<>();

    // METODO PARA MANUSEIO DA LISTA
    @Override
    public Map<Integer, Candidato> getList() {
        return Collections.unmodifiableMap(listaCandidato);
    }

    // REMOVE UM CANDIDATO DA LISTA
    @Override
    public void remove(Object numero) {
        if (numero instanceof Integer) {
            listaCandidato.remove(numero);
        }
    }

    // ADICIONA UM CANDIDATO A LISTA
    @Override
    public void add(Candidato candidato) {
        listaCandidato.put(candidato.getNumero(), candidato);
    }

    // RETORNA UM CANDIDATO COM BASE NO NUMERO DE ELEICAO
    @Override
    public Candidato buscar(Object numero) {
        if (numero instanceof Integer) {
            return listaCandidato.get(numero);
        }
        return null;
    }

    // METODO PARA RETORNAR SE UM CANDIDATO JA FOI CADASTRADO OU NAO
    @Override
    public boolean existente(Object numero) {
        return (numero instanceof Integer) && listaCandidato.containsKey(numero);
    }

    // EXIBICAO DAS INFORMACOES DOS CANDIDATOS COM BASE NO PARAMETRO PASSADO
    // (EX: CARGO, PRESIDENTE)
    public void FiltrarPor(String filtro, String valor) {

        for (Candidato candidato : listaCandidato.values()) {

            // VERIFICA SE O FILTRO É CARGO E MOSTRA OS CANDIDATOS QUE POSSUEM O CARGO
            // DIGITADO
            if (filtro.equalsIgnoreCase("cargo") && candidato.getCargo().equalsIgnoreCase(valor)) {
                System.out.println(candidato.toString("nome", "partido", "numero", "votos"));
            }

            // VERIFICA SE O FILTRO É PARTIDO E MOSTRA OS CANDIDATOS QUE POSSUEM O PARTIDO
            // DIGITADO
            else if (filtro.equalsIgnoreCase("partido") && candidato.getPartido().equalsIgnoreCase(valor)) {
                System.out.println(candidato.toString("nome", "numero", "votos"));
            }
        }
    }
}
