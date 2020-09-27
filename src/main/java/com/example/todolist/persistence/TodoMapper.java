package com.example.todolist.persistence;

import com.example.todolist.domain.NewToDoItemRequest;
import com.example.todolist.domain.TodoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TodoMapper {

    public List<TodoVO> getList();

    public TodoVO getById(@Param("newToDoItemRequest") NewToDoItemRequest newToDoItemRequest);

    public int createTodo(@Param("newToDoItemRequest") NewToDoItemRequest newToDoItemRequest);

    public int updateTodo(@Param("newToDoItemRequest") NewToDoItemRequest newToDoItemRequest);
}
