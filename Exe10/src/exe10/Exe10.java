package exe10;

import java.util.ArrayList;
import java.util.List;


class Estudante {
    private int id;
    private String nome;
    private List<Matricula> matriculas;

    public Estudante(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.matriculas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void matricular(Disciplina disciplina, double nota) {
        Matricula matricula = new Matricula(this, disciplina, nota);
        matriculas.add(matricula);
        disciplina.adicionarEstudante(this);
    }

    public double calcularMedia() {
        if (matriculas.isEmpty()) {
            return 0;
        }
        double somaNotas = 0;
        for (Matricula matricula : matriculas) {
            somaNotas += matricula.getNota();
        }
        return somaNotas / matriculas.size();
    }

    public List<Disciplina> listarDisciplinas() {
        List<Disciplina> disciplinas = new ArrayList<>();
        for (Matricula matricula : matriculas) {
            disciplinas.add(matricula.getDisciplina());
        }
        return disciplinas;
    }

    @Override
    public String toString() {
        return nome + " (ID: " + id + ")";
    }
}

class Professor {
    private int id;
    private String nome;
    private List<Disciplina> disciplinas;

    public Professor(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.disciplinas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        if (!disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
            disciplina.setProfessor(this);
        }
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinas;
    }

    @Override
    public String toString() {
        return nome + " (ID: " + id + ")";
    }
}

class Disciplina {
    private int id;
    private String nome;
    private Professor professor;
    private List<Estudante> estudantes;

    public Disciplina(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.estudantes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void adicionarEstudante(Estudante estudante) {
        if (!estudantes.contains(estudante)) {
            estudantes.add(estudante);
        }
    }

    public List<Estudante> getEstudantes() {
        return estudantes;
    }

    @Override
    public String toString() {
        return nome + " (ID: " + id + ")";
    }
}

class Matricula {
    private Estudante estudante;
    private Disciplina disciplina;
    private double nota;

    public Matricula(Estudante estudante, Disciplina disciplina, double nota) {
        this.estudante = estudante;
        this.disciplina = disciplina;
        this.nota = nota;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public double getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return estudante + " em " + disciplina + " com nota " + nota;
    }
}

public class Exe10{
    public static void main(String[] args) {

        Estudante estudante1 = new Estudante(1, "Alice");
        Estudante estudante2 = new Estudante(2, "Bob");

        Professor professor1 = new Professor(1, "Dr. Smith");
        Professor professor2 = new Professor(2, "Dr. Johnson");

        Disciplina disciplina1 = new Disciplina(1, "Matemática");
        Disciplina disciplina2 = new Disciplina(2, "Programação");

        professor1.adicionarDisciplina(disciplina1);
        professor2.adicionarDisciplina(disciplina2);

        estudante1.matricular(disciplina1, 8.5);
        estudante1.matricular(disciplina2, 9.0);
        estudante2.matricular(disciplina1, 7.0);

        System.out.println("Disciplinas de " + estudante1.getNome() + ": " + estudante1.listarDisciplinas());
        System.out.println("Disciplinas de " + estudante2.getNome() + ": " + estudante2.listarDisciplinas());

        System.out.println("Média de " + estudante1.getNome() + ": " + estudante1.calcularMedia());
        System.out.println("Média de " + estudante2.getNome() + ": " + estudante2.calcularMedia());

        System.out.println("Disciplinas de " + professor1.getNome() + ": " + professor1.listarDisciplinas());
        System.out.println("Disciplinas de " + professor2.getNome() + ": " + professor2.listarDisciplinas());
    }
}

