package com.rj.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Builder
public class ToDoItemDTO
{
    private long id;
    @NotEmpty(message = "Task cannot be empty")
    private String task;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;
}
