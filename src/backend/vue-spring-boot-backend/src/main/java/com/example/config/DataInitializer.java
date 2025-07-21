package com.example.config;

import com.example.entity.User;
import com.example.entity.Micropost;
import com.example.repository.UserRepository;
import com.example.repository.MicropostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private MicropostRepository micropostRepository;
    
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User user1 = new User("Alice Johnson");
            User user2 = new User("Bob Smith");
            User user3 = new User("Carol Davis");
            
            user1 = userRepository.save(user1);
            user2 = userRepository.save(user2);
            user3 = userRepository.save(user3);
            
            micropostRepository.save(new Micropost("Hello World! This is my first post.", user1.getId()));
            micropostRepository.save(new Micropost("Learning Spring Boot is fun!", user1.getId()));
            micropostRepository.save(new Micropost("Vue.js and Spring Boot make a great combination.", user2.getId()));
            micropostRepository.save(new Micropost("Building full-stack applications with modern tools.", user3.getId()));
            micropostRepository.save(new Micropost("SQLite is perfect for development and prototyping.", user2.getId()));
        }
    }
}
