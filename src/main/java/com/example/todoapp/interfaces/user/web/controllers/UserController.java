package com.example.todoapp.interfaces.user.web.controllers;

import com.example.todoapp.interfaces.security.facade.dto.UserDetailsDto;
import com.example.todoapp.interfaces.user.web.controllers.form.UserForm;
import com.example.todoapp.interfaces.user.facade.UserServiceFacade;
import com.example.todoapp.interfaces.user.facade.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;

/**
 * ユーザ管理画面コントローラ
 * Created by d_akihiro on 2017/02/25.
 */
@Controller
public class UserController {
    @Autowired
    private UserServiceFacade userServiceFacade;

    /**
     * 認証情報から認証ユーザ取得
     * @param principal 認証情報
     * @return 認証ユーザ
     */
    private UserDetailsDto getUserDetail(Principal principal){
        Authentication authentication = (Authentication) principal;
        return (UserDetailsDto) authentication.getPrincipal();
    }


    /**
     * ユーザ一覧画面へのリダイレクト
     * @return
     */
    private String redirectUserList(){
        return "redirect:/user";
    }

    /**
     * ユーザ一覧画面
     * @param userFormList ユーザ一覧
     * @return
     */
    @RequestMapping(value="/user", method= RequestMethod.GET)
    public String listView(@ModelAttribute ArrayList<UserForm> userFormList){
        for(UserDto userDto : userServiceFacade.getUserList()){
            UserForm userForm = new UserForm();
            userForm.update(userDto);
            userFormList.add(userForm);
        }

        return "user/list_view";
    }

    /**
     * ユーザ新規作成画面
     * @param userForm デフォルトユーザ情報
     * @return
     */
    @RequestMapping(value = "/user/new", method = RequestMethod.GET)
    public String newEditView(@ModelAttribute UserForm userForm){
        userForm.update(userServiceFacade.defaultUser());
        return "user/new_edit_view";
    }

    /**
     * ユーザ変種画面
     * @param id ユーザID
     * @param userForm ユーザフォーム
     * @return
     */
    @RequestMapping(value = "/user/{id}/edit", method = RequestMethod.GET)
    public String editView(@PathVariable long id,
                       @ModelAttribute UserForm userForm){
        userForm.update(userServiceFacade.getUser(id));
        return "user/edit_view";
    }

    /**
     * ユーザ作成
     * - ユーザ新規作成画面で使用
     * - ユーザ一覧画面へ遷移
     * @param userForm ユーザ情報
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String create(@ModelAttribute @Validated UserForm userForm,
                         BindingResult bindingResult){
        UserDto userDto = null;
        if(!bindingResult.hasErrors()) {
            userDto = userForm.createDto();
            userDto = userServiceFacade.createUser(userDto);
        }
        if(userDto == null){
            return "user/new_edit_view";
        }
        return redirectUserList();
    }

    /**
     * ユーザ更新
     * - ユーザ編集画面で使用
     * @param id
     * @param userForm
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public String update(@PathVariable long id,
                         @ModelAttribute @Validated UserForm userForm,
                         BindingResult bindingResult){
        UserDto userDto = null;
        if(!bindingResult.hasErrors()) {
            userDto = userForm.createDto();
            userDto.setId(id);
            userServiceFacade.updateUser(userDto);
        }
        if(userDto == null){
            return "user/edit_view";
        }
        return redirectUserList();
    }

    /**
     * ユーザ削除
     * - ユーザ一覧画面で使用
     * - 初期ユーザとログイン中のユーザは削除禁止
     * @param id ユーザID
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String destroy(@PathVariable long id,
                          Principal principal){
        if(id != 0 && id != getUserDetail(principal).getId()) {
            userServiceFacade.deleteUser(id);
        }
        return redirectUserList();
    }
}
