# API para o site turístico Jornada Milhas

<p align="center">
  <img src="https://img.shields.io/static/v1?label=spring&message=3.1.2&color=blue&style=for-the-badge&logo=SPRING"/>
  <img src="http://img.shields.io/static/v1?label=Java&message=17&color=blue&style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/static/v1?label=MySQL&message=8.0.31&color=blue&style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="http://img.shields.io/static/v1?label=Docker&message=4.24.0&color=blue&style=for-the-badge&logo=docker"/>
  <img src="http://img.shields.io/static/v1?label=Maven&message=4.0.0&color=blue&style=for-the-badge&logo=Apache%20Maven"/>
  <img src="http://img.shields.io/static/v1?label=CHATGPT&message=3.5&color=blue&style=for-the-badge&logo=openai&logoColor=white"/>
  <img src="http://img.shields.io/static/v1?label=TESTS&message=18%20passed&color=GREEN&style=for-the-badge"/>
   <img src="http://img.shields.io/static/v1?label=STATUS&message=CONCLUIDO&color=GREEN&style=for-the-badge"/>
</p>

### Descrição do Projeto

Essa é uma **API REST** que atua como backend para a plataforma de turismo "Jornada Milhas". Permite operações *CRUD* (Create, Read, Update, Delete) para destinos e depoimentos, atendendo às necessidades do domínio.

A aplicação foi desenvolvida em **Java 17** utilizando o ecossistema **Spring Framework**, notavelmente o **Spring Boot 3.1.2**. O banco de dados é o **MySQL**. Integra-se à API da **OpenAI** (usando **ChatGPT 3.5**) para gerar descrições atraentes dos destinos.

Além disso, foi implementado o uso do **Docker**, facilitando a distribuição e execução da aplicação em ambientes consistentes. Os testes são realizados com **JUnit 5**, **MockMVC**, e **Mockito**, garantindo a confiabilidade do código.

A documentação da API é feita com **Swagger**, simplificando a visualização e teste dos endpoints. Siga a documentação para utilizar a API de forma eficaz e eficiente.


### Destino (Destination)

Contém os seguintes atributos:

```json
"Id", //Id incremental auto gerado
"Name", //Nome descritivo do destino
"PhotoPath", //Link da imagem principal do destino
"PhotoPath2", //Link da imagem detalhada do destino
"Meta", //Frase resumindo o destino
"description", //Descrição opcional mais detalhada do destino
"price", //Preço da viagem
"active" //Variável para controle de objetos ativos
```

Suporta os seguintes endpoints:

POST ```/destinos```
Realiza o cadastro de um destino.

GET ```/destinos```
Retorna todas os destinos cadastrados.

GET ```/destinos/{id}```
Busca o destino com o id informada no endereço.

PUT ```/destinos/{id}```
Atualiza o destino cujo id for informado no endereço.

DELETE ```/destinos/{id}```
Exclui o destino com id informado no endereço.

### Depoimento (Testimonial)

Contém os seguintes atributos:

```json
"Id", //Id incremental auto gerado
"PersonName", //Nome do usuário
"TestimonialText", //Depoimento escrito pelo usuário
"ImagePath", //Link da imagem de perfil do usuário 
"active" //Variável para controle de objetos ativos
```

Suporta os seguintes endpoints:

POST ```/depoimentos```
Realiza o cadastro de um depoimento.

GET ```/depoimentos```
Retorna todas os depoimentos cadastrados.

GET ```/depoimentos-home```
Retorna três depoimentos aleatórios cadastrados.

GET ```/depoimentos/{id}```
Retorna o depoimento cujo id for informado no endereço.

PUT ```/depoimentos/{id}```
Atualiza o depoimento cujo id for informado no endereço.

DELETE ```/depoimentos/{id}```
Exclui o depoimento com id informado no endereço.

## Documentação

Este projeto utiliza o [Swagger](https://springdoc.org/) para gerar a documentação, que pode ser acessada pelo link abaixo após executar a API:

[localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


## Executando o projeto localmente

Para executar a aplicação localmente deve-se fazer inicialmente o clone desse repositório:

```bash
# Clone o repositório
git clone https://github.com/rldcarvalho/projeto-jornada-milhas.git

# Navegue até o repositório clonado
cd projeto-jornada-milhas
```
### Configurando o banco de dados

O projeto utiliza o banco de dados MySQL. Para executá-lo, crie um banco de dados chamado *jornada_milhas* e atualize o arquivo de configuração *application.properties*:

```properties
spring.datasource.url=jdbc:mysql://[URL]:[PORT]/jornada_milhas
spring.datasource.username=[USERNAME]
spring.datasource.password=[PASSWORD]
```

### Configurando a API Key da OpenAI

A aplicação integra-se à API da OpenAI para gerar descrições dos destinos usando o ChatGPT. Para utilizar essa funcionalidade, você precisa obter uma chave de API da OpenAI a partir do [OpenAI Platform](https://platform.openai.com/).

Existem três formas de configurar a API Key, dependendo do seu ambiente:

1. **Linux/macOS (exportar como variável de ambiente):**
   Execute o seguinte comando no terminal, substituindo `apikeyhere` pela sua API Key:
```bash
export OPENAI_API_KEY=apikeyhere
```

2. **Windows (usando variáveis de ambiente):**
   Adicione uma nova variável de ambiente chamada `OPENAI_API_KEY` e configure-a com o valor da sua API Key.
   
3. **Alterando o arquivo application.properties (não recomendado):**
   Você pode adicionar a API Key diretamente no arquivo `application.properties`:
```properties
    # Configuração da API Key da OpenAI
    services.openai.apikey=${OPENAI_API_KEY}
```
Certifique-se de substituir apikeyhere pela sua API Key.

Lembrando que a recomendação é usar as opções 1 ou 2 para manter as credenciais seguras e fora do código versionado.
### Execução

Execute o projeto como seguinte comando:

```bash
mvn spring-boot:run
```

Para fazer as requisições HTTP aos endpoints da API, utilize ferramentas como [Postman](https://www.postman.com/) ou [Insomnia](https://insomnia.rest/download).

## Licença

The [MIT License](https://github.com/rldcarvalho/projeto-jornada-milhas/blob/main/LICENSE) (MIT)

Copyright :copyright: 2023 - Api Jornada Milhas
