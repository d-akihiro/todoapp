package com.example.todoapp.interfaces.todo.web.controllers;

import com.example.todoapp.interfaces.security.facade.dto.UserDetailsDto;
import com.example.todoapp.interfaces.todo.facade.TodoServiceFacade;
import com.example.todoapp.interfaces.todo.facade.dto.ProjectDto;
import com.example.todoapp.interfaces.todo.web.controllers.form.ProjectForm;
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
import java.util.List;

/**
 * プロジェクト系画面コントローラ
 * Created by d_akihiro on 2017/02/19.
 */
@Controller
public class ProjectController {
    @Autowired
    private TodoServiceFacade todoServiceFacade;

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
     * プロジェクト一覧へのリダイレクト
     * @return
     */
    private String redirectProjectList(){
        return "redirect:/project";
    }

    /**
     * プロジェクト一覧
     * @param projectFormList プロジェクトフォームリスト
     * @param principal 認証情報
     * @return
     */
    @RequestMapping(value="/project", method= RequestMethod.GET)
    public String listView(@ModelAttribute ArrayList<ProjectForm> projectFormList,
                        Principal principal){
        UserDetailsDto userDetailsDto = getUserDetail(principal);

        List<ProjectDto> projectDtoList = todoServiceFacade.getProjectList(userDetailsDto.getId());

        projectFormList.clear();
        for(ProjectDto projectDto: projectDtoList){
            ProjectForm projectForm = new ProjectForm();
            projectForm.update(projectDto);
            projectFormList.add(projectForm);
        }
        return "project/list_view";
    }

    /**
     * プロジェクト作成画面
     * @param projectForm プロジェクトフォーム
     * @param principal 認証情報
     * @return
     */
    @RequestMapping(value = "/project/new", method = RequestMethod.GET)
    public String newEditView(@ModelAttribute ProjectForm projectForm,
                           Principal principal){
        UserDetailsDto userDetailsDto = getUserDetail(principal);

        projectForm.update(todoServiceFacade.defaultProject());
        return "project/new_edit_view";
    }

    /**
     * プロジェクト編集画面
     * @param projectId プロジェクトID
     * @param projectForm プロジェクトフォーム
     * @param principal 認証情報
     * @return
     */
    @RequestMapping(value = "/project/{projectId}/edit", method = RequestMethod.GET)
    public String editView(@PathVariable long projectId,
                            @ModelAttribute ProjectForm projectForm,
                            Principal principal){
        UserDetailsDto userDetailsDto = getUserDetail(principal);

        ProjectDto projectDto = todoServiceFacade.getProject(projectId);
        projectForm.update(projectDto);
        return "project/edit_view";
    }

    /**
     * プロジェクト作成
     * @param projectForm プロジェクトフォーム
     * @param principal 認証情報
     * @return プロジェクト一覧へリダイレクト
     */
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public String create(@ModelAttribute @Validated ProjectForm projectForm,
                         BindingResult bindingResult,
                         Principal principal){
        UserDetailsDto userDetailsDto = getUserDetail(principal);

        ProjectDto projectDto = null;
        if(!bindingResult.hasErrors()) {
            projectDto = projectForm.createDto();
            projectDto.setUserId(userDetailsDto.getId());
            projectDto = todoServiceFacade.createProject(projectDto);
        }
        if(projectDto == null){
            return "project/new_edit_view";
        }
        return redirectProjectList();
    }

    /**
     * プロジェクト更新
     * @param projectId プロジェクトID
     * @param projectForm プロジェクトフォーム
     * @param principal 認証情報
     * @return プロジェクト一覧へリダイレクト
     */
    @RequestMapping(value = "/project/{projectId}", method = RequestMethod.PUT)
    public String update(@PathVariable long projectId,
                         @ModelAttribute @Validated ProjectForm projectForm,
                         BindingResult bindingResult,
                         Principal principal){
        UserDetailsDto userDetailsDto = getUserDetail(principal);
        ProjectDto projectDto = null;
        if(!bindingResult.hasErrors()) {
            projectDto = projectForm.createDto();
            projectDto.setId(projectId);
            projectDto = todoServiceFacade.updateProject(projectDto);
        }
        if(projectDto == null){
            projectForm.setId(projectId);
            return "project/edit_view";
        }

        return redirectProjectList();
    }

    /**
     * プロジェクト削除
     * @param projectId プロジェクトID
     * @param principal 認証情報
     * @return プロジェクト一覧へリダイレクト
     */
    @RequestMapping(value = "/project/{projectId}", method = RequestMethod.DELETE)
    public String destroy(@PathVariable long projectId,
                          Principal principal){
        UserDetailsDto userDetailsDto = getUserDetail(principal);

        todoServiceFacade.deleteProject(projectId);
        return redirectProjectList();
    }
}
