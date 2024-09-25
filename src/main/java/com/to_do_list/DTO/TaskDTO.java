package com.to_do_list.DTO;

import jakarta.validation.constraints.NotNull;

public class Task {
    private Long id;
    @NotNull(message = "(˘ŏ_ŏ)  \nTitle field cannot be empty.")
    private String title;

}
