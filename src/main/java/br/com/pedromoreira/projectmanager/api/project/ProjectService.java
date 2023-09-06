package br.com.pedromoreira.projectmanager.api.project;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository rep;

    public List<ProjectDTO> getProjects(){
        return rep.findAll().stream().map(ProjectDTO::create).collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(Long id){
        Optional<Project> project = rep.findById(id);
        return project.map(ProjectDTO::create).orElse(null);
    }

    public List<ProjectDTO> getProjectsByNome(String nome){
        return rep.findByName(nome).stream().map(ProjectDTO::create).collect(Collectors.toList());
    }

    public ProjectDTO insert(Project project){
        Assert.isNull(project.getId(), "Não foi possível inserir o projeto");
        return ProjectDTO.create(rep.save(project));
    }

    public ProjectDTO update(Project project, Long id){
        Assert.notNull(id, "Não foi possível atualizar o projeto");
        Optional<Project> optional = rep.findById(id);
        if(optional.isPresent()){
            Project db = optional.get();
            db.setNome(project.getNome());
            db.setDescricao(project.getDescricao());
            db.setDataDeInicio(project.getDataDeInicio());
            db.setDataDeConclusao(project.getDataDeConclusao());
            rep.save(db);
            return ProjectDTO.create(db);
        }else{
            return null;
        }
    }

    public boolean delete(Long id){
        Optional<Project> optional = rep.findById(id);
        if(optional.isPresent()){
            rep.deleteById(id);
            return true;
        }else{
            return false;
        }
    }


}
