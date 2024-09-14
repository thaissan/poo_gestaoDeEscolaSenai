package poo.sistemagestaoescolasenai;

public enum UnidadeFederal {
    // Sudeste
    RJ("Rio de Janeiro"), 
    SP("São Paulo"), 
    MG("Minas Gerais"), 
    ES("Espírito Santo"),

    // Sul
    PR("Paraná"),
    SC("Santa Catarina"),
    RS("Rio Grande do Sul"),

    // Centro-Oeste
    DF("Distrito Federal"),
    GO("Goiás"),
    MS("Mato Grosso do Sul"),
    MT("Mato Grosso"),

    // Nordeste
    AL("Alagoas"),
    BA("Bahia"),
    CE("Ceará"),
    MA("Maranhão"),
    PB("Paraíba"),
    PE("Pernambuco"),
    PI("Piauí"),
    RN("Rio Grande do Norte"),
    SE("Sergipe"),

    // Norte
    AC("Acre"),
    AP("Amapá"),
    AM("Amazonas"),
    PA("Pará"),
    RO("Rondônia"),
    RR("Roraima"),
    TO("Tocantins");

    private final String nomePorExtenso;
    
    UnidadeFederal(String nomePorExtenso) {
        this.nomePorExtenso = nomePorExtenso;
    }

    public String getNomePorExtenso() {
        return nomePorExtenso;
    }
}
