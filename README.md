# 📚 Sistema de Gerenciamento de Biblioteca

Sistema de gerenciamento de acervo de uma biblioteca desenvolvido em Java com interface gráfica (Swing), como projeto acadêmico da disciplina de Programação Orientada a Objetos.

---

## 🖥️ Interface do Sistema

O sistema possui uma interface gráfica completa com:
- Tabela de livros atualizada em tempo real
- Formulários para todas as operações
- Mensagens de confirmação e erro

---

## ⚙️ Funcionalidades

- ➕ **Inserir** livros no acervo
- 🔍 **Pesquisar** livro por ID
- ✏️ **Atualizar** o título de um livro
- 🗑️ **Remover** livro do acervo
- 📋 **Listar** todos os livros cadastrados

---

## 🗂️ Estrutura do Projeto

```
src/
└── biblioteca/
    ├── modelo/
    │   ├── Livro.java               ← Classe base com 7 atributos e ID automático
    │   └── LivroDigital.java        ← Classe derivada (herança)
    ├── interfacecrud/
    │   └── ILivroOperacoes.java     ← Interface com os métodos CRUD
    ├── repositorio/
    │   └── LivroRepositorio.java    ← Implementação com array dinâmico manual
    └── principal/
        └── Main.java                ← Interface gráfica Swing
```

---

## 📋 Requisitos Atendidos (Projeto A3)

### 1. Classe base e herança
- `Livro.java` possui **7 atributos**: `id`, `titulo`, `autor`, `editora`, `anoPub`, `preco`, `disponivel`
- O `id` é **auto-incremental** a partir de 1, controlado por atributo estático
- Possui **3 construtores**: padrão, completo (sem id) e com apenas id (para pesquisa)
- Getters e setters para todos os atributos
- `LivroDigital.java` herda de `Livro` e adiciona `formatoArquivo` e `tamanhoMB`

### 2. Interface
- `ILivroOperacoes.java` define os métodos: `inserir`, `pesquisar`, `remover`, `atualizarTitulo`, `listarTodos`

### 3. Classe de implementação
- `LivroRepositorio.java` usa array `Livro[]` implementado manualmente
- Método **privativo** `buscarIndice(int id)` retorna a posição no array
- Método **privativo** `redimensionar()` aumenta o array em **50%** quando cheio
- Método **público** `pesquisar(Livro livro)` retorna o objeto completo

### 4. Controle do identificador
- Atributo estático `contadorId` em `Livro.java`
- Incrementado automaticamente a cada novo objeto criado

### 5. Interface gráfica
- Todas as operações realizadas via **GUI (Swing)**
- Nenhum uso de `Scanner` ou saída no console

### ⚠️ Sem uso de coleções prontas
Não foi utilizado `ArrayList`, `LinkedList` ou qualquer coleção da biblioteca padrão Java. Todo o armazenamento e manipulação de objetos foi implementado manualmente com arrays.

---

## 🚀 Como Executar

### Pré-requisitos
- Java JDK 8 ou superior
- NetBeans IDE (recomendado)

### Passos
1. Clone o repositório:
   ```bash
   git clone git@github.com:mathlhp/sistema-biblioteca.git
   ```
2. Abra o NetBeans: **File → Open Project**
3. Navegue até a pasta clonada e abra o projeto
4. Clique com botão direito no projeto → **Properties → Run**
5. Defina a classe principal como `biblioteca.principal.Main`
6. Pressione **F6** para executar

---

## 🛠️ Tecnologias Utilizadas

- **Java** — Linguagem principal
- **Swing** — Interface gráfica
- **NetBeans IDE** — Ambiente de desenvolvimento
- **Git / GitHub** — Controle de versão

---

## 👥 Integrantes do Grupo

- Matheus Alves de Lima
- Gabriel Cruz Duque
- Pedro Henrique Lopes Carvalho

---

## 📄 Licença

Projeto desenvolvido para fins acadêmicos.
