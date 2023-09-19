package br.com.pedromoreira.projectmanager.api.defaultTask;

import br.com.pedromoreira.projectmanager.api.project.Project;
import br.com.pedromoreira.projectmanager.api.task.Task;

import javax.persistence.*;

@Entity(name = "project_task_default")
public class DefaultTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

}
