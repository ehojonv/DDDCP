package Models;

import java.util.Objects;

public class Produto {
    private String nome;
    private String descricao;
    private double preco;

    public void exibirInformacoes() {
        System.out.printf(
                """
                Nome: %s
                Descrição: %s
                Preço: R$ %f
                """,nome,descricao,preco);
    }

    //Abaixo funções básicas (Construtores-Getters|Setters-Equals-HashCode-toString)

    public Produto() {
    }

    public Produto(String nome, String descricao, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Double.compare(getPreco(), produto.getPreco()) == 0 && Objects.equals(getNome(), produto.getNome()) && Objects.equals(getDescricao(), produto.getDescricao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getDescricao(), getPreco());
    }

    @Override
    public String toString() {
        return "{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco;
    }
}
