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
import security.api_authz.entity.Authority;
import security.api_authz.service.AuthorityService;


@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/authority")
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;

    @PreAuthorize("hasAuthority('Authority_Read')")
    @GetMapping("/{id}/")
    public Authority findById(@PathVariable long id){
        return authorityService.findById(id);
    }

    @PreAuthorize("hasAuthority('Authority_Read')")
    @GetMapping("/")
    public List<Authority> findAll(){
        return authorityService.findAll();
    }

    @PreAuthorize("hasAuthority('Authority_Write')")
    @PostMapping("/")
    public Authority save(@RequestBody Authority authority){
        return authorityService.save(authority);
    }

    @PreAuthorize("hasAuthority('Authority_Write')")
    @PutMapping("/")
    public Authority update(@RequestBody Authority authority){
        return authorityService.save(authority);
    }

    @PreAuthorize("hasAuthority('Authority_Delete')")
    @DeleteMapping("/{id}/")
    public void deleteById(@PathVariable long id){
        authorityService.deleteById(id);
    }
}
