package com.example.todoapp.interfaces.todo.facade.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * Created by d_akihiro on 2017/02/27.
 */
@Data
@NoArgsConstructor
public class ProjectDto implements Serializable {
    private static final long serialVersionUID = -9138581234036314971L;
    private long id;

    @NotBlank
    private String name;

    private String description;

    private List<TaskDto> taskDtoList;

    private long userId;
}
