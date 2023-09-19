package br.com.pedromoreira.projectmanager.api.member;

import br.com.pedromoreira.projectmanager.api.project.Project;
import br.com.pedromoreira.projectmanager.api.projectMember.ProjectMember;
import br.com.pedromoreira.projectmanager.api.task.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String nome;
    private String email;
    private String cargo;
    private Float valorHora;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = true)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id", nullable = true)
    private Task task;

    @OneToMany(mappedBy = "member")
    private List<ProjectMember> memberProject;
}
