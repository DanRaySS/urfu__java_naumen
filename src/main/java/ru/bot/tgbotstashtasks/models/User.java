package ru.bot.tgbotstashtasks.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "users")
public class User {

    @Setter
    @Getter
    @Id
    @Column
    private Long id;


    public User(Long id) {
        this.id = id;
    }

    public User() {
    }
}

