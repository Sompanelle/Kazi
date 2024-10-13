package com.rj.dto;

import com.rj.models.ToDoList;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class ToDoItemDTO
{
    private long id;
    @NotEmpty(message = "Task cannot be empty")
    private String task;
    private String description;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;
    private ToDoList toDoList;

    @Override
    public String toString()
    {
        return String.format("Id: %d, Task: %s, Description: %s", id, task, description);
    }
}
