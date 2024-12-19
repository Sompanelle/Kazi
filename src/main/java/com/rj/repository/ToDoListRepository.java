package com.rj.repository;

import com.rj.models.AppUser;
import com.rj.models.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long>
{
    Optional<ToDoList> findByListId(long Id);
    @Query("Select l from ToDoList l where l.user = :User ")
    List<ToDoList> findAllByUser(AppUser User);
}
