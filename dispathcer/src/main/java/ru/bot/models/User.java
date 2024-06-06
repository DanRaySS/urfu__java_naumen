package ru.bot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


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

