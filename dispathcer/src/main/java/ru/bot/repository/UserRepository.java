package ru.bot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bot.models.User;

public interface UserRepository extends CrudRepository<User,Long> {
}
