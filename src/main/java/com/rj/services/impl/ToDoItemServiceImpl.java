package com.rj.services.impl;

import com.rj.dto.ToDoItemDTO;
import com.rj.models.ToDoItem;
import com.rj.repository.ToDoItemRepository;
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

    @Autowired
    public ToDoItemServiceImpl(ToDoItemRepository toDoItemRepository)
    {
        this.toDoItemRepository = toDoItemRepository;
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
        ToDoItem item = toDoItemRepository.findById(itemId).get();
        return mapToDoDTO(item);
    }

    @Override
    public List<ToDoItemDTO> findByListId(long listId) {
        List<ToDoItem> items = toDoItemRepository.findByToDoListId(listId);
        return items.stream().map((item) -> mapToDoDTO(item)).collect(Collectors.toList());
    }

    @Override
    public void updateItem(ToDoItemDTO DTO) {
        ToDoItem item = mapToItem(DTO);
        toDoItemRepository.save(item);
    }

    @Override
    public void deleteItembyId(long itemId) {
        ToDoItem item = toDoItemRepository.findById(itemId).get();
        toDoItemRepository.delete(item);
    }


}
