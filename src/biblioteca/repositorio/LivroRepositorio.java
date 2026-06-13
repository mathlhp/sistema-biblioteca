package biblioteca.repositorio;

import biblioteca.interfacecrud.ILivroOperacoes;
import biblioteca.modelo.Livro;

/**
 * Implementação do repositório de Livros usando array manual.
 * Não utiliza ArrayList, LinkedList ou qualquer coleção da biblioteca padrão.
 * O array é redimensionado automaticamente (+50%) quando necessário.
 */
public class LivroRepositorio implements ILivroOperacoes {

    // Array principal de livros
    private Livro[] livros;

    // Quantidade de livros efetivamente armazenados
    private int totalLivros;

    // Capacidade inicial padrão
    private static final int CAPACIDADE_INICIAL = 4;

    // -------------------------------------------------------------------------
    // Construtor
    // -------------------------------------------------------------------------
    public LivroRepositorio() {
        this.livros      = new Livro[CAPACIDADE_INICIAL];
        this.totalLivros = 0;
    }

    // -------------------------------------------------------------------------
    // Método PRIVATIVO: retorna o ÍNDICE do livro no array pelo id
    // Usado internamente por remover, atualizar e pesquisar
    // -------------------------------------------------------------------------
    private int buscarIndice(int id) {
        for (int i = 0; i < totalLivros; i++) {
            if (livros[i] != null && livros[i].getId() == id) {
                return i;
            }
        }
        return -1; // não encontrado
    }

    // -------------------------------------------------------------------------
    // Método PRIVATIVO: redimensiona o array para 50% maior quando cheio
    // -------------------------------------------------------------------------
    private void redimensionar() {
        int novaCapacidade = (int) (livros.length * 1.5);
        Livro[] novoArray  = new Livro[novaCapacidade];

        // Copia elementos do array antigo para o novo
        for (int i = 0; i < totalLivros; i++) {
            novoArray[i] = livros[i];
        }

        // Atualiza a referência para o novo array
        livros = novoArray;
    }

    // -------------------------------------------------------------------------
    // INSERIR
    // -------------------------------------------------------------------------
    @Override
    public boolean inserir(Livro livro) {
        if (livro == null) {
            return false;
        }

        // Se o array estiver cheio, redimensiona
        if (totalLivros == livros.length) {
            redimensionar();
        }

        livros[totalLivros] = livro;
        totalLivros++;
        return true;
    }

    // -------------------------------------------------------------------------
    // PESQUISAR (método público da interface)
    // Recebe um objeto com apenas o id preenchido
    // -------------------------------------------------------------------------
    @Override
    public Livro pesquisar(Livro livro) {
        if (livro == null) {
            return null;
        }

        int indice = buscarIndice(livro.getId());

        if (indice == -1) {
            return null;
        }

        return livros[indice];
    }

    // -------------------------------------------------------------------------
    // REMOVER
    // -------------------------------------------------------------------------
    @Override
    public boolean remover(int id) {
        int indice = buscarIndice(id);

        if (indice == -1) {
            return false;
        }

        // Desloca os elementos para preencher o espaço removido
        for (int i = indice; i < totalLivros - 1; i++) {
            livros[i] = livros[i + 1];
        }

        // Limpa a última posição e decrementa o total
        livros[totalLivros - 1] = null;
        totalLivros--;

        return true;
    }

    // -------------------------------------------------------------------------
    // ATUALIZAR TÍTULO
    // -------------------------------------------------------------------------
    @Override
    public boolean atualizarTitulo(int id, String novoTitulo) {
        int indice = buscarIndice(id);

        if (indice == -1) {
            return false;
        }

        livros[indice].setTitulo(novoTitulo);
        return true;
    }

    // -------------------------------------------------------------------------
    // LISTAR TODOS
    // -------------------------------------------------------------------------
    @Override
    public Livro[] listarTodos() {
        // Retorna apenas os elementos válidos (sem nulls)
        Livro[] resultado = new Livro[totalLivros];
        for (int i = 0; i < totalLivros; i++) {
            resultado[i] = livros[i];
        }
        return resultado;
    }

    // -------------------------------------------------------------------------
    // TOTAL DE LIVROS
    // -------------------------------------------------------------------------
    @Override
    public int getTotalLivros() {
        return totalLivros;
    }

    // -------------------------------------------------------------------------
    // Informações sobre o array (útil para debug/apresentação)
    // -------------------------------------------------------------------------
    public int getCapacidadeAtual() {
        return livros.length;
    }
}
