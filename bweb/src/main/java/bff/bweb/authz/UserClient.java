package bff.bweb.authz;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user", url = "http://localhost:8081/api/user")
public interface UserClient {

    @GetMapping("/{id}")
    User findUserById(@PathVariable("id") String id);

    @GetMapping("/")
    List<User> findAll(@RequestHeader("Authorization") String authorizationHeader);

    @GetMapping("/hasAuthority/")
    public ResponseEntity<String> hasAuthority(@RequestHeader("Authorization") String authorizationHeader,@RequestHeader("EndPoint") String endpoint);
    
}
