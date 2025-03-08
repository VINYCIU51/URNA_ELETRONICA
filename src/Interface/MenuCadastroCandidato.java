package Interface;

import cargo.*;
import usuarios.Candidato;
import auxiliares.*;

// Classe responsável por permitir o cadastro de um candidato
public class MenuCadastroCandidato {
    private ContextoSistema ctx;

    public MenuCadastroCandidato(ContextoSistema ctx) {
        this.ctx = ctx;
    }

    public void exibir() {
        Auxi.clearTerminal();
        System.out.println("===== CADASTRO DE CANDIDATOS =====\n");

        // Validação do nome
        String nome = validarNome();

        // Validação do cargo
        String cargo = validarCargo();

        // Validação do partido
        String partido = validarPartido();

        // Validação do número de candidatura
        int numero = validarNumeroCandidatura(cargo);

        // Criação do candidato e adição à lista
        Candidato candidato = new Candidato(nome, cargo, partido, numero);
        ctx.listaCandidatos.add(candidato);

        // Mensagem de sucesso
        Auxi.printBold("\n✅ Cadastro efetuado com sucesso!");
        Auxi.pressEnter(ctx.scan);
        Auxi.clearTerminal();
    }

    private String validarNome() {
        while (true) {
            Auxi.printBold("NOME: ");
            String nome = ctx.scan.nextLine();

            if (nome.trim().isEmpty()) {
                Auxi.fixError("O nome não pode ser vazio");
            } else if (Auxi.hasInvalidSpace(nome)) {
                Auxi.fixError("Uso inválido de espaços vazios");
            } else if (Auxi.hasNum(nome)) {
                Auxi.fixError("Utilize apenas letras");
            } else {
                Auxi.fixedError();
                return nome;
            }
        }
    }

    private String validarCargo() {
        while (true) {
            Auxi.printBold("CARGO: ");
            String cargo = ctx.scan.nextLine();

            if (cargo.trim().isEmpty()) {
                Auxi.fixError("O cargo não pode ser vazio");
            } else if (Auxi.hasInvalidSpace(cargo)) {
                Auxi.fixError("Uso inválido de espaços vazios");
            } else if (Auxi.hasNum(cargo)) {
                Auxi.fixError("Utilize apenas letras");
            } else if (!ctx.listaCargos.getList().containsKey(cargo.toLowerCase())) {
                Auxi.fixError("Cargo não existente");
            } else {
                Auxi.fixedError();
                return cargo.toLowerCase(); // Retorna o cargo em minúsculas para consistência
            }
        }
    }

    private String validarPartido() {
        while (true) {
            Auxi.printBold("PARTIDO: ");
            String partido = ctx.scan.nextLine();

            if (partido.trim().isEmpty()) {
                Auxi.fixError("O partido não pode ser vazio");
            } else if (Auxi.hasInvalidSpace(partido)) {
                Auxi.fixError("Uso inválido de espaços vazios");
            } else if (Auxi.hasNum(partido)) {
                Auxi.fixError("Utilize apenas letras");
            } else {
                Auxi.fixedError();
                return partido;
            }
        }
    }

    private int validarNumeroCandidatura(String cargo) {
        while (true) {
            Auxi.printBold("NÚMERO: ");
            String strNum = ctx.scan.nextLine();

            if (!Auxi.isValidInt(strNum)) {
                Auxi.fixError("Apenas números são permitidos");
                continue;
            }

            int numero = Integer.parseInt(strNum);

            if (numero <= 0) {
                Auxi.fixError("Apenas números positivos são permitidos");
            } else if (ctx.listaCandidatos.getList().containsKey(numero)) {
                Auxi.fixError("Número já cadastrado");
            } else {
                Cargo cargoEleitoral = ctx.listaCargos.getList().get(cargo);
                if (strNum.length() != cargoEleitoral.getDigitos()) {
                    Auxi.fixError("O número deve ter " + cargoEleitoral.getDigitos() + " dígitos");
                } else {
                    Auxi.fixedError();
                    return numero;
                }
            }
        }
    }
}