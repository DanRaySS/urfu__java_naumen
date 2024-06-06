package ru.bot.tgbotstashtasks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bot.tgbotstashtasks.models.Tag;
import ru.bot.tgbotstashtasks.models.User;

import java.util.List;
@Repository
public interface TagRepository extends CrudRepository<Tag,Long> {
    Tag findByName(String summary);
    List<Tag> findByUser(User user);
}
