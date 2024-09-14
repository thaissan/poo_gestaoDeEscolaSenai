package poo.sistemagestaoescolasenai;

import java.util.Scanner;

public class MenuAdmin {

    private static final Scanner scanner = new Scanner(System.in);

    public static void exibirMenu() {

        String opcao;

        do {
            System.out.println("\n=============================================");
            System.out.println("|           * MENU ADMINISTRAÇÃO *          |");
            System.out.println("---------------------------------------------");
            System.out.println("|                                           |");
            System.out.println("|    (1) Cadastrar Aluno                    |");
            System.out.println("|    (2) Atualizar Cadastro do Aluno        |");
            System.out.println("|    (3) Remover Aluno                      |");
            System.out.println("|    (4) Listar Alunos                      |");
            System.out.println("|    (5) Cadastrar Professor                |");
            System.out.println("|    (6) Atualizar Cadastro do Professor    |");
            System.out.println("|    (7) Remover Professor                  |");
            System.out.println("|    (8) Listar Professores                 |");
            System.out.println("|    (9) Gerar Boletim                      |");
            System.out.println("|                                           |");
            System.out.println("|    (0) Sair                               |");
            System.out.println("|                                           |");
            System.out.println("---------------------------------------------\n");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    Aluno.cadastrarAluno();
                    break;
                case "2":
                    Aluno.atualizarCadastroAluno();
                    break;
                case "3":
                    Aluno.removerAluno();
                    break;
                case "4":
                    Aluno.listarAlunos();
                    break;
                case "5":
                    Professor.cadastrarProfesor();
                    break;
                case "6":
                    Professor.atualizarCadastroProfessor();
                    break;
                case "7":
                    Professor.removerProfessor();
                    break;
                case "8":
                    Professor.listarProfessores();
                    break;
                case "9":
                    Notas.gerarBoletim();                    
                    break;
                case "0":
                    System.out.println("Encerrando a aplicação...");
                    fecharScanner();
                    return;
                default:
                    System.out.println("Opção inválida! Por favor, escolha novamente.");
                    break;
            }

        } while (true);
    }

    private static void fecharScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }

    public static void main(String[] args) {
        exibirMenu();
    }
}
