package ru.bot.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tags")
public class Tags {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;
    @Column
    private String name;
    @Column
    private boolean deletable;
    @Column
    private int color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeletable() {
        return deletable;
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Tags() {
    }

    public Tags(Users users, String name, boolean deletable, int color) {
        this.users = users;
        this.name = name;
        this.deletable = deletable;
        this.color = color;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
