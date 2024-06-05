package ru.bot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bot.models.Tag;
import ru.bot.models.Task;
import ru.bot.models.User;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task,Long> {
    List<Task> findByUsers(User users);

}

