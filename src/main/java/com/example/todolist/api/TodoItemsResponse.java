package com.example.todolist.api;

import com.example.todolist.domain.TodoVO;
import com.example.todolist.persistence.TodoMapper;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TodoItemsResponse extends ApiResponse<List<TodoVO>> {

    @Builder
    public TodoItemsResponse(final List<TodoVO> todoVO, final List<String> errors){
        super(todoVO);
        this.setErrors(errors);
    }
}
