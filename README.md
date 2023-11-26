<div align="center">
  <h3 align="center">Bicho.bet</h3>

  <p align="center">
    Uma plataforma para simular a prática sócio-cultural brasileira chamada "Jogo do Bicho"
    <br />
    <strong>Desenvolvido por: </strong>
    <br />
    <a href="https://github.com/phillCD">Filipe Milaneze</a>
    ·
    <a href="https://github.com/GabrielGuinzani">Gabriel Ferreira</a>
    ·
    <a href="https://github.com/guilherme-savio">Guilherme Savio</a>
    ·
    <a href="https://github.com/higorgoulart">Higor Goulart</a>
    ·
    <a href="https://github.com/MaxWillianm">Max Willian Trajano</a>
  </p>
</div>


## Sobre o projeto

O projeto simula uma casa de apostas para a prática do "Jogo do Bicho" permitindo o usuário realizar depósitos fictícios e apostas funcionais em grupos de animais ou grupos de números. 

## Construído com

[![My Skills](https://skillicons.dev/icons?i=java,spring,maven,python,django,go,react,tailwind,postgres,redis,docker)](https://skillicons.dev)

## Iniciando

Nesta seção você encontrará os passos para instalar e executar o projeto na sua máquina.

## Pré-requisitos

* <a href="https://www.oracle.com/java/technologies/javase/jdk17-readme-downloads.html">JDK 17</a>
* <a href="https://maven.apache.org/download.cgi">Maven</a>
* <a href="https://www.docker.com/products/docker-desktop/">Docker Desktop</a>
* <a href="https://www.python.org/downloads/">Python</a>

## Instalação

1. Clone o repositório
   ```sh
   git clone https://github.com/higorgoulart/bicho.bet.git
   ```
2. Abra o serviço/pasta "springboot-api" com a IDE de sua preferência (recomendamos utilizar o IntelliJ) e realize o build com o Maven pela IDE ou terminal. <strong> O serviço disponibiliza um <a href="https://github.com/higorgoulart/bicho.bet/blob/main/springboot-api/src/main/resources/data.sql">script DML<a/> com apostadores, apostas, jogos e resultados pré-cadastrados para você que deseja uma visualização mais simples da aplicação. Recomendamos que ele seja excluído antes de realizar o build caso queira usufruir da experiência completa de abrir jogos, cadastrar apostadores, realizar apostas, pedir empréstimos e muito mais!. </strong>
    ![image](https://github.com/higorgoulart/bicho.bet/assets/110054084/b18a5c00-94e8-4df7-95ea-d91b9840f35e)
     * Terminal
       ```sh
       mvn -B clean install
       ```

3. Com o Docker Desktop instalado, garanta que ele esteja em execução para o funcionamento correto do docker-cli.

4. Com o Python também instalado, abra o terminal na pasta raiz e execute o comando:
   ```sh
   python run.py
   ```
5. O script <a href="https://github.com/higorgoulart/bicho.bet/blob/main/run.py">run.py</a> dará início a criação dos contêineres estabelecidos no arquivo <a href="https://github.com/higorgoulart/bicho.bet/blob/main/docker-compose.yaml">docker-compose.yaml</a>.

6. No decorrer do processo, quando a criação do contêiner do serviço Django iniciar, o terminal irá solicitar ao usuário a inserção de alguns comandos. Após inseri-los, pressione 'CTRL+D'.
    * Realizar as migrações do Django:
      ```sh
       python manage.py migrate
      ```
   * Criar a senha do super-usuário do Django (você pode inserir a senha que desejar):
      ```sh
       export DJANGO_SUPERUSER_PASSWORD=123
      ```
   * Criar o super-usuário (você pode modificar como preferir):
      ```sh
       python manage.py createsuperuser python manage.py createsuperuser --username bichobet --email bichobet.satc@gmail.com --noinput
      ```

7. O restante dos contêineres serão criados baseados nas suas configurações de "produção". Por exemplo, o scheduler esta programado para pegar a data de novos jogos de 12/12h e gerar o resultado e pagamentos conforme a data final cadastrada no jogo, as taxas de empréstimos só serão aplicadas à 00:00 de todos os dias. Caso queria alterar alguma configuração, edite o serviço com a IDE desejada e refaça o processo de instalação. 
 
8. Por fim, acesse http://localhost:5173 e divirta-se!


## Rotas

* http://localhost:5173 - Front-end da aplicação onde ocorre todo o funcionamento. 
* http://localhost:5173/admin ou http://localhost:8000/admin - Painel administrativo para o usuário cadastrar jogos e funcionários. O login é o que você criou no passo 6 da seção <strong>Instalação</strong>.
* http://localhost:8080 - API SpringBoot.
* http://localhost:5432 - Endereço do PostgreSQL. Senha e usuário podem ser encontrados no arquivo <a href="https://github.com/higorgoulart/bicho.bet/blob/main/docker-compose.yaml">docker-compose.yaml</a>. Recomendamos que estas credenciais não sejam alteradas sem pleno conhecimento de que poderá gerar erro em algum serviço. Caso ainda deseje alterar, será necessário também modificar os outros serviços dependentes.


## Contato

* <a href="https://www.linkedin.com/in/filipe-milaneze-de-aguiar-284876214/">Filipe Milaneze</a>

* <a href="https://www.linkedin.com/in/gabrielferreiraguinzani">Gabriel Ferreira</a>

* <a href="https://www.linkedin.com/in/guilhermesavio27/">Guilherme Savio</a>

* <a href="https://www.linkedin.com/in/higor-goulart-massiroli-a1354719b/">Higor Goulart</a>

* <a href="https://www.linkedin.com/in/max-willian-trajano-martins-689400234/">Max Willian Trajano</a>

