package poo.sistemagestaoescolasenai;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private static List<Aluno> listaAlunos = new ArrayList<>();

    // Construtor
    public Notas(Disciplina disciplina, double notaDisciplina, Date diaNota, int bimestre, Aluno aluno) {
        this.disciplina = disciplina;
        this.notaDisciplina = notaDisciplina;
        this.diaNota = diaNota;
        this.aluno = aluno;
        notasCadastradas.add(this);
    }

    // metodos
    @Override
    public String toString() {
        return "Nota de " + aluno.getNome() + " na disciplina " + disciplina.getNomeDisciplina() +
                ": " + notaDisciplina + " (Data: " + diaNota.toString() + ")";
    }

    public static void gerarBoletim() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();

        Aluno alunoSelecionado = null;
        for (Aluno aluno : listaAlunos) {
            if (aluno.getMatricula().equals(matricula)) {
                alunoSelecionado = aluno;
                break;
            }
        }

        if (alunoSelecionado == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.println(
                "\n==============================================================================================================================");
        System.out.println(
                "                                                  * BOLETIM ESCOLAR *                                                  ");
        System.out.println(
                "---------------+---------------------------------------------------------------------------+----------+----------+------------");
        System.out.format("%-15s %-75s %-10s %-10s %-10s\n", "Currículo", "Notas", "Média", "Faltas", "Status");
        System.out.println(
                "---------------+---------------------------------------------------------------------------+----------+----------+------------");

        Map<Disciplina, List<Double>> notasPorDisciplina = new HashMap<>();
        Map<Disciplina, Integer> faltasPorDisciplina = new HashMap<>();

        for (Notas nota : notasCadastradas) {
            if (nota.getAluno().equals(alunoSelecionado)) {
                Disciplina disciplina = nota.getDisciplina();
                notasPorDisciplina.putIfAbsent(disciplina, new ArrayList<>());
                notasPorDisciplina.get(disciplina).add(nota.getNotaDisciplina());

                faltasPorDisciplina.putIfAbsent(disciplina, 0);
                faltasPorDisciplina.put(disciplina, faltasPorDisciplina.get(disciplina) + 1);
            }
        }

        double totalFaltas = 0;

        for (Map.Entry<Disciplina, List<Double>> entry : notasPorDisciplina.entrySet()) {
            Disciplina disciplina = entry.getKey();
            List<Double> notas = entry.getValue();

            String notasString = formatarNotas(notas);
            double media = calcularMedia(notas);
            int faltas = faltasPorDisciplina.getOrDefault(disciplina, 0);
            String status = media >= 7.0 ? "Aprovado" : "Reprovado";

            totalFaltas += printDisciplina(disciplina.getNomeDisciplina(), notasString, media, faltas, status);
        }

        System.out.println(
                "---------------+---------------------------------------------------------------------------+----------+----------+------------");
        System.out.format("%-15s %-75s %-10s %-10.0f %-10s\n", "Total de Faltas", "", "", totalFaltas, "");
        System.out.println();
    }

    private static String formatarNotas(List<Double> notas) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(String.format("%dº Bimestre: %.1f  ", i + 1, i < notas.size() ? notas.get(i) : 0.0));
        }
        return sb.toString().trim();
    }

    private static double calcularMedia(List<Double> notas) {
        return notas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    private static double printDisciplina(String curriculo, String notas, double media, int faltas, String status) {
        System.out.format("%-15s %-75s %-10.2f %-10d %-10s\n", curriculo, notas, media, faltas, status);
        return faltas;
    }

    public static void adicionarAluno(Aluno aluno) {
        listaAlunos.add(aluno);
    }
}
