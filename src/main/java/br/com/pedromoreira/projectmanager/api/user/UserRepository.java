package br.com.pedromoreira.projectmanager.api.user;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);

}
