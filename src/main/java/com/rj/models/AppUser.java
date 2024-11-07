package com.rj.models;

import com.rj.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Users")
public class AppUser implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userId")
    private int userId;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name="userId", referencedColumnName = "userId"),
        inverseJoinColumns = @JoinColumn(name="roleId", referencedColumnName = "roleId"))
    private Set<Role> authorities;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ToDoList> toDoLists;

    public AppUser(String Username, String Password, Set<Role> Authorities)
    {
        this.username = Username;
        this.password = Password;
        this.authorities = Authorities;
    }

    public AppUser(UserDTO UserDTO)
    {
        this.username=UserDTO.getUsername();
    }

}
