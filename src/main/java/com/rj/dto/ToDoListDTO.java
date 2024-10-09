package com.rj.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class ToDoListDTO
{
    private long id;
    @NotEmpty(message = "ToDoList Requires a name")
    private String name;
    private String description;
    private List<ToDoItemDTO> todoitems;
}
