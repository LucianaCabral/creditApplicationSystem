```
#Mon Jan 16 2024
```

# Credit Application System
### API Rest para um Sistema de Analise de Solicitação de Crédito

#### Baseado no projeto da trilha Backend Dio  ministrado por Camnila Cavalcanti
- [Descrição do projeto](https://gist.github.com/cami-la/560b455b901778391abd2c9edea81286)

## Setup inicial do projeto
- Configura o projeto no [spring](https://start.spring.io/) o seguintes itens:
- Project:  Gradle-koltin,  Language: Koltin, Versão utlizada a mais atual. 3.1.7
- Versão Java 17
- Adiciona as seguintes dependências:
[Spring Web] | [Validation] | [Hibernate] | [Spring Data JPA] | [Flyway Migration] |  [H2 Database]

- Generate para garar o arquivo zip e salva arquivo zip e extrai na pasta da máquina para importar no intelij
- Abrir o intelij, abrir o projeto salvo no arquivo

## Configutando no projeto
O springBoot permite utilizar 2 tipos de arquivos: application.properties ou application.yml

No application.properties, refatora para application.yml e acrescenta:

```
spring:
  datasource:
    url: jdbc:h2:mem:credit_application_system_db
    username: 
    password: 
  jpa:
    show-sql: true
    hibernate:
    properties:
      hibernate.format_sql: true
  h2:
    console:
      enabled: true
      path: /h2-console
      settings:
        trace: false
        web-allow-others: false

```
- Reinicia o projeto e veja se o BD local está on através: #### Testa Banco De Dados local:
http://localhost:8080/h2-console, configure para a fazer o login

- Para Gerar a documentação automaticamente utilizo a lib **springboot-openapi**  Acrescenta neste mesmo arquivo application.yml a path da doc:

```
springdoc:
  swagger-ui:
    path: /swagger-ui.html'

```
Add no gradle a dependência

```
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'

```
Adiciona a classe de configuração do swagger

#### Testa Banco De Dados local http://localhost:8080/h2-console

#### Testa Swagger doc http://localhost:8080/swagger-ui/index.html

<img height="15" src="https://user-images.githubusercontent.com/25181517/192107854-765620d7-f909-4953-a6da-36e1ef69eea6.png"> <img height="15" src="https://user-images.githubusercontent.com/25181517/192107858-fe19f043-c502-4009-8c47-476fc89718ad.png"> <img height="15" src="https://user-images.githubusercontent.com/25181517/192108372-f71d70ac-7ae6-4c0d-8395-51d8870c2ef0.png"> <img height="15" src="https://user-images.githubusercontent.com/25181517/192108374-8da61ba1-99ec-41d7-80b8-fb2f7c0a4948.png"> <img height="15" src="https://user-images.githubusercontent.com/25181517/192108890-200809d1-439c-4e23-90d3-b090cf9a4eea.png">  <img height="15" src="https://user-images.githubusercontent.com/25181517/192109061-e138ca71-337c-4019-8d42-4792fdaa7128.png"> <img height="15" src="https://user-images.githubusercontent.com/25181517/117207493-49665200-adf4-11eb-808e-a9c0fcc2a0a0.png"> <img height="15" src="https://user-images.githubusercontent.com/25181517/183894676-137319b5-1364-4b6a-ba4f-e9fc94ddc4aa.png"> <img height="15" src="https://user-images.githubusercontent.com/25181517/185062810-7ee0c3d2-17f2-4a98-9d8a-a9576947692b.png"> <img height="15" src="https://user-images.githubusercontent.com/25181517/192158606-7c2ef6bd-6e04-47cf-b5bc-da2797cb5bda.png"> <img height="15" src="https://user-images.githubusercontent.com/25181517/186884150-05e9ff6d-340e-4802-9533-2c3f02363ee3.png"> <img height="15" src="https://user-images.githubusercontent.com/25181517/186711335-a3729606-5a78-4496-9a36-06efcc74f800.png"> <img height="15" src="https://user-images.githubusercontent.com/25181517/192107858-fe19f043-c502-4009-8c47-476fc89718ad.png">





