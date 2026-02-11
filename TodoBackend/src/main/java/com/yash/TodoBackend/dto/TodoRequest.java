package com.yash.TodoBackend.dto;

import lombok.Data;

@Data
public class TodoRequest {
    private String title;
    private boolean completed;
}
