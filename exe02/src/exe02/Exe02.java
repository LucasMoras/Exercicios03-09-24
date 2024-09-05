package exe02;

import java.util.ArrayList;
import java.util.List;

class Projeto {
    private String nome;
    private List<Funcionario> funcionarios;

    public Projeto(String nome) {
        this.nome = nome;
        this.funcionarios = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        if (!funcionarios.contains(funcionario)) {
            funcionarios.add(funcionario);
            funcionario.atribuirProjeto(this);
        } else {
            System.out.println("O funcionário " + funcionario.getNome() + " já está no projeto " + nome + ".");
        }
    }

    @Override
    public String toString() {
        return nome;
    }
}

class Departamento {
    private String nome;
    private List<Funcionario> funcionarios;

    public Departamento(String nome) {
        this.nome = nome;
        this.funcionarios = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        if (!funcionarios.contains(funcionario)) {
            funcionarios.add(funcionario);
            funcionario.setDepartamento(this);
        } else {
            System.out.println("O funcionário " + funcionario.getNome() + " já está no departamento " + nome + ".");
        }
    }

    public double calcularMediaSalarial() {
        if (funcionarios.isEmpty()) {
            return 0;
        }
        double totalSalario = 0;
        for (Funcionario funcionario : funcionarios) {
            totalSalario += funcionario.getSalario();
        }
        return totalSalario / funcionarios.size();
    }
}

class Funcionario {
    private int id;
    private String nome;
    private double salario;
    private Departamento departamento;
    private List<Projeto> projetos;

    public Funcionario(int id, String nome, double salario) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
        this.projetos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getSalario() {
        return salario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public void atribuirProjeto(Projeto projeto) {
        if (!projetos.contains(projeto)) {
            projetos.add(projeto);
            projeto.adicionarFuncionario(this);
        } else {
            System.out.println("O projeto " + projeto.getNome() + " já está atribuído ao funcionário " + nome + ".");
        }
    }

    public void exibirProjetos() {
        if (projetos.isEmpty()) {
            System.out.println("O funcionário " + nome + " não está associado a nenhum projeto.");
        } else {
            System.out.println("Projetos do funcionário " + nome + ":");
            for (Projeto projeto : projetos) {
                System.out.println("- " + projeto);
            }
        }
    }
}

public class Exe02 {
    public static void main(String[] args) {
        
        Departamento departamentoTI = new Departamento("TI");
        Departamento departamentoRH = new Departamento("Recursos Humanos");

        Funcionario funcionario1 = new Funcionario(1, "Lucas1", 5000);
        Funcionario funcionario2 = new Funcionario(2, "Lucas2", 6000);
        Funcionario funcionario3 = new Funcionario(3, "Lucas3", 5500);

        departamentoTI.adicionarFuncionario(funcionario1);
        departamentoTI.adicionarFuncionario(funcionario2);
        departamentoRH.adicionarFuncionario(funcionario3);

        Projeto projeto1 = new Projeto("Projeto A");
        Projeto projeto2 = new Projeto("Projeto B");

        projeto1.adicionarFuncionario(funcionario1);
        projeto1.adicionarFuncionario(funcionario2);
        projeto2.adicionarFuncionario(funcionario3);

        funcionario1.exibirProjetos();
        funcionario2.exibirProjetos();
        funcionario3.exibirProjetos();

        System.out.println("Média salarial do departamento TI: " + departamentoTI.calcularMediaSalarial());
        System.out.println("Média salarial do departamento Recursos Humanos: " + departamentoRH.calcularMediaSalarial());
    }
}

 
