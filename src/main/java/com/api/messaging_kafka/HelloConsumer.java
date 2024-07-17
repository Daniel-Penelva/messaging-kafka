package com.api.messaging_kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HelloConsumer {

    @KafkaListener(topics = "hello-topic", groupId = "group-1")  // Anotação do Spring Kafka usada para marcar um método como um ouvinte de mensagens Kafka.
    public void sendMessage(String message) {                    // Método será chamado sempre que uma mensagem for recebida no tópico especificado. 
        System.out.println("Consumer Message: " + message);      // Processa a mensagem recebida
    }

}

/* Detalhamento:
 * 
 * @KafkaListener(topics = "hello-topic", groupId = "group-1"): Anotação do Spring Kafka que configura este método para ser um ouvinte de 
 * mensagens Kafka. Os parâmetros são:
 *      -> topics = "hello-topic": Especifica que este ouvinte deve escutar mensagens do tópico hello-topic.
 *      -> groupId = "group-1": Define o grupo de consumidores ao qual este ouvinte pertence. Todos os consumidores no mesmo grupo compartilham 
 *         o trabalho de consumo das mensagens de um tópico.
*/