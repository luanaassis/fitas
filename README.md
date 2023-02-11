<h1 align="center">
    📼</br>Fitas
</h1>

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

### App

Classe destinada a receber as informações de entrada (arquivo de entrada, arquivo de saída, memória) e fazer as principais chamadas do projeto.

### Intercalation

Classe responsável pela leitura da fita de entrada, criação de fitas, intercalação de fitas e geração da fita de saída..

### QuickSortArray

Classe responsável pela ordenação das fitas criadas. A implementa um array de URLs, onde o tamanho máximo é igual ao tamanho da memória, o método usado pra ordenação é o `QuickSort`, ordenado pela quantidade de número de visitas de um URL e ordem alfabética. Caso o array não esteja completo na hora de ordenar, o mesmo é recriado reduzindo o seu tamanho para evitar possíveis erros.

### ReadTape

Classe destinada apenas a leitura das fitas. A classe implementa um `Iterable<Url>`, assim a cada linha lida em uma fita é transformada em um objeto da classe URL para a manipulação.

- `readNextLine:` Função responsável por ler a próxima linha de um arquivo usando um interator e retorna uma URL. Após ler uma linha o método seta o número de fita da URL, se a fita for a de output que não possui um valor é atribuído o número 0, caso contrário o valor é o mesmo da fita.

### Url

Classe modelo para a manipulação de URL no projeto, descreve seu formato e funções básicas.

- `fromStringRep:` Função responsável por receber uma entidade em String diretamente de uma fita e retornar um objeto URL.

### WriteTape

Class destinada a criação e escrita em fitas. A classe recebe as informações em objetos da classe URL e transforma em String para a escrita.

- `writeLine:` Método responsável por escrever uma nova linha em uma fita. O método recebe dois parâmetros: uma URL que deseja escrever em uma fita e uma variável boolean para decidir se deve ou não quebrar linha.

- `write:` Método responsável por escrever um conjunto de URLs em uma fita de uma vez. O método recebe como parâmetro um Array de URLs, o método percorre todo o array concatenando e criando apenas um dado de inserção com todas as URLs.

## 🔎 Análise de Complexidade

- `readNextLine:` A função readNextLine possui a complexidade de O(1). Através de um interator a função verifica a existência de uma próxima linha, caso exista ele retorna e guarda a posição atual, assim seu custo sempre será de O(1).

- `fromStringRep:` A função fromStringRep possui a complexidade de O(1). Pois apenas recebe uma entidade em String por parâmetro e retorna um objeto novo URL.

- `writeLine:` O método writeLine possui a complexidade de O(1). Por se tratar de apenas uma entidade a ser gravada, o método não necessita de nenhum gasto computacional além de acessar o arquivo e gravar a informação.

- `write:` O método write possui a complexidade de O(N), onde N é o tamanho do array de de URLs passadas por parâmetro para função. Como é necessário formar apenas um dado a inserção é necessário percorrer todo o array.

## 🔚 Conclusão

Com a implementação desta solução, aprendemos sobre os princípios e estratégias de ordenação externa. Além disso, aprendemos sobre a aplicação real desta tecnologia com as fitas e sobre a relação do software com a disponibilidade de recursos do hardware.

## 🏃 Instruções para compilação e execução (Apêndice)

- Para compilar o projeto, basta inserir a seguinte linha de comando no terminal dentro da pasta `src`:

      javac .\App.java

- Para executar o projeto, é necessário inicialmente um arquivo com as entidades (URLs e suas respectivas quantidades de visitas) a serem processadas. Este arquivo deve ser incluído na pasta `src`. Após isso, basta inserir a seguinte linha de comando, também dentro da pasta `src`, no terminal para realizar a execução:

      java .\App.java <nomeArquivoEntrada> <nomeArquivoSaida> <tamanhoMemória>
