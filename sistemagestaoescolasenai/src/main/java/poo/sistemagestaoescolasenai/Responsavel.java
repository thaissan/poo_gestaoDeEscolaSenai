package poo.sistemagestaoescolasenai;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Responsavel extends Pessoa {
    private String parentesco; // Parentesco do responsável em relação ao aluno

    // Construtor
    public Responsavel(String nome, String cpf, String telefone, Endereco endereco, String parentesco) {
        super(nome, cpf, "", endereco, telefone, "");
        this.parentesco = parentesco;
    }

    @Override
    public String toString() {
        return super.toString() + ", Parentesco: " + parentesco;
    }
}
