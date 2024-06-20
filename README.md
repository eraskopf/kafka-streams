# Documentação Técnica: Escolha entre Banco de Dados Relacional (MySQL) e Banco de Dados NoSQL (MongoDB)

## Visão Geral

Esta documentação fornece uma análise detalhada das vantagens e desvantagens de bancos de dados relacionais (utilizando MySQL como exemplo) e bancos de dados NoSQL (utilizando MongoDB como exemplo). Ela também sugere casos de uso apropriados para cada tipo de banco de dados, ajudando a orientar a escolha entre eles com base nos requisitos específicos da aplicação.

## Banco de Dados Relacional (MySQL)

### Prós

1. **Estruturação de Dados:**
    - Adequado para dados altamente estruturados e relacionados.
    - Suporte a esquemas rigorosos, garantindo integridade e organização dos dados.

2. **Transações ACID:**
    - Garante consistência, atomicidade, isolamento e durabilidade nas transações.
    - Essencial para aplicações que exigem integridade de dados, como sistemas financeiros.

3. **Suporte a Consultas Complexas:**
    - Oferece recursos avançados de consulta, incluindo joins, agregações e subconsultas.
    - Facilita a extração de informações complexas e a criação de relatórios detalhados.

4. **Maturidade e Confiabilidade:**
    - Amplamente utilizado, com uma grande base de conhecimento e suporte da comunidade.
    - Ferramentas e documentação extensas disponíveis para suporte e desenvolvimento.

### Contras

1. **Escalabilidade Horizontal Limitada:**
    - Difícil de escalar horizontalmente devido a restrições de esquema e relacionamentos.
    - Pode exigir partições complexas ou sharding manual.

2. **Desempenho em Operações de Leitura/Escrita Massiva:**
    - Pode apresentar desempenho inferior em operações de leitura/escrita massiva.
    - Estruturação dos dados e modelo de transação podem ser um gargalo.

3. **Esquema Fixo:**
    - Dificulta a adaptação a mudanças nos requisitos de dados sem modificação de esquema.
    - Requer planejamento antecipado e gerenciamento de mudanças no esquema.

### Casos de Uso

1. **Aplicações com Dados Altamente Relacionados:**
    - Sistemas de gerenciamento de banco de dados, sistemas de CRM, sistemas de gerenciamento de conteúdo.

2. **Aplicações que Requerem Transações Complexas:**
    - Sistemas financeiros, sistemas de comércio eletrônico, sistemas de gerenciamento de pedidos.

## Banco de Dados NoSQL (MongoDB)

### Prós

1. **Escalabilidade Horizontal:**
    - Facilmente escalável horizontalmente para lidar com grandes volumes de dados e tráfego.
    - Arquitetura distribuída que suporta sharding automático.

2. **Flexibilidade de Esquema:**
    - Suporta esquemas dinâmicos e flexíveis.
    - Permite fácil adaptação a mudanças nos requisitos de dados sem necessidade de grandes modificações.

3. **Desempenho em Operações de Leitura/Escrita Massiva:**
    - Excelente desempenho em operações de leitura/escrita massiva.
    - Arquitetura sem esquema fixo facilita a manipulação de grandes volumes de dados.

4. **Modelo de Dados Denormalizado:**
    - Ideal para casos de uso onde a estrutura dos dados é fluida e não segue um padrão rígido de relacionamentos.
    - Permite a armazenagem de dados complexos em documentos únicos.

### Contras

1. **Consistência Eventual:**
    - Consistência eventual em vez de consistência imediata.
    - Pode resultar em dados inconsistentes em cenários de falha de rede.

2. **Suporte a Consultas Complexas Limitado:**
    - Recursos de consulta limitados em comparação com bancos de dados relacionais.
    - Operações de junção complexas são menos eficientes e mais difíceis de implementar.

3. **Menos Conhecimento e Ferramentas:**
    - Menos estabelecido do que bancos de dados relacionais.
    - Menos ferramentas e conhecimento disponíveis na comunidade.

### Casos de Uso

1. **Aplicações com Dados Semiestruturados/Desestruturados:**
    - Análise de big data, logística de IoT, gerenciamento de conteúdo da web.

2. **Aplicações que Requerem Escalabilidade e Flexibilidade:**
    - Redes sociais, aplicativos de jogos, análise de eventos em tempo real.

## Resumo

A escolha entre um banco de dados relacional (MySQL) e um banco de dados NoSQL (MongoDB) depende dos requisitos específicos da aplicação:

- **MySQL** é ideal para aplicações que exigem transações complexas, integridade de dados rigorosa e suporte avançado a consultas.
- **MongoDB** é mais adequado para aplicações que necessitam de escalabilidade horizontal, flexibilidade de esquema e desempenho em operações de leitura/escrita massiva.

Considerar a estrutura dos dados, padrões de acesso e necessidades de escalabilidade e flexibilidade são cruciais para tomar a decisão correta.

# Documentação Técnica: PriceItemTopicConsumer

## Visão Geral

O `PriceItemTopicConsumer` é um componente Spring responsável pelo consumo de mensagens de um tópico Kafka, processamento de dados em fluxo e publicação dos resultados processados em um tópico de saída. Este componente utiliza Kafka Streams para manipular os dados, aplicando operações de agregação em janelas de sessão.

## Estrutura do Código

### Pacotes Importados

- **Pacote Kafka Streams:**
    - `org.apache.kafka.streams.KeyValue`
    - `org.apache.kafka.streams.StreamsBuilder`
    - `org.apache.kafka.streams.kstream.*`

- **Pacote Spring:**
    - `org.springframework.context.annotation.Bean`
    - `org.springframework.stereotype.Component`

- **Outros:**
    - `lombok.RequiredArgsConstructor`
    - `lombok.extern.slf4j.Slf4j`
    - `java.time.Duration`
    - `java.util.ArrayList`
    - `java.util.List`

### Dependências

- **Kafka Configuration (`KafkaConfiguration`):** Classe responsável por fornecer as configurações dos tópicos Kafka.
- **Custom Serdes (`CustomSerdes`):** Serializadores e desserializadores personalizados para os DTOs usados.
- **DTOs (`PriceItemGloboApiDTO`, `PriceItemSalesForceDTO`):** Classes de transferência de dados usadas no processamento.

### Componentes e Anotações

- **`@Slf4j`:** Anotação do Lombok para injeção de um logger.
- **`@Component`:** Indica que a classe é um componente Spring.
- **`@RequiredArgsConstructor`:** Gera um construtor com argumentos obrigatórios para os atributos final.

## Método Principal

### `kStreamPriceItem`

#### Definição
```java
@Bean
public KStream<String, List<PriceItemSalesForceDTO>> kStreamPriceItem(StreamsBuilder kStreamBuilder)
```

#### Descrição
Este método cria e configura um stream Kafka para consumir, processar e publicar mensagens.

#### Processo

1. **Criação do Stream**
   ```java
   KStream<String, List<PriceItemSalesForceDTO>> kStream = kStreamBuilder.stream(kafkaConfiguration.getInputTopicPriceItem(), Consumed.with(Serdes.String(), CustomSerdes.PriceItemSalesForceDTO()));
   ```
   Inicializa um stream a partir do tópico de entrada configurado, utilizando uma chave `String` e um valor `PriceItemSalesForceDTO`.

2. **Configuração da Janela de Sessão**
   ```java
   Duration windowSize = Duration.ofSeconds(10);
   Duration gracePeriod = Duration.ofSeconds(5);
   SessionWindows sessionWindow = SessionWindows.ofInactivityGapAndGrace(windowSize, gracePeriod);
   ```
   Define uma janela de sessão com duração de 10 segundos e um período de tolerância de 5 segundos.

3. **Agrupamento e Agregação**
   ```java
   streamToPriceList.groupByKey(Grouped.with(Serdes.String(), CustomSerdes.PriceItemSalesForceDTO()))
       .windowedBy(sessionWindow)
       .aggregate(ArrayList::new,
           (key, value, aggregate1) -> {
               log.info("preparing record received {}", value);

               if (nonNull(value)) {
                   value.forEach(pl -> aggregate1.add(PriceItemGloboApiDTO.from(pl)));
               }

               return aggregate1;
           },
           (key, aggregate1, aggregate2) -> {
               aggregate1.addAll(aggregate2);
               return aggregate1;
           },
           Materialized.with(Serdes.String(), CustomSerdes.MessageList()))
       .toStream()
       .map((key, value) -> new KeyValue<>(key.key(), value))
       .to(kafkaConfiguration.getOutputTopicPriceItem(), Produced.with(Serdes.String(), CustomSerdes.MessageList()));
   ```
    - **`groupByKey`**: Agrupa os registros por chave.
    - **`windowedBy`**: Aplica uma janela de sessão para os grupos.
    - **`aggregate`**: Agrega os registros dentro das janelas, transformando `PriceItemSalesForceDTO` em `PriceItemGloboApiDTO` e acumulando-os em listas.
    - **`toStream`**: Converte o resultado agregado de volta para um stream.
    - **`to`**: Envia o stream processado para o tópico de saída.

#### Retorno
O método retorna o stream configurado, permitindo que o Spring Kafka Streams gerencie o fluxo de dados.

## Considerações Finais

- **Logs:** O método faz uso de logs para informar sobre o processamento de cada registro recebido.
- **Tratamento de Dados:** A transformação de `PriceItemSalesForceDTO` para `PriceItemGloboApiDTO` é feita dentro da agregação, garantindo que os dados sejam processados conforme necessário.
- **Resiliência:** A janela de sessão com período de tolerância adiciona robustez ao processamento de fluxo, acomodando pequenos atrasos ou interrupções.

Esta documentação fornece uma visão detalhada sobre a configuração e funcionamento do `PriceItemTopicConsumer`, facilitando a manutenção e evolução do componente.


# Documentação Técnica: PricingIndexItemTopicConsumer

## Visão Geral

O `PricingIndexItemTopicConsumer` é um componente Spring que consome mensagens de um tópico Kafka, processa esses dados em fluxo e publica os resultados em um tópico de saída. Este componente utiliza Kafka Streams para realizar operações de agregação em janelas de sessão.

## Estrutura do Código

### Pacotes Importados

- **Kafka Streams:**
    - `org.apache.kafka.streams.KeyValue`
    - `org.apache.kafka.streams.StreamsBuilder`
    - `org.apache.kafka.streams.kstream.*`

- **Spring:**
    - `org.springframework.context.annotation.Bean`
    - `org.springframework.stereotype.Component`

- **Outros:**
    - `lombok.RequiredArgsConstructor`
    - `lombok.extern.slf4j.Slf4j`
    - `java.time.Duration`
    - `java.util.ArrayList`
    - `java.util.List`

### Dependências

- **Kafka Configuration (`KafkaConfiguration`):** Classe que fornece as configurações dos tópicos Kafka.
- **Custom Serdes (`CustomSerdes`):** Serializadores e desserializadores personalizados para os DTOs utilizados.
- **DTOs (`PricingIndexItemGloboApiDTO`, `PricingIndexItemSalesForceDTO`):** Classes de transferência de dados usadas no processamento.

### Componentes e Anotações

- **`@Slf4j`:** Anotação do Lombok para injeção de um logger.
- **`@Component`:** Indica que a classe é um componente Spring.
- **`@RequiredArgsConstructor`:** Gera um construtor com argumentos obrigatórios para os atributos final.

## Método Principal

### `kStreamPricingIndexItem`

#### Definição
```java
@Bean
public KStream<String, List<PricingIndexItemSalesForceDTO>> kStreamPricingIndexItem(StreamsBuilder kStreamBuilder)
```

#### Descrição
Este método cria e configura um stream Kafka para consumir, processar e publicar mensagens relacionadas a itens de índice de preços.

#### Processo

1. **Criação do Stream**
   ```java
   KStream<String, List<PricingIndexItemSalesForceDTO>> kStream = kStreamBuilder.stream(kafkaConfiguration.getInputTopicPricingIndexItem(), Consumed.with(Serdes.String(), CustomSerdes.PricingIndexItemSalesForceDTO()));
   ```
   Inicializa um stream a partir do tópico de entrada configurado, utilizando uma chave `String` e um valor `PricingIndexItemSalesForceDTO`.

2. **Configuração da Janela de Sessão**
   ```java
   Duration windowSize = Duration.ofSeconds(10);
   Duration gracePeriod = Duration.ofSeconds(5);
   SessionWindows sessionWindow = SessionWindows.ofInactivityGapAndGrace(windowSize, gracePeriod);
   ```
   Define uma janela de sessão com duração de 10 segundos e um período de tolerância de 5 segundos.

3. **Agrupamento e Agregação**
   ```java
   kStream.groupByKey(Grouped.with(Serdes.String(), CustomSerdes.PricingIndexItemSalesForceDTO()))
       .windowedBy(sessionWindow)
       .aggregate(ArrayList::new,
           (key, value, aggregate1) -> {
               log.info("preparing record received {}", value);

               if (nonNull(value)) {
                   value.forEach(pl -> aggregate1.add(PricingIndexItemGloboApiDTO.from(pl)));
               }

               return aggregate1;
           },
           (key, aggregate1, aggregate2) -> {
               aggregate1.addAll(aggregate2);
               return aggregate1;
           },
           Materialized.with(Serdes.String(), CustomSerdes.MessageList()))
       .toStream()
       .map((key, value) -> new KeyValue<>(key.key(), value))
       .to(kafkaConfiguration.getOutputTopicPricingIndexItem(), Produced.with(Serdes.String(), CustomSerdes.MessageList()));
   ```
    - **`groupByKey`**: Agrupa os registros por chave.
    - **`windowedBy`**: Aplica uma janela de sessão para os grupos.
    - **`aggregate`**: Agrega os registros dentro das janelas, transformando `PricingIndexItemSalesForceDTO` em `PricingIndexItemGloboApiDTO` e acumulando-os em listas.
    - **`toStream`**: Converte o resultado agregado de volta para um stream.
    - **`to`**: Envia o stream processado para o tópico de saída.

#### Retorno
O método retorna o stream configurado, permitindo que o Spring Kafka Streams gerencie o fluxo de dados.

## Considerações Finais

- **Logs:** O método faz uso de logs para informar sobre o processamento de cada registro recebido.
- **Tratamento de Dados:** A transformação de `PricingIndexItemSalesForceDTO` para `PricingIndexItemGloboApiDTO` é feita dentro da agregação, garantindo que os dados sejam processados conforme necessário.
- **Resiliência:** A janela de sessão com período de tolerância adiciona robustez ao processamento de fluxo, acomodando pequenos atrasos ou interrupções.

Esta documentação fornece uma visão detalhada sobre a configuração e funcionamento do `PricingIndexItemTopicConsumer`, facilitando a manutenção e evolução do componente.

# Documentação Técnica: AdFormatTopicConsumer

## Visão Geral

O `AdFormatTopicConsumer` é um componente Spring que consome mensagens de um tópico Kafka, processa esses dados em fluxo e publica os resultados em um tópico de saída. Este componente utiliza Kafka Streams para realizar operações de agregação em janelas de sessão, transformando dados do formato `AdFormatSalesForceDTO` para `AdFormatGloboApiDTO`.

## Estrutura do Código

### Pacotes Importados

- **Kafka Streams:**
    - `org.apache.kafka.streams.KeyValue`
    - `org.apache.kafka.streams.StreamsBuilder`
    - `org.apache.kafka.streams.kstream.*`

- **Spring:**
    - `org.springframework.context.annotation.Bean`
    - `org.springframework.stereotype.Component`

- **Outros:**
    - `lombok.RequiredArgsConstructor`
    - `lombok.extern.slf4j.Slf4j`
    - `java.time.Duration`
    - `java.util.ArrayList`
    - `static java.util.Objects.nonNull`

### Dependências

- **Kafka Configuration (`KafkaConfiguration`):** Classe que fornece as configurações dos tópicos Kafka.
- **Custom Serdes (`CustomSerdes`):** Serializadores e desserializadores personalizados para os DTOs utilizados.
- **DTOs (`AdFormatGloboApiDTO`, `AdFormatSalesForceDTO`):** Classes de transferência de dados usadas no processamento.

### Componentes e Anotações

- **`@Slf4j`:** Anotação do Lombok para injeção de um logger.
- **`@Component`:** Indica que a classe é um componente Spring.
- **`@RequiredArgsConstructor`:** Gera um construtor com argumentos obrigatórios para os atributos `final`.

## Método Principal

### `kStreamAdFormat`

#### Definição
```java
@Bean
public KStream<String, AdFormatSalesForceDTO> kStreamAdFormat(StreamsBuilder kStreamBuilder)
```

#### Descrição
Este método cria e configura um stream Kafka para consumir, processar e publicar mensagens relacionadas a formatos de anúncio.

#### Processo

1. **Criação do Stream**
   ```java
   KStream<String, AdFormatSalesForceDTO> kStream = kStreamBuilder.stream(kafkaConfiguration.getInputTopicAdFormat(), Consumed.with(Serdes.String(), CustomSerdes.AdFormatSalesForceDTO()));
   ```
   Inicializa um stream a partir do tópico de entrada configurado, utilizando uma chave `String` e um valor `AdFormatSalesForceDTO`.

2. **Configuração da Janela de Sessão**
   ```java
   Duration windowSize = Duration.ofSeconds(5);
   Duration gracePeriod = Duration.ofSeconds(2);
   SessionWindows sessionWindow = SessionWindows.ofInactivityGapAndGrace(windowSize, gracePeriod);
   ```
   Define uma janela de sessão com duração de 5 segundos e um período de tolerância de 2 segundos.

3. **Agrupamento e Agregação**
   ```java
   kStream.groupByKey(Grouped.with(Serdes.String(), CustomSerdes.AdFormatSalesForceDTO()))
       .windowedBy(sessionWindow)
       .aggregate(ArrayList::new,
           (key, value, aggregate1) -> {
               log.info("preparing record received {}", value);

               if (nonNull(value)) {
                   aggregate1.add(AdFormatGloboApiDTO.from(value));
               }

               return aggregate1;
           },
           (key, aggregate1, aggregate2) -> {
               aggregate1.addAll(aggregate2);
               return aggregate1;
           },
           Materialized.with(Serdes.String(), CustomSerdes.MessageList()))
       .toStream()
       .map((key, value) -> new KeyValue<>(key.key(), value))
       .to(kafkaConfiguration.getOutputTopicAdFormat(), Produced.with(Serdes.String(), CustomSerdes.MessageList()));
   ```
    - **`groupByKey`**: Agrupa os registros por chave.
    - **`windowedBy`**: Aplica uma janela de sessão para os grupos.
    - **`aggregate`**: Agrega os registros dentro das janelas, transformando `AdFormatSalesForceDTO` em `AdFormatGloboApiDTO` e acumulando-os em listas.
    - **`toStream`**: Converte o resultado agregado de volta para um stream.
    - **`to`**: Envia o stream processado para o tópico de saída.

#### Retorno
O método retorna o stream configurado, permitindo que o Spring Kafka Streams gerencie o fluxo de dados.

## Considerações Finais

- **Logs:** O método utiliza logs para informar sobre o processamento de cada registro recebido.
- **Tratamento de Dados:** A transformação de `AdFormatSalesForceDTO` para `AdFormatGloboApiDTO` é feita dentro da agregação, garantindo que os dados sejam processados conforme necessário.
- **Resiliência:** A janela de sessão com período de tolerância adiciona robustez ao processamento de fluxo, acomodando pequenos atrasos ou interrupções.

Esta documentação fornece uma visão detalhada sobre a configuração e funcionamento do `AdFormatTopicConsumer`, facilitando a manutenção e evolução do componente.