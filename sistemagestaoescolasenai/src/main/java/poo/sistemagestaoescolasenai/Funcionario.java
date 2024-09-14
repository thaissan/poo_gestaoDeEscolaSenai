package poo.sistemagestaoescolasenai;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Funcionario extends Pessoa {
    private String atestado;
    private int faltasRegistradas;
    private int presencasRegistradas;

    //método construtor
    public Funcionario(String nome, String cpf, String dataNascimento, Endereco endereco, String telefone, String email, String atestado, int faltasRegistradas, int presencasRegistradas) {
        super(nome, cpf, dataNascimento, endereco, telefone, email);
        this.atestado = atestado;
        this.faltasRegistradas = faltasRegistradas;
        this.presencasRegistradas = presencasRegistradas;
    }

    //outros métodos
    public void registrarFalta() {
        faltasRegistradas++;
        System.out.println("Falta registrada. Total de faltas: " + faltasRegistradas);
    }

    public void registrarPresenca() {
        presencasRegistradas++;
        System.out.println("Presença registrada. Total de presenças: " + presencasRegistradas);
    }

    public void emitirAtestado(String novoAtestado) {
        this.atestado = novoAtestado;
        System.out.println("Novo atestado emitido: " + atestado);
    }

    @Override
    public String toString() {
        return super.toString() + // Chama o toString da classe Pessoa
                ", Atestado: " + atestado +
                ", Faltas Registradas: " + faltasRegistradas +
                ", Presenças Registradas: " + presencasRegistradas;
    }
    
}