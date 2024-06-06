package ru.bot.services;

import org.springframework.stereotype.Service;
import ru.bot.models.Tag;
import ru.bot.models.Task;
import ru.bot.models.User;
import ru.bot.repository.TagRepository;
import ru.bot.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class TagService {
    protected final TagRepository tagRepository;
    protected final UserRepository userRepository;

    public TagService(TagRepository tagRepository, UserRepository userRepository) {
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
    }
    public String getAllTagsName(Long user_id){
        User user = new User();
        if(userRepository.findById(user_id).isPresent()){
            user = userRepository.findById(user_id).get();
        }
        else{
            userRepository.save(new User(user_id));
        }
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
    public void delTag(Long id){
        tagRepository.deleteById(id);
    }

    public Tag getTagBySummary(String summary){
        return  tagRepository.findByName(summary);
    }
}
