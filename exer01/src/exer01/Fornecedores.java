package exer01;

import java.util.HashMap;
import java.util.Map;

public class Fornecedores {
    private String nome;
    private Map<String, Integer> produtos;

    public Fornecedores(String nome) {
        this.nome = nome;
        this.produtos = new HashMap<>();
    }

    public void adicionarProduto(String codigoProduto, int quantidade) {
        if (codigoProduto == null || codigoProduto.isEmpty()) {
            throw new IllegalArgumentException("Código do produto não pode ser nulo ou vazio.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        produtos.put(codigoProduto, produtos.getOrDefault(codigoProduto, 0) + quantidade);
    }

    public int obterQuantidade(String codigoProduto) {
        return produtos.getOrDefault(codigoProduto, 0);
    }

    public String getNome() {
        return nome;
    }
}

