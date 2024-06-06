package ru.bot.services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.bot.models.Tag;
import ru.bot.models.Task;
import ru.bot.models.User;
import ru.bot.repository.TagRepository;
import ru.bot.repository.TaskRepository;
import ru.bot.repository.UserRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TaskService {
    protected final TaskRepository taskRepository;
    protected final UserRepository userRepository;
    protected final TagRepository tagRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;

        this.tagRepository = tagRepository;
    }

    public void createTask(String summary, String description, Long id, Tag tag){
        Long time = System.currentTimeMillis() / 1000L;
        Task task = new Task(userRepository.findById(id).get(),time,summary,description,tag);
        taskRepository.save(task);
    }
    public void delTaskById(Long user_id, Long task_id){
        User user = new User();
        if(userRepository.findById(user_id).isPresent()){
            user = userRepository.findById(user_id).get();
        }
        else{
            userRepository.save(new User(user_id));
        }
        List<Task> temp = new ArrayList<>(taskRepository.findByUsers(user));
        Task task = temp.get((int) (task_id-1));
        taskRepository.deleteById(task.getId());

    }
    public String getAllTasks(Long user_id){
        User user = new User();
        if(userRepository.findById(user_id).isPresent()){
            user = userRepository.findById(user_id).get();
        }
        else{
            userRepository.save(new User(user_id));
        }
        List<Task> temp = new ArrayList<>(taskRepository.findByUsers(user));
        StringBuilder tempS = new StringBuilder();
        for (int i = 0; i< temp.size(); i++) {
            tempS.append(Integer.toString(i+1)).append(". ").append(temp.get(i).toString()).append("\n");
        }
        return tempS.toString();
    }
    public String getTaskById(Long taskId, Long user_id){
        User user = new User();
        if(userRepository.findById(user_id).isPresent()){
            user = userRepository.findById(user_id).get();
        }
        else throw new RuntimeException("No Such User");

        List<Task> temp = new ArrayList<>(taskRepository.findByUsers(user));
        Task task = temp.get(Math.toIntExact(taskId)-1);
        Date time=new java.util.Date((long)task.getStart_date()*1000);
        SimpleDateFormat dateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate= dateFor.format(time);
        String tempS = String.format("%s\n\n%s\n\n%s\n\n%s",task.getSummary(),task.getDescription(),stringDate,task.getTag());

        return tempS;
    }
}
