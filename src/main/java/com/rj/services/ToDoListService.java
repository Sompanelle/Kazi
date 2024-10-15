package com.rj.services;

import com.rj.dto.ToDoListDTO;

import java.util.List;

public interface ToDoListService
{
    void createToDoList(ToDoListDTO toDoListDTO);
    void updateList(ToDoListDTO toDoListDTO);
    void deleteListById(long listId);
    List<ToDoListDTO> findAllToDoLists();
    ToDoListDTO findToDoListbyId(long listId);

}
