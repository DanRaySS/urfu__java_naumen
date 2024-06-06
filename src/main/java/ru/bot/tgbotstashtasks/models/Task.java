package ru.bot.tgbotstashtasks.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
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

    @ManyToOne
    @JoinColumn(name = "tag", referencedColumnName = "id")
    private Tag tag;

    public Task() {
    }

    public Task(User users, Long start_date, String summary, String description, Tag tag) {
        this.users = users;
        this.start_date = start_date;
        this.summary = summary;
        this.description = description;
        this.tag = tag;
    }

    @Override
    public String toString() {
        return summary;
    }
}
