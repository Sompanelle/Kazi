package com.rj.services.impl;

import com.rj.dto.ToDoItemDTO;
import com.rj.dto.ToDoListDTO;
import com.rj.mapper.ToDoItemMapper;
import com.rj.mapper.ToDoListMapper;
import com.rj.models.ToDoItem;
import com.rj.models.ToDoList;
import com.rj.repository.ToDoItemRepository;
import com.rj.repository.ToDoListRepository;
import com.rj.services.ToDoListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.stream.Collectors;

import static com.rj.mapper.ToDoListMapper.maptoList;

@Service
public class ToDoListServiceImpl implements ToDoListService
{
    private ToDoListRepository toDoListRepository;
    private ToDoItemRepository toDoItemRepository;

    @Autowired
    public ToDoListServiceImpl(ToDoListRepository ToDoListRepository, ToDoItemRepository ToDoItemRepository)
    {
        this.toDoListRepository = ToDoListRepository;
        this.toDoItemRepository = ToDoItemRepository;
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
    public void saveToDoList(ToDoListDTO toDoListDTO)
    {
        ToDoList list = maptoList(toDoListDTO);
        toDoListRepository.save(list);
    }

    @Override
    public void DeleteListById(long listId)
    {
        ToDoList toDoList = toDoListRepository.findById(listId).get();
        toDoListRepository.delete(toDoList);
    }


    @Override
    public void saveListItem(ToDoItemDTO toDoListDTO, long listId)
    {
        ToDoItem item = ToDoItemMapper.mapToItem(toDoListDTO);
        ToDoList toDoList = toDoListRepository.findById(listId).get();
        item.setToDoList(toDoList);
        toDoItemRepository.save(item);

    }

    @Override
    public void updateListItem(ToDoItemDTO toDoItemDTO, long listId) {
        ToDoItem item = ToDoItemMapper.mapToItem(toDoItemDTO);
        ToDoList toDoList = toDoListRepository.findById(listId).get();
        item.setToDoList(toDoList);
        toDoItemRepository.save(item);
    }

}
