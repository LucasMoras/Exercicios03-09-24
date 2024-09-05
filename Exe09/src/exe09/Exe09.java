package exe09;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

class Prato {
    private int id;
    private String nome;
    private double preco;

    public Prato(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return nome + " (ID: " + id + ", Preço: R$" + preco + ")";
    }
}

class Mesa {
    private int numero;
    private boolean reservada;

    public Mesa(int numero) {
        this.numero = numero;
        this.reservada = false;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isReservada() {
        return reservada;
    }

    public void reservar() {
        this.reservada = true;
    }

    public void desreservar() {
        this.reservada = false;
    }

    @Override
    public String toString() {
        return "Mesa " + numero + (reservada ? " (Reservada)" : " (Disponível)");
    }
}

class Pedido {
    private Mesa mesa;
    private List<Prato> pratos;
    private LocalDate dataPedido;
    private LocalTime horaPedido;

    public Pedido(Mesa mesa, List<Prato> pratos, LocalDate dataPedido, LocalTime horaPedido) {
        this.mesa = mesa;
        this.pratos = pratos;
        this.dataPedido = dataPedido;
        this.horaPedido = horaPedido;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public double calcularTotal() {
        return pratos.stream().mapToDouble(Prato::getPreco).sum();
    }

    @Override
    public String toString() {
        return "Pedido na " + mesa + " em " + dataPedido + " às " + horaPedido + ": " + pratos;
    }
}

class Reserva {
    private Mesa mesa;
    private LocalDate dataReserva;
    private LocalTime horaReserva;

    public Reserva(Mesa mesa, LocalDate dataReserva, LocalTime horaReserva) {
        this.mesa = mesa;
        this.dataReserva = dataReserva;
        this.horaReserva = horaReserva;
        mesa.reservar();
    }

    public Mesa getMesa() {
        return mesa;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public LocalTime getHoraReserva() {
        return horaReserva;
    }

    @Override
    public String toString() {
        return "Reserva na " + mesa + " em " + dataReserva + " às " + horaReserva;
    }
}

public class Exe09 {
    public static void main(String[] args) {

        Prato prato1 = new Prato(1, "Pizza Margherita", 45.00);
        Prato prato2 = new Prato(2, "Espaguete à Carbonara", 38.00);
        Prato prato3 = new Prato(3, "Sushi Variado", 55.00);

        Mesa mesa1 = new Mesa(1);
        Mesa mesa2 = new Mesa(2);
        Mesa mesa3 = new Mesa(3);

        List<Prato> pratosPedido1 = new ArrayList<>();
        pratosPedido1.add(prato1);
        pratosPedido1.add(prato2);
        Pedido pedido1 = new Pedido(mesa1, pratosPedido1, LocalDate.now(), LocalTime.now());

        List<Prato> pratosPedido2 = new ArrayList<>();
        pratosPedido2.add(prato3);
        Pedido pedido2 = new Pedido(mesa2, pratosPedido2, LocalDate.now(), LocalTime.now());

        Reserva reserva1 = new Reserva(mesa3, LocalDate.now().plusDays(1), LocalTime.of(19, 0));

        System.out.println("Pedidos:");
        System.out.println(pedido1);
        System.out.println("Total do pedido: R$" + pedido1.calcularTotal());

        System.out.println("\nPedidos:");
        System.out.println(pedido2);
        System.out.println("Total do pedido: R$" + pedido2.calcularTotal());

        System.out.println("\nReservas futuras:");
        System.out.println(reserva1);
    }
}

