package br.com.pedromoreira.projectmanager.api.task;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {
    @Autowired
    private TaskService service;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> selectAll(){
        return ResponseEntity.ok(service.getTasks());
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskDTO> selectById(@PathVariable("id") Long id){
        TaskDTO t = service.getTaskById(id);
        return t != null ? ResponseEntity.ok(t) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public  ResponseEntity<TaskDTO> insert(@RequestBody Task task){
        TaskDTO t = service.insert(task);
        URI location = getUri(t.getId());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<TaskDTO> update(@PathVariable("id") Long id, @RequestBody Task task){
        task.setId(id);
        TaskDTO t = service.update(task, id);
        return t != null ? ResponseEntity.ok(t) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        return service.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
