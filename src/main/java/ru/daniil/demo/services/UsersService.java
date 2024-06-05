package ru.daniil.demo.services;

import org.springframework.stereotype.Service;
import ru.bot.models.User;
import ru.bot.repository.UserRepository;

@Service
public class UsersService {
    final UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(Long id){
        User user = new User(id);
        userRepository.save(user);
    }
}
