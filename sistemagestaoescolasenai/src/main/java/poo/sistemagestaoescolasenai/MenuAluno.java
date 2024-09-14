package poo.sistemagestaoescolasenai;

import java.util.Scanner;

public class MenuAluno {

    public static void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        String opcao;
        do {
            System.out.println("\n=============================================");
            System.out.println("|              * MENU ALUNO *               |");
            System.out.println("---------------------------------------------");
            System.out.println("|                                           |");
            System.out.println("|    (1) Visualizar Informações             |");
            System.out.println("|    (2) Atualizar Cadastro                 |");
            System.out.println("|    (3) Ver Notas                          |");
            System.out.println("|                                           |");
            System.out.println("|    (0) Sair                               |");
            System.out.println("|                                           |");
            System.out.println("---------------------------------------------\n");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    visualizarInformacoes();
                    break;
                case "2":
                    Aluno.atualizarCadastroAluno();
                    break;
                case "3":
                    Notas.gerarBoletim();
                    break;
                case "0":
                    System.out.println("Encerrando a aplicação...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
                    break;
            }
        } while (!"0".equals(opcao));
    }

    private static void visualizarInformacoes() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Confirme sua matrícula para ter acesso as suas informações: ");
        String matriculaDigitada = scanner.nextLine();

        // Procura o aluno pela matrícula
        boolean alunoEncontrado = false; // verificar se encontrou o aluno

        for (Aluno aluno : Aluno.getAlunosCadastrados()) {
            if (aluno.getMatricula().equals(matriculaDigitada)) {
                System.out.println(aluno.toString());
                alunoEncontrado = true;
                break; // Para o loop assim que encontrar o aluno
            }
        }

        if (!alunoEncontrado) {
            System.out.println("Aluno não encontrado.");
        }
    }

}
