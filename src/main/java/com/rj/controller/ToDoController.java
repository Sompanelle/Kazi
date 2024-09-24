package com.rj.controller;

import com.rj.dto.ToDoItemDTO;
import com.rj.models.ToDoItem;
import com.rj.services.ToDoItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ToDoController
{
    private ToDoItemService toDoItemService;

    @Autowired
    public ToDoController(ToDoItemService toDoItemService) {
        this.toDoItemService = toDoItemService;
    }

    @GetMapping("/")
    public String Index(Model model)
    {
        return "Index.html";
    }

    @GetMapping("/todoitems")
    public String listToDoItems(Model model)
    {
        List<ToDoItemDTO> items = toDoItemService.findAllToDoItems();
        model.addAttribute("ToDoItems", items);
        return "ToDoItems-List.html";
    }

    @GetMapping("/todoitems/search")
    public String searchToDoItems(@RequestParam(value = "query") String query, Model model)
    {
        List<ToDoItemDTO> items = toDoItemService.SearchItemByTask(query);
        model.addAttribute("ToDoItems", items);
        return "ToDoItems-List.html";
    }

    @GetMapping("/todoitems/new")
    public String getCreateToDoItem(Model model)
    {
        ToDoItem toDoItem = new ToDoItem();
        model.addAttribute("toDoItem", toDoItem );
        return "ToDoItems-New.html";
    }

    @GetMapping("/todoitems/{itemId}/edit")
    public String editToDoItem(@PathVariable("itemId") long itemId,
                               Model model)
    {
        ToDoItemDTO item = toDoItemService.findItembyId(itemId);
        model.addAttribute("toDoItem", item);
        return "ToDoItems-Edit";
    }

    @GetMapping("todoitems/{itemId}/delete")
    public String deleteToDoItem(@PathVariable("itemId") long itemId)
    {
        toDoItemService.deleteItembyId(itemId);
        return "redirect:/todoitems";
    }


    @PostMapping("/todoitems/new")
    public String postCreateToDoItem(@Valid @ModelAttribute("toDoItem") ToDoItemDTO toDoItem,
                                 BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "ToDoItems-New.html";
        }
        toDoItemService.SaveToDoItem(toDoItem);
        return "redirect:/todoitems";
    }

    @PostMapping("/todoitems/{itemId}/edit")
    public String postUpdateToDoItem(@PathVariable("itemId") long itemId,
                                     @Valid @ModelAttribute("toDoItem") ToDoItemDTO toDoItem,
                                     BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "ToDoItems-Edit";
        }
        toDoItem.setId(itemId);
        toDoItemService.updateItem(toDoItem);
        return "redirect:/todoitems";
    }


}
