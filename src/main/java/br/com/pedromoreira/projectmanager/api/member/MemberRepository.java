package br.com.pedromoreira.projectmanager.api.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(value = "SELECT m FROM members m WHERE m.nome LIKE %?1%")
    List<Member> findByName(String nome);
}
