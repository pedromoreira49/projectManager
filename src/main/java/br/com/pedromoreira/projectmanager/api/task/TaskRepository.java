package br.com.pedromoreira.projectmanager.api.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT t FROM tasks t where t.descricao like ?1")
    List<Task> findByDescricao(String nome);
}
