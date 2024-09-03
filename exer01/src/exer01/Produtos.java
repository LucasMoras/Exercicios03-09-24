package exer01;

public class Produtos {
    private String nome;
    private String codigo;
    private int quantidadeEstoque;

    public Produtos(String nome, String codigo, int quantidadeEstoque) {
        this.nome = nome;
        this.codigo = codigo;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void adicionarProduto(int quantidade) {
        this.quantidadeEstoque += quantidade;
    }

    public void diminuirEstoque(int quantidade) {
        if (quantidade > 0 && quantidade <= quantidadeEstoque) {
            this.quantidadeEstoque -= quantidade;
        } else {
            throw new IllegalArgumentException("Quantidade inválida para diminuir o estoque.");
        }
    }

    @Override
    public String toString() {
        return String.format("Produto{nome='%s', codigo='%s', quantidadeEstoque=%d}", nome, codigo, quantidadeEstoque);
    }
}

