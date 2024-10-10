package com.rj.controller;

import com.rj.dto.ToDoItemDTO;
import com.rj.dto.ToDoListDTO;
import com.rj.models.ToDoItem;
import com.rj.models.ToDoList;
import com.rj.services.ToDoItemService;
import com.rj.services.ToDoListService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ToDoListController {
    private ToDoListService toDoListService;
    private ToDoItemService toDoItemService;

    public ToDoListController(ToDoListService toDoListService, ToDoItemService toDoItemService) {
        this.toDoListService = toDoListService;
        this.toDoItemService = toDoItemService;
    }

    @GetMapping("/todolists")
    public String getAllToDoLists(Model model) {
        List<ToDoList> Lists = toDoListService.findAllToDoLists();
        model.addAttribute("ToDoLists", Lists);
        return "ToDoLists-List.html";
    }

    @GetMapping("/todolists/{listId}")
    public String GetToDoListById(@PathVariable("listId") long listId,
                                  Model model) {
        ToDoList list = toDoListService.findToDoListbyId(listId);
        List<ToDoItemDTO> items = toDoItemService.findByListId(listId);
        if (list == null) {
            return "redirect:/todolists";
        }
        model.addAttribute("Items", items);
        model.addAttribute("ToDoList", list);
        return "ToDoList-Details";
    }

    @GetMapping("/todolists/new")
    public String getCreateToDoList(Model model)
    {
        ToDoList toDoList = new ToDoList();
        model.addAttribute("toDoList", toDoList);
        return "ToDoList-New.html";
    }

    @PostMapping("/todolists/new")
    public String postCreateToDoList(@Valid @ModelAttribute("toDoList") ToDoListDTO toDoList,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors())
        {
            return "ToDoList-New.html";
        }
        toDoListService.saveToDoList(toDoList);
        return "redirect:/todolists";
    }

}