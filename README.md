# Project

Esse projeto é uma API RestFull criada para criar, ler, atualizar e deletar produtos, além de realizar uma pesquisa utilizndo como parâmetros texto, preço máximo e mínimo.

## Tools

O projeto foi desenvolvido com Java 16, Maven 4, Spring 2.5.2, Tomcat 9.0.48. O banco de dados utilizado foi o PostgreSQL 11. Além disso o projeto foi adicionado em 3 containers do Docker, um para o webservice Tomcat, e dois para o banco de dados Postgresql e PGAdimn 4.

## Build

Para executar o projeto é necessário em primeiro lugar subir os containers do Postgresql e PGAdmin4. Com isso você poderá criar o banco de dados "desafio_db". Sem ele o projeto não irá executar.

Em seguida, via linha de comando, na pasta do projeto compile o projeto "mvn install". Para compilar o banco de dados deverá estar rodando. Caso queira testar se o projeto está rodando basta digitar "java -jar target/desafio-0.0.1-SNAPSHOT.jar

Para gerar o build e compilar o projeto digite os comandos:

$ docker-compose build

$ docker-compose up

## Finished

Agora é só esperar o servidor inicializar!
