package poo.sistemagestaoescolasenai;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pessoa {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private Endereco endereco;
    private String telefone;
    private String email;

    // Construtor
    public Pessoa(String nome, String cpf, String dataNascimento, Endereco endereco, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
                ",\nCPF: " + cpf +
                ",\nData de Nascimento: " + dataNascimento +
                ",\nEndereço: " + endereco +
                ",\nTelefone: " + telefone +
                ",\nEmail: " + email;
    }
}
