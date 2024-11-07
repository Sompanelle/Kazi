package com.rj.models;

import com.rj.dto.ToDoListDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="ToDoLists")
public class ToDoList
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="listId")
    private long listId;
    private String name;
    private String description;
    @OneToMany(mappedBy = "toDoList", cascade = CascadeType.ALL)
    private List<ToDoItem> toDoItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private AppUser user;

}
