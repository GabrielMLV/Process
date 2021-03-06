# Spring Boot, Postgresql, JPA, Hibernate Rest API e Swagger

Aplicação utilizando Spring Boot, Postgresql, JPA, Lombok e Hibernate.

## Requirements

1. Java - 1.8.x
2. Maven - 3.x.x
3. Postgresql - 5.x.x

## Passos para o SETUP

**1. Clone a aplicação**

```bash
git clone https://github.com/GabrielMLV/process.git
```

**2. Crie o banco de dados no PostgreSql**
```bash
create database processdb
```
> Tabelas são geradas automaticamente e alguns dados de testes são inseridos (verificar data.sql e schema.sql na pasta resources)

**3. Modifique o usuário e senha de acordo com a sua instalação**

+ abra `src/main/resources/application.properties`

+ modifique `spring.datasource.username` e `spring.datasource.password` de acordo com sua instalação

**4. Build e rode a aplicação usando MAVEN**

```bash
mvn spring-boot:run
```

O app vai rodar nesse endereço <http://localhost:8080/v1/api>.

