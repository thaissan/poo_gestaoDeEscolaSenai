package poo.sistemagestaoescolasenai;

public class Administrativo extends Funcionario {
    public static int contadorMatricula = 1000;
    private int matricula;

    public Administrativo(String nome, String cpf, String dataNascimento, Endereco endereco, String telefone,
            String email, String atestado, int faltasRegistradas, int presencasRegistradas) {
        super(nome, cpf, dataNascimento, endereco, telefone, email, atestado, faltasRegistradas, presencasRegistradas);
        this.matricula = contadorMatricula;
        contadorMatricula++;

    }

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
        return sb.toString();
    }
}