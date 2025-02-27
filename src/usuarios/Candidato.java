package usuarios;

public class Candidato {

    private String cargo;
    private String nome;
    private String partido;
    private int numero;
    private int votos;

    public Candidato(String nome, String cargo, String partido, int numero) {
        this.cargo = cargo;
        this.nome = nome;
        this.partido = partido;
        this.numero = numero;
        this.votos = 0;
    }

    public String getCargo() {
        return cargo;
    }

    public String getNome() {
        return nome;
    }

    public String getPartido() {
        return partido;
    }

    public int getNumero() {
        return numero;
    }

    public int getVotos() {
        return votos;
    }

    public void addVoto() {
        this.votos++;
    }

    // RETORNA AS INFORMACOES DO CANDIDATO FORMATADAS COM BASE NOS PARAMETROS
    // PEDIDOS

    public String toString(String... campos) {
        StringBuilder sb = new StringBuilder();

        for (String campo : campos) {
            switch (campo.toLowerCase()) {
                case "nome" -> sb.append("CANDIDATO: ").append(nome);
                case "numero" -> sb.append("NUMERO: ").append(numero);
                case "partido" -> sb.append("PARTIDO: ").append(partido);
                case "cargo" -> sb.append("CARGO: ").append(cargo);
                case "votos" -> sb.append("VOTOS RECEBIDOS: ").append(votos);
                default -> sb.append("ERRO: Campo Inválido!");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}