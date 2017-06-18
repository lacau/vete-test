package io.redspark.controller.dto;

import io.redspark.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private PersonDTO person;
    private Boolean admin;

    public UserDTO(User user) {
        this.id = user.getId();
        this.person = new PersonDTO(user.getPerson());
        this.admin = user.getAdmin();
    }
}