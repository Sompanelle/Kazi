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
    @NotEmpty(message = "name cannot be empty")
    private String name;
    private String description;
    private List<ToDoItemDTO> todoitems;

    @Override
    public String toString()
    {
        return String.format("Id: %d, Name: %s, Description: %s", id, name, description);
    }
}
