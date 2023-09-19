package br.com.pedromoreira.projectmanager.api.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {
    @Autowired
    private ProjectService service;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> selectAll(){
        return ResponseEntity.ok(service.getProjects());
    }


    @GetMapping("{id}")
    public ResponseEntity<ProjectDTO> selectById(@PathVariable("id") Long id){
        ProjectDTO p = service.getProjectById(id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ProjectDTO>> selectByName(@PathVariable("nome") String nome){
        List<ProjectDTO> projects = service.getProjectsByNome(nome);
        return projects.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(projects);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Project project){
        ProjectDTO p = service.insert(project);
        URI location = getUri(p.getId());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<ProjectDTO> update(@PathVariable("id") Long id, @RequestBody Project project){
        project.setId(id);
        ProjectDTO p = service.update(project, id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        return service.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
