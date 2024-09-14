package poo.sistemagestaoescolasenai;

import java.util.List;
import java.util.Scanner;

public class MenuProfessor {
    private Professor professor;
    private Scanner scanner = new Scanner(System.in);

    public MenuProfessor(Professor professor) {
        this.professor = professor;
    } // instancia de Professor

    public void exibirMenu() {
        String opcao;

        do {
            System.out.println("\n=============================================");
            System.out.println("|             * MENU PROFESSOR *            |");
            System.out.println("---------------------------------------------");
            System.out.println("|                                           |");
            System.out.println("|    (1) Visualizar Informações             |");
            System.out.println("|    (2) Atualizar Cadastro                 |");
            System.out.println("|    (3) Listar Alunos                      |");
            System.out.println("|    (4) Lançar Notas                       |");
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
                    Professor.atualizarCadastroProfessor();
                    break;
                case "3":
                    listarAlunos();
                    break;
                case "4":
                    lancarNotas();
                    break;
                case "0":
                    System.out.println("Encerrando a aplicação...");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
                    break;
            }
        } while (!"0".equals(opcao));
    }

    // Visualiza as informações do professor logado
    private void visualizarInformacoes() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Confirme sua matrícula para ter acesso às informaçoes do seu cadastro: ");
        String matricula = scanner.nextLine();
        Professor professorEncontrado = null;
        for (Professor professor : professor.professoresCadastrados) {
            if (professor.getMatricula().equals(matricula)) {
                professorEncontrado = professor;
                break;
            }
        }

        if (professorEncontrado != null) {
            System.out.println(professorEncontrado); // Chama o método para exibir informações detalhadas
        } else {
            System.out.println("/n>> Professor não encontrado.");
        }
    }

    // Lista todos os alunos e suas notas
    private void listarAlunos() {
        System.out.println("Listagem de alunos e suas notas:");

        // Exibir disciplinas lecionadas pelo professor
        System.out.println("Escolha a disciplina para listar os alunos:");
        for (int i = 0; i < professor.getDisciplinasLecionadas().size(); i++) {
            System.out.println((i + 1) + ". " + professor.getDisciplinasLecionadas().get(i).getNomeDisciplina());
        }

        int escolhaDisciplina = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir a nova linha

        if (escolhaDisciplina < 0 || escolhaDisciplina >= professor.getDisciplinasLecionadas().size()) {
            System.out.println("Opção inválida.");
            return;
        }

        Disciplina disciplinaEscolhida = professor.getDisciplinasLecionadas().get(escolhaDisciplina);

        // Exibir alunos e suas notas dos 4 bimestres
        List<Aluno> alunosDaDisciplina = disciplinaEscolhida.getAlunosMatriculados();
        if (alunosDaDisciplina == null || alunosDaDisciplina.isEmpty()) {
            System.out.println("Nenhum aluno matriculado nesta disciplina.");
            return;
        }

        System.out
                .println("===========================================================================================");
        System.out.println("Matrícula\tNome\t\t\t1º Bim\t2º Bim\t3º Bim\t4º Bim");
        System.out
                .println("===========================================================================================");
        for (Aluno aluno : alunosDaDisciplina) {
            // Obter as notas de cada bimestre para o aluno
            Double nota1 = disciplinaEscolhida.getNotaPorBimestre(1, aluno);
            Double nota2 = disciplinaEscolhida.getNotaPorBimestre(2, aluno);
            Double nota3 = disciplinaEscolhida.getNotaPorBimestre(3, aluno);
            Double nota4 = disciplinaEscolhida.getNotaPorBimestre(4, aluno);

            // Exibir as notas na tabela formatada
            System.out.printf("%-10s\t%-20s\t%-6s\t%-6s\t%-6s\t%-6s\n",
                    aluno.getMatricula(),
                    aluno.getNome(),
                    (nota1 != null ? nota1 : "N/A"),
                    (nota2 != null ? nota2 : "N/A"),
                    (nota3 != null ? nota3 : "N/A"),
                    (nota4 != null ? nota4 : "N/A"));
        }
    }

    // Lança notas para os alunos
    private void lancarNotas() {
        if (professor != null) {
            professor.lancarNotas(); // Chama o método sem os parâmetros
        } else {
            System.out.println("Professor não encontrado.");
        }
    }
}
