package com.rj.controller;

import com.rj.models.ToDoList;
import com.rj.services.ToDoListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ToDoListController
{
    private ToDoListService toDoListService;

    public ToDoListController(ToDoListService toDoListService)
    {
        this.toDoListService = toDoListService;
    }

    @GetMapping("/todolists")
    public String getAllToDoLists(Model model)
    {
        List<ToDoList> Lists = toDoListService.findAllToDoLists();
        model.addAttribute("ToDoLists", Lists);
        return "ToDoLists-List.html";
    }

    @GetMapping("/todolists/{listId}")
    public String GetToDoListById(@PathVariable("listId") long listId,
                                  Model model)
    {
        ToDoList toDoList = toDoListService.findToDoListbyId(listId);
        if (toDoList == null)
        {
            return "redirect:/todolists";
        }
        model.addAttribute("ToDoList", toDoList);
        return "ToDoLists-Details";
    }

}

