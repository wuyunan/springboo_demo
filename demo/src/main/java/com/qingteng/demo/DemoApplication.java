package com.qingteng.demo;

import com.qingteng.demo.service.SSHManager;
import com.qingteng.demo.storage.StorageProperties;
import com.qingteng.demo.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class DemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        logger.info("init");
//        sshCommand();
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }

    private void sshCommand() {
        String username = "root";
//        String password="wuyunan+";
//        String hostname="115.159.149.28";
        String password = "n2(MnF1zWcw-)*%f";
        String hostname = "149.28.49.208";

        SSHManager instance = new SSHManager(username, password, hostname, "");
        String errorMessage = instance.connect();

        if (errorMessage != null) {
            System.out.println(errorMessage);
        }
//        String command = "docker run -d -p 443:8989 oddrationale/docker-shadowsocks -s 0.0.0.0 -p 8989 -k wuyunan+ -m aes-256-cfb";
        String command = "ll";

        // call sendCommand for each command and the output
        //(without prompts) is returned
        String result = instance.sendCommand(command);
        // close only after all commands are sent
        instance.close();

        System.out.println(result);
    }

//  @Bean
//  CommandLineRunner initDatabase(BookRepository repository) {
//    return args -> {
//
//      Author author = new Author();
//      author.setName("Alibaba");
//      Book book = new Book();
//      List<Author> authors = new ArrayList<>();
//      authors.add(author);
//      book.setAuthors(authors);
//      book.setName("A Guide to the Bodhisattva Way of Life");
//      book.setPrice(new BigDecimal("15.41"));
//      repository.save(book);
//    };
//  }


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
