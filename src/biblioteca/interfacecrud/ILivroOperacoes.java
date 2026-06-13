package biblioteca.interfacecrud;

import biblioteca.modelo.Livro;

/**
 * Interface que define as operações disponíveis no sistema de biblioteca.
 */
public interface ILivroOperacoes {

    /**
     * Insere um novo livro no repositório.
     * @param livro objeto Livro a ser inserido
     * @return true se inserido com sucesso, false caso contrário
     */
    public boolean inserir(Livro livro);

    /**
     * Pesquisa um livro pelo id.
     * O objeto passado deve conter apenas o id preenchido.
     * @param livro objeto Livro com apenas o id definido
     * @return o objeto Livro completo, ou null se não encontrado
     */
    public Livro pesquisar(Livro livro);

    /**
     * Remove um livro do repositório a partir do seu id.
     * @param id identificador do livro a ser removido
     * @return true se removido com sucesso, false se não encontrado
     */
    public boolean remover(int id);

    /**
     * Atualiza o título de um livro a partir do seu id.
     * @param id identificador do livro
     * @param novoTitulo novo valor para o título
     * @return true se atualizado com sucesso, false se não encontrado
     */
    public boolean atualizarTitulo(int id, String novoTitulo);

    /**
     * Retorna todos os livros cadastrados no repositório.
     * @return array de Livros (pode conter nulls nas posições não utilizadas)
     */
    public Livro[] listarTodos();

    /**
     * Retorna o total de livros cadastrados.
     * @return quantidade de livros no repositório
     */
    public int getTotalLivros();
}
