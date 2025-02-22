# CertificaUTF BackEnd

Projeto de oficina de software voltado para emissão de certificados de maneira online.

### Variaveis de ambiente

Aqui está a tabela com exemplos para os valores que estavam em branco:

| Variável             | Descrição                                | Exemplo |
|----------------------|------------------------------------------|---------|
| `BROKER_EMAIL`       | E-mail padrão do broker                 | `default.email` |
| `GOOGLE_CLIENTE_ID`  | ID do Cliente do Google                 | `1234567890-abc123def456.apps.googleusercontent.com` |
| `MONGO_URL`          | URL de conexão com o MongoDB            | `mongodb://localhost:27017/meu-banco` |
| `RABITMQ_ADDRESS`    | URL dos servidores RabbitMQ             | `amqps://user:password@rabbitmq.example.com/vhost` |
| `TOKEN_SECURITY_JWT` | Token de segurança para autenticação JWT | `BATATINHA123` |
| `UTFPR_BASEURL`      | URL base dos serviços da UTFPR          | `https://coensapp.dv.utfpr.edu.br/siacoes/service` |

## Scripts nescessários

No repositório do seu projeto, você pode executar:

### `mvnw clean install`

Limpa todas as dependências e baixa elas novamente, e compila o código.\
Além de gerar a pasta "target" com o artefato do projeto.

Utilizado na hora que termina de clonar o projeto do git ou quando teve altração nas dependências.

### `mvnw compile`

Apenas compila o código.

Utilizado quando a alteação apenas no código e não nas dependências. 

### `mvnw spring-boot:run`

Roda a aplicação.

Deve ser utilizado de pois de ter executado o camando "mvnw compile" ou "mvnw clean install"
