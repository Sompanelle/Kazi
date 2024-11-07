package com.rj.repository;

import com.rj.models.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long>
{
    Optional<ToDoItem> findByItemId(long Id);
    @Query("Select t from ToDoItem t Where t.task Like concat('%', :query, '%') ")
    List<ToDoItem> SearchbyTask(String query);
    List<ToDoItem> findByToDoListListId(long listId);
}
