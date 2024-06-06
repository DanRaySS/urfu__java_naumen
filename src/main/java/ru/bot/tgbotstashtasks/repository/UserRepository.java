package ru.bot.tgbotstashtasks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bot.tgbotstashtasks.models.User;
@Repository
public interface UserRepository extends CrudRepository<User,Long> {
}
