package security.api_authz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.api_authz.entity.User;
import security.api_authz.entity.UserAuthority;
import security.api_authz.service.UserService;


@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('User_Read')")
    @GetMapping("/{id}/")
    public User findById(@PathVariable long id){
        return userService.findById(id);
    }

    @PreAuthorize("hasAuthority('User_Read')")
    @GetMapping("/")
    public List<User> findAll(){
        return userService.findAll();
    }

    @PreAuthorize("hasAuthority('User_Write')")
    @PostMapping("/")
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @PreAuthorize("hasAuthority('User_Write')")
    @PutMapping("/")
    public User update(@RequestBody User user){
        return userService.save(user);
    }

    @PreAuthorize("hasAuthority('User_Delete')")
    @DeleteMapping("/{id}/")
    public void deleteById(@PathVariable long id){
        userService.deleteById(id);
    }

    @GetMapping("/hasAuthority/")
    public ResponseEntity<String> hasAuthority(@RequestBody UserAuthority entity){

        if (entity==null || entity.getUsername() ==null || entity.getAuthority() == null )
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        if (userService.hasAuthority(entity.getUsername(), entity.getAuthority()))
            return ResponseEntity.ok("ok");
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}

