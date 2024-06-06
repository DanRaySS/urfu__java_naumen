package ru.bot.tgbotstashtasks.services;

import org.springframework.stereotype.Service;
import ru.bot.tgbotstashtasks.models.Tag;
import ru.bot.tgbotstashtasks.models.Task;
import ru.bot.tgbotstashtasks.models.User;
import ru.bot.tgbotstashtasks.repository.TagRepository;
import ru.bot.tgbotstashtasks.repository.TaskRepository;
import ru.bot.tgbotstashtasks.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class TagService {
    protected final TagRepository tagRepository;
    protected final TaskRepository taskRepository;
    protected final UserRepository userRepository;
    protected final UsersService usersService;

    public TagService(TagRepository tagRepository, TaskRepository taskRepository, UserRepository userRepository, UsersService usersService) {
        this.tagRepository = tagRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.usersService = usersService;
    }
    public String getAllTagsName(Long user_id){
        User user = usersService.getUser(user_id);
        List<Tag> temp = new ArrayList<>(tagRepository.findByUser(user));
        StringBuilder tempS = new StringBuilder();
        for (int i = 0; i< temp.size(); i++) {
            tempS.append(Integer.toString(i+1)).append(". ").append(temp.get(i).toString()).append("\n");
        }
        return tempS.toString();
    }

    public void addTag(Long user_id, String name, boolean deletable){
        Tag newTag = new Tag(userRepository.findById(user_id).get(),name,deletable);
        tagRepository.save(newTag);
    }
    public Tag getTagBySummary(String summary){
        return  tagRepository.findByName(summary);
    }
}
