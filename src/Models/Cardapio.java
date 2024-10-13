package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cardapio {
    private String nomeRestaurante;
    private List<Produto> conteudo = new ArrayList<>();

    public void exibirInformacoes() {
        System.out.printf("""
                
                \033[1mCardápio do %s\033[22m
                Produtos:""",nomeRestaurante);
        conteudo.forEach(Produto::exibirInformacoes);
    }

    public void adicionarProduto(Produto produto) {
        conteudo.add(produto);
    }

    public void removerProduto(Produto produto) {
        conteudo.remove(produto);
    }

    //Abaixo funções básicas (Construtores-Getters-Setters-Equals-HashCode-toString)

    public Cardapio() {
    }

    public Cardapio(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
    }

    public Cardapio(String nomeRestaurante, List<Produto> conteudo) {
        this.nomeRestaurante = nomeRestaurante;
        this.conteudo = conteudo;
    }

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    public void setNomeRestaurante(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
    }

    public List<Produto> getConteudo() {
        return conteudo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cardapio cardapio = (Cardapio) o;
        return Objects.equals(getNomeRestaurante(), cardapio.getNomeRestaurante()) && Objects.equals(conteudo, cardapio.conteudo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNomeRestaurante(), conteudo);
    }

    @Override
    public String toString() {
        return "Cardapio{" +
                "nomeRestaurante='" + nomeRestaurante + '\'' +
                ", conteudo=" + conteudo +
                '}';
    }
}
