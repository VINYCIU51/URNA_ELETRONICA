package registros;

import java.util.Map;

public interface AdmRegistos<T> {

    Map<?, ?> getList();

    void add(T usuario);

    void remove(Object chave);

    boolean existente(Object chave);

    T buscar(Object chave);

}
