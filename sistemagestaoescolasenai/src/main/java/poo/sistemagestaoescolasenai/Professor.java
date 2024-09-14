package poo.sistemagestaoescolasenai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Professor extends Funcionario {

    private static final Map<String, Professor> professores = new HashMap<>(); // Mapeia matrícula para professor
    private static int contadorMatricula = 0; // Contador para gerar matrículas
    private static final Scanner scanner = new Scanner(System.in);
    static List<Professor> professoresCadastrados = new ArrayList<>();

    private String matricula;
    private List<Disciplina> disciplinasLecionadas = new ArrayList<>(); // Lista de disciplinas do professor

    // Variável estática para matrícula do professor logado
    private static String matriculaLogado;

    public Professor(String nome, String cpf, String dataNascimento, Endereco endereco, String telefone, String email,
            String atestado, int faltasRegistradas, int presencasRegistradas) {
        super(nome, cpf, dataNascimento, endereco, telefone, email, atestado, faltasRegistradas, presencasRegistradas);
        this.matricula = gerarMatriculaProfessor();
        professores.put(this.matricula, this);
        // this.disciplinasLecionadas = new ArrayList<>();
    }

    // Gera matrícula do professor
    private static String gerarMatriculaProfessor() {
        contadorMatricula++; // Incrementa o contador
        return String.format("%03dF", contadorMatricula); // Formata matrícula do professor como XXXF
    }

    // Encontra um professor por matrícula
    public static Professor encontrarProfessorPorMatricula(String matricula) {
        return professores.get(matricula);
    }

    // Define a matrícula do professor logado
    public static void setMatriculaLogado(String matricula) {
        Professor.matriculaLogado = matricula;
    }

    public static String getMatriculaLogado() {
        return matriculaLogado;
    }

    public static void adicionarProfessor(Professor professor) {
        professoresCadastrados.add(professor);
        professores.put(professor.getMatricula(), professor);
    }

    // Adiciona e remove disciplina
    public void addDisciplina(Disciplina disciplina) {
        this.disciplinasLecionadas.add(disciplina);
    }

    public void removeDisciplina(Disciplina disciplina) {
        disciplinasLecionadas.remove(disciplina);
    }

    // listar disciplinas lecionadas
    public void listarDisciplinas() {
        if (disciplinasLecionadas.isEmpty()) {
            System.out.println("O professor " + getNome() + " não está associado a nenhuma disciplina.");
        } else {
            System.out.println("Disciplinas do professor " + getNome() + ":");
            for (Disciplina disciplina : disciplinasLecionadas) {
                System.out.println(disciplina);
            }
        }
    }

    // Cadastrar professor
    public static void cadastrarProfesor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=============================================");
        System.out.println("|          * CADASTRO DE PROFESSOR *        |");
        System.out.println("---------------------------------------------");
        System.out.print("Nome Completo: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Data de Nascimento (dd/mm/aaaa): ");
        String dataNascimento = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.println("\n---------------------------------------------");
        System.out.println("|                 Endereco                  |");
        System.out.println("---------------------------------------------");
        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();

        System.out.print("Número: ");
        String numero = scanner.nextLine();

        System.out.print("Bairro: ");
        String bairro = scanner.nextLine();

        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();

        System.out.print("Estado(sigla): ");
        String estadoSigla = scanner.nextLine();
        UnidadeFederal estado = UnidadeFederal.valueOf(estadoSigla.toUpperCase());

        System.out.print("CEP: ");
        String cep = scanner.nextLine();

        // Criação do objeto Endereco
        Endereco endereco = new Endereco(logradouro, numero, bairro, cidade, estado, cep);

        Professor professor = new Professor(nome, cpf, dataNascimento, endereco, telefone, email,
                "", 0, 0);

        adicionarProfessor(professor);

        System.out.println("\n---------------------------------------------");
        System.out.println("|      Professor cadastrado com sucesso!      |");
        System.out.println("=============================================\n");
    }

    // Atualiza o cadastro do professor
    public static void atualizarCadastroProfessor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n===========================================");
        System.out.println("|       * ATUALIZAÇÃO DE PROFESSORES *       |");
        System.out.println("---------------------------------------------");
        System.out.println("Digite a matrícula do professor que deseja atualizar: ");
        String matricula = scanner.nextLine();
        Professor professorParaAtualizar = null;

        for (Professor professor : professoresCadastrados) {
            if (professor.getMatricula().equals(matricula)) {
                professorParaAtualizar = professor;
                break;
            }
        }

        if (professorParaAtualizar == null) {
            System.out.println("\n>> Professor não encontrado.");
            return;
        }

        System.out.println("\n===========================================");
        System.out.println("Atualizando dados do professor: " + professorParaAtualizar.getNome());
        System.out.println("---------------------------------------------");
        while (true) {
            System.out.println("\nEscolha o dado que deseja atualizar: ");
            System.out.println(
                    "1. Nome\n2. CPF\n3. Data de Nascimento\n4. Endereço\n5. Telefone\n6. Email\n7. Atestado");
            System.out.println("8. Faltas\n9. Presenças\n0. Sair");
            System.out.print("Digite sua opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0)
                break;

            // String novoValor = scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o novo nome: ");
                    String nome = scanner.nextLine();
                    professorParaAtualizar.setNome(nome);
                }
                case 2 -> {
                    System.out.print("Digite o novo CPF: ");
                    String cpf = scanner.nextLine();
                    professorParaAtualizar.setCpf(cpf);
                }
                case 3 -> {
                    System.out.print("Digite a nova data de nascimento: ");
                    String dataNascimento = scanner.nextLine();
                    professorParaAtualizar.setDataNascimento(dataNascimento);
                }
                case 4 -> {
                    System.out.print("Novo Logradouro: ");
                    String logradouro = scanner.nextLine();
                    System.out.print("Número: ");
                    String numero = scanner.nextLine();
                    System.out.print("Bairro: ");
                    String bairro = scanner.nextLine();
                    System.out.print("Cidade: ");
                    String cidade = scanner.nextLine();
                    System.out.print("Estado (sigla): ");
                    String estadoSigla = scanner.nextLine();
                    UnidadeFederal estado = UnidadeFederal.valueOf(estadoSigla.toUpperCase());
                    System.out.print("CEP: ");
                    String cep = scanner.nextLine();
                    Endereco novoEndereco = new Endereco(logradouro, numero, bairro, cidade, estado, cep);
                    professorParaAtualizar.setEndereco(novoEndereco);
                }
                case 5 -> {
                    System.out.print("Digite o novo telefone: ");
                    String telefone = scanner.nextLine();
                    professorParaAtualizar.setTelefone(telefone);
                }
                case 6 -> {
                    System.out.print("Digite o novo email: ");
                    String email = scanner.nextLine();
                    professorParaAtualizar.setEmail(email);
                }
                case 7 -> {
                    System.out.print("Digite o novo atestado: ");
                    String atestado = scanner.nextLine();
                    professorParaAtualizar.setAtestado(atestado);

                }
                case 8 -> {
                    System.out.print("Digite o novo número de faltas: ");
                    int faltas = scanner.nextInt();
                    scanner.nextLine();
                    professorParaAtualizar.setFaltasRegistradas(faltas);
                }
                case 9 -> {
                    System.out.print("Digite o novo número de presenças: ");
                    int presencas = scanner.nextInt();
                    scanner.nextLine();
                    professorParaAtualizar.setPresencasRegistradas(presencas);
                }
                default -> System.out.println("\n>> Opção inválida. Tente novamente.");

            }

            System.out.println("\n>> Cadastro atualizado com sucesso.");
        }
    }

    // Remover Professor
    public static void removerProfessor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a matrícula do professor que deseja remover do sistema: ");
        String matricula = scanner.nextLine();

        Professor professorParaRemover = null;

        // Procura o professor na lista de alunos cadastrados
        for (Professor professor : professoresCadastrados) {
            if (professor.getMatricula().equals(matricula)) {
                professorParaRemover = professor;
                break;
            }
        }

        if (professorParaRemover != null) {
            professoresCadastrados.remove(professorParaRemover);
            System.out.println("\n>> Professor removido com sucesso.");
        } else {
            System.out.println("\n>> Professor não encontrado.");
        }

    }

    public static void listarProfessores() {
        System.out.println(
                "\n====================================================================================================");
        System.out.println(
                "                                          * MENU PROFESSOR *                                         ");
        System.out.println(
                "----------+------------------------------+---------------+---------------+--------------------------");
        System.out.format("%-10s %-30s %-15s %-15s %-10s\n", "Matrícula", "Nome", "CPF", "Telefone", "Email");
        System.out.println(
                "----------+------------------------------+---------------+---------------+--------------------------");

        // Verifica se há alunos cadastrados
        if (professoresCadastrados.isEmpty()) {
            System.out.println("\n>> Nenhum professor cadastrado.");
        } else {
            // Lista os alunos com matrícula e nome
            for (Professor professor : professoresCadastrados) {
                System.out.format("%-10s %-30s %-15s %-15s %-10s\n",
                        professor.getMatricula(),
                        professor.getNome(),
                        professor.getCpf(),
                        professor.getTelefone(),
                        professor.getEmail());
            }
        }

        // Exibir mais infos
        // Scanner scanner = new Scanner(System.in);
        System.out.println("\nDeseja exibir informações detalhadas de algum professor? (s/n)");
        String resposta = scanner.nextLine().trim().toLowerCase();

        if (resposta.equals("s")) {
            System.out.print("Digite a matrícula do professor: ");
            String matricula = scanner.nextLine();

            // Procurar professor pela matrícula
            Professor professorEncontrado = null;
            for (Professor professor : professoresCadastrados) {
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
        // scanner.close();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n=============================================\n");
        sb.append("|        * INFORMAÇÕES DO PROFESSOR *        |\n");
        sb.append("---------------------------------------------\n");
        sb.append(super.toString()).append("\n"); // Chama o toString da classe Pessoa e adiciona a string
        sb.append("Matrícula: ").append(matricula).append("\n");
        sb.append("Disciplinas que leciona:\n");

        for (Disciplina disciplina : disciplinasLecionadas) {
            sb.append("  - ").append(disciplina.getNomeDisciplina()).append("\n");
        }

        return sb.toString(); // Retorna a string construída
    }

    // LANÇAMENTO DE NOTAS********
    public void lancarNotas() {
        Scanner scanner = new Scanner(System.in);

        if (disciplinasLecionadas.isEmpty()) {
            System.out.println("Nenhuma disciplina disponível para lançar notas.");
            return;
        }

        // Exibir disciplinas lecionadas
        System.out.println("Escolha a disciplina para lançar notas:");
        for (int i = 0; i < disciplinasLecionadas.size(); i++) {
            System.out.println((i + 1) + ". " + disciplinasLecionadas.get(i).getNomeDisciplina());
        }
        System.out.print("Digite o número da disciplina: ");
        int escolhaDisciplina = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir a nova linha

        if (escolhaDisciplina < 0 || escolhaDisciplina >= disciplinasLecionadas.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        Disciplina disciplinaEscolhida = disciplinasLecionadas.get(escolhaDisciplina);

        // Exibir alunos da disciplina
        List<Aluno> alunosDaDisciplina = disciplinaEscolhida.getAlunosMatriculados();
        if (alunosDaDisciplina == null || alunosDaDisciplina.isEmpty()) {
            System.out.println("Nenhum aluno matriculado nesta disciplina.");
            return;
        }

        // Escolher o bimestre
        System.out.println("Escolha o bimestre (1 a 4):");
        int bimestre = scanner.nextInt();
        if (bimestre < 1 || bimestre > 4) {
            System.out.println("Bimestre inválido.");
            return;
        }

        // Exibir alunos da disciplina
        System.out.println("Escolha o aluno para lançar a nota:");
        for (int i = 0; i < alunosDaDisciplina.size(); i++) {
            System.out.println((i + 1) + ". " + alunosDaDisciplina.get(i).getNome());
        }
        System.out.print("Digite o número do aluno: ");
        int escolhaAluno = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir a nova linha

        if (escolhaAluno < 0 || escolhaAluno >= alunosDaDisciplina.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        Aluno alunoEscolhido = alunosDaDisciplina.get(escolhaAluno);

        // Inserir a nota
        System.out.print("Digite a nota para o aluno " + alunoEscolhido.getNome() + ": ");
        double nota = scanner.nextDouble();

        // Atualizar a nota do aluno na disciplina para o bimestre escolhido
        disciplinaEscolhida.lancarNotaProfessor(alunoEscolhido, nota, bimestre);

        System.out.println("Nota lançada com sucesso.");
    }

}
