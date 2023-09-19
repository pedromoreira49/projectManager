package br.com.pedromoreira.projectmanager.api.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value = "SELECT p FROM projects p WHERE p.nome LIKE %?1%")
    List<Project> findByName(String nome);
}
