# Implementação de uma API de cadastro de pessoas utilizando os princípios da Clean Architecture

Este repositório apresenta um API de cadastro de pessoas desenvolvido seguindo os princípios da ***Clean Architecture***. O objetivo aqui é me ajudar na consolidação dos conceitos que venho estudando sobre esse tema de arquitetura limpa.

Adicionalmente, este estudo dirigido se apoia na proposta deste vídeo: [Desenvolvendo API com Arquitetura Limpa: Aprenda na Prática!](https://www.youtube.com/watch?v=MsskoOicoQo).

Para uma leitura mais aprofundada do tema, você pode acessar a publicação original do autor em [The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).

## Uma breve revisão sobre a Clean Architecture

Esta é a famosa imagem que o autor traz em sua publicação:
<div align="center">
  <img src="https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg" alt="The Clean Architecture">
</div>

A ideia que a imagem acima apresenta é a <mark>divisão do software em camadas</mark> para separar as responsabilidades, com o intuito de se construir um software que contém as seguintes características:
- Independente de framework;
- Testável;
- Independente de UI;
- Independente de banco de dados;
- Independente de qualquer outro componente externo.

Algumas observações que gostaria de colocar:
- A quantidade de camadas acima é apenas uma sugestão, portando devemos avaliar à luz do contexto que estivermos escrevendo a aplicação;
- Não existe um passo a passo que nos guia do início ao fim para então julgarmos: isto é *clean architecture*, isso não é. Na verdade, encontramos por aí muitas variações, já que estão sujeitas ao contexto de negócio e à interpretação do desenvolvedor. A *Hexagonal Architecture* e a *Onion Architecture* são exemplos de interpretações que resultam numa arquitetura limpa. Apesar das variações a regra mais importante a ser seguida é a <mark>regra da dependência</mark>, que falaremos um pouco mais à frente.

### A Regra da Dependência

As setas da imagem acima representa a regra da dependência que diz: <mark>camadas mais internas não devem ter dependência de camadas mais externas</mark>. Trazendo um exemplo para ajudar no entendimento, um *repository* da JPA, que pela divisão faz parte da camada *Frameworks & Drivers*, nunca deveria estar sendo importado dentro de um *Use Case*.

### Uma breve definição de alguns conceitos

**Entidades (_Entities_)**:
> No contexto de grandes empresas, as entidades são objetos que contém <mark>regras de negócio abrangentes da organização</mark>. Em projetos simples como o deste repo, nós normalmente vemos classes que representam um POJO (*Plain Old Java Object*).

**Casos de Uso (_Use Cases_)**:
> É onde são implementadas as regras de negócio da aplicação.

**Adaptadores de Interfaces (_Interface Adapters_)**
> Camada que converte os dados de forma mais adequada de algum componente externo como bancos de dados, WEB, para os casos de uso e entidades, e vice-versa.

**Frameworks e Drivers**
> Camada onde ficam os detalhes de implementação de banco de dados, WEB, e o Framework. Detalhes do Framework Spring, por exemplo, só deveriam aparecer aqui.


## Entendendo a API

À luz dos conceitos acima, vamos tentar identificar os elementos da arquitetura limpa no desenvolvimento desta aplicação.

A API possui apenas dois endpoints: um para cadastrar pessoa e outro para listar todas as pessoas cadastradas. Então aqui vai algumas observações:
- Não há *swagger* disponível. Os endpoints podem ser utilizados e seus contratos conhecidos utilizando a *collection do postman* disponível na pasta `postman-collection`;
- O código fonte está na pasta `person-api`;
- Não há nenhuma validação dos dados de entrada;
- O banco de dados utilizado aqui é o H2, podendo ser acessado pelo console em `http://localhost:8080/h2-console`;
- O projeto requer Java 21 ou superior para ser executado.


### Estrutura de pacotes

```
com.rochards.registerapi.person
|
|-- core
|   |-- entities
|   |   |-- Person.java
|   |-- usecases
|   |   |-- CreatePersonUseCase.java
|   |   |-- CreatePersonUseCaseImpl.java
|   |   |-- FindAllPersonUseCase.java
|   |   |-- FindAllPersonUseCaseImpl.java
|   |-- enums
|   |   |-- PersonType.java
|   |-- exceptions
|   |   |-- BusinessException.java
|   |-- gateways
|   |   |-- PersonGateway.java
|-- infrastructure
|   |-- configuration
|       |-- BeansConfig.java
|   |-- controllers
|   |   |-- CreatePersonController.java
|   |   |-- FindPersonController.java
|   |-- dtos
|   |   |-- PersonRequest.java
|   |   |-- PersonResponse.java
|   |-- gateways
|   |   |- PersonRepositoryGateway.java
|   |-- persistence
|   |   |- PersonModel.java
|   |   |- PersonRepository.java
|-- CleanArchPersonApiApplication.java
```

#### Analisando a partir do pacote base `core`

O pacote base `core` concentra as regras de negócio da aplicação. Dois elementos da figura acima podem ser facilmente notados dentro dos pacotes`entites`e `usecases`. Os demais pacotes também fazem parte da regra negócio. 

Um atenção especial ao pacote `gateways`: este não representa um elemento da camada de *Interface Adapters*, mas é apenas para favorecer a inversão de dependência, uma vez que nossos casos de uso não devem importar classes com implementações concretas dessas operações. Elementos de camadas mais externas vão de fato prover implementações concretas para as interfaces declaradas aqui.

O que podemos afirmar com certeza é que nenhuma classe do pacote base `core` está importando algo de alguma camada mais externa, <mark>respeitando assim a regra da dependência</mark>.

#### Analisando a partir do pacote base `infrastructure`

Com o entendimento atual que tenho da proposta da arquitetura limpa, creio que este pacote não está estruturado de uma forma a respeitar as camadas sugeridas pelo autor. Por exemplo, os *controllers* estão claramente dependendo de anotações do *Framework Spring*. Particularmente não vejo mal algum esse cruzamente de dependências acontecendo nesta chamada.


## Contribuindo

Se possui dúvidas, críticas ou sugestões sobre o texto apresentado aqui, sinta-se livre para abrir um *issue*.