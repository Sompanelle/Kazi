package com.rj.services.impl;

import com.rj.dto.ToDoListDTO;
import com.rj.dto.UserDTO;
import com.rj.mapper.ToDoListMapper;
import com.rj.models.AppUser;
import com.rj.models.ToDoList;
import com.rj.repository.ToDoListRepository;
import com.rj.repository.UserRepository;
import com.rj.services.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rj.mapper.ToDoListMapper.maptoList;
import static com.rj.mapper.ToDoListMapper.maptoListDTO;

@Service
public class ToDoListServiceImpl implements ToDoListService
{
    private ToDoListRepository listRepo;

    private UserRepository userRepo;

    @Autowired
    public ToDoListServiceImpl(ToDoListRepository ListRepository, UserRepository UserRepository)
    {
        this.listRepo = ListRepository;
        this.userRepo = UserRepository;
    }

    @Override
    public void createToDoList(ToDoListDTO toDoListDTO, UserDTO userDTO)
    {
        ToDoList list = maptoList(toDoListDTO);
        AppUser user = userRepo.findByUsername(userDTO.getUsername()).get();
        list.setUser(user);
        listRepo.save(list);
    }

    @Override
    public List<ToDoListDTO> findAllToDoLists()
    {
        List<ToDoListDTO> toDoLists = (listRepo.findAll().stream().map((item) -> ToDoListMapper.maptoListDTO(item)).collect(Collectors.toList()));
        return toDoLists;
    }

    @Override
    public ToDoListDTO findToDoListbyId(long listId) {
        ToDoListDTO list = ToDoListMapper.maptoListDTO(listRepo.findById(listId).get());
        return list;
    }

    @Override
    public List<ToDoListDTO> findListByUser(UserDTO UserDTO) {
        AppUser user = userRepo.findByUsername(UserDTO.getUsername()).get();
        List<ToDoListDTO> list = listRepo.findByUser(user).stream().map(l -> maptoListDTO(l)).collect(Collectors.toList());
        return list;
    }

    @Override
    public void updateList(ToDoListDTO toDoListDTO)
    {
        ToDoList list = ToDoListMapper.maptoList(toDoListDTO);
        listRepo.save(list);
    }


    @Override
    public void deleteListById(long listId)
    {
        ToDoList toDoList = listRepo.findById(listId).get();
        listRepo.delete(toDoList);
    }






}
