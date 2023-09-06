package br.com.pedromoreira.projectmanager.api.project;

import br.com.pedromoreira.projectmanager.api.member.Member;
import br.com.pedromoreira.projectmanager.api.task.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String nome;
    private String descricao;
    private Date dataDeInicio;
    private Date dataDeConclusao;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    @OneToMany(mappedBy = "project")
    private List<Member> members;
}
