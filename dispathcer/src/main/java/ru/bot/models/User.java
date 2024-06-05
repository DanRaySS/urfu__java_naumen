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
    public String lastSummary;
    public String lastDesc;

    public User(Long id, String lastSummary, String lastDesc) {
        this.id = id;
        this.lastSummary = lastSummary;
        this.lastDesc = lastDesc;
    }

    public User() {
    }
}

