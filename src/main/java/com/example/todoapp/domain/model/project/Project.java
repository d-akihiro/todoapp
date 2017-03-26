package com.example.todoapp.domain.model.project;

import com.example.todoapp.domain.model.task.Task;
import com.example.todoapp.domain.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * プロジェクト-エンティティ
 *
 * Created by d_akihiro on 2017/02/19.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "project_data")
public class Project implements Serializable {
    private static final long serialVersionUID = -8303522046310593939L;
    /**
     * プロジェクトID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * プロジェクト名
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
     * オーナーユーザID
     */
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    /**
     * プロジェクト所有者
     */
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    /**
     * 所属タスクリスト
     */
    @OneToMany(mappedBy = "project")
    private List<Task> taskList;

    public void setOwner(User value){
        this.owner = value;
        this.userId = value != null ? value.getId() : null;
    }

    /**
     * 比較処理
     * - プロジェクトIDが同一のプロジェクト-エンティティは同一のエンティティを示す
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
        Project project = (Project)object;
        return id.equals(project.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
