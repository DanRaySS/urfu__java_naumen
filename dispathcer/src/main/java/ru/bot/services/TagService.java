package ru.bot.services;

import org.springframework.stereotype.Service;
import ru.bot.models.Tag;
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

    public void addTag(User user, String name, int color, boolean deletable){
        Tag newTag = new Tag(user,name,deletable,color);
        tagRepository.save(newTag);
    }
    public void delTag(Long id){
        tagRepository.deleteById(id);
    }
}
