package biblioteca.modelo;

/**
 * Classe base que representa um Livro no sistema de biblioteca.
 * O id é incrementado automaticamente a partir de 1.
 */
public class Livro {

    // Controle do id automático (estático, compartilhado entre todas as instâncias)
    private static int contadorId = 0;

    // Atributos
    private int id;
    private String titulo;
    private String autor;
    private String editora;
    private int anoPub;
    private double preco;
    private boolean disponivel;

    // -------------------------------------------------------------------------
    // Construtor padrão (sem parâmetros)
    // -------------------------------------------------------------------------
    public Livro() {
        this.id = ++contadorId;
        this.disponivel = true;
    }

    // -------------------------------------------------------------------------
    // Construtor que recebe todos os atributos EXCETO o id
    // -------------------------------------------------------------------------
    public Livro(String titulo, String autor, String editora, int anoPub, double preco, boolean disponivel) {
        this.id = ++contadorId;
        this.titulo    = titulo;
        this.autor     = autor;
        this.editora   = editora;
        this.anoPub    = anoPub;
        this.preco     = preco;
        this.disponivel = disponivel;
    }

    // -------------------------------------------------------------------------
    // Construtor adicional que recebe APENAS o id (usado em pesquisas)
    // -------------------------------------------------------------------------
    public Livro(int id) {
        this.id = id;
    }

    // -------------------------------------------------------------------------
    // Getters e Setters
    // -------------------------------------------------------------------------
    public int getId() {
        return id;
    }

    // Não há setter para id (é gerado automaticamente)

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAnoPub() {
        return anoPub;
    }

    public void setAnoPub(int anoPub) {
        this.anoPub = anoPub;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    // -------------------------------------------------------------------------
    // Método utilitário para exibição
    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return String.format(
            "ID: %d | Título: %s | Autor: %s | Editora: %s | Ano: %d | Preço: R$ %.2f | Disponível: %s",
            id, titulo, autor, editora, anoPub, preco, disponivel ? "Sim" : "Não"
        );
    }

    // -------------------------------------------------------------------------
    // Permite resetar o contador (útil para testes)
    // -------------------------------------------------------------------------
    public static void resetarContador() {
        contadorId = 0;
    }

    public static int getContadorId() {
        return contadorId;
    }
}
