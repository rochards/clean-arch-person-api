# Implementação de uma API de cadastro de pessoas utilizando os princípios da Clean Architecture

> :building_construction: Work in progress...

Este repositório apresenta um API de cadastro de pessoas desenvolvido seguindo os princípios da ***Clean Architecture***. O objetivo aqui é me ajudar na consolidação dos conceitos que venho estudando sobre esse tema de arquitetura limpa.

Adicionalmente, este estudo dirigido se apoia na interpretação deste vídeo: [Desenvolvendo API com Arquitetura Limpa: Aprenda na Prática!](https://www.youtube.com/watch?v=MsskoOicoQo).

Para uma leitura mais aprofundado do tema, você pode acessar a publicação original do autor em [The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).

## Uma breve revisão sobre a Clean Architecture

Esta é a famosa imagem que o autor traz em sua publicação:
<div align="center">
  <img src="https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg" alt="The Clean Architecture">
</div>

A ideia que a imagem acima apresenta é a **divisão do software em camadas** para separar as responsabilidades, com o intuito de se construir um software que contém as seguintes características:
- Independente de framework;
- Testável;
- Independente de UI;
- Independente de banco de dados;
- Independente de qualquer outro componente externo.

Algumas observações que gostaria de colocar:
- A quantidade de camadas acima é apenas uma sugestão, portando devemos avaliar à luz do contexto que estivermos escrevendo a aplicação;
- Não existe um passo a passo que nos guia do início ao fim para então julgarmos: isto é *clean architecture*, isso não é. Na verdade, encontramos por aí muitas variações, já que estão sujeitas ao contexto de negócio e à interpretação do desenvolvedor. A *Hexagonal Architecture* e a *Onion Architecture* são exemplos de interpretações que resultam numa arquitetura limpa. Apesar das variações a regra mais importante a ser seguida é a **regra da dependência**, que falaremos um pouco mais à frente.

### A Regra da Dependência

As setas da imagem acima representa a regra da dependência que diz: **camadas mais internas não devem ter dependência de camadas mais externas**. Trazendo um exemplo para ajudar no entendimento, um *repository* da JPA, que pela divisão faz parte da camada *Frameworks & Drivers*, nunca deveria estar sendo importado dentro de um *Use Case*.


## Entendendo a API

À luz dos conceitos acima, vamos tentar identificar os elements da arquitetura limpa no desenvolvimento desta aplicação.

A API possui apenas dois endpoints: um para cadastrar pessoa e outro para listar todas as pessoas cadastradas. Então aqui vai algumas observações:
- Não há *swagger* disponível. Os endpoints podem ser utilizados e seus contratos conhecidos utilizando a *collection do postman* disponível na pasta `postman-collection`;
- Não há nenhuma validação dos dados de entrada;
- O banco de dados utilizado aqui é o H2;
- O projeto requer Java 21 ou superior para ser executado.

