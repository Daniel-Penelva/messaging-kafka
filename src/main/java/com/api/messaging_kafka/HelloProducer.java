package com.api.messaging_kafka;

import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class HelloProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;    // O tipo KafkaTemplate é usado para enviar mensagens kafka.  Tanto a chave quanto o valor das mensagens são do tipo Strings.

    public HelloProducer(KafkaTemplate<String, String> kafkaTemplate) {  // Construtor para injeção de dependência
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {           // esse método envia uma mensagem ao Kafka.
        kafkaTemplate.send("hello-topic", message);
    }

}


/* Detalhamento do método sendMessage():
 * 
 * Primeiro String: Representa o tipo da chave da mensagem.
 * Segundo String: Representa o tipo do valor da mensagem.
 * 
 *  -> Chave da Mensagem (String): No Kafka, cada mensagem pode ter uma chave associada. Essa chave é usada para determinar a partição em que a 
 *     mensagem será armazenada. No caso de KafkaTemplate<String, String>, a chave é do tipo String.
 * 
 *  -> Valor da Mensagem (String): Este é o conteúdo da mensagem que você está enviando para o Kafka. No caso de KafkaTemplate<String, String>, 
 *     o valor da mensagem é do tipo String.
 * 
 * No Contexto do Código:
 *  -> Quando chama kafkaTemplate.send("hello-topic", message), o "hello-topic" é o nome do tópico onde a mensagem será enviada.
 *  -> message é o valor da mensagem que será enviado.
 *  -> Vale ressaltar que a chave da mensagem não é explicitamente definida aqui, então será null por padrão. No entanto, se quisesse enviar uma 
 *     chave específica junto com a mensagem poderia usar um dos métodos send que aceitam uma chave como argumento.
 * 
 *          public void sendMessageWithKey(String key, String message) {
 *              kafkaTemplate.send("hello-topic", key, message);
 *          }
 * 
 * Neste caso:
 *  -> key seria a chave da mensagem.
 *  -> message seria o valor da mensagem.
*/