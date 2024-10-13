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


    //Get Create ToDoLists
    @GetMapping("/todolists/new")
    public String getCreateToDoList(Model model)
    {
        ToDoList toDoList = new ToDoList();
        model.addAttribute("toDoList", toDoList);
        return "ToDoList-New";
    }

    //Get Create ToDoItem under Specific ToDoList
    @GetMapping("/todolists/{listId}/new")
    public String getCreateChildToDoItem(@PathVariable("listId") long listId,
                                         Model model)
    {
        ToDoListDTO list = toDoListService.findToDoListbyId(listId);
        if (list != null) {
            ToDoItem item = new ToDoItem();
            model.addAttribute("toDoItem", item);
            return "ToDoItem-New-Child";
        }
        return "redirect:/todolists";
    }


    //Get all ToDoLists
    @GetMapping("/todolists")
    public String getAllToDoLists(Model model) {
        List<ToDoListDTO> Lists = toDoListService.findAllToDoLists();
        model.addAttribute("ToDoLists", Lists);
        return "ToDoLists-List";
    }


    //Get Specific ToDoList
    @GetMapping("/todolists/{listId}")
    public String GetToDoListById(@PathVariable("listId") long listId,
                                  Model model) {
        ToDoListDTO list = toDoListService.findToDoListbyId(listId);
        List<ToDoItemDTO> items = toDoItemService.findByListId(listId);
        if (list == null) {
            return "redirect:/todolists";
        }
        model.addAttribute("Items", items);
        model.addAttribute("ToDoList", list);
        return "ToDoList-Details";
    }

    //Delete Specific ToDoList
    @GetMapping("/todolists/{listId}/delete")
    public String DeleteToDoListById(@PathVariable("listId") long listId,
                                     Model model)
    {
        ToDoListDTO toDoList = toDoListService.findToDoListbyId(listId);
        if (toDoList != null)
        {
            toDoListService.DeleteListById(listId);
            return "redirect:/todolists";
        }
        return "redirect:/todolists";
    }

    //Post Create ToDoList
    @PostMapping("/todolists/new")
    public String postCreateToDoList(@Valid @ModelAttribute("toDoList") ToDoListDTO toDoListDTO,
                                     BindingResult bindingResult,
                                     Model model)
    {
        if (!bindingResult.hasErrors())
        {
            toDoListService.saveToDoList(toDoListDTO);
            return "redirect:/todolists";

        }
        return "ToDoList-New";
    }


    //Post Create ToDoItem under ToDoList
    @PostMapping("/todolists/{listId}/new")
    public String postCreateChildToDoItem(@PathVariable("listId") long listId,@ModelAttribute("toDoItem") ToDoItemDTO toDoItemDTO,
                                     BindingResult bindingResult, Model model)
    {
        model.addAttribute("listId", listId);
        if (bindingResult.hasErrors())
        {
            return "ToDoItem-New-Child";
        }
        toDoListService.saveListItem(toDoItemDTO, listId);
        return "redirect:/todolists/"+listId;
    }

    //Get Update ToDoItem under ToDoList
    @GetMapping("/todolists/{listId}/{itemId}/edit")
    public String getUpdateChildToDoItem(@PathVariable("listId") long listId,
                                         @PathVariable("itemId") long itemId, Model model)
    {
        ToDoListDTO list = toDoListService.findToDoListbyId(listId);
        ToDoItemDTO item = toDoItemService.findItembyId(itemId);
        if(list == null || item == null)
        {
            if (list != null)
            {
                return "redirect:/todolists/"+ listId;
            }
            return "redirect:/todolists/";
        }
        model.addAttribute("toDoItem", item);
        model.addAttribute("listId", listId);

        return "ToDoList-Edit-Child";
    }

    @PostMapping("/todolists/{listId}/{itemId}/edit")
    public String postUpdateChildToDoItem(@PathVariable("listId") long listId,
                                          @PathVariable("itemId") long itemId,
                                          @Valid @ModelAttribute("toDoItem") ToDoItemDTO ToDoItem)
    {

        ToDoListDTO list = toDoListService.findToDoListbyId(listId);
        ToDoItemDTO item = toDoItemService.findItembyId(itemId);
        if (list == null || item == null)
        {
            return "redirect:/todolists/"+ listId;
        }
        toDoListService.updateListItem(ToDoItem, listId);
        return "redirect:/todolists/"+listId;
    }

    @GetMapping("/todolists/{listId}/{itemId}/delete")
    public String getDeleteChildToDoItem(@PathVariable("listId") long listId,
                                         @PathVariable("itemId") long itemId,
                                         Model model)
    {
        ToDoItemDTO item = toDoItemService.findItembyId(itemId);
        if (item == null)
            return "redirect:/todolists/"+listId;
        toDoItemService.deleteItembyId(itemId);
        return "redirect:/todolists/"+listId;
    }


}