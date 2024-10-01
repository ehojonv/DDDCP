package Models;

import java.util.Objects;

public class Bebida extends Produto {
    private boolean armazemEhGelado;

    @Override
    public void exibirInformacoes() {
        System.out.printf(
                """
                Nome: %s
                Descrição: %s
                Preço: R$ %.2f
                Tipo de armázem: %s
                """,this.getNome(),this.getDescricao(),this.getPreco(),armazemEhGelado ? "Gelado" : "Quente");
    }

    //Abaixo funções básicas (Construtores-Getters-Setters-Equals-HashCode-toString)

    public Bebida() {
    }

    public Bebida(String nome, String descricao, double preco, boolean armazemEhGelado) {
        super(nome, descricao, preco);
        this.armazemEhGelado = armazemEhGelado;
    }

    public boolean isArmazemEhGelado() {
        return armazemEhGelado;
    }

    public void setArmazemEhGelado(boolean armazemEhGelado) {
        this.armazemEhGelado = armazemEhGelado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bebida bebida = (Bebida) o;
        return isArmazemEhGelado() == bebida.isArmazemEhGelado();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isArmazemEhGelado());
    }

    @Override
    public String toString() {
        return super.toString() +
                ", armazemGelado=" + armazemEhGelado +
                "} ";
    }
}