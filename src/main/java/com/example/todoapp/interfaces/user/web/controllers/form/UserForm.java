package com.example.todoapp.interfaces.user.web.controllers.form;

import com.example.todoapp.interfaces.user.facade.dto.UserDto;
import com.example.todoapp.interfaces.user.facade.impl.assembler.UserDtoAssembler;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * ユーザ編集フォーム
 * Created by d_akihiro on 2017/03/19.
 */
@Data
@NoArgsConstructor
public class UserForm implements Serializable {

    private static final long serialVersionUID = 5116313109849282605L;

    /**
     * ユーザID
     */
    private long id;

    /**
     * ユーザ名
     */
    @NotBlank
    @Size(min = 5, max=20)
    private String username;

    /**
     * パスワード
     */
    @Size(min = 5)
    private String password;

    /**
     * ユーザDtoで更新
     * @param userDto ユーザDto
     */
    public void update(UserDto userDto){
        id = userDto.getId();
        username = userDto.getUsername();
        password = userDto.getPassword();
    }

    /**
     * ユーザDto作成
     * @return
     */
    public UserDto createDto(){
        UserDtoAssembler userDtoAssembler = new UserDtoAssembler();
        return userDtoAssembler.createDto(0, username, password);
    }
}
