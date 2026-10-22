package iuh.fit.springdatamongodb.config;

import iuh.fit.springdatamongodb.model.User;
import iuh.fit.springdatamongodb.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                List<User> initialUsers = List.of(
                        new User(null, "admin1", "123", "ADMIN"),
                        new User(null, "admin2", "123", "ADMIN"),
                        new User(null, "user1", "111", "USER"),
                        new User(null, "user2", "222", "USER"),
                        new User(null, "guest1", "333", "GUEST"),
                        new User(null, "guest2", "444", "GUEST")
                );
                userRepository.saveAll(initialUsers);
            }
        };
    }
}