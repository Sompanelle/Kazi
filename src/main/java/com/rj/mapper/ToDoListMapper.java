package com.rj.mapper;

import com.rj.dto.ToDoListDTO;
import com.rj.models.ToDoList;

import java.util.stream.Collectors;

import static com.rj.mapper.ToDoItemMapper.mapToDoDTO;
import static com.rj.mapper.ToDoItemMapper.mapToItem;

public class ToDoListMapper
{
    public static ToDoListDTO maptoListDTO(ToDoList toDoList)
    {
        ToDoListDTO listDTO = ToDoListDTO.builder()
                .id(toDoList.getId())
                .name(toDoList.getName())
                .description(toDoList.getDescription())
                .todoitems(toDoList.getToDoItems().stream().map((item) -> mapToDoDTO(item)).collect(Collectors.toList()))
                .build();

        return listDTO;
    }

    public static ToDoList maptoList(ToDoListDTO listDTO)
    {
        ToDoList list = ToDoList.builder()
                .id(listDTO.getId())
                .name(listDTO.getName())
                .description(listDTO.getDescription())
                .build();

        return list;
    }

}
