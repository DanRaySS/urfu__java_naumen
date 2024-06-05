package ru.bot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Tag {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column
    private String name;

    @Column
    private boolean deletable;

    @Column
    private int color;

    public Tag() {
    }

    public Tag(User users, String name, boolean deletable, int color) {
        this.user = users;
        this.name = name;
        this.deletable = deletable;
        this.color = color;
    }

    public User getUsers() {
        return user;
    }

    public void setUsers(User users) {
        this.user = users;
    }
}
