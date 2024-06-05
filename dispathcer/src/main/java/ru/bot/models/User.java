package ru.bot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table
public class User {
    @Id
    @Column
    private Long id;

    public User(Long id) {
        this.id = id;
    }

    public User() {
    }
}

