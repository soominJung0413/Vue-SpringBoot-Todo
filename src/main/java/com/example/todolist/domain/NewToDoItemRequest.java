package com.example.todolist.domain;

import lombok.Data;

@Data
public class NewToDoItemRequest {

    private int taskNo;
    private String title;
    private boolean done;
}
