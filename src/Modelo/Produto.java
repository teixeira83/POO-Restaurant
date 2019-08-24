package Modelo;

public class Produto {

    private String nome;
    private int id;
    private double valor;

    public Produto(String nome, int id, double valor) {
        this.nome = nome;
        this.id = id;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
