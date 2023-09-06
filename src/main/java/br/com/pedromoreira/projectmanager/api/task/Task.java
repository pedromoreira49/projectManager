package br.com.pedromoreira.projectmanager.api.task;

import br.com.pedromoreira.projectmanager.api.member.Member;
import br.com.pedromoreira.projectmanager.api.project.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String descricao;
    private Date dataDeInicio;
    private Date prazo;
    private Boolean status;

    @ManyToOne()
    private Project project;

    @OneToMany(mappedBy = "task")
    private List<Member> members;
}
