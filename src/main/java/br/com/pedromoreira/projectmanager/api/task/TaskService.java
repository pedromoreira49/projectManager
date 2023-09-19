package br.com.pedromoreira.projectmanager.api.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository rep;

    public List<TaskDTO> getTasks() {
        return rep.findAll().stream().map(TaskDTO::create).collect(Collectors.toList());
    }

    public TaskDTO getTaskById(Long id){
        Optional<Task> task = rep.findById(id);
        return task.map(TaskDTO::create).orElse(null);
    }

    public TaskDTO insert(Task task){
        Assert.isNull(task.getId(), "Não foi possível inserir a Task");
        return TaskDTO.create(rep.save(task));
    }

    public TaskDTO update(Task task, Long id){
        Assert.notNull(id, "Não foi possível atualizar a Task");
        Optional<Task> optional = rep.findById(id);
        if(optional.isPresent()){
            Task db = optional.get();
            db.setDescricao(task.getDescricao());
            db.setDataDeInicio(task.getDataDeInicio());
            db.setPrazo(task.getPrazo());
            db.setStatus(task.getStatus());
            db.setValorTask(task.getValorTask());

            rep.save(db);
            return TaskDTO.create(db);
        }else{
            return null;
        }
    }

    public boolean delete(Long id){
        Optional<Task> optional = rep.findById(id);
        if(optional.isPresent()){
            rep.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
