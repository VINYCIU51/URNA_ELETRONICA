package Interface;

import cargo.*;
import usuarios.Candidato;
import auxiliares.*;

public class MenuCadastroCandidato {

    private ContextoSistema ctx;

    public MenuCadastroCandidato(ContextoSistema ctx) {
        this.ctx = ctx;
    }

    public void exibir() {
        Auxi.clearTerminal();
        System.out.println("===== CADASTRO DE CANDIDATOS =====\n");

        // PEDIDO DE NOME
        String nome;
        while (true) {
            Auxi.printBold("NOME: ");
            nome = ctx.scan.nextLine();

            if (Auxi.hasInvalidSpace(nome)) {
                Auxi.fixError("Uso inválido de espaços vazios");
            } else if (Auxi.hasNum(nome)) {
                Auxi.fixError("Utilize apenas letras");
            } else {
                Auxi.fixedError();
                break;
            }
        }

        // PEDIDO DE CARGO
        String cargo;
        while (true) {
            Auxi.printBold("CARGO: ");
            cargo = ctx.scan.nextLine();

            if (Auxi.hasInvalidSpace(cargo)) {
                Auxi.fixError("Uso inválido de espaços vazios");
            } else if (Auxi.hasNum(cargo)) {
                Auxi.fixError("Utilize apenas letras");
            } else if (!ctx.listaCargos.getList().containsKey(cargo.toLowerCase())) {
                Auxi.fixError("Cargo não existente");
            } else {
                Auxi.fixedError();
                break;
            }
        }

        // PEDIDO DE PARTIDO
        String partido;
        while (true) {
            Auxi.printBold("PARTIDO: ");
            partido = ctx.scan.nextLine(); // Usando ctx.scan

            if (Auxi.hasInvalidSpace(partido)) {
                Auxi.fixError("Uso inválido de espaços vazios");
            } else if (Auxi.hasNum(partido)) {
                Auxi.fixError("Utilize apenas letras");
            } else {
                Auxi.fixedError();
                break;
            }
        }

        // PEDIDO DE NÚMERO DE CANDIDATURA
        int numero = 0;
        while (true) {
            Auxi.printBold("NÚMERO: ");
            String strNum = ctx.scan.nextLine(); // Usando ctx.scan

            if (!Auxi.isValidInt(strNum)) {
                Auxi.fixError("Apenas números");
                continue;
            }

            numero = Integer.parseInt(strNum);

            if (numero <= 0) {
                Auxi.fixError("Apenas números positivos");
            } else if (ctx.listaCandidatos.getList().containsKey(numero)) {
                Auxi.fixError("Número já cadastrado");
            } else {
                Cargo cargoEleitoral = ctx.listaCargos.getList().get(cargo);
                if (strNum.length() != cargoEleitoral.getDigitos()) {
                    Auxi.fixError("O número deve ter " + cargoEleitoral.getDigitos() + " dígitos");
                } else {
                    Auxi.fixedError();
                    break;
                }
            }
        }

        // CRIACAO E ADIÇÃO À LISTA
        Candidato candidato = new Candidato(nome, cargo, partido, numero);
        ctx.listaCandidatos.add(candidato);
        Auxi.printBold("\nCadastro efetuado com sucesso!");
        Auxi.pressEnter(ctx.scan);
        Auxi.clearTerminal();
    }
}