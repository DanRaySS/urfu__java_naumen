package ru.bot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bot.models.Tag;
import ru.bot.models.User;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag,Long> {
    List<Tag> findByUser(User user);
}
