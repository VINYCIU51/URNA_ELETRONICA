package registros;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import cargo.Cargo;

public class ListaCargos {

    private Map<String, Cargo> cargos = new HashMap<>();

    public ListaCargos() {
        // criacao de cargos majoritarios
        Cargo presidente = new Cargo("presidente", "majoritário", 2, 1);
        Cargo governador = new Cargo("governador", "majoritário", 2, 1);
        Cargo prefeito = new Cargo("prefeito", "majoritário", 2, 1);
        Cargo senador = new Cargo("senador", "majoritário", 3, 1);

        // Cargos proporcionais (com várias vagas)
        Cargo deputadoFederal = new Cargo("deputado federal", "proporcional", 4, 3); // 513 na vida real
        Cargo deputadoEstadual = new Cargo("deputado estadual", "proporcional", 5, 2); // 94 vida real
        Cargo vereador = new Cargo("vereador", "proporcional", 5, 5); // 500 grande cidade vida real

        // Adicionando os cargos ao Map
        cargos.put("presidente", presidente);
        cargos.put("governador", governador);
        cargos.put("prefeito", prefeito);
        cargos.put("senador", senador);
        cargos.put("deputado federal", deputadoFederal);
        cargos.put("deputado estadual", deputadoEstadual);
        cargos.put("vereador", vereador);

    }

    public Map<String, Cargo> getList() {
        return Collections.unmodifiableMap(cargos);
    }

    // Método para verificar se o cargo existe
    public boolean cargoExistente(String cargo) {
        return cargos.containsKey(cargo);
    }

}
