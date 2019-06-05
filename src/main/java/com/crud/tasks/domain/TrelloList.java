package com.crud.tasks.domain;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class TrelloList {
    private String id;
    private String name;
    private boolean isClosed;
}
