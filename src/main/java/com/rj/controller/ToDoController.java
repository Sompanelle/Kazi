package com.rj.controller;

import com.rj.dto.ToDoItemDTO;
import com.rj.dto.ToDoListDTO;
import com.rj.dto.UserDTO;
import com.rj.models.AppUser;
import com.rj.models.ToDoItem;
import com.rj.services.ToDoItemService;
import com.rj.services.ToDoListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ToDoController
{
    private ToDoItemService toDoItemService;
    private ToDoListService toDoListService;

    @Autowired
    public ToDoController(ToDoItemService ToDoItemService, ToDoListService ToDoListService)
    {
        this.toDoItemService = ToDoItemService;
        this.toDoListService = ToDoListService;
    }

    @GetMapping("/")
    public String Index()
    {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String Home()
    {
        return "Index";
    }

    @GetMapping("/todoitems")
    public String getAllToDoItems(Model model)
    {
        UserDTO user = new UserDTO((AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<ToDoItemDTO> items = toDoItemService.findToDoItemsByUser(user);
        model.addAttribute("ToDoItems", items);
        return "ToDoItems-List";
    }

    @GetMapping("/todoitems/search")
    public String searchToDoItems(@RequestParam(value = "query") String query, Model model)
    {
        List<ToDoItemDTO> items = toDoItemService.SearchItemByTask(query);
        model.addAttribute("ToDoItems", items);
        return "ToDoItems-List";
    }

    @GetMapping("/todoitems/new")
    public String getCreateToDoItem(Model model)
    {
        ToDoItem toDoItem = new ToDoItem();
        UserDTO user = new UserDTO ((AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<ToDoListDTO> toDoLists = toDoListService.findListsByUser(user);
        model.addAttribute("toDoLists", toDoLists);
        model.addAttribute("toDoItem", toDoItem );
        return "ToDoItem-New";
    }

    @PostMapping("/todoitems/new")
    public String postCreateToDoItem(@Valid @ModelAttribute("toDoItem") ToDoItemDTO toDoItem,
                                     BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "ToDoItem-New";
        }
        toDoItemService.SaveToDoItem(toDoItem);
        return "redirect:/todoitems";
    }

    @GetMapping("/todoitems/{itemId}/edit")
    public String editToDoItem(@PathVariable("itemId") long itemId,
                               Model model)
    {
        ToDoItemDTO item = toDoItemService.findItembyId(itemId);
        if (item == null)
        {
            return "redirect:/todoitems";
        }
        model.addAttribute("toDoItem", item);
        return "ToDoItem-Edit";
    }

    @GetMapping("todoitems/{itemId}/delete")
    public String deleteToDoItem(@PathVariable("itemId") long itemId)
    {
        toDoItemService.deleteItembyId(itemId);
        return "redirect:/todoitems";
    }




    @PostMapping("/todoitems/{itemId}/edit")
    public String postUpdateToDoItem(@PathVariable("itemId") long itemId,
                                     @Valid @ModelAttribute("toDoItem") ToDoItemDTO toDoItem,
                                     BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "ToDoItem-Edit";
        }
        toDoItem.setItemId(itemId);
        toDoItemService.updateItem(toDoItem);
        return "redirect:/todoitems";
    }


}
