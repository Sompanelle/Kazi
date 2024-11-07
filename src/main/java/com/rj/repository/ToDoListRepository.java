package com.rj.repository;

import com.rj.models.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long>
{
    Optional<ToDoList> findByListId(long id);

}
