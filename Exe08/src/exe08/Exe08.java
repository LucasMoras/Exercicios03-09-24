package exe08;

import java.util.ArrayList;
import java.util.List;

class Tarefa {
    private int id;
    private String descricao;
    private Desenvolvedor desenvolvedor;
    private int horasEstimadas;
    private int horasTrabalhadas;

    public Tarefa(int id, String descricao, int horasEstimadas) {
        this.id = id;
        this.descricao = descricao;
        this.horasEstimadas = horasEstimadas;
        this.horasTrabalhadas = 0;
        this.desenvolvedor = null;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getHorasEstimadas() {
        return horasEstimadas;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(int horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public Desenvolvedor getDesenvolvedor() {
        return desenvolvedor;
    }

    public void setDesenvolvedor(Desenvolvedor desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }

    @Override
    public String toString() {
        return "Tarefa ID: " + id + ", Descrição: " + descricao + ", Horas Estimadas: " + horasEstimadas + ", Horas Trabalhadas: " + horasTrabalhadas;
    }
}

class Desenvolvedor {
    private int id;
    private String nome;
    private List<Tarefa> tarefas;

    public Desenvolvedor(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.tarefas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
        tarefa.setDesenvolvedor(this);
    }

    public void listarTarefas() {
        System.out.println("Tarefas de " + nome + ":");
        for (Tarefa tarefa : tarefas) {
            System.out.println(tarefa);
        }
    }

    public int calcularHorasTrabalhadas() {
        int horasTrabalhadas = 0;
        for (Tarefa tarefa : tarefas) {
            horasTrabalhadas += tarefa.getHorasTrabalhadas();
        }
        return horasTrabalhadas;
    }

    @Override
    public String toString() {
        return nome + " (ID: " + id + ")";
    }
}

class Projeto {
    private int id;
    private String nome;
    private List<Tarefa> tarefas;

    public Projeto(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.tarefas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public int calcularCargaDeTrabalho() {
        int cargaDeTrabalho = 0;
        for (Tarefa tarefa : tarefas) {
            cargaDeTrabalho += tarefa.getHorasEstimadas();
        }
        return cargaDeTrabalho;
    }

    public void listarTarefas() {
        System.out.println("Tarefas do projeto " + nome + ":");
        for (Tarefa tarefa : tarefas) {
            System.out.println(tarefa);
        }
    }

    @Override
    public String toString() {
        return nome + " (ID: " + id + ")";
    }
}

public class Exe08 {
    public static void main(String[] args) {

        Desenvolvedor dev1 = new Desenvolvedor(1, "Alice");
        Desenvolvedor dev2 = new Desenvolvedor(2, "Bob");

        Projeto projeto1 = new Projeto(1, "Desenvolvimento de Sistema");
        Projeto projeto2 = new Projeto(2, "Otimização de Banco de Dados");

        Tarefa tarefa1 = new Tarefa(1, "Implementar módulo de login", 20);
        Tarefa tarefa2 = new Tarefa(2, "Desenvolver API de pagamentos", 30);
        Tarefa tarefa3 = new Tarefa(3, "Revisar índices de banco", 15);

        projeto1.adicionarTarefa(tarefa1);
        projeto1.adicionarTarefa(tarefa2);
        projeto2.adicionarTarefa(tarefa3);

        dev1.adicionarTarefa(tarefa1);
        dev2.adicionarTarefa(tarefa2);
        dev2.adicionarTarefa(tarefa3);

        tarefa1.setHorasTrabalhadas(10);
        tarefa2.setHorasTrabalhadas(25);
        tarefa3.setHorasTrabalhadas(15);

        projeto1.listarTarefas();
        projeto2.listarTarefas();

        System.out.println("Carga de trabalho do projeto '" + projeto1.getNome() + "': " + projeto1.calcularCargaDeTrabalho() + " horas");
        System.out.println("Carga de trabalho do projeto '" + projeto2.getNome() + "': " + projeto2.calcularCargaDeTrabalho() + " horas");

        dev1.listarTarefas();
        dev2.listarTarefas();

        System.out.println("Horas trabalhadas por " + dev1.getNome() + ": " + dev1.calcularHorasTrabalhadas() + " horas");
        System.out.println("Horas trabalhadas por " + dev2.getNome() + ": " + dev2.calcularHorasTrabalhadas() + " horas");
    }
}

