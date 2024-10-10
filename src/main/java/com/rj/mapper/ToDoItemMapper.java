package com.rj.mapper;

import com.rj.dto.ToDoItemDTO;
import com.rj.models.ToDoItem;

import java.time.LocalDateTime;

public class ToDoItemMapper
{

    //ToDoItem to DTO
    public static ToDoItemDTO mapToDoDTO(ToDoItem ToDoItem)
    {
        ToDoItemDTO itemDto = ToDoItemDTO.builder()
                .id(ToDoItem.getId())
                .task(ToDoItem.getTask())
                .description(ToDoItem.getDescription())
                .creationDate(ToDoItem.getCreationDate())
                .updatedDate(LocalDateTime.now())
                .toDoList(ToDoItem.getToDoList())
                .build();
        return itemDto;
    }


    //DTO to ToDoItem
    public static ToDoItem mapToItem(ToDoItemDTO toDoItem)
    {
        ToDoItem item = ToDoItem.builder()
                .id(toDoItem.getId())
                .task(toDoItem.getTask())
                .description(toDoItem.getDescription())
                .creationDate(toDoItem.getCreationDate())
                .updatedDate(LocalDateTime.now())
                .toDoList(toDoItem.getToDoList())
                .build();
        return item;
    }
}
