## Trabalho 2 - *Tester*

### Imagens Docker:

**smduarte/sd2425-tester-tp2**, versão publicada mais recente;

**smduarte/sd2425-tester-tp2-alpha**, versão em desenvolvimento.

Para o segundo trabalho, o Tester espera encontrar no ficheiro ***fctreddit.props*** as seguintes propriedades **adicionais**:
```
CLIENT_TRUSTSTORE=<trustore> ; formato: filename,password
USERS_KEYSTORES=<keystores do serviço users>; formato: users-1,filename,password
IMAGE_KEYSTORES=<keystores do serviço image>; formato: image-1,filename1,password1 image-2,filename2,password2
CONTENT_KEYSTORES=<keystores do serviço content>; formato: content-1,filename1,password1 content-2,filename2,password2 content-3,filename3,password3

IMAGE_PROXY_MAINCLASS=<classe main do Proxy>
IMAGE_PROXY_EXTRA_ARGS=<eventais parâmetros adicionais a passar ao Proxy>
IMAGE_PROXY_PORT=<porto do servidor Proxy>
IMAGE_PROXY_PROTO=<tipo do servidor Proxy>; valores esperados: rest ou grpc

USERS_REST_EXTRA_ARGS=<eventais parâmetros adicionais a passar ao servidor Users REST>
IMAGE_REST_EXTRA_ARGS=<eventais parâmetros adicionais a passar ao servidor Image REST>
CONTENT_REST_EXTRA_ARGS=<eventais parâmetros adicionais a passar ao servidor Content REST>

USERS_GRPC_EXTRA_ARGS=<eventais parâmetros adicionais a passar ao servidor Users GRPC>
IMAGE_GRPC_EXTRA_ARGS=<eventais parâmetros adicionais a passar ao servidor Image GRPC>
CONTENT_GRPC_EXTRA_ARGS=<eventais parâmetros adicionais a passar ao servidor Content GRPC>

CONTENT_REP_SERVER_MAINCLASS=<classe main do servidor Content replicado>,
CONTENT_REP_PORT=<porto do servidor Content replicado>,
CONTENT_REP_EXTRA_ARGS_FIRST=<eventais parâmetros adicionais a passar ao servidor Content replicado que iniciará como primário, se aplicável>
CONTENT_REP_EXTRA_ARGS_OTHER=<eventais parâmetros adicionais a passar aos servidores Content replicado secundários, se aplicável>
```

As propriedades não utilizadas devem ser declaradas, mas podem ser deixadas sem valor:
```
USERS_REST_EXTRA_ARGS=
```

### Opções do Tester específicas para o segundo trabalho:

* **-tp1** - Desabilita as novas propriedades do TP2, indicadas acima, e permite usar o novo Tester para testar uma imagem do primeiro trabalho.

* **-tls true/false** - Serve para indicar se o Tester deve ou não usar TLS nos pedidos. Usar **-tls false** se a solução (ainda) não implementa TLS.

* **-services true/false** - Indica se o Kafka/Zookeeper deve ser lançado; **(default: true)**.

* **-kafkaSleep** - Indica o tempo de espera entre o lançamento do Kafka e os servidores do trabalho **(default: 10 segundos)**.

* **-gcSleep** - Indica o tempo de espera até verificar se o mecanismo de GC/Integridade atuou como esperado **(default: 35 segundos)**.

### Opções do Tester que podem ajudar na replicação:

* **-restDeadline** - Indica o tempo de espera/timeout, incluindo tentativas, até falhar um pedido com **TESTER_TIMEOUT**, (default 20000 ms). 

* **-replicaTimeout** - Indica o tempo de espera/timeout até desistir de um pedido a uma réplica (falhada) **(default: 10000 ms)**. Pode ser aumentado para dar tempo para eleger o novo primário, por exemplo. Deve ser menor que o valor de **restDeadline**.


### Novas opções do tester:

* **-single** - Usar junto com **-test XX** para correr apenas o teste indicado e terminar.

### Testes envolvendo o servidor Image Proxy (Imgur)

Para esta bateria de testes, o servidor *Proxy* é sempre lançado ***um argumento*** inicial com o valor **true** ou **false**, para além dos que tenham sido eventualmente indicados no ficheiro fctreddit.props. O valor **true** indica que no arranque, o Proxy deve limpar o seu armazenamento de imagens no serviço externo **imgur**; **false** indica que o **Proxy** deve ser capaz de responder a pedidos de imagens que já estejam presentes no serviço externo **imgur**, antes do seu arranque.


## Versões

#### Versão 10

+ Testes **2x - 11x** - TLS;

#### Versão 11

+ Testes **12x** - Imgur Proxy;
   * createImage - (12a)
   * getImage - (12b)
   * deleteImage - (12c)
   * getImage w/ restart (both proxies with same clearState flag) (12d)
   * getImage w/ mixed restart (proxies with different clearState flags) (12e)
 
#### Versão 12

+ Testes **13x** - Image/Content Integrity (GC);
   * Check if mediaUrl that point to deleted imagens are set to null (13a)
   * Check if images are deleted when they are no longer referenced because of deleted posts (13b)
   * Check if images are deleted when they are no longer referenced because of updated posts (13c)
   * Check if images are deleted when they are created by they are not refereced after the gcSleep timeout (13d)

#### Versão 13 (RC)

+ Testes **14x** - Content Replication s/ Falhas;
  Estes testes correspondem aos testes 7x do TP1 que testam o serviço de conteúdos.
  Foram excluídos testes que despoletem ações no serviço Content por eventos noutros serviços (Users/Image);
  Nenhum réplica falha.
  As operações, sejam escritas ou leituras, são dirigidas a qualquer réplica;
  
#### Versão 14 (ALPHA)

+ Testes **15x** - Content Replication c/ 1 Falha num secundário estático;
 - Entende-se por secundários estáticos, os servidores Content que foram lançados com os argumentos colocados na prop: **CONTENT_REP_EXTRA_ARGS_OTHER**
 - Não testa a eventual recuperação do secundário; depois de falhado, é esquecido pelo Tester.

+ Testes **16x** - Content Replication c/ 1 Falha no primário estático;
 - Entende-se por primário estático, o servidor Content que foi lançado com os argumentos colocados na prop: **CONTENT_REP_EXTRA_ARGS_FIRST**
 - Não testa a eventual recuperação do primário; depois de falhado, é esquecido pelo Tester.
   
## Trabalho 1 - *Tester*

O *Tester* tem como objetivo permitir o teste do trabalho prático 1, de forma sistemática e uniforme para todos os grupos.
Os alunos poderão testar a correção da sua implementação, usando o *Tester*.

Para executar o tester, os alunos com sistemas operativos Linux ou MacOS devem usar o script 
[test-sd-tp1.sh](https://raw.githubusercontent.com/jcleitao/sd2425-proj-api/refs/heads/main/test-sd-tp1.sh),
enquanto que os alunos com Windows devem usar o script [test-sd-tp1.bat](https://raw.githubusercontent.com/jcleitao/sd2425-proj-api/refs/heads/main/test-sd-tp1.bat).
<br>

**Nota**: Todos os ficheiros mencionados estão dispopíveis em:
[https://github.com/jcleitao/sd2425-proj-api](https://github.com/jcleitao/sd2425-proj-api).
 
### Preparação
Antes de executar o tester e criar a imagem docker, devem adicionar e atualizar o 
fichero [fctreddit.props]([https://github.com/preguica/sd2324-proj-api/blob/main/shorts.props](https://raw.githubusercontent.com/jcleitao/sd2425-proj-api/refs/heads/main/fctreddit.props)) 
na raiz do vosso projeto com a informação correta:


```
USERS_REST_SERVER_MAINCLASS=classe do servidor Users REST (e.g. sd2425.tp1.server.RESTUsersServer)
USERS_REST_PORT=porto do servidor Users REST (e.g. 8080)
USERS_REST_EXTRA_ARGS=parâmetro adicional a passar ao servidor Users REST, se algum
CONTENT_REST_SERVER_MAINCLASS=classe do servidor Content REST 
CONTENT_REST_PORT=porto do servidor Content REST
CONTENT_REST_EXTRA_ARGS=parâmetro adicional a passar ao servidor Content REST, se algum
IMAGE_REST_SERVER_MAINCLASS=classe do servidor Image REST 
IMAGE_REST_PORT=porto do servidor Image REST
IMAGE_REST_EXTRA_ARGS=parâmetro adicional a passar ao servidor Image REST, se algum
USERS_GRPC_SERVER_MAINCLASS=classe do servidor Users GPRC 
USERS_GRPC_PORT=porto do servidor Users GRPC
USERS_GRPC_EXTRA_ARGS=parâmetro adicional a passar ao servidor Users GRPC, se algum
CONTENT_GRPC_SERVER_MAINCLASS=classe do servidor Content GRPC 
CONTENT_GRPC_PORT=porto do servidor Content GRPC
CONTENT_GRPC_EXTRA_ARGS=parâmetro adicional a passar ao servidor Content GRPC, se algum
IMAGE_GRPC_SERVER_MAINCLASS=classe do servidor Image GRPC 
IMAGE_GRPC_PORT=porto do servidor Image GRPC
IMAGE_GRPC_EXTRA_ARGS=parâmetro adicional a passar ao servidor Image GRPC, se algum
DISCOVERY_MULTICAST_IP=endereço multicast para descoberta (e.g. 226.226.226.226)
DISCOVERY_MULTICAST_PORT=porto para descoberta (e.g. 2266)
```

Enquanto não tiverem a parte GRPC implementada, devem deixar as propriedades respetivas sem nenhum valor, mas não as devem apagar.
Podem igualmente testar o servidor ***Users*** sem terem os servidores ***Content*** e ***Image*** funcionais, deixando as respetivas propriedades sem
nenhum valor.

Devem usar o ficheiro **Dockerfile** presente no repositório das interfaces,
presente no seguinte link: [Dockerfile](https://raw.githubusercontent.com/jcleitao/sd2425-proj-api/refs/heads/main/Dockerfile), 
o qual necessita do ficheiro de configuração do Hibernate 
[hibernate.cfg.xml](https://raw.githubusercontent.com/jcleitao/sd2425-proj-api/refs/heads/main/hibernate.cfg.xml), convenientemente parametrizado.

Sugere-se que usem o ficheiro [pom.xml](https://github.com/jcleitao/sd2425-proj-api/blob/main/pom.xml) que se encontra no repositório, como ponto de partida.


**Sempre que alterarem o vosso trabalho (incluindo o ficheiro fctreddit.props)** devem criar 
uma nova imagem Docker do vosso trabalho (usando o projeto Maven disponibilizado, 
tal consiste em executar: `mvn clean compile assembly:single docker:build`).


**NOTA:** Em algumas versões do Docker, o docker:build pode não funcionar. Nesse caso, devem criar
a imagem usando os seguintes comandos:
```
mvn clean compile assembly:single
docker build -t sd2425-trab1-XXXXX-YYYYY .
```

Nota: Substituir XXXXX-YYYYY pelos números de aluno do grupo. Remover -YYYYY, se o grupo for individual, inclusivamente no pom.xml.
### Execução dos testes

Para executar o *Tester*, basta executar o seguinte comando, 
usando o nome da imagem do vosso trabalho (**substituir sd2425-trab1-xxxxx-yyyyy pelo 
nome da vossa imagem**):

```
Linux / MacOS
sh test-sd-tp1.sh -image sd2425-trab1-xxxxx-yyyyy
```
```
Windows
test-sd-tp1.bat -image sd2425-trab1-xxxxx-yyyyy
```
*NOTA:* há uma secção sobre problemas com a execução deste comando mais tarde neste documento.

O *Tester*, ao executar, corre uma série de passos, indicando genericamente 
as verificações que está a efetuar. 
Caso os testes de cada fase corram corretamente, no fim de cada fase 
aparece a mensagem OK. 

Exemplo de uma execução correta (truncada):

![Imagem de execução correta](tester-runok.png)

Caso um teste falhe, apresenta informação de qual o erro que ocorreu, indicando o resultado recebido do vosso trabalho e qual o resultado esperado. Adicionalmente, serão apresentadas as menssagens escritas pelo vosso programa para o output.

Exemplo de uma execução com um erro (em que se pode ver que se esperaria o código 403 e se recebeu o código 404 na execução dum DeleteUser):

![Imagem de execução com erro](tester-runerror.png)


### Outras opções do *Tester*

O *Tester* possui algumas opções que podem ser especificadas ao corrê-lo, que modificam o seu comportamento.

* **-test \<num\>** : Permite omitir a execução de alguns testes. 
Por exemplo, se passarem o valor **-test 2b**, os testes começarão no 
teste 2b. Esta opção é útil quando já verificaram que o vosso trabalho 
funciona corretamente até um dado teste e estão a corrigir erros num 
teste específico.
  
* **-sleep \<seconds\>** : Permite diminuir o tempo de espera entre serem 
lançadas os containers com o vosso trabalho e iniciar a execução dos 
testes. Podem ajustar este valor consoante a capacidade do vosso 
computador e as operações que estejam a fazer na fase de inicialização.

<!---
* **-log OFF\|ALL\|FINE\|FINEST** : Permite controlar o nível de mensagens 
gerado pelo programa. Por exemplo, ao usarem a opção **-log FINE**, o 
programa vai indicar todas as operações que está a fazer ao vosso sistema, 
indicando as mensagens recebidas e as esperadas, como se apresenta na 
imagem seguinte:

* **-textsize \<len\>** : Permite indicar a dimensão máxoma do texto das 
mensagens criadas.

![Imagen com debug a FINE](tester-rundetail.png)

-->


### Problemas com o script

Dicas sobre execução no Windows a adicionar brevemente, no entanto se não conseguirem usar o script fornecido num terminal, podem executar o Tester manualmente com o seguinte conjunto de comandos:

Para criar a rede -- a executar uma vez, ou quando a rede *sdnet* tiver sido removida (por exemplo pelo docker system prune):
```
docker network create -d bridge sdnet 
```

Para atualizar a imagem -- a executar sempre que o *Tester* for atualizado:
```
docker pull smduarte/sd2425-tester-tp1
```

Para executar o *Tester* (notem que podem ter de fazer scroll na linha abaixo):
```
docker run --rm --network=sdnet -it -v /var/run/docker.sock:/var/run/docker.sock smduarte/sd2425-tester-tp1:latest -image sd2425-trab1-xxxxx-yyyyy
```

Quem tenha problemas a correr o *Tester*, deve 
fazer um post no ***Discord*** da cadeira, 
com a seguinte informação: dump da mensagem de erro, sistema de operação e versão do 
docker que estão a usar. 
Caso já exista uma mensagem semelhante, façam reply a essa mensagem.

### Códigos de erro

Os códigos de erro que os vossos servidores devolvem devem estar de acordo
com os comentários das respetivas interfaces, disponíveis em: [https://github.com/jcleitao/sd2425-proj-api/tree/main/src/fctreddit/api/java).

### Versões

As versões do *Tester* são incrementais, i.e., a versão N do *Tester* executará todos os testes das versões anteriores.
Nesta secção indicam-se as funcionalidades testadas por cada versão.

#### Versão 1 

+ Testes **1x** - Testa a integridade do ambiente de teste;
+ Testes **2x** - Testa mecanismo descoberta no serviço **Users**.

#### Versão 2

+ Testes **3x** - Testa o serviço Users REST, isoladamente, sem tocar no **avatarUrl**;

#### Versão 3

+ Testes **4x** - Testa os serviços Image + Users REST;
+ Testes **5x** - Testa o Users GRPC; 
+ Testes **6x** - Testa o Image + Users GRPC; 

#### Versão 4

+ Testes **7x** - (ALPHA, incompleto) Testa os serviços Content + Users REST;
  * createPost, getPost - (7a)
  * Invalid getPost - (7b)
  * getPostAnswers (c/ TIMEOUT 0) - (7c)
  * upVote/DownVote - (7d)
  * getupVotes/getDownVotes - (7e)
  * getPosts (c/ Timestamp) - (7f)
  * getPosts (s/ Timestamp) - (7g)
  * getPosts (c/ Timestamp + MOST_REPLIES) - (7h)
  * getPosts (c/ Timestamp + MOST_UP_VOTES) - (7i)
  * getPostAnswers (c/ MAX_TIMEOUT) - (7j)

#### Versão 5

+ Testes **7x** - Testa os serviços Content + Users REST;
  * getPost (com validação do número de votos do post) - (7k)

+ Testes **8x** - Testa os serviços Content + Users GRPC;
  * São os mesmos testes **7x** mas, com o serviço Users e Content em GRPC

#### Versão 6

+ Testes **7x** - Testa os 3 serviços em REST;
  * updatePost - (7l)
  * deletePost - (7m)
  * deleteAuthor - (7n)

+ Testes **8x** - Testa os 3 serviços em GRPC;
  * São os mesmos testes **7x**;

#### Versão 7

+ Testes **4x** - Testa os 3 serviços em REST;
  * deleteImage - (4c)

+ Testes **6x** - Testa os 3 serviços em GRPC;
  * deleteImage - (6c)

#### Versão 8 

+ Testes **9x** - Testa falhas de comunicação em REST;
+ Testes **10x** - Testa interoperabilidade entre serviços de diferentes tipos (REST/GRPC);
+ Testes **11x** - **(Instáveis)** Testa robustez dos servidores com pedidos concorrentes.

#### Versão 9 (Final RC)

+ Testes **11x** - Testa robustez dos servidores com pedidos concorrentes.
  Este teste faz um mix de pedidos concorrentes ao serviço Content. Falha se
  forem retornados erros do tipo INTERNAL_ERROR.

### INTERNAL_SERVER_ERROR

Normalmente, estes erros correspondem a uma excepção, não apanhada, que 
ocorreu no servidor, na resposta a um pedido. A classe [GenericExceptionMapper](https://raw.githubusercontent.com/smduarte/sd2425-tester/refs/heads/main/GenericExceptionMapper.java) neste
repositório pode ser registada (ResourceConfig) nos servidores REST para ajudar a depurar o problema.

<!---
#### Versão 1
Testa as seguintes funcionalidades (REST):
* criação e leitura de utilizadores (2a)
* atualização de utilizadores (2b)
* remoção de utilizadores (2c)
* pesquisa de utilizadores (2d)

#### Versão 3
Testa as seguintes funcionalidades (REST):
* Anúncios com discovery (3a)
* Shorts only: create, get (4a)
* Shorts only: create, get, get feed, follow/unfollow (4b)
* Shorts only: create, get, get feed, follow/unfollow, delete (4c)
* Shorts only:create, get, get feed, follow/unfollow, like/unlike, delete (4d)
* Shorts+Blobs: create, get (5a)
* Shorts+Blobs: create, get, get feed, follow/unfollow (5b)
* Shorts+Blobs: create, get, get feed, follow/unfollow, delete (5c)
* Shorts+Blobs:create, get, get feed, follow/unfollow, like/unlike, delete (5d)

#### Versão 4
Testa as seguintes funcionalidades com dois servidores de blobs(REST):
* Shorts+Blobs: create, get (6a)
* Shorts+Blobs:create, get, get feed, follow/unfollow, like/unlike, delete (6b)

Testa as seguintes funcionalidades (REST):
* Testa se bloquador de rede funciona (7a)
* Shorts+Blobs com falhas para o users: create, get, get feed, follow/unfollow (7b)

Testa as seguintes funcionalidades (GRPC):
* criação e leitura de utilizadores (9a)
* atualização de utilizadores (9b)
* remoção de utilizadores (9c)
* pesquisa de utilizadores (9d)
* Shorts only: create, get (10a)
* Shorts+Blobs: create, get (10b)
* Shorts+Blobs: create, get, get feed, follow/unfollow (10c)
* Shorts+Blobs:create, get, get feed, follow/unfollow, like/unlike, delete (10d)

#### Versão 5
Testa as seguintes funcionalidades (REST):
* Shorts+Blobs:create, get, get feed, follow/unfollow, like/unlike, delete, delete user (5e)
* Shorts+Blobs com falhas para o shorts: create, get, get feed, follow/unfollow (7c)

Testa as seguintes funcionalidades (mix servidores REST + GRPC):
* Shorts+Blobs: create, get, get feed, follow/unfollow, delete (12a)
* Shorts+Blobs: create, get, get feed, follow/unfollow, delete (12b)
* Shorts+Blobs: create, get, get feed, follow/unfollow, delete (12c)
* Shorts+Blobs: create, get, get feed, follow/unfollow, delete (12d)
* Shorts+Blobs: create, get, get feed, follow/unfollow, delete (12e)

FIX in all tests that use getFeeds and multiple blob servers.

#### Versão 6
Testa as seguintes funcionalidades (REST):
* Users: mix de operações concorrentes (8a)
* Shorts+Blobs: mix de operações concorrentes (8b)
* Users+Shorts+Blobs: mix de operações concorrentes (8c)
* Shorts+Blobs com falhas longas users/shorts (7d)

RETIRADO : Testa as seguintes funcionalidades (GRPC):
* Shorts+Blobs com falhas para o users: create, get, get feed, follow/unfollow (11a)
* Shorts+Blobs com falhas para o shorts: create, get, get feed, follow/unfollow (11b)
* Shorts+Blobs com falhas longas users/shorts (11c)


-->

### Notas finais

O facto dum trabalho passar os testes todos (ou o teste duma funcionalidade) não equivale a que tenha a cotação máxima. 
Primeiro, devem ter em atenção que os testes apenas estão a testar as funcionalidades indicadas na listagem anterior.
Segundo, a avaliação do trabalho terá em conta a qualidade do código e, sobretudo, da solução.

