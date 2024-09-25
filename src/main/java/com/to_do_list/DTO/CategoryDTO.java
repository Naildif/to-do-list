package com.to_do_list.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoryDTO {
    private Long id;
    @NotNull(message = "(˶˃ᆺ˂˶) \nPlease, select a category.")
    @Size(min = 1, max = 100, message = "(｡•́︿•̀｡) \nTask title must have between 1 and 100 characters.")
    private String name;

    public CategoryDTO(){}

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
