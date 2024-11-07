package com.rj.dto;

import com.rj.models.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO
{
    private String username;
    private String password;

    public UserDTO(AppUser User)
    {
        this.username = User.getUsername();
        this.password = User.getPassword();
    }
}
