package br.com.pedromoreira.projectmanager.api.task;

import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
public class TaskDTO {
    private Long id;
    private String descricao;
    private Date dataDeInicio;
    private Date prazo;
    private Boolean status;

    public static TaskDTO create(Task p){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(p, TaskDTO.class);
    }
}
