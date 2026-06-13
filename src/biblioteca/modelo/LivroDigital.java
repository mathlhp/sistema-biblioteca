package biblioteca.modelo;

/**
 * Classe derivada que representa um Livro Digital (e-book).
 * Herda todos os atributos de Livro e adiciona atributos específicos.
 */
public class LivroDigital extends Livro {

    private String formatoArquivo; // PDF, EPUB, MOBI, etc.
    private double tamanhoMB;

    // -------------------------------------------------------------------------
    // Construtor padrão
    // -------------------------------------------------------------------------
    public LivroDigital() {
        super();
    }

    // -------------------------------------------------------------------------
    // Construtor completo (sem id)
    // -------------------------------------------------------------------------
    public LivroDigital(String titulo, String autor, String editora, int anoPub,
                        double preco, boolean disponivel,
                        String formatoArquivo, double tamanhoMB) {
        super(titulo, autor, editora, anoPub, preco, disponivel);
        this.formatoArquivo = formatoArquivo;
        this.tamanhoMB      = tamanhoMB;
    }

    // -------------------------------------------------------------------------
    // Construtor com apenas id (pesquisa)
    // -------------------------------------------------------------------------
    public LivroDigital(int id) {
        super(id);
    }

    // -------------------------------------------------------------------------
    // Getters e Setters específicos
    // -------------------------------------------------------------------------
    public String getFormatoArquivo() {
        return formatoArquivo;
    }

    public void setFormatoArquivo(String formatoArquivo) {
        this.formatoArquivo = formatoArquivo;
    }

    public double getTamanhoMB() {
        return tamanhoMB;
    }

    public void setTamanhoMB(double tamanhoMB) {
        this.tamanhoMB = tamanhoMB;
    }

    // -------------------------------------------------------------------------
    // toString sobrescrito
    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() +
               String.format(" | Formato: %s | Tamanho: %.1f MB", formatoArquivo, tamanhoMB);
    }
}
