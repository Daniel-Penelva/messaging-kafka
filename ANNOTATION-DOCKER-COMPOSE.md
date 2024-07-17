# Docker Compose 

Este arquivo de configuração do Docker Compose define um ambiente com dois serviços: Zookeeper e Kafka, ambos usando imagens da Confluent.

### Estrutura Geral do Arquivo

```yaml
version: '2'
services:
  zookeeper:
    ...
  kafka:
    ...
```

#### Versão
- `version: '2'`: Define a versão do Docker Compose. A versão 2 é uma escolha mais antiga, mas ainda amplamente suportada.

### Serviço Zookeeper

```yaml
  zookeeper:
    hostname: zookeeper
    image: confluentinc/cp-zookeeper:latest
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000
    ports:
      - 22181:2181
```

- `hostname: zookeeper`: Define o nome do host para o container do Zookeeper.
- `image: confluentinc/cp-zookeeper:latest`: Utiliza a imagem `confluentinc/cp-zookeeper` na versão mais recente.
- `environment`: Define variáveis de ambiente:
  - `ZOOKEEPER_CLIENT_PORT: 2181`: Porta que o Zookeeper usará para se comunicar com os clientes.
  - `ZOOKEEPER_TICK_TIME: 2000`: Configura o tempo de tick (em milissegundos) do Zookeeper.
- `ports: - 22181:2181`: Mapeia a porta 2181 do container para a porta 22181 no host.

### Serviço Kafka

```yaml
  kafka:
    hostname: kafka
    image: confluentinc/cp-kafka:latest
    depends_on:
      - zookeeper
    ports:
      - 9092:9092
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092,PLAINTEXT_HOST://kafka:29092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
```

- `hostname: kafka`: Define o nome do host para o container do Kafka.
- `image: confluentinc/cp-kafka:latest`: Utiliza a imagem `confluentinc/cp-kafka` na versão mais recente.
- `depends_on: - zookeeper`: Especifica que o Kafka só será iniciado após o Zookeeper estar em execução.
- `ports: - 9092:9092`: Mapeia a porta 9092 do container para a porta 9092 no host.
- `environment`: Define variáveis de ambiente para configurar o Kafka:
  - `KAFKA_BROKER_ID: 1`: Identificador do broker Kafka.
  - `KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181`: Conexão do Kafka ao Zookeeper.
  - `KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092,PLAINTEXT_HOST://kafka:29092`: Define os listeners do Kafka.
  - `KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT`: Mapeia os protocolos de segurança.
  - `KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT`: Define o listener usado para comunicação entre brokers.
  - `KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1`: Define o fator de replicação do tópico de offsets.

### Resumo

Esta configuração cria um ambiente Docker com Zookeeper e Kafka, permitindo que execute uma infraestrutura básica para sistemas que dependem dessas tecnologias. É uma configuração mínima e simples para fins de desenvolvimento ou teste.