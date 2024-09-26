package Models;

import java.util.Objects;

public class PratoPrincipal extends Produto {
    private int tempoPreparcaoMinutos;

    @Override
    public void exibirInformacoes() {
        System.out.printf(
                """
                Nome: %s
                Descrição: %s
                Preço: R$ %.2f
                Tempo de Preparo: %d min
                """,this.getNome(),this.getDescricao(),this.getPreco(),this.getTempoPreparcaoMinutos());
    }

    //Abaixo funções básicas (Construtores-Getters-Setters-Equals-HashCode-toString)

    public PratoPrincipal() {
    }

    public PratoPrincipal(String nome, String descricao, double preco, int tempoPreparcaoMinutos) {
        super(nome, descricao, preco);
        this.tempoPreparcaoMinutos = tempoPreparcaoMinutos;
    }

    public int getTempoPreparcaoMinutos() {
        return tempoPreparcaoMinutos;
    }

    public void setTempoPreparcaoMinutos(int tempoPreparcaoMinutos) {
        this.tempoPreparcaoMinutos = tempoPreparcaoMinutos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PratoPrincipal that = (PratoPrincipal) o;
        return getTempoPreparcaoMinutos() == that.getTempoPreparcaoMinutos();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTempoPreparcaoMinutos());
    }

    @Override
    public String toString() {
        return super.toString() +
                ", tempoPreparcaoMinutos=" + tempoPreparcaoMinutos +
                "} ";
    }
}