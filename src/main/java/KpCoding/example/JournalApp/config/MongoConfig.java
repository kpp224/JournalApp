package KpCoding.example.JournalApp.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        // Reads from environment variable: MONGODB_URI
        return MongoClients.create(System.getenv("MONGODB_URI"));
    }
}
