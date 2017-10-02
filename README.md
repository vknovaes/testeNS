# testeNS

Para a arquitetura deste projeto, foram utilizados alé do JPA, o Hibernate para persistência e o Jersey para a camada de serviço.

A aplicação é construída em maven, portanto todas as dependências são baixadas na compilação do projeto.

O método de desenvolvimento envolveu criar uma camada de serviço que trabalha com REST utilizando JSON, repassando os objetos serializados para a persistência e serializando a resposta diretamente em um Json para devolução à tela.

O projeto está estruturado da seguinte forma:

TesteNS (root node)
  |
  |
  |
  rest (servlet principal)
  |
  |
  |
  |-------- campaign (serviço das campanhas)
  |             |
  |             |------ create, update, delete, all (busca todas), byteam (busca pelo id do time do coração)
  |             
  |             
  |
  |
  |
  |-------- user (serviço de usuário)
              |
              |------ create, campanhas (associa as campanhas ao usuário, pelo id do time)
              
  
  
  Então, por exemplo, se você quiser consultar todas as campanhas disponíveis, utilize a url (assumindo localhost):
  http://localhost:8080/TesteNS/rest/campaign/all
  
  Todos os serviços trabalham com JSON somente, e os objetos serializados são:
  socio (user):
  email - String
  nome - string
  dtNasc - java.sql.Date
  idTime - Long
  
  campaign (campanha):
  campaignId - Long
  teamId - Long
  campaignName - String
  campaignStartDate - java.sql.Date
  campaignEndDate - java.sql.Date
  
  Para implementação, é necessário tomcat 8.0, java 1.8, banco de dados mysql. deve-se acessar o arquivo persistence.xml, em src/META-INF, e trocar a string de conexão, usuário e senha. Devido à versatilidade do hibernate, o mysql não é necessário, apenas recomendado.
  Caso queria trocar de banco de dados, substituir também o dialect no persistence.xml pelo dialeto adequado. também é necessário maior configuração do arquivo log4j.properties, dependendo de como deseja "loggar" a aplicação.
  
