package com.example.todoapp.domain.model.task;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.example.todoapp.domain.model.project.Project;
import com.example.todoapp.domain.model.utils.LocalDateTimeToTimestampConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * タスク-エンティティ
 *
 * Created by d_akihiro on 2017/02/21.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "task_data")
public class Task implements Serializable{

    private static final long serialVersionUID = -2669770570767203842L;
    /**
     * タスクID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * タスク名
     */
    @Column(nullable = false)
    @NotBlank
    private String name;

    /**
     * 注釈
     */
    @Column
    private String description;

    /**
     * 所属プロジェクトID
     */
    @Column(name = "project_id", insertable = false, updatable = false)
    @NotNull
    private Long projectId;

    /**
     * 所属プロジェクト
     */
    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    public void setProject(Project value){
        project = value;
        projectId = value != null ? value.getId() : null;
    }

    /**
     * タスク期限
     */
    @Column
    @Convert(converter = LocalDateTimeToTimestampConverter.class)
    private LocalDateTime deadline;

    /**
     * タスク完了日時
     */
    @Column
    @Convert(converter = LocalDateTimeToTimestampConverter.class)
    private LocalDateTime finished;

    /**
     * タスク完了取り消し
     */
    public void updateUnfinished(){
        finished = null;
    }

    /**
     * タスク完了
     */
    public void updateFinished(){
        finished = LocalDateTime.now();
    }

    /**
     * タスク完了判定
     * @return
     */
    public boolean isFinished(){
        return finished != null;
    }

    /**
     * タスク期限切れ判定
     * @return
     */
    public boolean isExpired(){
        return deadline != null && deadline.isBefore(LocalDateTime.now());
    }

    /**
     * 比較処理
     * - タスクIDが同一のタスク-エンティティは同一のエンティティを示す
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object){
        if(this == object){
            return true;
        }
        if(object == null || getClass() != object.getClass()){
            return false;
        }
        Task task = (Task)object;
        return id.equals(task.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
