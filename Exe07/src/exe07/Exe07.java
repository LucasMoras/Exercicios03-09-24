package exe07;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario, LocalDate dataEmprestimo) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = null;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public double calcularMulta() {
        if (dataDevolucao == null) {
            return 0;
        }
        long diasAtraso = ChronoUnit.DAYS.between(dataEmprestimo.plusDays(14), dataDevolucao);
        return diasAtraso > 0 ? diasAtraso * 0.5 : 0;
    }

    @Override
    public String toString() {
        return livro + " emprestado para " + usuario.getNome() + " em " + dataEmprestimo;
    }
}

// Classe Livro
class Livro {
    private int id;
    private String titulo;
    private Autor autor;
    private int anoPublicacao;
    private boolean disponivel;
    List<Emprestimo> emprestimos;

    public Livro(int id, String titulo, Autor autor, int anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.disponivel = true;
        this.emprestimos = new ArrayList<>();
        autor.adicionarLivro(this);
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void emprestar(Usuario usuario) {
        if (disponivel) {
            Emprestimo emprestimo = new Emprestimo(this, usuario, LocalDate.now());
            emprestimos.add(emprestimo);
            usuario.emprestarLivro(emprestimo);
            disponivel = false;
            System.out.println("Livro '" + titulo + "' emprestado para " + usuario.getNome() + ".");
        } else {
            System.out.println("Livro '" + titulo + "' não está disponível para empréstimo.");
        }
    }

    public void devolver(Usuario usuario) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().equals(usuario) && emprestimo.getDataDevolucao() == null) {
                emprestimo.setDataDevolucao(LocalDate.now());
                disponivel = true;
                System.out.println("Livro '" + titulo + "' devolvido por " + usuario.getNome() + ".");
                return;
            }
        }
        System.out.println("O livro '" + titulo + "' não foi emprestado para " + usuario.getNome() + " ou já foi devolvido.");
    }

    public double calcularMulta() {
        double multaTotal = 0;
        for (Emprestimo emprestimo : emprestimos) {
            multaTotal += emprestimo.calcularMulta();
        }
        return multaTotal;
    }

    @Override
    public String toString() {
        return titulo + " (ID: " + id + ")";
    }
}

// Classe Autor
class Autor {
    private int id;
    private String nome;
    private List<Livro> livros;

    public Autor(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.livros = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarLivro(Livro livro) {
        if (!livros.contains(livro)) {
            livros.add(livro);
        }
    }

    public List<Livro> getLivros() {
        return livros;
    }
}

// Classe Usuario
class Usuario {
    private int id;
    private String nome;
    private List<Emprestimo> emprestimos;

    public Usuario(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.emprestimos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void emprestarLivro(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    public void devolverLivro(Livro livro) {
        livro.devolver(this);
    }

    public void listarEmprestimos() {
        if (emprestimos.isEmpty()) {
            System.out.println("Usuário " + nome + " não possui empréstimos.");
        } else {
            System.out.println("Empréstimos de " + nome + ":");
            for (Emprestimo emprestimo : emprestimos) {
                System.out.println(emprestimo);
            }
        }
    }
}

public class Exe07 {
    public static void main(String[] args) {
        Autor autor1 = new Autor(1, "J.K. Rowling");
        Autor autor2 = new Autor(2, "George R.R. Martin");

        Livro livro1 = new Livro(1, "Harry Potter e a Pedra Filosofal", autor1, 1997);
        Livro livro2 = new Livro(2, "Harry Potter e a Câmara Secreta", autor1, 1998);
        Livro livro3 = new Livro(3, "A Guerra dos Tronos", autor2, 1996);

        Usuario usuario1 = new Usuario(1, "Ana");
        Usuario usuario2 = new Usuario(2, "Pedro");

        livro1.emprestar(usuario1);
        livro2.emprestar(usuario2);

        LocalDate dataDevolucaoAtrasada1 = LocalDate.now().plusDays(20); // 6 dias após o prazo
        LocalDate dataDevolucaoAtrasada2 = LocalDate.now().plusDays(15); // 1 dia após o prazo

        usuario1.devolverLivro(livro1);
        usuario2.devolverLivro(livro2);

        for (Emprestimo e : livro1.emprestimos) {
            if (e.getUsuario().equals(usuario1)) {
                e.setDataDevolucao(dataDevolucaoAtrasada1);
            }
        }

        for (Emprestimo e : livro2.emprestimos) {
            if (e.getUsuario().equals(usuario2)) {
                e.setDataDevolucao(dataDevolucaoAtrasada2);
            }
        }

        usuario1.listarEmprestimos();
        usuario2.listarEmprestimos();

        System.out.println("Multa total para o livro '" + livro1.getTitulo() + "': " + livro1.calcularMulta());
        System.out.println("Multa total para o livro '" + livro2.getTitulo() + "': " + livro2.calcularMulta());

        List<Livro> livros = new ArrayList<>();
        livros.add(livro1);
        livros.add(livro2);
        livros.add(livro3);

        livros.sort(Comparator.comparingInt(l -> -l.emprestimos.size())); // Ordena por número de empréstimos (decrescente)
        
        System.out.println("Livros mais populares:");
        for (Livro livro : livros) {
            System.out.println(livro + " - Empréstimos: " + livro.emprestimos.size());
        }
    }
}
