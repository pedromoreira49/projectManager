package br.com.pedromoreira.projectmanager.api.project;

import br.com.pedromoreira.projectmanager.api.member.Member;
import br.com.pedromoreira.projectmanager.api.member.MemberDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;

@Data
public class ProjectDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Date dataDeInicio;
    private Date dataDeConclusao;
    private List<MemberDTO> members;

    public static ProjectDTO create(Project p){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(p, ProjectDTO.class);
    }
}
