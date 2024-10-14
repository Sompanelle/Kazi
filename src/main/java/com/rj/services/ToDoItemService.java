package com.rj.services;

import com.rj.dto.ToDoItemDTO;
import com.rj.models.ToDoItem;

import java.util.List;

public interface ToDoItemService
{
    void updateItem(ToDoItemDTO toDoItem);
    void deleteItembyId(long itemId);
    ToDoItem SaveToDoItem(ToDoItemDTO toDoItem);
    List<ToDoItemDTO> SearchItemByTask(String query);
    List<ToDoItemDTO> findAllToDoItems();
    ToDoItemDTO findItembyId(long itemId);
    List<ToDoItemDTO> findByListId(long listId);

    void updateListItem(ToDoItemDTO toDoItemDTO, long listId);
    void saveListItem(ToDoItemDTO toDoListDTO, long listId);
}
