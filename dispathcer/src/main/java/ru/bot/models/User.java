package ru.bot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {


    @Id
    @Column
    private Long id;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }
}

