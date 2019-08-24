package Modelo;

public class Pedido {

    private int id;
    private double valorTotal;

    public Pedido(int id, double valorTotal) {
        this.id = id;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
