package ru.bot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bot.models.Tag;
import ru.bot.models.Task;
import ru.bot.models.User;

import java.util.List;
@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {
    List<Task> findByUsers(User users);
}

