package com.rj.services;

import com.rj.dto.ToDoListDTO;
import com.rj.models.ToDoList;

import java.util.List;

public interface ToDoListService
{
    List<ToDoList> findAllToDoLists();
    ToDoList findToDoListbyId(long listId);
    ToDoList saveToDoList(ToDoListDTO toDoList);
}
