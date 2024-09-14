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
        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();
        
        Aluno aluno = encontrarAlunoPorMatricula(matricula);
        if (aluno != null) {
            System.out.println(aluno);
            System.out.println("Responsável: " + aluno.getResponsavel());
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    private static Aluno encontrarAlunoPorMatricula(String matricula) {
        for (Aluno aluno : Aluno.getListaAlunos()) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null; // retorna null se o aluno n for encontrado
    }
}
