package usuarios;

// Classe responsável por representar o eleitor
public class Eleitor {
    private String nome;
    private int idade;
    private String cpf;
    private boolean jaVotou = false;
    private String senha;

    public Eleitor(String nome, int idade, String cpf, String senha) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.senha = senha;
    }

    public String getNome() {
        return this.nome;
    }

    public int getIdade() {
        return this.idade;
    }

    public String getCpf() {
        return this.cpf;
    }

    public boolean getJaVotou() {
        return this.jaVotou;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setJaVotou() {
        this.jaVotou = true;
    }

    // Retorna as informações do eleitor com base nos parâmetros passados
    public String toString(String... campos) {
        StringBuilder sb = new StringBuilder();

        for (String campo : campos) {
            switch (campo.toLowerCase()) {
                case "nome" -> sb.append("Nome: ").append(nome);
                case "cpf" -> sb.append("CPF: ").append(cpf);
                case "idade" -> sb.append("Idade: ").append(idade);
                case "votou" -> sb.append("Votou: ").append(jaVotou ? "Sim" : "Não");
                default -> sb.append("ERRO: Campo Inválido!");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
