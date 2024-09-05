
package exe03;

import java.util.ArrayList;
import java.util.List;


class Comentario {
    private int id;
    private String texto;
    private Usuario usuario;

    public Comentario(int id, String texto, Usuario usuario) {
        this.id = id;
        this.texto = texto;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return "Comentário de " + usuario.getNome() + ": " + texto;
    }
}

class Publicacao {
    private int id;
    private String texto;
    private List<Comentario> comentarios;

    public Publicacao(int id, String texto) {
        this.id = id;
        this.texto = texto;
        this.comentarios = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public void comentar(Comentario comentario) {
        comentarios.add(comentario);
    }

    public void listarComentarios() {
        if (comentarios.isEmpty()) {
            System.out.println("Nenhum comentário nesta publicação.");
        } else {
            System.out.println("Comentários na publicação " + id + ":");
            for (Comentario comentario : comentarios) {
                System.out.println(comentario);
            }
        }
    }

    @Override
    public String toString() {
        return "Publicação " + id + ": " + texto;
    }
}

class Usuario {
    private int id;
    private String nome;
    private List<Publicacao> publicacoes;

    public Usuario(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.publicacoes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void criarPublicacao(Publicacao publicacao) {
        publicacoes.add(publicacao);
    }

    public void listarPublicacoes() {
        if (publicacoes.isEmpty()) {
            System.out.println("O usuário " + nome + " não tem publicações.");
        } else {
            System.out.println("Publicações do usuário " + nome + ":");
            for (Publicacao publicacao : publicacoes) {
                System.out.println(publicacao);
            }
        }
    }
}

public class Exe03 {
    public static void main(String[] args) {
        
        Usuario usuario1 = new Usuario(1, "Lucas1");
        Usuario usuario2 = new Usuario(2, "Lucas2");

        Publicacao publicacao1 = new Publicacao(1, "Qualquer coisa1");
        Publicacao publicacao2 = new Publicacao(2, "Qualquercoisa2");

        usuario1.criarPublicacao(publicacao1);
        usuario2.criarPublicacao(publicacao2);

        Comentario comentario1 = new Comentario(1, "Nossa", usuario2);
        Comentario comentario2 = new Comentario(2, "(:", usuario1);

        publicacao1.comentar(comentario1);
        publicacao1.comentar(comentario2);
        publicacao2.comentar(comentario1);

        usuario1.listarPublicacoes();
        usuario2.listarPublicacoes();

        System.out.println();

        publicacao1.listarComentarios();
        publicacao2.listarComentarios();
    }
}

