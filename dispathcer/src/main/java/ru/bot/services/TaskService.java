package ru.bot.services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.bot.models.Tag;
import ru.bot.models.Task;
import ru.bot.models.User;
import ru.bot.repository.TaskRepository;
import ru.bot.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {
    protected final TaskRepository taskRepository;
    protected final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public void createTask(String summary, String description, Long id, List<Tag> tag){
        Long time = System.currentTimeMillis() / 1000L;
        Task task = new Task(userRepository.findById(id).get(),time,summary,description,tag);
        taskRepository.save(task);
    }
    public void delTaskById(Long id){
        taskRepository.deleteById(id);
    }
    public List<String> getAllTasks(Long user_id){
        User user = new User();
        if(userRepository.findById(user_id).isPresent()){
            user = userRepository.findById(user_id).get();
        }
        else throw new RuntimeException("No Such User");

        List<Task> temp = new ArrayList<>(taskRepository.findByUsers(user));

        return temp.stream().map(Objects::toString).toList();
    }
}
