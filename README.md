# Fitas

## 💻 Grupo

- [Fábio Augusto Araújo Santos](https://github.com/fabio-aug)
- [Luana Assis Silva](https://github.com/luanaassis)

## 📰 Introdução

Desenvolvimento de um ordenador de URLs, que considera a capacidade máxima de memória RAM disponível para realizar a ordenação. Para isso, foi adotada a estratégia de ordenação externa, com o algoritmo QuickSort, onde caso a quantidade de entidades inseridas no arquivo de entrada ultrapasasse a capacidade máxima de memória, as mesmas são divididas em fitas. A inserção das URLs nas fitas ocorre de maneira ordenada, levando em consideração a quantidade de visitas, e caso seja igual, é ordenado alfabeticamente. No final, as fitas já ordenadas são intercaladas ordenadamente, utilizando um Max Heap, e escritas no arquivo de saída.

## 📂 Estrutura

O ambiente de trabalho utilizado foi o Visual Studio Code. Os arquivos foram organizados da seguinte forma:

- `/src`: pasta destinada aos códigos fontes do projeto.
- `/.vscode`: pasta destinada a configuração do projeto caso use a extensão ['Extension Pack for Java'](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack).
- `/images`: pasta destinada as imagens do projeto.

Obs: para rodar usando a extensão é necessário algumas alterações no código.

## 🔨 Classes e Métodos

<p align="center">
    <strong>Diagrama de classes</strong>
    </br>
    <img src="./images/diagramaDeClasses.png" />
</p>

## 🔎 Análise de Complexidade

## 🔚 Conclusão

Com a implementação desta solução, aprendemos sobre os princípios e estratégias de ordenação externa. Além disso, aprendemos sobre a aplicação real desta tecnologia com as fitas e sobre a relação do software com a disponibilidade de recursos do hardware.

## 🏃 Instruções para compilação e execução (Apêndice)

- Para compilar o projeto, basta inserir a seguinte linha de comando no terminal dentro da pasta `src`:

      javac .\App.java

- Para executar o projeto, é necessário inicialmente um arquivo com as entidades (URLs e suas respectivas quantidades de visitas) a serem processadas. Este arquivo deve ser incluído na pasta `src`. Após isso, basta inserir a seguinte linha de comando, também dentro da pasta `src`, no terminal para realizar a execução:

      java .\App.java <nomeArquivoEntrada> <nomeArquivoSaida> <tamanhoMemória>
