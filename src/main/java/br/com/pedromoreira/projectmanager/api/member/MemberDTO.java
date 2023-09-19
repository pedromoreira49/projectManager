package br.com.pedromoreira.projectmanager.api.member;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class MemberDTO {

    private Long id;
    private String nome;
    private String email;
    private String cargo;
    private Float valorHora;

    public static MemberDTO create(Member m){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(m, MemberDTO.class);
    }
}
