package ru.bot.tgbotstashtasks.services;

import org.springframework.stereotype.Service;
import ru.bot.tgbotstashtasks.models.User;
import ru.bot.tgbotstashtasks.repository.UserRepository;

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

    public User getUser(Long user_id){
        User user = new User();
        if(userRepository.findById(user_id).isPresent()){
            user = userRepository.findById(user_id).get();
        }
        else{
            userRepository.save(new User(user_id));
        }
        return user;
    }
}
