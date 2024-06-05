package ru.bot.models;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table
public class Tasks {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private Users users;

    @Column
    private Integer number;
    @Column
    private Long start_date;
    @OneToMany
    @JoinColumn(name = "tags", referencedColumnName = "id")
    private List<Tags> tags;

    public Tasks() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getStart_date() {
        return start_date;
    }

    public void setStart_date(Long start_date) {
        this.start_date = start_date;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public Tasks(Long id, Users users, Integer number, Long start_date, List<Tags> tags) {
        this.id = id;
        this.users = users;
        this.number = number;
        this.start_date = start_date;
        this.tags = tags;
    }
}
