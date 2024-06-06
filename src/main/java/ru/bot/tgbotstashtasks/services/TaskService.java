package ru.bot.tgbotstashtasks.services;

import org.springframework.stereotype.Service;
import ru.bot.tgbotstashtasks.models.Tag;
import ru.bot.tgbotstashtasks.models.Task;
import ru.bot.tgbotstashtasks.models.User;
import ru.bot.tgbotstashtasks.repository.TagRepository;
import ru.bot.tgbotstashtasks.repository.TaskRepository;
import ru.bot.tgbotstashtasks.repository.UserRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    protected final TaskRepository taskRepository;
    protected final UserRepository userRepository;
    protected final TagRepository tagRepository;
    protected final UsersService usersService;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, TagRepository tagRepository, UsersService usersService) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;

        this.usersService = usersService;
    }

    public void createTask(String summary, String description, Long id, Tag tag){
        Long time = System.currentTimeMillis() / 1000L;
        Task task = new Task(userRepository.findById(id).get(),time,summary,description,tag);
        taskRepository.save(task);
    }
    public void delTaskById(Long user_id, Long task_id){
        User user = usersService.getUser(user_id);
        List<Task> temp = new ArrayList<>(taskRepository.findByUsers(user));
        Task task = temp.get((int) (task_id-1));
        taskRepository.deleteById(task.getId());

    }
    public String getAllTasks(Long user_id){
        User user = usersService.getUser(user_id);
        List<Task> temp = new ArrayList<>(taskRepository.findByUsers(user));
        StringBuilder tempS = new StringBuilder();
        for (int i = 0; i< temp.size(); i++) {
            tempS.append(Integer.toString(i+1)).append(". ").append(temp.get(i).toString()).append("\n");
        }
        return tempS.toString();
    }
    public String getTaskById(Long taskId, Long user_id){
        User user = usersService.getUser(user_id);

        List<Task> temp = new ArrayList<>(taskRepository.findByUsers(user));
        Task task = temp.get(Math.toIntExact(taskId)-1);
        Date time=new java.util.Date((long)task.getStart_date()*1000);
        SimpleDateFormat dateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate= dateFor.format(time);
        String tempS = String.format("%s\n\n%s\n\n%s\n\n%s","Название: " + task.getSummary(),"Описание: " + task.getDescription(),"Время создания: " +  stringDate,"Тег: " + task.getTag());

        return tempS;
    }
}
