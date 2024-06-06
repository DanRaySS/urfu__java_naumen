package ru.bot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bot.models.Tag;
import ru.bot.models.Task;
import ru.bot.models.User;

import java.util.List;
@Repository
public interface TagRepository extends CrudRepository<Tag,Long> {
    Tag findByName(String summary);
    List<Tag> findByUser(User user);
}
