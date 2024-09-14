package poo.sistemagestaoescolasenai;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notas {
    private Disciplina disciplina;
    private double notaDisciplina;
    private Date diaNota;
    private int bimestre;
    private Aluno aluno;
    private static List<Notas> notasCadastradas = new ArrayList<>();
    private static List<Aluno> listaAlunos;
    // Construtor
    public Notas(Disciplina disciplina, double notaDisciplina, Date diaNota, Aluno aluno) {
        this.disciplina = disciplina;
        this.notaDisciplina = notaDisciplina;
        this.diaNota = diaNota;
        this.aluno = aluno;
    }
    //metodos
    @Override
    public String toString() {
        return "Nota de " + aluno.getNome() + " na disciplina " + disciplina.getNomeDisciplina() +
               ": " + notaDisciplina + " (Data: " + diaNota.toString() + ")";
    }
    

    // public static void gerarBoletim() {
    //     Scanner scanner = new Scanner(System.in);

    //     System.out.print("Digite a matrícula do aluno: ");
    //     String matricula = scanner.nextLine();

    //     // Buscar aluno pela matrícula
    //     Aluno aluno = buscarAlunoPorMatricula(matricula);

    //     if (aluno == null) {
    //         System.out.println("Aluno não encontrado.");
    //         return;
    //     }

    //     System.out.println(
    //         "\n==============================================================================================================================");
    //     System.out.println(
    //         "                                                  * BOLETIM ESCOLAR *                                                  ");
    //     System.out.println(
    //         "---------------+---------------------------------------------------------------------------+----------+----------+------------");
    //     System.out.format("%-15s %-75s %-10s %-10s %-10s\n", "Currículo", "Notas", "Média", "Faltas", "Status");
    //     System.out.println(
    //         "---------------+---------------------------------------------------------------------------+----------+----------+------------");

    //     double totalFaltas = 0;

    //     // Gerar boletim para todas as disciplinas que o aluno está cursando
    //     for (Disciplina disciplina : aluno.getDisciplinasCursando()) {
    //         double media = disciplina.calcularMediaFinal(aluno);
    //         int faltas = 0;
    //         String status = media >= 7.0 ? "Aprovado" : "Reprovado";
    //         totalFaltas += faltas;

    //         List<Double> notas = disciplina.getNotasAluno(aluno);
    //         String notasString = notasToString(notas);

    //         printDisciplina(disciplina.getNomeDisciplina(), notasString, media, faltas, status);
    //     }

    //     System.out.println(
    //         "---------------+---------------------------------------------------------------------------+----------+----------+------------");
    //     // Exibindo o total de faltas na última linha
    //     System.out.format("%-15s %-75s %-10s %-10.0f %-10s\n", "Total de Faltas", "", "", totalFaltas, "");
    //     System.out.println();
    // }
    public static void gerarBoletim() {
        System.out.println(
            "\n==============================================================================================================================");
        System.out.println(
            "                                                  * BOLETIM ESCOLAR *                                                  ");
        System.out.println(
            "---------------+---------------------------------------------------------------------------+----------+----------+------------");
        System.out.format("%-15s %-75s %-10s %-10s %-10s\n", "Currículo", "Notas", "Média", "Faltas", "Status");
        System.out.println(
            "---------------+---------------------------------------------------------------------------+----------+----------+------------");

        // Dados de exemplo para cada disciplina
        double totalFaltas = 0;

        totalFaltas += printDisciplina("Matemática", "1º Bimestre: 8.5  2º Bimestre: 7.0  3º Bimestre: 9.0  4º Bimestre: 6.5", 7.75, 3, "Aprovado");
        totalFaltas += printDisciplina("Português", "1º Bimestre: 7.5  2º Bimestre: 8.0  3º Bimestre: 6.5  4º Bimestre: 7.0", 7.00, 2, "Aprovado");
        totalFaltas += printDisciplina("História", "1º Bimestre: 9.0  2º Bimestre: 9.5  3º Bimestre: 8.5  4º Bimestre: 9.0", 9.00, 1, "Aprovado");
        totalFaltas += printDisciplina("Química", "1º Bimestre: 6.5  2º Bimestre: 7.0  3º Bimestre: 7.5  4º Bimestre: 6.0", 6.75, 4, "Aprovado");
        totalFaltas += printDisciplina("Física", "1º Bimestre: 8.0  2º Bimestre: 8.5  3º Bimestre: 7.5  4º Bimestre: 8.0", 8.00, 2, "Aprovado");
        totalFaltas += printDisciplina("Biologia", "1º Bimestre: 7.0  2º Bimestre: 6.5  3º Bimestre: 7.0  4º Bimestre: 6.0", 6.63, 3, "Reprovado");

        System.out.println(
            "---------------+---------------------------------------------------------------------------+----------+----------+------------");
        // Exibindo o total de faltas na última linha
        System.out.format("%-15s %-75s %-10s %-10.0f %-10s\n", "Total de Faltas", "", "", totalFaltas, "");
        System.out.println();
    }

    private static double printDisciplina(String curriculo, String notas, double media, int faltas, String status) {
        System.out.format("%-15s %-75s %-10.2f %-10d %-10s\n", curriculo, notas, media, faltas, status);
        return faltas;
    }  

    private static String notasToString(List<Double> notas) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < notas.size(); i++) {
            sb.append((i + 1) + "º Bimestre: " + notas.get(i) + " ");
        }
        return sb.toString().trim();
    }

    private static Aluno buscarAlunoPorMatricula(String matricula) {
        for (Aluno aluno : listaAlunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }





    //  public static void listarNotas() {
    //     System.out.println(
    //             "\n====================================================================================================");
    //     System.out.println(
    //             "                                          * NOTAS *                                                   ");
    //     System.out.println(
    //             "----------+------------------------------+---------------+---------------+--------------------------");
    //     System.out.format("%-10s %-30s %-15s %-15s %-10s\n", "Matrícula", "Nome", "CPF", "Telefone", "Email");
    //     System.out.println(
    //             "----------+------------------------------+---------------+---------------+--------------------------");

    //     // Verifica se há alunos cadastrados
    //     if (notasCadastradas.isEmpty()) {
    //         System.out.println("Nenhum nota cadastrado.");
    //     } else {
    //         // Nota os alunos com matrícula e nome
    //         for (Notas nota : notasCadastradas) {
    //             System.out.format("%-10s %-30s %-15s %-15s %-10s\n",
    //                     nota.aluno.getNome(),
    //                     nota.getDiaNota(),
    //                     nota.disciplina.getNomeDisciplina(),
    //                     nota.getNotaDisciplina());

    //         }
    //     }

    //     // Exibir mais infos
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.println("\nDeseja exibir informações detalhadas de algum nota? (s/n)");
    //     String resposta = scanner.nextLine().trim().toLowerCase();

    //     if (resposta.equals("s")) {
    //         System.out.print("Digite a matrícula do nota: ");
    //         String matricula = scanner.nextLine();

    //         // Procurar nota pela matrícula
    //         Notas alunoEncontrado = null;
    //         for (Notas nota : notasCadastradas) {
    //             if (nota.aluno.getMatricula().equals(matricula)) {
    //                 alunoEncontrado = nota;
    //                 break;
    //             }
    //         }

    //         if (alunoEncontrado != null) {
    //             System.out.println(alunoEncontrado); // Chama o método para exibir informações detalhadas
    //         } else {
    //             System.out.println("Nota não encontrado.");
    //         }
    //     }
    //     // scanner.close();
    // }
}
