package poo.sistemagestaoescolasenai;

public enum Turno {
    MANHA("manhã"),
    TARDE("tarde"),
    NOITE("noite");

    private final String descricao;

    Turno(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
