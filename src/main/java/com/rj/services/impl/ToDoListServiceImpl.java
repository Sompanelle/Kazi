package com.rj.services.impl;

import com.rj.dto.ToDoListDTO;
import com.rj.mapper.ToDoListMapper;
import com.rj.models.ToDoList;
import com.rj.repository.ToDoListRepository;
import com.rj.services.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rj.mapper.ToDoListMapper.maptoList;

@Service
public class ToDoListServiceImpl implements ToDoListService
{
    private ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoListServiceImpl(ToDoListRepository ToDoListRepository)
    {
        this.toDoListRepository = ToDoListRepository;
    }

    @Override
    public void createToDoList(ToDoListDTO toDoListDTO)
    {
        ToDoList list = maptoList(toDoListDTO);
        toDoListRepository.save(list);
    }

    @Override
    public List<ToDoListDTO> findAllToDoLists()
    {
        List<ToDoListDTO> toDoLists = (toDoListRepository.findAll().stream().map((item) -> ToDoListMapper.maptoListDTO(item)).collect(Collectors.toList()));
        return toDoLists;
    }

    @Override
    public ToDoListDTO findToDoListbyId(long listId) {
        ToDoListDTO list = ToDoListMapper.maptoListDTO(toDoListRepository.findById(listId).get());
        return list;
    }

    @Override
    public void updateList(ToDoListDTO toDoListDTO)
    {
        ToDoList list = ToDoListMapper.maptoList(toDoListDTO);
        toDoListRepository.save(list);
    }


    @Override
    public void deleteListById(long listId)
    {
        ToDoList toDoList = toDoListRepository.findById(listId).get();
        toDoListRepository.delete(toDoList);
    }






}
