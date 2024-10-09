package com.rj.services.impl;

import com.rj.models.ToDoList;
import com.rj.repository.ToDoListRepository;
import com.rj.services.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoListServiceImpl implements ToDoListService
{
    private ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoListServiceImpl(ToDoListRepository toDoListRepository)
    {
        this.toDoListRepository = toDoListRepository;
    }

    @Override
    public List<ToDoList> findAllToDoLists()
    {
        List<ToDoList> toDoLists = toDoListRepository.findAll();
        return toDoLists;
    }

    @Override
    public ToDoList findToDoListbyId(long listId) {
        ToDoList list = toDoListRepository.findById(listId).get();
        return list;
    }
}
