package com.qingteng.demo;

import com.qingteng.demo.entity.CreditCard;
import com.qingteng.demo.service.CreditCardService;
import com.qingteng.demo.storage.StorageProperties;
import com.qingteng.demo.storage.StorageService;
import com.qingteng.demo.utils.CreditCardUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {

        System.out.println("init");


        int dueDate = 13;
        int satementDate = 25;


        for (int i = 0; i < 365; i++) {
            CreditCardUtil.getInterestholiday(LocalDate.now().plusDays(i), satementDate, dueDate);
        }

        dueDate = 5;
        satementDate = 13;
        System.out.println("==============");


        for (int i = 0; i < 365; i++) {
            CreditCardUtil.getInterestholiday(LocalDate.now().plusDays(i), satementDate, dueDate);
        }


        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }


    static final String topicExchangeName = "spring-boot-exchange";

    static final String queueName = "spring-boot";

//    @Bean
//    Queue queue() {
//        return new Queue(queueName, false);
//    }
//
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange(topicExchangeName);
//    }
//
//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
//    }
//
//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//                                             MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(queueName);
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }
//
//    @Bean
//    MessageListenerAdapter listenerAdapter(Receiver receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }
}
