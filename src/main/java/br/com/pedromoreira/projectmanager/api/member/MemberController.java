package br.com.pedromoreira.projectmanager.api.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/members")
public class MemberController {
    @Autowired
    private MemberService service;

    @GetMapping
    public ResponseEntity<List<MemberDTO>> selectAll(){
        return ResponseEntity.ok(service.getMembers());
    }

    @GetMapping("{id}")
    public ResponseEntity<MemberDTO> selectById(@PathVariable("id") Long id){
        MemberDTO m = service.getMemberById(id);
        return m != null ? ResponseEntity.ok(m) : ResponseEntity.notFound().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<MemberDTO>> selectByName(@PathVariable("nome") String nome){
        List<MemberDTO> members = service.getMembersByNome(nome);
        return members.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(members);
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Member member){
        MemberDTO m = service.insert(member);
        URI location = getUri(m.getId());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<MemberDTO> update(@PathVariable("id") Long id, @RequestBody Member member){
        member.setId(id);
        MemberDTO m = service.update(member, id);
        return m != null ? ResponseEntity.ok(m) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        return service.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
