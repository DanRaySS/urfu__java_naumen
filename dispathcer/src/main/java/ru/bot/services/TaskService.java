package ru.bot.services;

import ru.bot.models.Tag;
import ru.bot.models.Task;
import ru.bot.models.User;
import ru.bot.repository.TaskRepository;
import ru.bot.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    protected final TaskRepository taskRepository;
    protected final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public void createTask(String summary, String description, User user, List<Tag> tag){
        Long time = System.currentTimeMillis() / 1000L;
        Task task = new Task(user,time,summary,description,tag);
    }
    public void delTaskById(Long id){
        taskRepository.deleteById(id);
    }
    public List<Task> getAllTasks(Long user_id){
        return new ArrayList<>(taskRepository.findByUsers(userRepository.findById(user_id).get()));
    }
}
