package br.com.pedromoreira.projectmanager.api.project;

import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
public class ProjectDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Date dataDeInicio;
    private Date dataDeConclusao;

    public static ProjectDTO create(Project p){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(p, ProjectDTO.class);
    }
}
