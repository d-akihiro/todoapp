package com.example.todoapp.interfaces.user.facade.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * ユーザ情報
 * Created by d_akihiro on 2017/02/25.
 */
@Data
@NoArgsConstructor
public class UserDto implements Serializable {
    private static final long serialVersionUID = 4054271935684962541L;
    /**
     * ユーザID
     */
    private long id;

    /**
     * ユーザ名
     */
    @NotBlank
    @Size(min=5, max=20)
    private String username;

    /**
     * パスワード(平文)
     */
    @NotBlank
    @Size(min=5)
    private String password;
}
