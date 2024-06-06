package ru.bot.tgbotstashtasks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bot.tgbotstashtasks.models.Task;
import ru.bot.tgbotstashtasks.models.User;

import java.util.List;
@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {
    List<Task> findByUsers(User users);
}

