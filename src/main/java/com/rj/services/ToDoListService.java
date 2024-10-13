package com.rj.services;

import com.rj.dto.ToDoItemDTO;
import com.rj.dto.ToDoListDTO;

import java.util.List;

public interface ToDoListService
{
    void saveToDoList(ToDoListDTO toDoListDTO);
    void DeleteListById(long listId);
    List<ToDoListDTO> findAllToDoLists();
    ToDoListDTO findToDoListbyId(long listId);

    void saveListItem(ToDoItemDTO toDoItemDTO, long listId);
    void updateListItem(ToDoItemDTO toDoItemDTO, long listId);

}
