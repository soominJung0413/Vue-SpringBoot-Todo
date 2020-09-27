package com.example.todolist.controller;

import com.example.todolist.api.TodoItemsResponse;
import com.example.todolist.domain.NewToDoItemRequest;
import com.example.todolist.domain.TodoVO;
import com.example.todolist.persistence.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoMapper todoMapper;

    @GetMapping(value = "/todo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TodoItemsResponse Todo(){
        List<String> errors = new ArrayList<>();
        List<TodoVO> list = null;
        try{
            list = todoMapper.getList();
        }catch(Exception e){
            errors.add(e.getMessage());
        }
        return new TodoItemsResponse(list, errors);
    }

    @PostMapping(value = "/todo")
    @ResponseBody
    public TodoItemsResponse create(@RequestBody NewToDoItemRequest newToDoItemRequest) {
        System.out.println(newToDoItemRequest);
        todoMapper.createTodo(newToDoItemRequest);
        return null;
    }

    @PutMapping(value = "/todo")
    public TodoItemsResponse update(@RequestBody final NewToDoItemRequest newToDoItemRequest){
        System.out.println("체크 ::::::::::::::::::::"+newToDoItemRequest);
        List<String> error = new ArrayList<>();
        TodoVO todoVO = todoMapper.getById(newToDoItemRequest);
        try{
            todoVO.setDone(newToDoItemRequest.isDone());
            todoMapper.updateTodo(newToDoItemRequest);
        }catch(Exception e){
            error.add(e.getMessage());
        }
        List<TodoVO> list = todoMapper.getList();
        return new TodoItemsResponse(list, error);
    }
}
