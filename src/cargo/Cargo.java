package cargo;

// Classe responsavel por criar e e manusear atributos do cargo
public class Cargo {

    private String cargo;
    private String tipo;
    private int digitos;
    private int vagas;

    public Cargo(String cargo, String tipo, int digitos, int vagas) {
        this.cargo = cargo;
        this.tipo = tipo;
        this.digitos = digitos;
        this.vagas = vagas;
    }

    public String getCargo() {
        return cargo;
    }

    public String getTipo() {
        return tipo;
    }

    public int getDigitos() {
        return digitos;
    }

    public int getVagas() {
        return vagas;
    }
}
