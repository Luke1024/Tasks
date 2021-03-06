package com.crud.tasks.domain;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "tasks")
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String title;

    @Column(name = "description")
    private String content;

    public Task(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
