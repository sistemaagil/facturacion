package bff.bweb.authz;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "bff.authority", url = "http://localhost:8081/api/authority")
public interface AuthorityClient {

    @GetMapping("/{id}/")
    AuthorityDTO findAuthorityById(@RequestHeader("Authorization") String authHeader, @PathVariable("id") Long id);

    @GetMapping("/")
    List<AuthorityDTO> findAll(@RequestHeader("Authorization") String authorizationHeader);

    @PostMapping("/") 
    AuthorityDTO save(@RequestHeader("Authorization") String authorizationHeader, AuthorityDTO entity);

    @DeleteMapping("/{id}/")
    void deleteById(@RequestHeader("Authorization") String authorizationHeader,@PathVariable("id")  Long id);

    @PutMapping("/{id}/")
    AuthorityDTO update(@RequestHeader("Authorization") String authorizationHeader, @PathVariable("id")  Long id, AuthorityDTO entity);

}