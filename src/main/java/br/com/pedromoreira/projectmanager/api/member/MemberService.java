package br.com.pedromoreira.projectmanager.api.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {
    @Autowired
    private MemberRepository rep;

    public List<MemberDTO> getMembers() {
        return rep.findAll().stream().map(MemberDTO::create).collect(Collectors.toList());
    }

    public MemberDTO getMemberById(Long id){
        Optional<Member> member = rep.findById(id);
        return member.map(MemberDTO::create).orElse(null);
    }

    public List<MemberDTO> getMembersByNome(String nome){
        return rep.findByName(nome).stream().map(MemberDTO::create).collect(Collectors.toList());
    }

    public MemberDTO insert(Member member){
        Assert.isNull(member.getId(), "Não foi possível inserir o registro");
        return MemberDTO.create(rep.save(member));
    }

    public MemberDTO update(Member m, Long id){
        Assert.notNull(id, "Não foi possível atualizar o registro");

        Optional<Member> optional = rep.findById(id);

        if(optional.isPresent()){
            Member db = optional.get();
            db.setNome(m.getNome());
            db.setEmail(m.getEmail());
            db.setCargo(m.getCargo());

            rep.save(db);

            return MemberDTO.create(db);
        }else{
            return null;
        }
    }

    public boolean delete(Long id){
        Optional<Member> optional = rep.findById(id);
        if(optional.isPresent()){
            rep.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
