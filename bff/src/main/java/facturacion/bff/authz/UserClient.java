package facturacion.bff.authz;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user", url = "http://localhost:8081/api/user")
public interface UserClient {

    @GetMapping("/{id}")
    User findUserById(@PathVariable("id") String id);

    @GetMapping("/")
    List<User> findAll(@RequestHeader("Authorization") String authorizationHeader);
}
