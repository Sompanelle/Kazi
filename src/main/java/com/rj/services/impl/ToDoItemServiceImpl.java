package com.rj.services.impl;

import com.rj.dto.ToDoItemDTO;
import com.rj.mapper.ToDoItemMapper;
import com.rj.models.ToDoItem;
import com.rj.models.ToDoList;
import com.rj.repository.ToDoItemRepository;
import com.rj.repository.ToDoListRepository;
import com.rj.services.ToDoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import static com.rj.mapper.ToDoItemMapper.mapToDoDTO;
import static com.rj.mapper.ToDoItemMapper.mapToItem;

@Service
public class ToDoItemServiceImpl implements ToDoItemService
{
    private ToDoItemRepository toDoItemRepository;
    private ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoItemServiceImpl(ToDoItemRepository toDoItemRepository, ToDoListRepository toDoListRepository)
    {
        this.toDoItemRepository = toDoItemRepository;
        this.toDoListRepository = toDoListRepository;
    }

    @Override
    public List<ToDoItemDTO> findAllToDoItems() {
        List<ToDoItem> toDoItems = toDoItemRepository.findAll();
        return toDoItems.stream().map((todoitem) -> mapToDoDTO(todoitem)).collect(Collectors.toList());
    }

    @Override
    public ToDoItem SaveToDoItem(ToDoItemDTO toDoItem)
    {
        ToDoItem item = mapToItem(toDoItem);
        return toDoItemRepository.save(item);
    }

    @Override
    public List<ToDoItemDTO> SearchItemByTask(String query) {
        List<ToDoItem> items = toDoItemRepository.SearchbyTask(query);
        return items.stream().map((item) -> mapToDoDTO(item)).collect(Collectors.toList());
    }

    @Override
    public ToDoItemDTO findItembyId(long itemId)
    {
        ToDoItem item = toDoItemRepository.findByItemId(itemId).get();
        return mapToDoDTO(item);
    }

    @Override
    public List<ToDoItemDTO> findByListId(long listId) {
        List<ToDoItem> items = toDoItemRepository.findByToDoListListId(listId);
        return items.stream().map((item) -> mapToDoDTO(item)).collect(Collectors.toList());
    }

    @Override
    public void updateItem(ToDoItemDTO DTO) {
        ToDoItem item = mapToItem(DTO);
        toDoItemRepository.save(item);
    }

    @Override
    public void deleteItembyId(long itemId) {
        ToDoItem item = toDoItemRepository.findByItemId(itemId).get();
        toDoItemRepository.delete(item);
    }

    @Override
    public void saveListItem(ToDoItemDTO toDoListDTO, long listId)
    {
        ToDoItem item = ToDoItemMapper.mapToItem(toDoListDTO);
        ToDoList toDoList = toDoListRepository.findByListId(listId).get();
        item.setToDoList(toDoList);
        toDoItemRepository.save(item);

    }

    @Override
    public void updateListItem(ToDoItemDTO toDoItemDTO, long listId) {
        ToDoItem item = ToDoItemMapper.mapToItem(toDoItemDTO);
        ToDoList toDoList = toDoListRepository.findByListId(listId).get();
        item.setToDoList(toDoList);
        toDoItemRepository.save(item);
    }

}
