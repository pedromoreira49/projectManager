package br.com.pedromoreira.projectmanager.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository rep;

    @Autowired
            private RoleRepository roleRep;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<UserDTO> getUsers() {
        return rep.findAll().stream().map(UserDTO::create).collect(Collectors.toList());
    }

    @Transactional
    public UserDTO insert(User user){
        Assert.isNull(user.getId(), "Não foi possível efetuar o login");
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        System.out.println(user);
        var role = roleRep.findById(user.getRoles().get(0).getId());
        user.getRoles().get(0).setNome(role.get().getNome());
        return UserDTO.create(rep.save(user));
    }
}
