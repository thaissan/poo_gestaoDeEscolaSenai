package poo.sistemagestaoescolasenai;

public class Endereco {
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private UnidadeFederal estado;
    private String cep;

    // Construtor
    public Endereco(String logradouro, String numero, String bairro, String cidade, UnidadeFederal estado, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    @Override
    public String toString() {
        return String.format("%s, %s - %s, %s - %s, %s", logradouro, numero, bairro, cidade, estado, cep);
    }
}
