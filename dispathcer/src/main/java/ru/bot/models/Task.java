package ru.bot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
public class Task {
    @Getter
    @Setter
    @Id
    @GeneratedValue
    private Long id;
    @Setter
    @Getter
    @OneToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private User users;
    @Setter
    @Getter
    @Column
    private Long start_date;

    @Setter
    @Getter
    @Column
    private String summary;
    @Setter
    @Getter
    @Column
    private String description;
    @OneToMany
    @JoinColumn(name = "tags", referencedColumnName = "id")
    private List<Tag> tag;

    public Task() {
    }

    public Task(User users, Long start_date, String summary, String description, List<Tag> tags) {
        this.users = users;
        this.start_date = start_date;
        this.summary = summary;
        this.description = description;
        this.tag = tags;
    }

    public List<Tag> getTags() {
        return tag;
    }

    public void setTags(List<Tag> tags) {
        this.tag = tags;
    }
}
