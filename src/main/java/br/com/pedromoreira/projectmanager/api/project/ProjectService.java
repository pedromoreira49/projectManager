package br.com.pedromoreira.projectmanager.api.project;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository rep;

    public List<ProjectDTO> getProjects(){
        return rep.findAll().stream().map(ProjectDTO::create).collect(Collectors.toList());
    }

    public ProjectDTO insert(Project project){
        Assert.isNull(project.getId(), "Não foi possível inserir o projeto");
        return ProjectDTO.create(rep.save(project));
    }
}
