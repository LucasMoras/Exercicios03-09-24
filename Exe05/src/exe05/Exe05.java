package exe05;

import java.util.ArrayList;
import java.util.List;

class Consulta {
    private String data;
    private Medico medico;
    private Paciente paciente;

    public Consulta(String data, Medico medico, Paciente paciente) {
        this.data = data;
        this.medico = medico;
        this.paciente = paciente;
    }

    public String getData() {
        return data;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    @Override
    public String toString() {
        return "Consulta com Dr. " + medico.getNome() + " no dia " + data + " para o paciente " + paciente.getNome();
    }
}

class Medico {
    private int id;
    private String nome;
    private List<Consulta> consultas;

    public Medico(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.consultas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void agendarConsulta(String data, Paciente paciente) {
        Consulta consulta = new Consulta(data, this, paciente);
        consultas.add(consulta);
        paciente.agendarConsulta(consulta);
        System.out.println("Consulta agendada para  " + paciente.getNome() + " com Dr. " + nome + " no dia " + data + ".");
    }

    public void listarPacientes() {
        if (consultas.isEmpty()) {
            System.out.println("O Dr. " + nome + " não tem consultas agendadas.");
        } else {
            System.out.println("Pacientes atendidos pelo Dr. " + nome + ":");
            for (Consulta consulta : consultas) {
                System.out.println(consulta.getPaciente().getNome());
            }
        }
    }
}

class Paciente {
    private int id;
    private String nome;
    private List<Consulta> consultas;

    public Paciente(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.consultas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void agendarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    public void listarConsultas() {
        if (consultas.isEmpty()) {
            System.out.println("O paciente " + nome + " não tem consultas agendadas.");
        } else {
            System.out.println("Consultas do paciente " + nome + ":");
            for (Consulta consulta : consultas) {
                System.out.println(consulta);
            }
        }
    }
}

public class Exe05 {
    public static void main(String[] args) {

        Medico medico1 = new Medico(1, "Lucas1");
        Medico medico2 = new Medico(2, "Lucas2");

        Paciente paciente1 = new Paciente(1, "Lucas3");
        Paciente paciente2 = new Paciente(2, "Lucas4");

        medico1.agendarConsulta("2024-10-01", paciente1);
        medico1.agendarConsulta("2024-10-02", paciente2);
        medico2.agendarConsulta("2024-10-03", paciente2);

        medico1.listarPacientes();
        medico2.listarPacientes();

        paciente1.listarConsultas();
        paciente2.listarConsultas();
    }
}

