package poo.sistemagestaoescolasenai;

import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aluno extends Pessoa {
    // Guarda ano e semestre para gerar a matricula > 2024-1
    private static Map<String, Integer> matriculasAlunoPorAnoSemestre = new HashMap<>();
    private static List<Aluno> alunosCadastrados = new ArrayList<>();

    private String matricula;
    private Pessoa responsavel;
    private String observacoes;
    private List<Disciplina> disciplinasCursando;
    private List<Notas> notas;
    private int faltas;
    private int presencas;
    private String atestado;
    private int semestre;

    // Construtor
    public Aluno(String nome, String cpf, String dataNascimento, Endereco endereco, String telefone,
            String email, Pessoa responsavel, String observacoes,
            int faltas, int presencas, String atestado, int semestre) {
        super(nome, cpf, dataNascimento, endereco, telefone, email);
        this.matricula = gerarMatriculaAluno(semestre);
        this.responsavel = responsavel;
        this.observacoes = observacoes;
        this.disciplinasCursando = new ArrayList<>();
        this.notas = new ArrayList<>();
        this.faltas = faltas;
        this.presencas = presencas;
        this.atestado = atestado;
        this.semestre = semestre;
    }

    // Métodos
    public void addDisciplina(Disciplina disciplina) {
        this.disciplinasCursando.add(disciplina);
    }

    public void registrarFalta() {
        this.faltas++;
    }

    public void registrarPresenca() {
        this.presencas++;
    }

    public void emitirAtestado(String novoAtestado) {
        this.atestado = novoAtestado;
    }

    public static void adicionarAluno(Aluno aluno) {
        alunosCadastrados.add(aluno);
    }

    // listar disciplinas que o aluno está cursando
    public void listarDisciplinas() {
        if (disciplinasCursando.isEmpty()) {
            System.out.println("O aluno " + getNome() + " não está associado a nenhuma disciplina.");
        } else {
            System.out.println("Disciplinas do aluno " + getNome() + ":");
            for (Disciplina disciplina : disciplinasCursando) { // para cada disciplina em disciplinasCursando
                System.out.println(disciplina);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n=============================================\n");
        sb.append("|          * INFORMAÇÕES DO ALUNO *         |\n");
        sb.append("---------------------------------------------\n");
        sb.append(super.toString()).append("\n"); // Chama o toString da classe Pessoa e adiciona a string
        sb.append("Matrícula: ").append(matricula).append("\n");

        if (responsavel != null) {
            sb.append("Nome do Responsável: ").append(responsavel.getNome()).append("\n");
            sb.append("Contato do Responsável: ").append(responsavel.getTelefone()).append("\n");
        } else {
            sb.append("Responsável não definido.\n");
        }

        sb.append("Outras Observações: ").append(observacoes).append("\n");
        sb.append("Faltas: ").append(faltas).append("\n");
        sb.append("Presenças: ").append(presencas).append("\n");
        sb.append("Atestado: ").append(atestado).append("\n");

        sb.append("\nDisciplinas Cursando:\n");
        for (Disciplina disciplina : disciplinasCursando) {
            sb.append("  - ").append(disciplina.getNomeDisciplina()).append("\n");
        }

        sb.append("\nNotas:\n");
        for (Notas nota : notas) {
            sb.append("  - ").append(nota).append("\n");
        }

        return sb.toString(); // Retorna a string construída
    }

    public static List<Aluno> getAlunosCadastrados() {
        return alunosCadastrados;
    }

    // Gerar Matrícula
    public static String gerarMatriculaAluno(int semestre) {
        Calendar now = Calendar.getInstance(); // pega a data atual
        int ano = now.get(Calendar.YEAR); // pega só o ano da data
        String chaveAnoSemestre = ano + "-" + semestre;
        int numeroSerial = matriculasAlunoPorAnoSemestre.getOrDefault(chaveAnoSemestre, 0) + 1; // se chaveAnoS não
                                                                                                // existir ele retorna 0
        matriculasAlunoPorAnoSemestre.put(chaveAnoSemestre, numeroSerial);
        // Formata a matrícula do aluno
        return String.format("%04d-%d-%03d", ano, semestre, numeroSerial);
    }

    // Cadastrar Aluno
    public static void cadastrarAluno() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=============================================");
        System.out.println("|           * CADASTRO DE ALUNOS *          |");
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

        System.out.println("\n---------------------------------------------");
        System.out.println("|                Responsável                |");
        System.out.println("---------------------------------------------");
        System.out.print("Nome do Responsável: ");
        String nomeResponsavel = scanner.nextLine();

        System.out.print("Telefone do Responsável: ");
        String telefoneResponsavel = scanner.nextLine();

        System.out.print("Email do Responsável: ");
        String emailResponsavel = scanner.nextLine();

        Pessoa responsavel = new Pessoa(nomeResponsavel, null, null, endereco, telefoneResponsavel, emailResponsavel);

        System.out.print("\nObservações: ");
        String observacoes = scanner.nextLine();

        System.out.print("Semestre atual: ");
        int semestre = scanner.nextInt();

        // Criação do objeto Aluno
        Aluno aluno = new Aluno(nome, cpf, dataNascimento, endereco, telefone, email,
                responsavel, observacoes, 0, 0, null, semestre);

        alunosCadastrados.add(aluno);

        System.out.println("\n---------------------------------------------");
        System.out.println("|       Aluno cadastrado com sucesso!       |");
        System.out.println("=============================================\n");

    }

    public static void atualizarCadastroAluno() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=============================================");
        System.out.println("|         * ATUALIZAÇÃO DE ALUNOS *         |");
        System.out.println("---------------------------------------------");
        System.out.println("\nDigite a matrícula do aluno que deseja atualizar: ");
        String matricula = scanner.nextLine();
        Aluno alunoParaAtualizar = null;

        for (Aluno aluno : alunosCadastrados) {
            if (aluno.getMatricula().equals(matricula)) {
                alunoParaAtualizar = aluno;
                break;
            }
        }

        if (alunoParaAtualizar == null) {
            System.out.println("\n>> Aluno não encontrado.");
            return;
        }

        System.out.println("\nAtualizando dados do aluno: " + alunoParaAtualizar.getNome());
        System.out.println("---------------------------------------------");
        while (true) {
            System.out.println("\nEscolha o dado que deseja atualizar: ");
            System.out.println(
                    "1. Nome\n2. CPF\n3. Data de Nascimento\n4. Endereço\n5. Telefone\n6. Email\n7. Responsável");
            System.out.println("8. Observações\n9. Faltas\n10. Presenças\n11. Atestado\n12. Semestre\n0. Sair");
            System.out.print("Digite sua opção: ");
            int opcao = scanner.nextInt();
            // scanner.nextLine();

            if (opcao == 0)
                break;

            String novoValor = scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o novo nome: ");
                    String nome = scanner.nextLine();
                    alunoParaAtualizar.setNome(nome);
                }
                case 2 -> {
                    System.out.print("Digite o novo CPF: ");
                    String cpf = scanner.nextLine();
                    alunoParaAtualizar.setCpf(cpf);
                }
                case 3 -> {
                    System.out.print("Digite a nova data de nascimento: ");
                    String dataNascimento = scanner.nextLine();
                    alunoParaAtualizar.setDataNascimento(dataNascimento);
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
                    alunoParaAtualizar.setEndereco(novoEndereco);
                }
                case 5 -> {
                    System.out.print("Digite o novo telefone: ");
                    String telefone = scanner.nextLine();
                    alunoParaAtualizar.setTelefone(telefone);
                }
                case 6 -> {
                    System.out.print("Digite o novo email: ");
                    String email = scanner.nextLine();
                    alunoParaAtualizar.setEmail(email);
                }
                case 7 -> {
                    System.out.print("Nome do Responsável: ");
                    String nomeResponsavel = scanner.nextLine();
                    System.out.print("CPF do Responsável: ");
                    String cpfResponsavel = scanner.nextLine();
                    System.out.print("Data de Nascimento do Responsável: ");
                    String dataNascimentoResponsavel = scanner.nextLine();
                    System.out.println("Novo Endereço do Responsável:");
                    System.out.print("Logradouro: ");
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
                    Endereco novoEnderecoResponsavel = new Endereco(logradouro, numero, bairro, cidade, estado, cep);
                    System.out.print("Telefone do Responsável: ");
                    String telefoneResponsavel = scanner.nextLine();
                    System.out.print("Email do Responsável: ");
                    String emailResponsavel = scanner.nextLine();
                    Pessoa novoResponsavel = new Pessoa(nomeResponsavel, cpfResponsavel, dataNascimentoResponsavel,
                            novoEnderecoResponsavel, telefoneResponsavel, emailResponsavel);
                    alunoParaAtualizar.setResponsavel(novoResponsavel);
                }
                case 8 -> {
                    System.out.print("Digite as novas observações: ");
                    String observacoes = scanner.nextLine();
                    alunoParaAtualizar.setObservacoes(observacoes);
                }
                case 9 -> {
                    System.out.print("Digite o novo número de faltas: ");
                    int faltas = scanner.nextInt();
                    scanner.nextLine();
                    alunoParaAtualizar.setFaltas(faltas);
                }
                case 10 -> {
                    System.out.print("Digite o novo número de presenças: ");
                    int presencas = scanner.nextInt();
                    scanner.nextLine();
                    alunoParaAtualizar.setPresencas(presencas);
                }
                case 11 -> {
                    System.out.print("Digite o novo atestado: ");
                    String atestado = scanner.nextLine();
                    alunoParaAtualizar.setAtestado(atestado);
                }
                case 12 -> {
                    System.out.print("Digite o novo semestre: ");
                    int semestre = scanner.nextInt();
                    scanner.nextLine();
                    alunoParaAtualizar.setSemestre(semestre);
                }
                default -> System.out.println("\n>> Opção inválida. Tente novamente.");

            }

            System.out.println("\n>> Cadastro atualizado com sucesso.");
        }
    }

    // Listar Alunos
    public static void listarAlunos() {
        System.out.println(
                "\n====================================================================================================");
        System.out.println(
                "                                          * MENU ALUNO *                                         ");
        System.out.println(
                "----------+------------------------------+---------------+---------------+--------------------------");
        System.out.format("%-10s %-30s %-15s %-15s %-10s\n", "Matrícula", "Nome", "CPF", "Telefone", "Email");
        System.out.println(
                "----------+------------------------------+---------------+---------------+--------------------------");

        // Verifica se há alunos cadastrados
        if (alunosCadastrados.isEmpty()) {
            System.out.println("\n>> Nenhum aluno cadastrado.");
        } else {
            // Lista os alunos com matrícula e nome
            for (Aluno aluno : alunosCadastrados) {
                System.out.format("%-10s %-30s %-15s %-15s %-10s\n",
                        aluno.getMatricula(),
                        aluno.getNome(),
                        aluno.getCpf(),
                        aluno.getTelefone(),
                        aluno.getEmail());
            }
        }

        // Exibir mais infos
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDeseja exibir informações detalhadas de algum aluno? (s/n)");
        String resposta = scanner.nextLine().trim().toLowerCase();

        if (resposta.equals("s")) {
            System.out.print("\nDigite a matrícula do aluno: ");
            String matricula = scanner.nextLine();

            // Procurar aluno pela matrícula
            Aluno alunoEncontrado = null;
            for (Aluno aluno : alunosCadastrados) {
                if (aluno.getMatricula().equals(matricula)) {
                    alunoEncontrado = aluno;
                    break;
                }
            }

            if (alunoEncontrado != null) {
                System.out.println(alunoEncontrado); // Chama o método para exibir informações detalhadas
            } else {
                System.out.println("\n>> Aluno não encontrado.");
            }
        }
    }

    // Remover Aluno
    public static void removerAluno() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a matrícula do aluno que deseja remover do sistema: ");
        String matricula = scanner.nextLine();

        Aluno alunoParaRemover = null;

        // Procura o aluno na lista de alunos cadastrados
        for (Aluno aluno : alunosCadastrados) {
            if (aluno.getMatricula().equals(matricula)) {
                alunoParaRemover = aluno;
                break;
            }
        }

        if (alunoParaRemover != null) {
            alunosCadastrados.remove(alunoParaRemover);
            System.out.println("\n>> Aluno removido com sucesso.");
        } else {
            System.out.println("\n>> Aluno não encontrado.");
        }

    }
}
