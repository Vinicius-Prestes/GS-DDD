Global Solution 2025 - NexaWork: Plataforma de Upskilling/Reskilling
1. Descrição do Projeto
A NexaWork é uma API RESTful desenvolvida para atender ao desafio da Global Solution 2025 – O Futuro do Trabalho. O objetivo da plataforma é conectar profissionais às competências do futuro (2030+), facilitando processos de Requalificação (Reskilling) e Aperfeiçoamento (Upskilling).

A aplicação permite:

Cadastro de Usuários: Profissionais que buscam se preparar para o futuro.
Gerenciamento de Trilhas: Trilhas de aprendizagem focadas em competências tecnológicas e humanas.
Inscrições (Assignments): Associação de usuários a trilhas para acompanhamento de progresso.
O projeto está alinhado com os Objetivos de Desenvolvimento Sustentável (ODS) 4, 8, 9 e 10.

2. Tecnologias Utilizadas
Java: 21
Spring Boot: 3.5.7
Gerenciador de Dependências: Maven
Banco de Dados: Oracle (JDBC/JPA)
Documentação: SpringDoc OpenAPI (Swagger UI)
Segurança: Spring Security (Basic Auth)
3. Configuração e Instalação
Pré-requisitos
JDK 21 instalado.
Maven instalado (ou utilizar o wrapper ./mvnw).
Acesso ao banco de dados Oracle da FIAP (ou configuração de um banco local).
Passo a Passo
Clonar o repositório:

git clone <url-do-repositorio>
cd NexaWork
Configurar o Banco de Dados: O projeto já vem configurado para conectar ao Oracle da FIAP. Verifique o arquivo src/main/resources/application.properties:

spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521/orcl
spring.datasource.username=rm559097
spring.datasource.password=080503
spring.jpa.hibernate.ddl-auto=create
Nota: Para produção, recomenda-se o uso de variáveis de ambiente para credenciais.

Compilar e Executar:

# Linux/Mac
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
Acessar a Aplicação: A API estará rodando em http://localhost:8080.

4. Documentação da API (Swagger)
A documentação interativa está disponível através do Swagger UI. Lá é possível testar todos os endpoints diretamente pelo navegador.

URL do Swagger: http://localhost:8080/swagger-ui.html
URL da Docs (JSON): http://localhost:8080/api-docs
Autenticação: O projeto utiliza Basic Auth.

Usuário: admin
Senha: fiap
5. Exemplos de Uso (Endpoints)
Abaixo estão exemplos de como interagir com os principais recursos usando JSON.

5.1. Usuários (/user)
Criar Usuário (POST)

POST /user
{
  "name": "Maria Silva",
  "email": "maria.silva@email.com",
  "birthDate": "1995-05-20",
  "field": "Engenharia de Software"
}
Atualizar Usuário (PUT)

PUT /user/{id}
{
  "id": 1,
  "name": "Maria Silva Souza",
  "email": "maria.souza@email.com",
  "birthDate": "1995-05-20",
  "field": "Engenharia de Dados"
}
5.2. Trilhas (/trail)
Criar Trilha (POST)

POST /trail
{
  "name": "Inteligência Artificial 2030",
  "field": "Tecnologia",
  "description": "Curso completo sobre IA Generativa e Automação."
}
5.3. Inscrições (/assignment)
Inscrever Usuário em Trilha (POST)

POST /assignment
{
  "userId": 1,
  "trailId": 1,
  "status": "ACTIVE"
}
Status possíveis: ACTIVE, COMPLETED, CANCELED.

6. Como Testar
Você pode utilizar ferramentas como Postman, Insomnia ou cURL.

Exemplo com cURL (Listar Usuários):

curl -u admin:fiap http://localhost:8080/user
