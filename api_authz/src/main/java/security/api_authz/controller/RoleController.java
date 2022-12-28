package security.api_authz.controller;

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
import security.api_authz.entity.Role;
import security.api_authz.service.RoleService;


@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PreAuthorize("hasAuthority('Role_Read')")
    @GetMapping("/{id}/")
    public Role findById(@PathVariable long id){
        return roleService.findById(id);
    }

    @PreAuthorize("hasAuthority('Role_Read')")
    @GetMapping("/")
    public List<Role> findAll(){
        return roleService.findAll();
    }

    @PreAuthorize("hasAuthority('Role_Write')")
    @PostMapping("/")
    public Role save(@RequestBody Role role){
        return roleService.save(role);
    }

    @PreAuthorize("hasAuthority('Role_Write')")
    @PutMapping("/{id}/")
    public Role update(@RequestBody Role role){
        return roleService.save(role);
    }

    @PreAuthorize("hasAuthority('Role_Delete')")
    @DeleteMapping("/{id}/")
    public void deleteById(@PathVariable long id){
        roleService.deleteById(id);
    }
}
