package exe04;
 
   import java.util.ArrayList;
import java.util.List;

class Reserva {
    private Passageiro passageiro;
    private Voo voo;

    public Reserva(Passageiro passageiro, Voo voo) {
        this.passageiro = passageiro;
        this.voo = voo;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public Voo getVoo() {
        return voo;
    }

    @Override
    public String toString() {
        return "Reserva: Passageiro " + passageiro.getNome() + " no voo " + voo.getNumero();
    }
}

class Voo {
    private String numero;
    private String origem;
    private String destino;
    private int capacidade;
    private List<Reserva> reservas;

    public Voo(String numero, String origem, String destino, int capacidade) {
        this.numero = numero;
        this.origem = origem;
        this.destino = destino;
        this.capacidade = capacidade;
        this.reservas = new ArrayList<>();
    }

    public String getNumero() {
        return numero;
    }

    public boolean verificarDisponibilidade() {
        return reservas.size() < capacidade;
    }

    public void fazerReserva(Passageiro passageiro) {
        if (verificarDisponibilidade()) {
            Reserva reserva = new Reserva(passageiro, this);
            reservas.add(reserva);
            passageiro.fazerReserva(reserva);
            System.out.println("Reserva realizada  " + passageiro.getNome() + " no voo " + numero + ".");
        } else {
            System.out.println("O voo " + numero + " está cheio.");
        }
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
}

class Passageiro {
    private int id;
    private String nome;
    private List<Reserva> reservas;

    public Passageiro(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.reservas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void fazerReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public void listarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("O passageiro " + nome + " não tem reservas.");
        } else {
            System.out.println("Reservas do passageiro " + nome + ":");
            for (Reserva reserva : reservas) {
                System.out.println(reserva);
            }
        }
    }
}

public class Exe04 {
    public static void main(String[] args) {

        Voo voo1 = new Voo("AA101", "SP", "RJ", 2);
        Voo voo2 = new Voo("BB202", "SP", "RS", 1);

        Passageiro passageiro1 = new Passageiro(1, "Lucas1");
        Passageiro passageiro2 = new Passageiro(2, "Lucas2");
        Passageiro passageiro3 = new Passageiro(3, "Lucas3");

        voo1.fazerReserva(passageiro1);
        voo1.fazerReserva(passageiro2);
        voo1.fazerReserva(passageiro3);

        voo2.fazerReserva(passageiro3);

        passageiro1.listarReservas();
        passageiro2.listarReservas();
        passageiro3.listarReservas();
    }
}

