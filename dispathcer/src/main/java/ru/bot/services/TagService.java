package ru.bot.services;

import org.springframework.stereotype.Service;
import ru.bot.models.Tag;
import ru.bot.models.Task;
import ru.bot.models.User;
import ru.bot.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class TagService {
    protected final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    public List<Tag> getAllTags(User users){
        return new ArrayList<>(tagRepository.findByUser(users));
    }

    public void addTag(User user, String name, boolean deletable){
        Tag newTag = new Tag(user,name,deletable);
        tagRepository.save(newTag);
    }
    public void delTag(Long id){
        tagRepository.deleteById(id);
    }
    public  void init(User user){
        Tag newTag = new Tag(user,"❗Важно",false);
        tagRepository.save(newTag);
    }
    public Tag getTagBySummary(String summary){
        return  tagRepository.findByName(summary);
    }
}
