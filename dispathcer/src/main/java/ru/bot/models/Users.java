package ru.bot.models;

import jakarta.persistence.*;

@Entity
@Table
public class Users {
    @Id
    @Column
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users(Long id) {
        this.id = id;
    }

    public Users() {
    }
}

