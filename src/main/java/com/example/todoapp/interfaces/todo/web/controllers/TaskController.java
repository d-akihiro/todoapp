package com.example.todoapp.interfaces.todo.web.controllers;

import com.example.todoapp.interfaces.security.facade.dto.UserDetailsDto;
import com.example.todoapp.interfaces.todo.facade.TodoServiceFacade;
import com.example.todoapp.interfaces.todo.facade.dto.TaskDto;
import com.example.todoapp.interfaces.todo.web.controllers.form.TaskForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * タスク系画面コントローラ
 * Created by d_akihiro on 2017/02/19.
 */
@Controller
public class TaskController {
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
     * タスク一覧画面へのリダイレクト
     * @param projectId プロジェクトID
     * @return
     */
    private String redirectTaskList(long projectId){
        return "redirect:/project/" + projectId;
    }

    /**
     * タスク一覧画面
     * @param projectId プロジェクトID
     * @param newTaskForm 新規タスク
     * @param taskFormList タスク一覧
     * @param principal 認証情報
     * @return
     */
    @RequestMapping(value = "/project/{projectId}", method = RequestMethod.GET)
    public String listView(@PathVariable long projectId,
                       @ModelAttribute(name = "newTaskForm") TaskForm newTaskForm,
                       @ModelAttribute(name = "taskFormList") ArrayList<TaskForm> taskFormList,
                       Principal principal){
        UserDetailsDto userDetailsDto = getUserDetail(principal);

        TaskDto newTaskDto = todoServiceFacade.defaultTask();
        newTaskDto.setProjectId(projectId);
        newTaskForm.update(newTaskDto);

        List<TaskDto> taskDtoList = todoServiceFacade.getTaskList(projectId);
        taskFormList.clear();
        for(TaskDto taskDto : taskDtoList){
            TaskForm taskForm = new TaskForm();
            taskForm.update(taskDto);
            taskFormList.add(taskForm);
        }
        return "task/list_view";
    }

    /**
     * タスク編集画面
     * @param taskId タスクID
     * @param taskForm タスクフォーム
     * @param principal 認証情報
     * @return
     */
    @RequestMapping(value = "/project/{projectId}/task/{taskId}/edit", method = RequestMethod.GET)
    public String editView(@PathVariable long projectId,
                            @PathVariable long taskId,
                            @ModelAttribute TaskForm taskForm,
                            Principal principal){
        UserDetailsDto userDetailsDto = getUserDetail(principal);

        TaskDto taskDto = todoServiceFacade.getTask(taskId);
        taskForm.update(taskDto);

        return "task/edit_view";
    }


    /**
     * タスク作成
     * - タスク一覧画面から使用
     * @param projectId プロジェクトID
     * @param taskForm タスクフォーム
     * @param principal 認証情報
     * @return タスク一覧へリダイレクト
     */
    @RequestMapping(value = "/project/{projectId}/task", method = RequestMethod.POST)
    public String create(
            @PathVariable long projectId,
            @ModelAttribute @Validated TaskForm taskForm,
            BindingResult bindingResult,
                         Principal principal){
        UserDetailsDto userDetailsDto = getUserDetail(principal);

        if(!bindingResult.hasErrors()) {
            TaskDto taskDto = taskForm.createDto();
            taskDto.setProjectId(projectId);
            todoServiceFacade.createTask(taskDto);
        }
        return redirectTaskList(projectId);
    }


    /**
     * タスク更新
     * - タスク編集画面から使用
     * @param projectId プロジェクトID
     * @param taskId タスクID
     * @param taskFrom タスクフォーム
     * @param principal 認証情報
     * @return タスク一覧へリダイレクト
     */
    @RequestMapping(value = "/project/{projectId}/task/{taskId}", method = RequestMethod.PUT)
    public String update(@PathVariable long projectId,
                         @PathVariable long taskId,
                         @ModelAttribute @Validated TaskForm taskFrom,
                         BindingResult bindingResult,
                         Principal principal){
        UserDetailsDto userDetailsDto = getUserDetail(principal);

        TaskDto taskDto = null;
        if(!bindingResult.hasErrors()) {
            taskDto = taskFrom.createDto();
            taskDto.setProjectId(projectId);
            taskDto.setId(taskId);
            taskDto = todoServiceFacade.updateTask(taskDto);
        }
        if(taskDto == null){
            taskFrom.setProjectId(projectId);
            taskFrom.setId(taskId);
            return "task/edit_view";
        }
        return redirectTaskList(projectId);
    }

    /**
     * タスク削除
     * @param projectId
     * @param taskId
     * @param principal
     * @return
     */
    @RequestMapping(value = "/project/{projectId}/task/{taskId}", method = RequestMethod.DELETE)
    public String destroy(
            @PathVariable long projectId,
            @PathVariable long taskId,
            Principal principal){
        UserDetailsDto userDetailsDto = getUserDetail(principal);

        todoServiceFacade.deleteTask(taskId);
        return redirectTaskList(projectId);
    }

    /**
     * タスク完了状態変更
     * @param projectId
     * @param taskId
     * @param isFinished
     * @param principal
     */
    @RequestMapping(value = "/project/{projectId}/task/{taskId}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void changeTask(
            @PathVariable long projectId,
            @PathVariable long taskId,
            @RequestParam("isFinished") boolean isFinished,
            Principal principal){
        UserDetailsDto userDetailsDto = getUserDetail(principal);

        if(isFinished) {
            todoServiceFacade.updateTaskFinished(taskId);
        }
        else{
            todoServiceFacade.updateTaskUnfinished(taskId);
        }
    }

}
