package com.rj.mapper;

import com.rj.dto.ToDoListDTO;
import com.rj.dto.UserDTO;
import com.rj.models.AppUser;
import com.rj.models.ToDoList;

import java.util.stream.Collectors;

import static com.rj.mapper.ToDoItemMapper.mapToDoDTO;

public class ToDoListMapper
{
    public static ToDoListDTO maptoListDTO(ToDoList toDoList)
    {
        ToDoListDTO listDTO = ToDoListDTO.builder()
                .listId(toDoList.getListId())
                .name(toDoList.getName())
                .description(toDoList.getDescription())
                .toDoItems(toDoList.getToDoItems().stream().map((item) -> mapToDoDTO(item)).collect(Collectors.toList()))
                .user(new UserDTO(toDoList.getUser()))
                .build();

        return listDTO;
    }

    public static ToDoList maptoList(ToDoListDTO listDTO)
    {
        ToDoList list = ToDoList.builder()
                .listId(listDTO.getListId())
                .name(listDTO.getName())
                .description(listDTO.getDescription())
                .build();

        return list;
    }

}
