package com.rj.services.impl;

import com.rj.dto.ToDoItemDTO;
import com.rj.dto.UserDTO;
import com.rj.mapper.ToDoItemMapper;
import com.rj.models.AppUser;
import com.rj.models.ToDoItem;
import com.rj.models.ToDoList;
import com.rj.repository.ToDoItemRepository;
import com.rj.repository.ToDoListRepository;
import com.rj.repository.ToDoItemRepository;
import com.rj.repository.ToDoListRepository;
import com.rj.repository.UserRepository;
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
    private ToDoItemRepository toDoItemRepo;
    private ToDoListRepository toDoListRepo;
    private UserRepository userRepo;

    @Autowired
    public ToDoItemServiceImpl(ToDoItemRepository toDoItemRepo, ToDoListRepository toDoListRepo, UserRepository UserRepository)
    {
        this.toDoItemRepo = toDoItemRepo;
        this.toDoListRepo = toDoListRepo;
        this.userRepo = UserRepository;
    }

    @Override
    public List<ToDoItemDTO> findAllToDoItems() {
        List<ToDoItem> toDoItems = toDoItemRepo.findAll();
        return toDoItems.stream().map((todoitem) -> mapToDoDTO(todoitem)).collect(Collectors.toList());
    }

    @Override
    public List<ToDoItemDTO> findToDoItemsByUser(UserDTO UserDTO)  
    {
        AppUser user = userRepo.findByUsername(UserDTO.getUsername()).get();
        List<ToDoItem> toDoItems = toDoItemRepo.SearchByUser(user.getUsername());
        return toDoItems.stream().map((todoitem) -> mapToDoDTO(todoitem)).collect(Collectors.toList());
    }

    @Override
    public ToDoItem SaveToDoItem(ToDoItemDTO toDoItem)
    {
        ToDoItem item = mapToItem(toDoItem);
        return toDoItemRepo.save(item);
    }

    @Override
    public List<ToDoItemDTO> SearchItemByTask(String query) {
        List<ToDoItem> items = toDoItemRepo.SearchbyTask(query);
        return items.stream().map((item) -> mapToDoDTO(item)).collect(Collectors.toList());
    }

    @Override
    public ToDoItemDTO findItembyId(long itemId)
    {
        ToDoItem item = toDoItemRepo.findByItemId(itemId).get();
        return mapToDoDTO(item);
    }

    @Override
    public List<ToDoItemDTO> findByListId(long listId) {
        List<ToDoItem> items = toDoItemRepo.findByToDoListListId(listId);
        return items.stream().map((item) -> mapToDoDTO(item)).collect(Collectors.toList());
    }

    @Override
    public void updateItem(ToDoItemDTO DTO) {
        ToDoItem item = mapToItem(DTO);
        toDoItemRepo.save(item);
    }

    @Override
    public void deleteItembyId(long itemId) {
        ToDoItem item = toDoItemRepo.findByItemId(itemId).get();
        toDoItemRepo.delete(item);
    }

    @Override
    public void saveListItem(ToDoItemDTO toDoListDTO, long listId)
    {
        ToDoItem item = ToDoItemMapper.mapToItem(toDoListDTO);
        ToDoList toDoList = toDoListRepo.findByListId(listId).get();
        item.setToDoList(toDoList);
        toDoItemRepo.save(item);

    }

    @Override
    public void updateListItem(ToDoItemDTO toDoItemDTO, long listId) {
        ToDoItem item = ToDoItemMapper.mapToItem(toDoItemDTO);
        ToDoList toDoList = toDoListRepo.findByListId(listId).get();
        item.setToDoList(toDoList);
        toDoItemRepo.save(item);
    }

}
