package exer01;

public class Exer01 {

    public static void main(String[] args) {
        
        Pedidos sistema = new Pedidos(10);

        Produtos p1 = new Produtos("Produto A", "A001", 50);
        Produtos p2 = new Produtos("Produto B", "B001", 5);
        sistema.adicionarProduto(p1);
        sistema.adicionarProduto(p2);

        Fornecedores f1 = new Fornecedores("Fornecedor X");
        f1.adicionarProduto("A001", 20);
        f1.adicionarProduto("B001", 10);

        sistema.fazerPedido(f1, "A001");
        sistema.fazerPedido(f1, "B001");
    }
    
}
