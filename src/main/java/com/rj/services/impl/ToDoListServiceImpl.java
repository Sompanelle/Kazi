package com.rj.services.impl;

import com.rj.dto.ToDoListDTO;
import com.rj.models.ToDoList;
import com.rj.repository.ToDoListRepository;
import com.rj.services.ToDoListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

import static com.rj.mapper.ToDoListMapper.maptoList;

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

    @Override
    public ToDoList saveToDoList(ToDoListDTO toDoListDTO)
    {
        ToDoList list = maptoList(toDoListDTO);
        return toDoListRepository.save(list);
    }
}
