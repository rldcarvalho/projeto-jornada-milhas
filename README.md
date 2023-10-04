# API Jornada Milhas 

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

## Descrição do Projeto

Essa é uma **API REST** que atua como backend para a plataforma de turismo "Jornada Milhas". Permite operações *CRUD* (Create, Read, Update, Delete) para destinos e depoimentos, atendendo às necessidades do domínio.

### Tecnologias Utilizadas:

| Descrição              | Tecnologia                                                                                                                                                                    |
|------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Linguagem              | [Java](https://www.java.com/)                                                                                                                                                 |
| Framework              | [Spring Boot](https://spring.io/)                                                                                                                                             |
| Banco de Dados         | [MySQL](https://www.mysql.com/)                                                                                                                                               |
| Ferramenta de Migração | [Flywaydb](https://flywaydb.org/)                                                                                                                                             |
| Documentação           | [SpringDoc Swagger](https://springdoc.org/)                                                                                                                                   |
| Testes Unitários       | [Junit](https://junit.org/junit5/), [Mockito](https://site.mockito.org/), [MockMvc](https://docs.spring.io/spring-framework/reference/testing/spring-mvc-test-framework.html) |
| Integração             | [OpenAI Java](https://github.com/TheoKanning/openai-java)                                                                                                                     |
| Outras Bibliotecas     | [DataFaker Java](https://github.com/datafaker-net/datafaker)                                                                                                                  |
| Conteinerização        | [Docker](https://www.docker.com/)                                                                                                                                             |
| Ferramenta de Build    | [Apache Maven](https://maven.apache.org/)                                                                                                                                     |
| Editor de Código       | [Intellij](https://www.jetbrains.com/idea/)                                                                                                                                   |
| Versionamento          | [Git](https://git-scm.com/)                                                                                                                                                   |

## Índice
- [Endpoints da API](#endpoints-da-api)
   - [Cadastro de Depoimento](#cadastro-de-depoimento)
   - [Depoimentos Aleatórios](#depoimentos-aleatórios)
   - [Consulta Detalhada de Depoimento por ID](#consulta-detalhada-de-depoimento-por-id)
   - [Atualização de Depoimento](#atualização-de-depoimento)
   - [Remoção de Depoimento](#remoção-de-depoimento)
   - [Registro de Destino](#registro-de-destino)
   - [Consulta de Destino por Nome](#consulta-de-destino-por-nome)
   - [Consulta de Destino por ID](#consulta-de-destino-por-id)
   - [Atualização de Destino](#atualização-de-destino)
   - [Remoção de Destino](#remoção-de-destino)
- [Documentação](#documentação)
- [Executando o projeto localmente](#executando-o-projeto-localmente)
- [Licença](#licença)

## Endpoints da API

### Cadastro de Depoimento

`POST /depoimentos`

Permite o cadastro de um novo depoimento. O corpo da requisição deve conter um objeto JSON com `personName` (nome do autor), `testimonialText` (texto do depoimento) e `photoPath` (URL da foto do autor) como campos obrigatórios. Retorna um JSON com os dados do depoimento criado.

**Exemplo de Requisição:**

```json
{
   "personName": "João Silva",
   "testimonialText": "Experiência incrível!",
   "photoPath": "caminho/para/imagem.jpg"
}
```

**Exemplo de Resposta:**

```json
{
   "id": 1,
   "personName": "João Silva",
   "testimonialText": "Experiência incrível!",
   "photoPath": "caminho/para/imagem.jpg"
}
```
### Depoimentos Aleatórios
`GET /depoimentos-home`

Retorna até 3 depoimentos selecionados aleatoriamente.

**Exemplo de Resposta:**

```json
[
   {
      "id": 1, 
      "personName": "João Silva", 
      "testimonialText": "Experiência incrível!", 
      "photoPath": "caminho/para/imagem.jpg"
   },
   {
      "id": 4, 
      "personName": "Maria Souza", 
      "testimonialText": "Adorei cada momento!", 
      "photoPath": "caminho/para/imagem2.jpg"
   },
   {
      "id": 10, 
      "personName": "Carlos Ferreira", 
      "testimonialText": "Viagem inesquecível!", 
      "photoPath": "caminho/para/imagem3.jpg"
   }
]
```

### Consulta Detalhada de Depoimento por ID

`GET /depoimentos/{id}`

Retorna o depoimento correspondente ao ID fornecido.

**Exemplo de Resposta:**

```json
{
   "id": 1,
   "personName": "João Silva",
   "testimonialText": "Experiência incrível!",
   "photoPath": "caminho/para/imagem.jpg"
}
```

### Atualização de Depoimento
        
`PUT /depoimentos`

Permite a atualização dos campos `personName` (nome do autor), `testimonialText` (texto do depoimento) e `photoPath` (URL da foto do autor) do depoimento correspondente ao ID informado no corpo da requisição. Retorna um JSON com os dados do depoimento atualizado.

**Exemplo de Requisição:**

```json
{
    "id": 1,
    "testimonialText": "Isso não é mais divertido.",
    "photoPath": "caminho/nova/imagem.jpg"
}
```

**Exemplo de Resposta:**

```json
{
   "id": 1,
   "personName": "João Silva",
   "testimonialText": "Isso não é mais divertido.",
   "photoPath": "caminho/nova/imagem.jpg"
}
```

### Remoção de Depoimento

`DELETE /depoimentos/{id}`

Permite excluir o depoimento correspondente ao ID fornecido.

### Registro de Destino

`POST /destinos`

Permite o cadastro de um novo destino. O corpo da requisição deve conter um objeto JSON com `name` (nome do destino), `photoPath` (URL da foto do destino), `photoPath2` (URL da segunda foto do destino), `meta` (meta do destino) e `price` (preço do destino) como campos obrigatórios. O campo `description` (descrição do destino) é opcional. Se não fornecido, a API gera automaticamente uma descrição usando a OpenAI API. Para usar essa função, configure sua chave de API da OpenAI na variável de ambiente OPENAI_API_KEY. Retorna um JSON com os dados do destino criado.

**Exemplo de Requisição:**

```json
{
   "name": "Paris",
   "photoPath": "caminho/para/imagem-paris.jpg",
   "photoPath2": "caminho/para/imagem-paris2.jpg",
   "meta": "A cidade do amor",
   "price": "500"
}
```

**Exemplo de Resposta:**

```json
{
   "id": 1,
   "name": "Paris",
   "photoPath": "caminho/para/imagem-paris.jpg",
   "photoPath2": "caminho/para/imagem-paris2.jpg",
   "meta": "A cidade do amor",
   "description": "Paris é uma cidade romântica, conhecida como a cidade do amor. Com suas icônicas ruas de paralelepípedos, cafés charmosos e monumentos mundialmente famosos, como a Torre Eiffel e o Museu do Louvre, Paris cativa os corações dos visitantes.",
   "price": "500"
}
```
### Consulta de Destino por Nome

`GET /destinos?name={nome}`

Retorna todos os destinos que correspondem ao nome informado como parâmetro no endpoint. Os destinos serão retornados como uma lista JSON no corpo da resposta.

**Exemplo de Resposta:**

```json
[
    {
        "id": 2,
        "name": "Nova Iorque",
        "photoPath": "caminho/para/imagem-nova-iorque.jpg",
        "photoPath2": "caminho/para/imagem-nova-iorque2.jpg",
        "meta": "A cidade que nunca dorme",
        "description": "Nova Iorque, a cidade que nunca dorme, é um dos destinos mais vibrantes do mundo. Com arranha-céus deslumbrantes, vida noturna agitada e uma variedade cultural incrível, é uma experiência inesquecível para qualquer viajante.",
        "price": "800"
    },
    {
        "id": 3,
        "name": "Nova Orleans",
        "photoPath": "caminho/para/imagem-nova-orleans.jpg",
        "photoPath2": "caminho/para/imagem-nova-orleans2.jpg",
        "meta": "A joia do sul",
        "description": "Nova Orleans, também conhecida como 'A joia do sul', é famosa por sua rica herança cultural, música vibrante, arquitetura única e, claro, sua culinária extraordinária. Uma visita a Nova Orleans é uma jornada através de uma mistura fascinante de influências culturais.",
        "price": "700"
    }
]
```

### Consulta de Destino por ID

`GET /destinos/{id}`

Retorna o destino correspondente ao ID fornecido. Será retornado como um objeto JSON no corpo da resposta. É usado para retornar o objeto criado em requisições POST e PUT no endpoint `/destinos`.

**Exemplo de Resposta:**

```json
{
   "id": 1,
   "name": "Vale dos Reis",
   "photoPath": "caminho/imagem-vale-dos-reis.jpg",
   "photoPath2": "caminho/imagem-vale-dos-reis2.jpg",
   "meta": "Explorando a história antiga",
   "description": "Descubra o fascinante Vale dos Reis, um lugar repleto de túmulos e monumentos antigos. Uma experiência única para os amantes da história.",
   "price": "500"
}
```

### Atualização de Destino

`PUT /destinos`

Permite a atualização dos campos `name` (nome do destino), `photoPath` (URL da foto do destino), `photoPath2` (URL da segunda foto do destino), `meta` (meta do destino), `description` (descrição do destino) e `price` (preço do destino) do destino correspondente ao ID informado no objeto JSON enviado no corpo da requisição. Retorna um JSON com os dados do destino atualizado.

**Exemplo de Requisição:**

```json
{
   "name": "Vale dos Reis",
   "description": "Atualizando a descrição do Vale dos Reis."
}
```

**Exemplo de Resposta:**

```json
{
   "id": 1,
   "name": "Vale dos Reis",
   "photoPath": "caminho/imagem-vale-dos-reis.jpg",
   "photoPath2": "caminho/imagem-vale-dos-reis2.jpg",
   "meta": "Explorando a história antiga",
   "description": "Atualizando a descrição do Vale dos Reis.",
   "price": "500"
}

```
### Remoção de Destino

`DELETE /destinos/{id}`

Permite excluir o destino correspondente ao ID fornecido.

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
