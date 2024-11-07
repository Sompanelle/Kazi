package com.rj.dto;

import com.rj.models.AppUser;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ToDoListDTO
{
    private long listId;
    @NotEmpty(message = "name cannot be empty")
    private String name;
    private String description;
    private List<ToDoItemDTO> toDoItems;
    private AppUser user;

    @Override
    public String toString()
    {
        return String.format("Id: %d, Name: %s, Description: %s", listId, name, description);
    }
}
