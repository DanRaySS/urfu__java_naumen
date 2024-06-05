package ru.daniil.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@Table
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private User users;

    @Column
    private Long start_date;


    @Column
    private String summary;

    @Column
    private String description;

    @ManyToMany
    @JoinColumn(name = "tags", referencedColumnName = "id")
    private List<Tag> tags;

    public Task() {
    }

    public Task(User users, Long start_date, String summary, String description, List<Tag> tags) {
        this.users = users;
        this.start_date = start_date;
        this.summary = summary;
        this.description = description;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return summary;
    }
}
