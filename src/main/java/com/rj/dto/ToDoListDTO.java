package com.rj.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ToDoListDTO
{
    private long id;
    @NotEmpty(message = "ToDoList Requires a name")
    private String name;
    private String description;
    private List<ToDoItemDTO> todoitems;
}
