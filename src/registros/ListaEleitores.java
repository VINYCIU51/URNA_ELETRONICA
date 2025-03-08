package registros;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import usuarios.Eleitor;

// Classe responsável por permitir manuseio da lista de eleitores
public class ListaEleitores implements AdmRegistos<Eleitor> {
    private final Map<String, Eleitor> listaEleitor = new HashMap<>();

    // Retorna a lista para manuseio
    @Override
    public Map<String, Eleitor> getList() {
        return Collections.unmodifiableMap(listaEleitor);
    }

    // Retorna um eleitor com base no CPF
    @Override
    public Eleitor buscar(Object cpf) {
        if (cpf instanceof String) {
            return listaEleitor.get(cpf);
        }
        return null;
    }

    // Adiciona um eleitor à lista
    @Override
    public void add(Eleitor eleitor) {
        listaEleitor.put(eleitor.getCpf(), eleitor);
    }

    // Remove um eleitor da lista
    @Override
    public void remove(Object cpf) {
        if (cpf instanceof String) {
            listaEleitor.remove(cpf);
        }
    }

    // Exibe as informações de um eleitor com base nos parâmetros passados
    public void exibir(String... campos) {
        for (Eleitor eleitor : listaEleitor.values()) {
            System.out.println(eleitor.toString(campos));
        }
    }
}
