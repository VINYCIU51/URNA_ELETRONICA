package registros;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import usuarios.Eleitor;

public class ListaEleitores implements AdmRegistos<Eleitor> {
    // LISTA QUE VAI RECEBER TODOS OS ELEITORES
    private final Map<String, Eleitor> listaEleitor = new HashMap<>();

    // RETORNA A LISTA PARA MANUSEIO
    public Map<String, Eleitor> getList() {
        return Collections.unmodifiableMap(listaEleitor);
    }

    // RETORNA UM ELEITOR COM BASE NO CPF
    public Eleitor buscar(Object cpf) {
        if (cpf instanceof String) {
            return listaEleitor.get(cpf);
        }
        return null;
    }

    // ADICIONA UM ELEITOR A LISTA
    public void add(Eleitor eleitor) {
        listaEleitor.put(eleitor.getCpf(), eleitor);
    }

    // REMOVE UM ELEITOR DA LISTA
    public void remove(Object cpf) {
        if (cpf instanceof String) {
            listaEleitor.remove(cpf);
        }
    }

    // RETORNA SE O ELEITOR JA FOI CADASTRADO OU NAO
    public boolean existente(Object cpf) {
        return (cpf instanceof String) && listaEleitor.containsKey(cpf);
    }

    // EXIBE AS INFORMACOES PEDIDAS DO ELEITOR
    public void exibir(String... campos) {
        for (Eleitor eleitor : listaEleitor.values()) {
            System.out.println(eleitor.toString(campos));
        }
    }
}
