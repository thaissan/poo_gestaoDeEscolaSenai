package poo.sistemagestaoescolasenai;

public class Administrativo extends Funcionario {
    // add disciplinas
    public static int contadorMatricula = 1000;
    private int matricula;

    public Administrativo(String nome, String cpf, String dataNascimento, Endereco endereco, String telefone,
            String email, String atestado, int faltasRegistradas, int presencasRegistradas) {
        super(nome, cpf, dataNascimento, endereco, telefone, email, atestado, faltasRegistradas, presencasRegistradas);
        // this.disciplinas..
        this.matricula = contadorMatricula;
        contadorMatricula++;

    }

    // Incluir métodos para inserir e remover as disciplinas
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== Informações da Administrativo ==========");
        sb.append(super.toString()).append("\n");
        // + adicionar as disciplinas que ele leciona aqui
        return sb.toString();
    }
}