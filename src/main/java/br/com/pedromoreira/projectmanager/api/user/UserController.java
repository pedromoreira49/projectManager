package br.com.pedromoreira.projectmanager.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> get() {
        List<UserDTO> list = service.getUsers();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/info")
    public UserDTO userInfo(@AuthenticationPrincipal User user) {
        return UserDTO.create(user);
    }

    @PostMapping("/register")
    public UserDTO insert(@RequestBody User user){
        return service.insert(user);
    }
}
