package exe06;

import java.util.ArrayList;
import java.util.List;

class Carro {
    private int id;
    private String modelo;
    private double preco;
    private Marca marca;
    private Vendedor vendedor;

    public Carro(int id, String modelo, double preco, Marca marca) {
        this.id = id;
        this.modelo = modelo;
        this.preco = preco;
        this.marca = marca;
        this.vendedor = null;
    }

    public double getPreco() {
        return preco;
    }

    public Marca getMarca() {
        return marca;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public String toString() {
        return "Carro " + modelo + " (ID: " + id + ", Preço: " + preco + ")";
    }
}

class Marca {
    private String nome;
    private List<Carro> carros;

    public Marca(String nome) {
        this.nome = nome;
        this.carros = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarCarro(Carro carro) {
        if (!carros.contains(carro)) {
            carros.add(carro);
        } else {
            System.out.println("O carro " + carro + " já está registrado na marca " + nome + ".");
        }
    }

    public double calcularMediaPrecos() {
        if (carros.isEmpty()) {
            return 0;
        }
        double totalPreco = 0;
        for (Carro carro : carros) {
            totalPreco += carro.getPreco();
        }
        return totalPreco / carros.size();
    }
}

class Vendedor {
    private int id;
    private String nome;
    private List<Carro> carrosVendidos;

    public Vendedor(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.carrosVendidos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void venderCarro(Carro carro) {
        if (carro.getVendedor() == null) {
            carro.setVendedor(this);
            carrosVendidos.add(carro);
            System.out.println("Carro vendido: " + carro + " pelo vendedor " + nome + ".");
        } else {
            System.out.println("O carro " + carro + " já foi vendido.");
        }
    }

    public void listarCarrosVendidos() {
        if (carrosVendidos.isEmpty()) {
            System.out.println("O vendedor " + nome + " não vendeu nenhum carro.");
        } else {
            System.out.println("Carros vendidos pelo vendedor " + nome + ":");
            for (Carro carro : carrosVendidos) {
                System.out.println(carro);
            }
        }
    }
}

public class Exe06 {
    public static void main(String[] args) {

        Marca marcaToyota = new Marca("Toyota");
        Marca marcaHonda = new Marca("Honda");

        Carro carro1 = new Carro(1, "Corolla", 80000, marcaToyota);
        Carro carro2 = new Carro(2, "Camry", 120000, marcaToyota);
        Carro carro3 = new Carro(3, "Civic", 70000, marcaHonda);

        marcaToyota.adicionarCarro(carro1);
        marcaToyota.adicionarCarro(carro2);
        marcaHonda.adicionarCarro(carro3);

        Vendedor vendedor1 = new Vendedor(1, "João");
        Vendedor vendedor2 = new Vendedor(2, "Maria");

        vendedor1.venderCarro(carro1);
        vendedor1.venderCarro(carro2);
        vendedor2.venderCarro(carro3);

        vendedor1.listarCarrosVendidos();
        vendedor2.listarCarrosVendidos();

        System.out.println("Média de preços da marca Toyota: " + marcaToyota.calcularMediaPrecos());
        System.out.println("Média de preços da marca Honda: " + marcaHonda.calcularMediaPrecos());
    }
}


