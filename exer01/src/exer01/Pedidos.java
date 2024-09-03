package exer01;

import java.util.ArrayList;
import java.util.List;

public class Pedidos {
    private List<Produtos> estoque;
    private int count;

    public Pedidos(int capacidade) {
        this.estoque = new ArrayList<>(capacidade);
        this.count = 0;
    }

    public void adicionarProduto(Produtos produto) {
        if (produto != null) {
            estoque.add(produto);
            count++;
        }
    }

    public void fazerPedido(Fornecedores fornecedor, String codigoProduto) {
        for (Produtos produto : estoque) {
            if (produto.getCodigo().equals(codigoProduto)) {
                int quantidadeDisponivel = fornecedor.obterQuantidade(codigoProduto);
                if (quantidadeDisponivel > 0) {
                    produto.adicionarProduto(quantidadeDisponivel);
                    System.out.println("Pedido realizado com sucesso para o produto: " + produto);
                } else {
                    System.out.println("Produto com código " + codigoProduto + " não disponível no fornecedor.");
                }
                return;
            }
        }
        System.out.println("Produto com código " + codigoProduto + " não encontrado no estoque.");
    }

    @Override
    public String toString() {
        return "Pedidos{" +
                "estoque=" + estoque +
                '}';
    }
}

