package ru.bot.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
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
    @Column
    private int[] tags;

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

    public int[] getTags() {
        return tags;
    }

    public void setTags(int[] tags) {
        this.tags = tags;
    }

    public Tasks(Users users, Integer number, Long start_date, int[] tags) {
        this.users = users;
        this.number = number;
        this.start_date = start_date;
        this.tags = tags;
    }

    public Tasks() {
    }
}
