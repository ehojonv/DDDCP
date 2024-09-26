package Models;

import java.util.Objects;

public class Bebida extends Produto {
    private boolean armazemGelado;

    @Override
    public void exibirInformacoes() {
        String tipoArmazem;
        if (armazemGelado) {
            tipoArmazem = "Gelado";
        } else {
            tipoArmazem = "Quente";
        }
        System.out.printf(
                """
                Nome: %s
                Descrição: %s
                Preço: R$ %.2f
                Tipo de armázem: %s
                """,this.getNome(),this.getDescricao(),this.getPreco(),tipoArmazem);
    }

    //Abaixo funções básicas (Construtores-Getters-Setters-Equals-HashCode-toString)

    public Bebida() {
    }

    public Bebida(String nome, String descricao, double preco, boolean armazemGelado) {
        super(nome, descricao, preco);
        this.armazemGelado = armazemGelado;
    }

    public boolean isArmazemGelado() {
        return armazemGelado;
    }

    public void setArmazemGelado(boolean armazemGelado) {
        this.armazemGelado = armazemGelado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bebida bebida = (Bebida) o;
        return isArmazemGelado() == bebida.isArmazemGelado();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isArmazemGelado());
    }

    @Override
    public String toString() {
        return super.toString() +
                ", armazemGelado=" + armazemGelado +
                "} ";
    }
}