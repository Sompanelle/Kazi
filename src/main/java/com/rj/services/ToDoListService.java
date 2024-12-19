package com.rj.services;

import com.rj.dto.ToDoListDTO;
import com.rj.dto.UserDTO;

import java.util.List;

public interface ToDoListService
{
    void createToDoList(ToDoListDTO toDoListDTO, UserDTO UserDTO);
    void updateList(ToDoListDTO toDoListDT, UserDTO UserDTO);
    void deleteListById(long listId);
    List<ToDoListDTO> findAllToDoLists();
    ToDoListDTO findToDoListbyId(long listId);
    List<ToDoListDTO> findListsByUser(UserDTO UserDTO);
}
