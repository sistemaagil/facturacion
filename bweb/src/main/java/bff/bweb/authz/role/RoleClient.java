package bff.bweb.authz.role;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "bff.role", url = "http://localhost:8081/api/role")
public interface RoleClient {

    @GetMapping("/{id}/")
    RoleDTO findRoleById(@RequestHeader("Authorization") String authHeader, @PathVariable("id") Long id);

    @GetMapping("/")
    List<RoleDTO> findAll(@RequestHeader("Authorization") String authorizationHeader);

    @PostMapping("/") 
    RoleDTO save(@RequestHeader("Authorization") String authorizationHeader, RoleDTO entity);

    @DeleteMapping("/{id}/")
    void deleteById(@RequestHeader("Authorization") String authorizationHeader,@PathVariable("id")  Long id);

    @PutMapping("/{id}/")
    RoleDTO update(@RequestHeader("Authorization") String authorizationHeader, @PathVariable("id")  Long id, RoleDTO entity);

}