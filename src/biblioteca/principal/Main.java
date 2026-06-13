package biblioteca.principal;

import biblioteca.modelo.Livro;
import biblioteca.repositorio.LivroRepositorio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Classe principal com interface gráfica (Swing) para o sistema de Biblioteca.
 * Todas as interações com o usuário são feitas via GUI.
 */
public class Main extends JFrame {

    // -------------------------------------------------------------------------
    // Repositório de livros
    // -------------------------------------------------------------------------
    private final LivroRepositorio repositorio = new LivroRepositorio();

    // -------------------------------------------------------------------------
    // Componentes de tabela
    // -------------------------------------------------------------------------
    private DefaultTableModel tableModel;
    private JTable tabela;

    // -------------------------------------------------------------------------
    // Campos de entrada
    // -------------------------------------------------------------------------
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtEditora;
    private JTextField txtAnoPub;
    private JTextField txtPreco;
    private JCheckBox  chkDisponivel;

    // Campos para operações por ID
    private JTextField txtIdPesquisar;
    private JTextField txtIdRemover;
    private JTextField txtIdAtualizar;
    private JTextField txtNovoTitulo;

    // -------------------------------------------------------------------------
    // Cores do tema
    // -------------------------------------------------------------------------
    private static final Color COR_FUNDO       = new Color(245, 245, 250);
    private static final Color COR_PAINEL      = new Color(255, 255, 255);
    private static final Color COR_PRIMARIA    = new Color(41, 98, 255);
    private static final Color COR_SUCESSO     = new Color(34, 139, 34);
    private static final Color COR_PERIGO      = new Color(220, 53, 69);
    private static final Color COR_AVISO       = new Color(255, 153, 0);
    private static final Color COR_TEXTO       = new Color(33, 33, 50);

    // -------------------------------------------------------------------------
    // Construtor / inicialização da janela
    // -------------------------------------------------------------------------
    public Main() {
        setTitle("Sistema de Biblioteca — Gerenciamento de Livros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 720);
        setLocationRelativeTo(null);
        setBackground(COR_FUNDO);

        inicializarUI();
        carregarDadosExemplo();
        atualizarTabela();

        setVisible(true);
    }

    // -------------------------------------------------------------------------
    // Monta toda a UI
    // -------------------------------------------------------------------------
    private void inicializarUI() {
        JPanel painelRaiz = new JPanel(new BorderLayout(10, 10));
        painelRaiz.setBackground(COR_FUNDO);
        painelRaiz.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(painelRaiz);

        // Cabeçalho
        painelRaiz.add(criarCabecalho(), BorderLayout.NORTH);

        // Centro: tabela + formulários lado a lado
        JPanel painelCentro = new JPanel(new BorderLayout(10, 10));
        painelCentro.setBackground(COR_FUNDO);

        // Painel esquerdo: formulários de operações
        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setLayout(new BoxLayout(painelEsquerdo, BoxLayout.Y_AXIS));
        painelEsquerdo.setBackground(COR_FUNDO);
        painelEsquerdo.setPreferredSize(new Dimension(340, 0));

        painelEsquerdo.add(criarPainelInserir());
        painelEsquerdo.add(Box.createVerticalStrut(10));
        painelEsquerdo.add(criarPainelPesquisar());
        painelEsquerdo.add(Box.createVerticalStrut(10));
        painelEsquerdo.add(criarPainelAtualizar());
        painelEsquerdo.add(Box.createVerticalStrut(10));
        painelEsquerdo.add(criarPainelRemover());

        painelCentro.add(painelEsquerdo, BorderLayout.WEST);
        painelCentro.add(criarPainelTabela(), BorderLayout.CENTER);

        painelRaiz.add(painelCentro, BorderLayout.CENTER);
    }

    // -------------------------------------------------------------------------
    // Cabeçalho superior
    // -------------------------------------------------------------------------
    private JPanel criarCabecalho() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(COR_PRIMARIA);
        painel.setBorder(new EmptyBorder(12, 20, 12, 20));

        JLabel titulo = new JLabel("📚  Sistema de Gerenciamento de Biblioteca");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        painel.add(titulo, BorderLayout.WEST);

        JLabel subtitulo = new JLabel("Projeto A3 — Estrutura de Dados com Arrays");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitulo.setForeground(new Color(200, 215, 255));
        painel.add(subtitulo, BorderLayout.EAST);

        return painel;
    }

    // -------------------------------------------------------------------------
    // Painel INSERIR
    // -------------------------------------------------------------------------
    private JPanel criarPainelInserir() {
        JPanel painel = criarPainelComBorda("➕  Inserir Livro", COR_SUCESSO);
        painel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 5, 3, 5);

        txtTitulo    = new JTextField();
        txtAutor     = new JTextField();
        txtEditora   = new JTextField();
        txtAnoPub    = new JTextField();
        txtPreco     = new JTextField();
        chkDisponivel = new JCheckBox("Disponível para empréstimo", true);
        chkDisponivel.setBackground(COR_PAINEL);
        chkDisponivel.setForeground(COR_TEXTO);

        String[] labels = {"Título:", "Autor:", "Editora:", "Ano Pub.:", "Preço (R$):"};
        JTextField[] campos = {txtTitulo, txtAutor, txtEditora, txtAnoPub, txtPreco};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; gbc.gridy = i; gbc.weightx = 0.3;
            painel.add(criarLabel(labels[i]), gbc);
            gbc.gridx = 1; gbc.weightx = 0.7;
            estilizarCampo(campos[i]);
            painel.add(campos[i], gbc);
        }

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        painel.add(chkDisponivel, gbc);

        gbc.gridy = 6;
        JButton btnInserir = criarBotao("Inserir Livro", COR_SUCESSO);
        btnInserir.addActionListener(this::acaoInserir);
        painel.add(btnInserir, gbc);

        return painel;
    }

    // -------------------------------------------------------------------------
    // Painel PESQUISAR
    // -------------------------------------------------------------------------
    private JPanel criarPainelPesquisar() {
        JPanel painel = criarPainelComBorda("🔍  Pesquisar por ID", COR_PRIMARIA);
        painel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 5, 4, 5);

        txtIdPesquisar = new JTextField();
        estilizarCampo(txtIdPesquisar);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        painel.add(criarLabel("ID do livro:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        painel.add(txtIdPesquisar, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        JButton btnPesquisar = criarBotao("Pesquisar", COR_PRIMARIA);
        btnPesquisar.addActionListener(this::acaoPesquisar);
        painel.add(btnPesquisar, gbc);

        return painel;
    }

    // -------------------------------------------------------------------------
    // Painel ATUALIZAR
    // -------------------------------------------------------------------------
    private JPanel criarPainelAtualizar() {
        JPanel painel = criarPainelComBorda("✏️  Atualizar Título", COR_AVISO);
        painel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 5, 4, 5);

        txtIdAtualizar = new JTextField();
        txtNovoTitulo  = new JTextField();
        estilizarCampo(txtIdAtualizar);
        estilizarCampo(txtNovoTitulo);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        painel.add(criarLabel("ID:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        painel.add(txtIdAtualizar, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        painel.add(criarLabel("Novo Título:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        painel.add(txtNovoTitulo, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JButton btnAtualizar = criarBotao("Atualizar", COR_AVISO);
        btnAtualizar.addActionListener(this::acaoAtualizar);
        painel.add(btnAtualizar, gbc);

        return painel;
    }

    // -------------------------------------------------------------------------
    // Painel REMOVER
    // -------------------------------------------------------------------------
    private JPanel criarPainelRemover() {
        JPanel painel = criarPainelComBorda("🗑️  Remover Livro", COR_PERIGO);
        painel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 5, 4, 5);

        txtIdRemover = new JTextField();
        estilizarCampo(txtIdRemover);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        painel.add(criarLabel("ID do livro:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        painel.add(txtIdRemover, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        JButton btnRemover = criarBotao("Remover", COR_PERIGO);
        btnRemover.addActionListener(this::acaoRemover);
        painel.add(btnRemover, gbc);

        return painel;
    }

    // -------------------------------------------------------------------------
    // Painel da TABELA de livros
    // -------------------------------------------------------------------------
    private JPanel criarPainelTabela() {
        JPanel painel = criarPainelComBorda("📋  Acervo da Biblioteca", COR_TEXTO);
        painel.setLayout(new BorderLayout(5, 5));

        String[] colunas = {"ID", "Título", "Autor", "Editora", "Ano", "Preço (R$)", "Disponível"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tabela somente leitura
            }
        };

        tabela = new JTable(tableModel);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabela.setRowHeight(26);
        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabela.getTableHeader().setBackground(COR_PRIMARIA);
        tabela.getTableHeader().setForeground(Color.WHITE);
        tabela.setSelectionBackground(new Color(200, 215, 255));
        tabela.setGridColor(new Color(220, 220, 235));
        tabela.setShowVerticalLines(true);

        // Larguras das colunas
        int[] larguras = {40, 200, 140, 120, 55, 85, 80};
        for (int i = 0; i < larguras.length; i++) {
            tabela.getColumnModel().getColumn(i).setPreferredWidth(larguras[i]);
        }

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 235)));
        painel.add(scroll, BorderLayout.CENTER);

        // Rodapé com informações do array
        JButton btnAtualizar = criarBotao("🔄 Atualizar Lista", new Color(100, 100, 130));
        btnAtualizar.addActionListener(e -> atualizarTabela());
        painel.add(btnAtualizar, BorderLayout.SOUTH);

        return painel;
    }

    // -------------------------------------------------------------------------
    // AÇÕES DOS BOTÕES
    // -------------------------------------------------------------------------

    private void acaoInserir(ActionEvent e) {
        try {
            String titulo   = txtTitulo.getText().trim();
            String autor    = txtAutor.getText().trim();
            String editora  = txtEditora.getText().trim();
            int    anoPub   = Integer.parseInt(txtAnoPub.getText().trim());
            double preco    = Double.parseDouble(txtPreco.getText().trim().replace(",", "."));
            boolean disp    = chkDisponivel.isSelected();

            if (titulo.isEmpty() || autor.isEmpty() || editora.isEmpty()) {
                mostrarErro("Título, Autor e Editora são obrigatórios.");
                return;
            }

            Livro novoLivro = new Livro(titulo, autor, editora, anoPub, preco, disp);
            boolean ok = repositorio.inserir(novoLivro);

            if (ok) {
                mostrarSucesso("Livro inserido com sucesso!\nID gerado: " + novoLivro.getId());
                limparCamposInsercao();
                atualizarTabela();
            } else {
                mostrarErro("Não foi possível inserir o livro.");
            }

        } catch (NumberFormatException ex) {
            mostrarErro("Ano e Preço devem ser números válidos.\nExemplo — Ano: 2023  |  Preço: 49.90");
        }
    }

    private void acaoPesquisar(ActionEvent e) {
        try {
            int id = Integer.parseInt(txtIdPesquisar.getText().trim());
            Livro chave = new Livro(id);
            Livro encontrado = repositorio.pesquisar(chave);

            if (encontrado != null) {
                String msg = String.format(
                    "Livro encontrado!\n\n" +
                    "ID:         %d\n" +
                    "Título:     %s\n" +
                    "Autor:      %s\n" +
                    "Editora:    %s\n" +
                    "Ano:        %d\n" +
                    "Preço:      R$ %.2f\n" +
                    "Disponível: %s",
                    encontrado.getId(), encontrado.getTitulo(), encontrado.getAutor(),
                    encontrado.getEditora(), encontrado.getAnoPub(), encontrado.getPreco(),
                    encontrado.isDisponivel() ? "Sim" : "Não"
                );
                JOptionPane.showMessageDialog(this, msg, "Resultado da Pesquisa",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                mostrarErro("Nenhum livro encontrado com ID " + id + ".");
            }

            txtIdPesquisar.setText("");

        } catch (NumberFormatException ex) {
            mostrarErro("Digite um ID numérico válido.");
        }
    }

    private void acaoAtualizar(ActionEvent e) {
        try {
            int    id         = Integer.parseInt(txtIdAtualizar.getText().trim());
            String novoTitulo = txtNovoTitulo.getText().trim();

            if (novoTitulo.isEmpty()) {
                mostrarErro("O novo título não pode estar vazio.");
                return;
            }

            boolean ok = repositorio.atualizarTitulo(id, novoTitulo);

            if (ok) {
                mostrarSucesso("Título atualizado com sucesso!");
                txtIdAtualizar.setText("");
                txtNovoTitulo.setText("");
                atualizarTabela();
            } else {
                mostrarErro("Livro com ID " + id + " não encontrado.");
            }

        } catch (NumberFormatException ex) {
            mostrarErro("Digite um ID numérico válido.");
        }
    }

    private void acaoRemover(ActionEvent e) {
        try {
            int id = Integer.parseInt(txtIdRemover.getText().trim());

            // Confirmação antes de remover
            int confirma = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente remover o livro com ID " + id + "?",
                "Confirmar Remoção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirma == JOptionPane.YES_OPTION) {
                boolean ok = repositorio.remover(id);

                if (ok) {
                    mostrarSucesso("Livro removido com sucesso!");
                    txtIdRemover.setText("");
                    atualizarTabela();
                } else {
                    mostrarErro("Livro com ID " + id + " não encontrado.");
                }
            }

        } catch (NumberFormatException ex) {
            mostrarErro("Digite um ID numérico válido.");
        }
    }

    // -------------------------------------------------------------------------
    // Atualiza os dados exibidos na tabela
    // -------------------------------------------------------------------------
    private void atualizarTabela() {
        tableModel.setRowCount(0); // limpa a tabela

        Livro[] livros = repositorio.listarTodos();

        for (Livro livro : livros) {
            if (livro != null) {
                tableModel.addRow(new Object[]{
                    livro.getId(),
                    livro.getTitulo(),
                    livro.getAutor(),
                    livro.getEditora(),
                    livro.getAnoPub(),
                    String.format("%.2f", livro.getPreco()),
                    livro.isDisponivel() ? "✅ Sim" : "❌ Não"
                });
            }
        }

        // Atualiza o título da janela com informações do array
        setTitle(String.format(
            "Sistema de Biblioteca  |  Livros: %d  |  Capacidade do array: %d",
            repositorio.getTotalLivros(),
            repositorio.getCapacidadeAtual()
        ));
    }

    // -------------------------------------------------------------------------
    // Limpa os campos do formulário de inserção
    // -------------------------------------------------------------------------
    private void limparCamposInsercao() {
        txtTitulo.setText("");
        txtAutor.setText("");
        txtEditora.setText("");
        txtAnoPub.setText("");
        txtPreco.setText("");
        chkDisponivel.setSelected(true);
    }

    // -------------------------------------------------------------------------
    // Dados de exemplo para facilitar a apresentação
    // -------------------------------------------------------------------------
    private void carregarDadosExemplo() {
        repositorio.inserir(new Livro("Clean Code",              "Robert C. Martin", "Prentice Hall", 2008, 89.90, true));
        repositorio.inserir(new Livro("O Senhor dos Anéis",      "J.R.R. Tolkien",   "HarperCollins", 1954, 59.90, true));
        repositorio.inserir(new Livro("Inteligência Artificial",  "Stuart Russell",   "Campus",        2020, 149.90, false));
        repositorio.inserir(new Livro("Java: Como Programar",    "Deitel & Deitel",  "Pearson",       2017, 110.00, true));
    }

    // -------------------------------------------------------------------------
    // Utilitários de UI
    // -------------------------------------------------------------------------

    private JPanel criarPainelComBorda(String titulo, Color corBorda) {
        JPanel painel = new JPanel();
        painel.setBackground(COR_PAINEL);
        TitledBorder borda = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(corBorda, 2), titulo);
        borda.setTitleFont(new Font("Segoe UI", Font.BOLD, 13));
        borda.setTitleColor(corBorda);
        painel.setBorder(BorderFactory.createCompoundBorder(
            borda,
            new EmptyBorder(6, 8, 8, 8)
        ));
        return painel;
    }

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        label.setForeground(COR_TEXTO);
        return label;
    }

    private void estilizarCampo(JTextField campo) {
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 210), 1),
            new EmptyBorder(3, 6, 3, 6)
        ));
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 13));
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.setOpaque(true);
        return botao;
    }

    private void mostrarSucesso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Sucesso ✅", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro ❌", JOptionPane.ERROR_MESSAGE);
    }

    // -------------------------------------------------------------------------
    // Ponto de entrada
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        // Garante que a UI seja criada na thread correta (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new Main();
        });
    }
}
