package poo.sistemagestaoescolasenai;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Disciplina {

    private static List<Disciplina> listaDisciplinas = new ArrayList<>();
    private Map<Aluno, Double> notas; // mapeia aluno para as notas
    private Map<Integer, Map<Aluno, Double>> notasPorBimestre; // mapeia bimestres que vao se associar com os alunos e
                                                               // notas em cada bimestre

    private int idCodigo;
    private String nomeDisciplina;
    private int cargaHoraria;
    private Professor professor;
    private List<Aluno> alunos;
    private Turno turno;

    // Construtor
    public Disciplina(int idCodigo, String nomeDisciplina, int cargaHoraria, Professor professor, Turno turno) {
        this.idCodigo = idCodigo;
        this.nomeDisciplina = nomeDisciplina;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.turno = turno;
        this.alunos = new ArrayList<>();
        this.notas = new HashMap<>();
        this.notasPorBimestre = new HashMap<>();

    }

    // Getters e Setters
    public void addAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public void lancarNota(Aluno aluno, double nota, int bimestre, Date data) {
        Notas novaNota = new Notas(this, nota, data, bimestre, aluno);
    }

    // Método para lançar a nota
    public void lancarNotaProfessor(Aluno aluno, double nota, int bimestre) {
        if (!notasPorBimestre.containsKey(bimestre)) { // ve se ja existe map de notas por bimestre
            notasPorBimestre.put(bimestre, new HashMap<>()); // Se não existir, cria um novo mapa para o bimestr
        }
        Map<Aluno, Double> notas = notasPorBimestre.get(bimestre);// armazena nota do aluno para o bimestre
        notas.put(aluno, nota); // add nota do aluno
    }

    // Método para obter a nota de um aluno em um determinado bimestre
    public Double getNotaPorBimestre(int bimestre, Aluno aluno) {
        // Verifica se o bimestre tem notas e se o aluno tem uma nota no bimestre
        if (notasPorBimestre.containsKey(bimestre)) {
            Map<Aluno, Double> notasBimestre = notasPorBimestre.get(bimestre);
            return notasBimestre.getOrDefault(aluno, null); // Retorna a nota do aluno ou null se não houver
        }
        return null;
    }

    // Exibir informações da disciplina
    @Override
    public String toString() {
        return "Disciplina [idCodigo=" + idCodigo + ", nomeDisciplina=" + nomeDisciplina +
                ", cargaHoraria=" + cargaHoraria + ", turno=" + turno + ", professor=" + professor.getNome() + "]";
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunos;
    }

    // Método para listar as disciplinas
    public static void listarDisciplinas() {
        if (listaDisciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
        } else {
            for (Disciplina disciplina : listaDisciplinas) {
                System.out.println(disciplina);
            }
        }
    }
}
