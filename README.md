# API para o site turístico Jornada Milhas

<p align="center">
  <img src="https://img.shields.io/static/v1?label=spring&message=3.1.2&color=blue&style=for-the-badge&logo=SPRING"/>
  <img src="http://img.shields.io/static/v1?label=Java&message=17&color=red&style=for-the-badge&logo=JAVA"/>
  <img src="http://img.shields.io/static/v1?label=Maven&message=4.0.0&color=red&style=for-the-badge&logo=Apache%20Maven"/>
  <img src="http://img.shields.io/static/v1?label=TESTS&message=18%20passed&color=GREEN&style=for-the-badge"/>
   <img src="http://img.shields.io/static/v1?label=License&message=MIT&color=green&style=for-the-badge"/>
   <img src="http://img.shields.io/static/v1?label=STATUS&message=CONCLUIDO&color=GREEN&style=for-the-badge"/>
</p>

### Descrição do Projeto

Esse projeto consiste em uma *API REST* que realiza o backend de uma página Web de turismo chamada Jornada Milhas. A aplicação apresenta o *CRUD* de destinos e depoimentos conforme as regras de negócios solicitadas.
O mesmo foi desenvolvido em *Java* com auxílio do *Spring Framework*. Utilizei como banco de dados o *MySQL*, armazenando em tabelas as entidades Testimonial e Destination. Foi implementado também uma integração com a API da *OpenAI* para auxiliar na elaboração da descrição dos destinos através do *ChatGPT 3.5*. Os testes de unidade foram realizados através do *JUnit 5*, *MockMVC* e *Mockito*.

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

PUT ```/depoimentos/{id}```
Atualiza o depoimento cujo id for informado no endereço.

DELETE ```/depoimentos/{id}```
Exclui o depoimento com id informado no endereço.

## Licença

The [MIT License](https://github.com/rldcarvalho/projeto-jornada-milhas/blob/main/LICENSE) (MIT)

Copyright :copyright: 2023 - Api Jornada Milhas
