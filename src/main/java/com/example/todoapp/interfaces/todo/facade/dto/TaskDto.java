package com.example.todoapp.interfaces.todo.facade.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by d_akihiro on 2017/02/27.
 */
@Data
@NoArgsConstructor
public class TaskDto implements Serializable {
    private static final long serialVersionUID = 925535436702051373L;
    private long id;

    @NotBlank
    private String name;

    private String description;

    private LocalDateTime deadline;

    private boolean isFinished;

    private boolean isExpired;

    private long projectId;

    private long userId;
}
