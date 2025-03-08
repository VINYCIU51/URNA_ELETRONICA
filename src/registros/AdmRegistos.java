package registros;

import java.util.Map;

// Interface apenas para manter um padrão nas classes de listas
public interface AdmRegistos<T> {

    Map<?, ?> getList();

    void add(T usuario);

    void remove(Object chave);

    T buscar(Object chave);

}
